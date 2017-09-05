package com.example.torey.projectlogin.model;

import java.util.List;



public class HeroList extends GenericStatus {
    private List<Hero> elements;

    public void setElements(List<Hero> elements) {
        this.elements = elements;
    }

    public List<Hero> getElements() {
        return elements;
    }

}
