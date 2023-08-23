package com.example.thefinish;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Edge {
    private SimpleIntegerProperty id;
    private SimpleStringProperty name;


    public Edge(int id, String name, Node from, Node to) {
        this.id = new SimpleIntegerProperty();
        this.name = new SimpleStringProperty();
        this.id.set(id);
        this.name.set(name);
        this.from = from;
        this.to = to;
    }

    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public Node getFrom() {
        return from;
    }

    public void setFrom(Node from) {
        this.from = from;
    }

    public Node getTo() {
        return to;
    }

    public void setTo(Node to) {
        this.to = to;
    }

    private Node from;
    private Node to;
}
