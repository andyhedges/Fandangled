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

grammar Fandangled;

options {

   language=Java;  // Default
   output=AST;
   ASTLabelType=CommonTree;
}

tokens {
 OPERATION;
 EVENT;
 TYPE;
 EXCEPTION;
 ENUMERATION;
 RETURNS;
 PARAMETER;
 LIST;
 MAP;
 SET;
 TYPE;
 AUTHOR;
 OWNER;
 VERS;
 DESC;
 PARAM;
 ATTR;
 EXCEPT;
 SINCE;
 RETURN;
 VALUE;
 FANDANGLED;
 ORGANISATION;
 NAMESPACE_PREFIX;
 ORGANISATION_DOMAIN_NAME;
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



serviceInterface 
	: fandangledVersionAnnotation
	INTERFACE ID EXPOSES ID OPEN_CURLY
		 preambleAnnotation+
		 structure*
	 CLOSE_CURLY -> ^(INTERFACE ID ^(EXPOSES ID) preambleAnnotation+ structure* fandangledVersionAnnotation);

preambleAnnotation : (    ownerAnnotation
						| authorAnnotation
						| versionAnnotation
						| descriptionAnnotation
						| organisationAnnotation
						| namespacePrefixAnnotation
						| organisationDomainNameAnnotation
					  );

structure :
		(
		  operation 
		| event 
		| type
		| exception 
		| enumeration
		);

fandangledVersionAnnotation         : '@fandangled' VERSION SEMICOLON -> ^(FANDANGLED VERSION);
organisationAnnotation              : '@organisation' STRING_LITERAL SEMICOLON -> ^(ORGANISATION STRING_LITERAL);
namespacePrefixAnnotation           : '@namespacePrefix' STRING_LITERAL SEMICOLON -> ^(NAMESPACE_PREFIX STRING_LITERAL);
organisationDomainNameAnnotation    : '@organisationDomainName' STRING_LITERAL SEMICOLON -> ^(ORGANISATION_DOMAIN_NAME STRING_LITERAL);
ownerAnnotation		               	: '@owner' STRING_LITERAL COMMA STRING_LITERAL SEMICOLON -> ^(OWNER STRING_LITERAL STRING_LITERAL);
authorAnnotation        		    : '@author' STRING_LITERAL COMMA STRING_LITERAL SEMICOLON -> ^(AUTHOR STRING_LITERAL STRING_LITERAL);
versionAnnotation       	    	: '@version' VERSION SEMICOLON -> ^(VERS VERSION);
descriptionAnnotation              	: '@description' STRING_LITERAL SEMICOLON -> ^(DESC STRING_LITERAL);
parameterAnnotation     	    	: '@parameter' ID COMMA STRING_LITERAL SEMICOLON -> ^(PARAM ID STRING_LITERAL);
exceptionAnnotation	            	: '@exception' ID COMMA STRING_LITERAL SEMICOLON -> ^(EXCEPT ID STRING_LITERAL);
sinceAnnotation     		    	: '@since' VERSION SEMICOLON -> ^(SINCE VERSION);
returnAnnotation        	    	: '@return' STRING_LITERAL SEMICOLON -> ^(RETURN STRING_LITERAL);
valueAnnotation     		    	: '@value' ID (COMMA STRING_LITERAL)? SEMICOLON  -> ^(VALUE ID STRING_LITERAL?);


operation
	:	 ASYNC? typeName ID OPEN_BRACKET (parameterList? ) CLOSE_BRACKET (THROWS ID (COMMA ID)*)?
		OPEN_CURLY
			(descriptionAnnotation
			| sinceAnnotation
			| returnAnnotation
			| parameterAnnotation
			| exceptionAnnotation)*
		CLOSE_CURLY
	 -> ^(OPERATION ID typeName ASYNC? parameterList* descriptionAnnotation* sinceAnnotation* returnAnnotation* parameterAnnotation* exceptionAnnotation*);

event
	:	EVENT_STR typeDefinition -> ^(EVENT typeDefinition);

type 	:	TYPE_STR typeDefinition -> ^(TYPE typeDefinition);

exception
	:	EXCEPTION_STR typeDefinition -> ^(EXCEPTION typeDefinition);

typeDefinition
	:	ID OPEN_BRACKET parameterList? CLOSE_BRACKET OPEN_CURLY
		(descriptionAnnotation | sinceAnnotation | parameterAnnotation)*
	CLOSE_CURLY -> ^(ID parameterList? descriptionAnnotation* sinceAnnotation* parameterAnnotation*);

enumeration
	:	ENUMERATION_STR ID OPEN_BRACKET ID (COMMA ID)* CLOSE_BRACKET OPEN_CURLY
			(descriptionAnnotation | valueAnnotation | sinceAnnotation)*
		CLOSE_CURLY
		-> ^(ENUMERATION ID descriptionAnnotation* valueAnnotation* sinceAnnotation*);

typeName 	:	( 	OPEN_LIST typeName CLOSE_COLLECTION -> ^(LIST typeName) |
					OPEN_SET typeName CLOSE_COLLECTION -> ^(SET typeName) |
					OPEN_MAP typeName COMMA typeName CLOSE_COLLECTION -> ^(MAP typeName typeName)|
					ID -> ID);

fragment parameter
	: 	MANDATORY? typeName ID -> ^(PARAMETER typeName ID MANDATORY?);

fragment parameterList
	:	parameter (COMMA parameter)* -> parameter+;



INTERFACE : 'interface';

OPEN_CURLY : '{';
CLOSE_CURLY : '}';
OPEN_BRACKET : '(';
CLOSE_BRACKET : ')';
COMMA : ',';

OPEN_MAP : 'map<';
OPEN_SET : 'set<';
OPEN_LIST : 'list<';

CLOSE_COLLECTION : '>';

STRING_LITERAL
    :   '"' ('\\"' | ~('"'))* '"' {
		String text = StringUtils.normalise(getText());
    	setText(text);
    };

EVENT_STR : 'event';

TYPE_STR	: 'type';

EXCEPTION_STR : 'exception';

ENUMERATION_STR : 'enumeration';
    
MANDATORY
	:	'mandatory';
	
THROWS	:	'throws';
	
ASYNC 
	:	'async';
	
EXPOSES
	: 'exposes';
	
SEMICOLON : ';';

ID : ('a'..'z' | 'A'..'Z' | '_') ('a'..'z' | 'A'..'Z'| '0'..'9' | '_')* ;
	
 
VERSION	:	
	('0'..'9')+ (('.') ('0'..'9')+)*; //0.1.23.10.01

SL_COMMENT
 	:	'//' ~('\r'|'\n')* '\r'? '\n' {$channel=HIDDEN;}
	;

ML_COMMENT
	:	'/*' {$channel=HIDDEN;} .* '*/';

WS: (' '|'\n'|'\r'|'\t')+ {$channel=HIDDEN;} ; // ignore whitespace

	

