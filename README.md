# Quickstream REST API - Java Client Library 

The Quickstream REST API Java library provides integration across the Quickstream platform.

[![Build Status](https://travis-ci.org/seanobjames/quickstreamapi-java.svg?branch=master)](https://travis-ci.org/seanobjames/quickstreamapi-java) [![Codacy Badge](https://api.codacy.com/project/badge/Grade/f2b8d03bec4d482ca969c0e3f2354a93)](https://www.codacy.com/app/seanobjames/quickstreamapi-java?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=seanobjames/quickstreamapi-java&amp;utm_campaign=Badge_Grade) [![codecov](https://codecov.io/gh/seanobjames/quickstreamapi-java/branch/master/graph/badge.svg)](https://codecov.io/gh/seanobjames/quickstreamapi-java)

## Dependencies

* none

## Quick Start Example

```java
import com.qvalent.quickstreamapi.*;

public class QuickstreamAPIExample
{
    final QuickstreamAPI quickstreamAPI = new QuickstreamAPI(
        Environment.TEST, 
        "thePublishableKey",
        "theSecretKey" );
        
    final CardRequest cardRequest = new CardRequestBuilder( "theSupplierCode" )
            .cardNumber( "4242424242424242" ).expiryDateMonth( "01" ).expiryDateYear( "2050" ).build()
            
    final Result<SingleUseToken> result = quickstreamAPI.singleUseTokens().generate( cardRequest );
    
    System.out.print( "Single Use Token Id: " + result.getTarget().getSingleUseTokenId() );
}
```

## Documentation

* [Official documentation](https://quickstream.westpac.com.au/docs/quickstreamapi/v1/).

## Maven

With Maven installed, this package can be built by running this command:

`mvn package`

The resulting jar file will be produced in the directory named "target".

## Tests

The unit tests can be run by anyone on any system. The unit tests do not test your integration. 

## Open Source Attribution

A list of open source projects that help power QuickStream REST API - Java Client Library are:

* junit - [LICENSE](https://github.com/junit-team/junit5/blob/master/LICENSE.md)
* commons-lang3 - [Apace Licence v2](https://github.com/apache/commons-lang/blob/master/LICENSE.txt)
* jackson-core - [Apache Licence v2](http://www.apache.org/licenses/LICENSE-2.0)
* jackson-annotations - [Apache Licence v2](http://www.apache.org/licenses/LICENSE-2.0)
* jackson-databind - [Apache Licence v2](http://www.apache.org/licenses/LICENSE-2.0)

## Licence

See the LICENSE file.
