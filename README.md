The purpose of the Fandangled framework of which the IDL plays a central role is to enable Service analysts to specify service interfaces without the need to detail the technical implementation details of that service. This way business owners can ensure that a service interface exposes the functionality and data they require without having to detail or indeed understand the technical detail of how the service is exposed.

Service implementors can then take that service interface defintion (an IDL file) and convert it to the technical implementation using either a provided codec or one that they have created themselves. Codecs in Fandangled are always one way and so perhaps they should be called encoders, notwithstanding, their purpose is to translate from IDL to a technical implementation.

At the time of writing there exists the following codecs:

* Microsoft Word Documents in Open Office XML format (usually refered to as docx)
* Web Services Description Language (WSDL sometimes pronounced wiz-dul)

Currently in planning or early development are codec for:

* Representational State Transfer (REST)
* Java Messaging Service (JMS)
* An efficient binary protocol
