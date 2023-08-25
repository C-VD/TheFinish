package com.example.thefinish;

import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.TreeMap;

public class Model {
    static ObservableMap<Integer, Node> nodes;
    static ObservableMap<Integer, Edge> edges;
    static ObservableMap<Integer, PathToNode> paths;
    static ArrayList<Edge> unvisited;
    static PathToNode tmpVoid(){
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
        return calculateRoute(0,1);
    }
    static void addNode(int id, String name){
        Node node = new Node(id, name);
        nodes.put(id, node);
    }
    static void addEdge(int id, String name, int fromId, int toId){
        Edge edge = new Edge(id, name, nodes.get(Integer.valueOf(fromId)), nodes.get(Integer.valueOf(toId)));
        edges.put(id, edge);
    }

    static PathToNode calculateRoute(int fromId, int toId){
        paths = FXCollections.observableMap(new TreeMap<>());
        unvisited = new ArrayList<>();
        int nNodes = 0;
        for (Integer n :
                nodes.keySet()) {
            PathToNode path = new PathToNode(n);
            nNodes++;
        }
        for (int id :
                edges.keySet()) {
            unvisited.add(edges.get(id));
        }
        Edge[][] matrix = createMatrix(nNodes);
        PathToNode stub = new PathToNode(fromId);
        paths.put(fromId, stub);

        ArrayList<Node> neighbors = getNeighbors(matrix, fromId);
        System.out.println("------------TMP----------------");
        for (Edge e : paths.get(1).getPath()) {
            System.out.println("Name: " + e.getName() + " From: " + e.getFrom().getId() + " To: " + e.getTo().getId());
        }
        System.out.println("Cost: " + paths.get(1).getCost());

        PathToNode pathToNode = paths.get(toId);
        return pathToNode;
    }

    static Edge[][] createMatrix(int nNodes){
        Edge[][] matrix = new Edge[nNodes][nNodes];
        for (int edgeId :
                edges.keySet()) {
            Edge e = edges.get(edgeId);
            Edge oldEdge = matrix[e.getFrom().getId()][e.getTo().getId()];
            if (oldEdge != null) {
                if (oldEdge.getWeight() > e.getWeight()){
                    matrix[e.getFrom().getId()][e.getTo().getId()] = e;
                }
            }
            else
            {
                matrix[e.getFrom().getId()][e.getTo().getId()] = e;
            }
        }
        return matrix;
    }

    static ArrayList<Node> getNeighbors(Edge[][] matrix, int id){
        ArrayList<Node> neighbors = new ArrayList<>();
        for (int i = 0; i < matrix[id].length; i++) {
            Edge e = matrix[id][i];
            if (e == null) {
                continue;
            }
            System.out.println("ID: " + e.getId());
            neighbors.add(nodes.get(i));
            if(paths.containsKey(Integer.valueOf(i))) {
                PathToNode p = paths.get(Integer.valueOf(i));
                System.out.println("P: " + p.getCost() + " E: " + e.getWeight());
                if (p.getCost() > paths.get(id).getCost() + e.getWeight()) {
                    ArrayList<Edge> newPath = paths.get(id).getPath();
                    newPath.add(e);
                    p.setPath(newPath);
                }
            }
            else {
                PathToNode p = new PathToNode(Integer.valueOf(i));
                p.getPath().clear();
                p.getPath().add(e);
                paths.put(i, p);
            }
        }
        for (int i = 0; i < unvisited.size(); i++) {
            if (unvisited.get(i).getFrom().getId() == id){
                System.out.println("Checking " + unvisited.get(i).getName());
                System.out.println(" ID: " + id);

                getNeighbors(matrix, unvisited.get(i).getTo().getId());
                unvisited.remove(i);
            }
        }
        return neighbors;
    }
}
