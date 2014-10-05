package ua.org.javaday.spring.mvc.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ua.org.javaday.spring.mvc.dao.MovieRepository;
import ua.org.javaday.spring.mvc.domain.Movie;
import ua.org.javaday.spring.mvc.rest.exception.MovieNotFoundException;

@RestController
@RequestMapping("/movies")
public class MovieController {
    @Autowired
    private MovieRepository movieRepository;

    @RequestMapping(value = "/{movieId}", method = RequestMethod.GET)
    public Movie getMovie(@PathVariable int movieId) {
        Movie movie = movieRepository.findById(movieId);

        if (movie != null) {
            return movie;
        }

        throw new MovieNotFoundException();
    }

}
