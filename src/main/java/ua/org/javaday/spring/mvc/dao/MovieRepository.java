package ua.org.javaday.spring.mvc.dao;

import ua.org.javaday.spring.mvc.domain.Movie;

public interface MovieRepository {

    Movie findById(int id);

}
