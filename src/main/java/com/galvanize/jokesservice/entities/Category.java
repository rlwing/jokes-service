package com.galvanize.jokesservice.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "categories")
public class Category {
    @Id
    @Column(name = "SHORT_NAME", unique = true, nullable = false)
    String shortName;
    @Column(name = "DISPLAY_NAME")
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

    public void doNothing(){
        //Dummy command to force rebuild.
    }
}
