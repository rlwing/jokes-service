package com.galvanize.jokesservice.entities;

import javax.persistence.*;

@Entity
@Table(name = "JOKES")
public class Joke {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "JOKE_ID", unique = true, nullable = false)
    private Long jokeId;

    @Column(name = "SOURCE")
    private String source;

    @Column(name = "CATEGORY", columnDefinition = "TEXT")
    @Convert(converter = CategoryConverter.class)
    private JokeCategory category;

    @Column(name = "JOKE", columnDefinition = "LONGTEXT")
    private String joke;

    public Joke() { }

    public Joke(String source, JokeCategory category, String joke) {
        this.source = source;
        this.category = category;
        this.joke = joke;
    }

    public Joke(String source) {
        this.source = source;
    }

    public Long getJokeId() {
        return jokeId;
    }

    public void setJokeId(Long jokeId) {
        this.jokeId = jokeId;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public JokeCategory getCategory() {
        return category;
    }

    public void setCategory(JokeCategory category) {
        this.category = category;
    }

    public String getJoke() {
        return joke;
    }

    public void setJoke(String joke) {
        this.joke = joke;
    }
}
