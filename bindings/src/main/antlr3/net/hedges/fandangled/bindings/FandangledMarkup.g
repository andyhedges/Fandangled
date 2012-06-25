grammar FandangledMarkup;

options {
   language=Java;  // Default
   output=AST;
   ASTLabelType=CommonTree;
}

tokens {
 WORDS;
 PARAGRAPHS;
}

@header {
    package net.hedges.fandangled.bindings;
}

@lexer::header {
	package net.hedges.fandangled.bindings;
}

document 
	:	LITERAL_DELIM ((paragraph PARAGRAPH_BREAK)? paragraph)? LITERAL_DELIM -> ^(PARAGRAPHS paragraph*);

paragraph :  SPACE* (WORD SPACE*)+ -> ^(WORDS WORD+);

WORD 	:	 CHARACTER+;

fragment CHARACTER 
	:	 ('a'..'z' | 'A'..'Z' | '0'..'9' | '.');

LITERAL_DELIM : '\"';

PARAGRAPH_BREAK : '\n\n';

SPACE : (' ' | '\t' | '\r' | '\n' ~'\n' | ~'\n' '\n');
