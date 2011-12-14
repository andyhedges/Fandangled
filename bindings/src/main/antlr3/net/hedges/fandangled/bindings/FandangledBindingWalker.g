tree grammar FandangledBindingWalker;

options {
   tokenVocab=Fandangled;
   ASTLabelType=CommonTree;
}

@header {
    package net.hedges.fandangled.bindings;
    
    import java.util.Map;
    import java.util.LinkedHashMap;

    import net.hedges.fandangled.bindings.domain.*;
    import net.hedges.fandangled.bindings.domain.Exception; //This is so that our Exception take priority over java.lang's version
}

@members {
	protected Interface _interface = new Interface();
}

serviceInterface returns [Interface serviceInterface]
	: ^(INTERFACE name=ID ^(EXPOSES serviceName=ID) preambleAnnotation+ structure*)
	
	{
		_interface.setName($name.text);
		_interface.setServiceName($serviceName.text);
		$serviceInterface = _interface;
	}
	;
	
preambleAnnotation : (    ownerAnnotation {_interface.setOwner($ownerAnnotation.owner);}
						| authorAnnotation {_interface.getAuthors().add($authorAnnotation.author);}
						| versionAnnotation {_interface.setVersion($versionAnnotation.version);}
						| descriptionAnnotation {_interface.setDescription($descriptionAnnotation.description);}
					  );
					  
ownerAnnotation	returns [Owner owner]
		: ^(OWNER name=STRING_LITERAL email=STRING_LITERAL)
							{
								$owner = new Owner();
								$owner.setName($name.text);
								$owner.setEmail($email.text);
							};
							
authorAnnotation returns [Author author]
		: ^(AUTHOR name=STRING_LITERAL email=STRING_LITERAL)
		{
			$author = new Author();
			$author.setName($name.text);
			$author.setEmail($email.text);
		};
		
versionAnnotation returns [Version version]
		: ^(VERS VERSION)
		{
			$version = new Version();
			$version.setValue($VERSION.text);
		};
		
descriptionAnnotation returns [String description]
		: ^(DESC STRING_LITERAL)
		{
			$description = $STRING_LITERAL.text;		
		};
		
parameterAnnotation [Map<String, Parameter> parameters] returns [Parameter parameter]
		: ^(PARAM ID STRING_LITERAL)
		{
			$parameter = parameters.get($ID.text) == null ? new Parameter() : parameters.get($ID.text);
			parameter.setName($ID.text);
			$parameter.setDescription($STRING_LITERAL.text);
		};
				
exceptionAnnotation returns [ThrownException thrownException]
		: ^(EXCEPT ID STRING_LITERAL)
		{
			$thrownException = new ThrownException();
			$thrownException.setDescription($STRING_LITERAL.text);
			$thrownException.setTypeName($ID.text);
		};
		
sinceAnnotation returns [Since since]
		: ^(SINCE VERSION)
		{
			$since = new Since();
			$since.setValue($VERSION.text);
		};
		
returnAnnotation [Return ret]
		: ^(RETURN STRING_LITERAL)
		{
			ret.setDescription($STRING_LITERAL.text);
		};
		
valueAnnotation returns [Value value]
		: ^(VALUE ID STRING_LITERAL?)
		{
			$value = new Value();
			$value.setName($ID.text);
			$value.setDescription($STRING_LITERAL.text);
		};
	 
structure : 
		(
		  operation {_interface.getOperations().add($operation.operation);}
		| event {_interface.getEvents().add($event.event);}
		| type {_interface.getTypes().add($type.type);}
		| exception {_interface.getExceptions().add($exception.exception);}
		| enumeration {_interface.getEnumerations().add($enumeration.enumeration);}
		);
		
		
operation returns [Operation operation]
	@init{
		$operation = new Operation();
		Map<String, Parameter> parameters = new LinkedHashMap<String, Parameter>();
		Return ret = new Return();
	}
	: ^(OPERATION ID typeName ASYNC?
					parameterList[parameters]?
					(descriptionAnnotation {$operation.setDescription($descriptionAnnotation.description);})*
					(sinceAnnotation {$operation.setSince($sinceAnnotation.since);})*
					(returnAnnotation[ret])*
					parameterAnnotation[parameters]*
					(exceptionAnnotation {$operation.getExceptions().add($exceptionAnnotation.thrownException);})*
		)
		{
		    $operation.setAsync($ASYNC.text != null);
			$operation.setName($ID.text);
			ret.setTypeInfo($typeName.typeInfo);
			$operation.setReturn(ret);
			for(String parameterName : parameters.keySet()){
				$operation.getParameters().add(parameters.get(parameterName));
			}
		};

event returns [Event event]
	@init{
		 $event = new Event();
	}
 	:^(EVENT typeDefinition[$event]);
	
type returns [Type type]
	@init{
		 $type = new Type();
	}
 	:^(TYPE typeDefinition[$type]);

exception returns [Exception exception]
	@init{
		$exception = new Exception();
	}
	:	^(EXCEPTION typeDefinition[$exception]);
	
typeDefinition [DataStructure ds]
	@init{
		Map<String, Parameter> parameters = new LinkedHashMap<String, Parameter>();
	}
	:	^(ID parameterList[parameters]?
			(descriptionAnnotation{$ds.setDescription($descriptionAnnotation.description);})*
			(sinceAnnotation{$ds.setSince($sinceAnnotation.since);})*
			parameterAnnotation[parameters]*){
		$ds.setName($ID.text);
		for(String parameterName : parameters.keySet()){
			$ds.getParameters().add(parameters.get(parameterName));
		}
	};
	
enumeration returns [Enumeration enumeration]
	@init {
		$enumeration = new Enumeration();
	}
	:	^(ENUMERATION ID 
			(descriptionAnnotation{$enumeration.setDescription($descriptionAnnotation.description);})*
			(valueAnnotation{$enumeration.getValues().add($valueAnnotation.value);})*
			(sinceAnnotation{$enumeration.setSince($sinceAnnotation.since);})*
		)
	{
		$enumeration.setName($ID.text);
	};
		
typeName returns [TypeInfo typeInfo]
 	:	( 	  ^(collection=LIST contained=typeName) {
 					CollectionInfo ci = new CollectionInfo();
 					ci.setTypeName("list");
 					ci.setContainedTypeInfo($contained.typeInfo);
 					ci.setName($text);
 					$typeInfo = ci;
 				}
			| ^(collection=SET contained=typeName) {
 					CollectionInfo ci = new CollectionInfo();
 					ci.setTypeName("set");
 					ci.setContainedTypeInfo($contained.typeInfo);
 					ci.setName($text);
 					$typeInfo = ci;			
			}
			| ^(collection=MAP name=typeName value=typeName){
 					MapInfo ci = new MapInfo();
 					ci.setTypeName("map");
 					ci.setContainedNameTypeInfo($name.typeInfo);
 					ci.setContainedValueTypeInfo($value.typeInfo);
 					ci.setName($text);
 					$typeInfo = ci;
			}
			| ID {
					$typeInfo = new TypeInfo();
					$typeInfo.setName($ID.text);
					$typeInfo.setTypeName($ID.text);	
				 }
				);

fragment parameter returns [Parameter parameter]
	: 	^(PARAMETER typeName ID MANDATORY?)
	{
		$parameter = new Parameter();
		$parameter.setTypeInfo($typeName.typeInfo);
		$parameter.setName($ID.text);
		$parameter.setMandatory($MANDATORY != null);
	};
	
fragment parameterList [Map<String, Parameter> inParameters] returns [Map<String, Parameter> parameters]
	@init {
		$parameters = $inParameters;
	}
	:	(parameter {
			Parameter p = $parameters.get($parameter.parameter.getName());
			if(p == null){
				p = $parameter.parameter;
			} else {
				p.setTypeInfo($parameter.parameter.getTypeInfo());
			}
			$parameters.put(p.getName(), p);
		})+;
	
	

	

	

