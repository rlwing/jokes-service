package com.galvanize.jokesservice.entities;

public enum JokeCategories {
    TECHNOLOGY("Technology"),
    DADJOKES("Dad Joke"),
    MOMJOKES("Mom Joke"),
    KIDJOKES("Kid Joke"),
    KNOCKKNOCK("Knock Knock");

    private String type;

    JokeCategories(String type) {
        this.type = type;
    }

    public String getType(){
        return this.type;
    }


}
