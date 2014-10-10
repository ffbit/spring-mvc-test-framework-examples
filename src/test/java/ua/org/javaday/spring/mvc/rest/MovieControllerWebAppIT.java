package ua.org.javaday.spring.mvc.rest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.context.WebApplicationContext;
import ua.org.javaday.spring.mvc.dao.MovieRepository;
import ua.org.javaday.spring.mvc.rest.exception.MovieNotFoundException;

import javax.transaction.Transactional;

import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.assertThat;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({
        "file:src/main/resources/servlet-context.xml",
        "file:src/main/resources/persistence-context.xml"
})
@Transactional
@Sql(scripts = "classpath:test-movie-seeds.sql")
public class MovieControllerWebAppIT {
    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private MovieRepository movieRepository;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = webAppContextSetup(wac)
                .alwaysDo(print())
                .build();
    }

    @Test
    public void itShouldFindMovie() throws Exception {
        mockMvc.perform(get("/movies/1").accept(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.title").value("Futurama"))
                .andExpect(jsonPath("$.rating").value(8.7));
    }

    @Test
    public void itShouldNotFindMovie() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/movies/-1").accept(APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andReturn();

        assertThat(mvcResult.getResolvedException(),
                instanceOf(MovieNotFoundException.class));
    }

}
