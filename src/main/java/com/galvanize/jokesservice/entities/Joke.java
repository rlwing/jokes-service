package com.galvanize.jokesservice.entities;

import javax.persistence.*;

@Entity
public class Joke {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "JOKE_ID", unique = true, nullable = false)
    private Long JokeId;

    @Column(name = "SOURCE")
    private String source;

    @Column(name = "CATEGORY")
    private String category;

    @Column(name = "JOKE", columnDefinition = "LONGTEXT")
    private String joke;

    public Joke() { }

    public Joke(String source, String category, String joke) {
        this.source = source;
        this.category = category;
        this.joke = joke;
    }

    public Joke(String source) {
        this.source = source;
    }

    public Long getJokeId() {
        return JokeId;
    }

    public void setJokeId(Long jokeId) {
        JokeId = jokeId;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getJoke() {
        return joke;
    }

    public void setJoke(String joke) {
        this.joke = joke;
    }
}
