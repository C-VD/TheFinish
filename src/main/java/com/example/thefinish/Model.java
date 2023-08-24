package com.example.thefinish;

import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;

import java.util.ArrayList;
import java.util.TreeMap;

public class Model {
    static ObservableMap<Integer, Node> nodes;
    static ObservableMap<Integer, Edge> edges;
    static void tmpVoid(){
        nodes = FXCollections.observableMap(new TreeMap<>());
        addNode(1, "foo");
        addNode(2, "bar");
        addNode(3, "foo-bar");
        edges = FXCollections.observableMap(new TreeMap<>());
        addEdge(1, "Way", 1,2);
        addEdge(2, "Way2", 2,3);
        System.out.println("---------------OUTPUT:--------------");
        Edge e = edges.get(1);
        System.out.println("Edge " + e.getId() + " from: " +e.getFrom().getName() + " to: " + e.getTo().getName());
        e = edges.get(2);
        System.out.println("Edge " + e.getId() + " from: " +e.getFrom().getName() + " to: " + e.getTo().getName());
    }
    static void addNode(int id, String name){
        Node node = new Node(id, name);
        nodes.put(id, node);
    }
    static void addEdge(int id, String name, int fromId, int toId){
        Edge edge = new Edge(id, name, nodes.get(Integer.valueOf(fromId)), nodes.get(Integer.valueOf(toId)));
        edges.put(id, edge);
    }

    ArrayList<Edge> calculateRoute(int fromId, int toId){
        ArrayList<Edge> unvisited = new ArrayList<>();
        return unvisited;
    }
}
