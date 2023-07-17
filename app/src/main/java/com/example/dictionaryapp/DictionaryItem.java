package com.example.dictionaryapp;

import java.util.List;

public class DictionaryItem {
    private String word;
    private String definition;
    private List<String> synonyms;
    private List<String> antonyms;

    public DictionaryItem(String word, String definition) {
        this.word = word;
        this.definition = definition;
    }

    public String getWord() {
        return word;
    }

    public String getDefinition() {
        return definition;
    }

    // Getters and setters for the other attributes (definition, synonyms, antonyms) if needed
    // ...
}







