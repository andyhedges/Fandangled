/*
   Copyright 2010 Andy Hedges <andy@hedges.net>

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
*/

tree grammar FandangledMarkupWalker;

options {
   tokenVocab=FandangledMarkup;
   ASTLabelType=CommonTree;
}

@header {
    package net.hedges.fandangled.bindings;

    import java.io.*;
    import java.util.Map;
    import java.util.LinkedHashMap;

    import net.hedges.fandangled.bindings.domain.document.*;
}

document returns [Document document]
	@init {
		$document = new Document();
	}
	:	^(PARAGRAPHS (paragraph{
            Paragraph para = new Paragraph();
            para.setText($paragraph.text);
            document.getParagraphs().add(para);
        })*);

paragraph
    :  ^(WORDS WORD+);