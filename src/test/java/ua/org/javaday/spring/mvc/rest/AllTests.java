package ua.org.javaday.spring.mvc.rest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
        ChannelControllerTest.class,
        ChannelControllerStandaloneIT.class,
        ChannelControllerWebAppIT.class
})
public class AllTests {
}
