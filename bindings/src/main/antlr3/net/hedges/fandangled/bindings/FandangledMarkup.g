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
	:	( PARAGRAPH_BREAK? (paragraph PARAGRAPH_BREAK)* paragraph)? PARAGRAPH_BREAK? -> ^(PARAGRAPHS paragraph*);

paragraph :  SPACE* (WORD NEWLINE? SPACE*)+ -> ^(WORDS WORD+);

WORD 	:	  (CHARACTER | '\\"')*;

fragment CHARACTER : '!' | '\u0023'..'\u007F';

PARAGRAPH_BREAK : NEWLINE NEWLINE;

NEWLINE : '\r'? '\n';

SPACE : (' ' | '\t' | '\r');





