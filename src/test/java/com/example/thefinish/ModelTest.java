package com.example.thefinish;

import javafx.collections.FXCollections;

import java.util.ArrayList;
import java.util.TreeMap;

public class ModelTest {
    static void tmpVoid(){
        nodes = FXCollections.observableMap(new TreeMap<>());
        addNode(0, "foo");
        addNode(1, "bar");
        addNode(2, "foo-bar");
        edges = FXCollections.observableMap(new TreeMap<>());
        addEdge(0, "Way", 0,2);
        edges.get(0).setWeight(6);
        addEdge(1, "Way2", 2,1);
        edges.get(1).setWeight(7);
        addEdge(2, "Way3", 0,1);
        edges.get(2).setWeight(100);
        calculateRoute(0,1);
    }

}
