package com.galvanize.jokesservice.entities;

public enum JokeCategory {
    TECHNOLOGY("Technology"),
    DADJOKES("Dad Joke"),
    MOMJOKES("Mom Joke"),
    KIDJOKES("Kid Joke"),
    KNOCKKNOCK("Knock Knock");

    private String type;

    JokeCategory(String type) {
        this.type = type;
    }

    public String getType(){
        return this.type;
    }


}
