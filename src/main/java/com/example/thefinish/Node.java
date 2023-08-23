package com.example.thefinish;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Node {
    private SimpleIntegerProperty id;

    public String getName() {
        return name.get();
    }

    public Node(int id, String name) {
        this.id = new SimpleIntegerProperty();
        this.name = new SimpleStringProperty();
        this.id.set(id);
        this.name.set(name);
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    private SimpleStringProperty name;

    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }
}