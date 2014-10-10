package ua.org.javaday.spring.mvc.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.org.javaday.spring.mvc.domain.Movie;

public interface MovieRepository extends JpaRepository<Movie, Integer> {
}
