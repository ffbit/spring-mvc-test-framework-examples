package ua.org.javaday.spring.mvc.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ua.org.javaday.spring.mvc.dao.MovieRepository;
import ua.org.javaday.spring.mvc.domain.Movie;
import ua.org.javaday.spring.mvc.rest.exception.MovieNotFoundException;

@Controller
@RequestMapping("/movies")
public class MovieController {
    @Autowired
    private MovieRepository movieRepository;

    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Movie getMovie(@PathVariable int id) {
        Movie movie = movieRepository.findById(id);

        if (movie != null) {
            return movie;
        }

        throw new MovieNotFoundException();
    }

}
