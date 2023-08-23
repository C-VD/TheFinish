package com.example.thefinish;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class Model {
    static ObservableList<Node> nodeList;
    ObservableList<Edge> edgeList;
    static void tmpVoid(){
        nodeList = FXCollections.observableArrayList(new ArrayList<Node>())
        Node node1 = new Node(1, "foo");
        Node node2 = new Node(2, "bar");
        Edge e = new Edge(1, "route", node1, node2);
        System.out.println("OUTPUT:");
        System.out.println("Edge " + e.getId() + " from: " +e.getFrom().getName() + " to: " + e.getTo().getName());
    }
    Node addNode(int id, String name){
        Node node = new Node(id, name);
        nodeList.add(id, node);
    }
}
