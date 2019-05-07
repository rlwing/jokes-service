package com.galvanize.jokesservice.entities;

public class Category {
    String shortName;
    String displayName;

    public Category(String shortName, String displayName) {
        this.shortName = shortName;
        this.displayName = displayName;
    }

    public Category() { }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
}
