package ua.org.javaday.spring.mvc.rest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import ua.org.javaday.spring.mvc.dao.MovieRepository;
import ua.org.javaday.spring.mvc.domain.Movie;
import ua.org.javaday.spring.mvc.rest.exception.MovieNotFoundException;

import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(MockitoJUnitRunner.class)
public class MovieControllerStandaloneIT {
    @Mock
    private MovieRepository movieRepository;

    private Movie movie = new Movie(1, "Futurama", 8.7f);

    @InjectMocks
    private MovieController movieController = new MovieController();

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = standaloneSetup(movieController)
                .alwaysDo(print())
                .build();
    }

    @Test
    public void itShouldFindMovie() throws Exception {
        when(movieRepository.findOne(1)).thenReturn(movie);

        mockMvc.perform(get("/movies/1")
                .accept(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.title").value("Futurama"))
                .andExpect(jsonPath("$.rating").value(8.7));
    }

    @Test
    public void itShouldNotFindMovie() throws Exception {
        given(movieRepository.findOne(-1)).willReturn(null);

        MvcResult mvcResult = mockMvc
                .perform(get("/movies/-1")
                        .accept(APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andReturn();

        assertThat(mvcResult.getResolvedException(),
                instanceOf(MovieNotFoundException.class));
    }

}
