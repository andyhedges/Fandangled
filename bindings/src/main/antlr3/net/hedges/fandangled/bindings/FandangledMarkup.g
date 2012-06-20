grammar FandangledMarkup;

options {
   language=Java;  // Default
   output=AST;
   ASTLabelType=CommonTree;
}

@header {
    package net.hedges.fandangled.bindings;
}

@lexer::header {
	package net.hedges.fandangled.bindings;
}

document 
	:	LITERAL_DELIM (paragraph PARAGRAPH_BREAK)? paragraph LITERAL_DELIM;

paragraph :  (WORD SPACE?)+;

WORD 	:	 CHARACTER+;

fragment CHARACTER 
	:	 ('a'..'z' | 'A'..'Z' | '0'..'9' | '.');

LITERAL_DELIM : '\"';

PARAGRAPH_BREAK : '\n\n';

SPACE : (' ' | '\t' | '\r');
