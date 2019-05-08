package com.galvanize.jokesservice.entities;

import javax.persistence.AttributeConverter;

public class CategoryConverter implements AttributeConverter<JokeCategory, String> {
    @Override
    public String convertToDatabaseColumn(JokeCategory attribute) {
        switch (attribute){
            case TECHNOLOGY: return "TECHNOLOGY";
            case DADJOKES: return "DADJOKES";
            case MOMJOKES: return "MOMJOKES";
            case KIDJOKES:return "KIDJOKES";
            case KNOCKKNOCK: return "KNOCKKNOCK";
            default: throw new IllegalArgumentException(attribute+" is NOT a valid JokeCategory");
        }
    }

    @Override
    public JokeCategory convertToEntityAttribute(String dbData) {
        switch(dbData){
            case "TECHNOLOGY": return JokeCategory.TECHNOLOGY;
            case "DADJOKES": return JokeCategory.DADJOKES;
            case "MOMJOKES": return JokeCategory.MOMJOKES;
            case "KIDJOKES": return JokeCategory.KIDJOKES;
            case "KNOCKKNOCK": return JokeCategory.KNOCKKNOCK;
            default: throw new IllegalArgumentException(dbData+" is NOT a valid JokeCategory");
        }
    }
}
