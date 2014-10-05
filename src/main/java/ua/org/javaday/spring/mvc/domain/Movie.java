package ua.org.javaday.spring.mvc.domain;

public class Movie {
    private int id;
    private String title;
    private float rating;

    public Movie(int id, String title, float rating) {
        this.id = id;
        this.title = title;
        this.rating = rating;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
