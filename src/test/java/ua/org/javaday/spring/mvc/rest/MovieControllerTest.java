package ua.org.javaday.spring.mvc.rest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import ua.org.javaday.spring.mvc.dao.MovieRepository;
import ua.org.javaday.spring.mvc.domain.Movie;
import ua.org.javaday.spring.mvc.rest.exception.MovieNotFoundException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class MovieControllerTest {
    @Mock
    private MovieRepository movieRepository;

    @Mock
    private Movie movie;

    @InjectMocks
    private MovieController movieController = new MovieController();

    @Test
    public void itShouldFindMovie() {
        given(movieRepository.findById(1)).willReturn(movie);

        assertThat(movieController.getMovie(1), is(movie));
    }

    @Test(expected = MovieNotFoundException.class)
    public void itShouldNotFoundMovie() {
        given(movieRepository.findById(-1)).willReturn(null);

        movieController.getMovie(-1);
    }

}
