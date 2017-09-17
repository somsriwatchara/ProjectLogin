package com.example.torey.projectlogin.model;

import java.util.List;


public class UserDetailList extends GenericStatus{
    private List<UserDetail> elements;
    public List<UserDetail> getElements() {
        return elements;
    }

    public void setElements(List<UserDetail> elements) {
        this.elements = elements;
    }
}
