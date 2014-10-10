package ua.org.javaday.spring.mvc.domain;

import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "MOVIES")
public class Movie extends AbstractPersistable<Integer> {
    private String title;
    private float rating;

    private Movie() {
    }

    public Movie(int id, String title, float rating) {
        setId(id);
        this.title = title;
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

}
