grammar FandangledMarkup;


options {

   language=Java;  // Default
   output=AST;
   ASTLabelType=CommonTree;
}

// What package should the generated source exist in?
//
@header {
    package net.hedges.fandangled.bindings;
}

@lexer::header {
	package net.hedges.fandangled.bindings;
	import net.hedges.fandangled.bindings.builder.StringUtils;
	import java.io.*;
}

document :  LITERAL_DELIM (WORD SPACE?)* LITERAL_DELIM;

WORD 	:	 (~(SPACE|LITERAL_DELIM))*;

LITERAL_DELIM : '"';

SPACE : (' ' | '\t' | '\r' | '\n');