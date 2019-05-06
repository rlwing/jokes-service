package com.galvanize.jokesservice.entities;

public enum JokeCategories {
    TECHNOLOGY("Technology"),
    DADJOKES("Dad Jokes"),
    MOMJOKES("Mom Jokes"),
    KIDJOKES("Kid Jokes"),
    KNOCKKNOCK("Knock Knock");

    private String type;

    JokeCategories(String type) {
        this.type = type;
    }

    public String getType(){
        return this.type;
    }


}
