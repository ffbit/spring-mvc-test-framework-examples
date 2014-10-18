
## Slides
[Testing Web Apps with Spring Framework](https://www.slideshare.net/dmytro-chyzhykov/testing-web-apps-with-spring-framework)

[![Testing Web Apps with Spring Framework Slides](http://image.slidesharecdn.com/testingwebappswithspringframework-141014145311-conversion-gate01/95/testing-web-apps-with-spring-framework-1-638.jpg)](http://www.slideshare.net/dmytro-chyzhykov/testing-web-apps-with-spring-framework)


## Code Examples

### Domain
[Channel](https://github.com/ffbit/spring-mvc-test-framework-examples/blob/master/src/main/java/ua/org/javaday/spring/mvc/domain/Channel.java)

### DAO
[ChannelRepository](https://github.com/ffbit/spring-mvc-test-framework-examples/blob/master/src/main/java/ua/org/javaday/spring/mvc/dao/ChannelRepository.java)

### Subject Under Test
[ChannelController](https://github.com/ffbit/spring-mvc-test-framework-examples/blob/master/src/main/java/ua/org/javaday/spring/mvc/rest/ChannelController.java)

[ChannelNotFoundException](https://github.com/ffbit/spring-mvc-test-framework-examples/blob/master/src/main/java/ua/org/javaday/spring/mvc/rest/exception/ChannelNotFoundException.java)


### Test Suites

Pure Unit Test [ChannelControllerTest](https://github.com/ffbit/spring-mvc-test-framework-examples/blob/master/src/test/java/ua/org/javaday/spring/mvc/rest/ChannelControllerTest.java)

Standalone Controller Integration Test [ChannelControllerStandaloneIT](https://github.com/ffbit/spring-mvc-test-framework-examples/blob/master/src/test/java/ua/org/javaday/spring/mvc/rest/ChannelControllerStandaloneIT.java) 

Web App Context Integration Test [ChannelControllerWebAppIT](https://github.com/ffbit/spring-mvc-test-framework-examples/blob/master/src/test/java/ua/org/javaday/spring/mvc/rest/ChannelControllerWebAppIT.java)


## How to run tests

### Gradle

    ./gradlew test

### Apache Maven

    mvn integration-test
