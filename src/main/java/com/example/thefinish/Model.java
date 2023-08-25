package com.example.thefinish;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;

import java.util.ArrayList;
import java.util.TreeMap;

public class Model {
    static ObservableMap<Integer, Node> nodes;
    static ObservableList<Node> nodesList;
    static ObservableList<Edge> edgesList;

    static ObservableMap<Integer, Edge> edges;
    static ObservableMap<Integer, PathToNode> paths;
    static ArrayList<Edge> unvisited;

    static PathToNode tmpVoid(int fromId, int toId) {
        //addEdge(0, "Way", 0,2);
        edges.get(0).setWeight(6);
        //addEdge(1, "Way2", 2,1);
        edges.get(1).setWeight(7);
        //addEdge(2, "Way3", 0,1);
        edges.get(2).setWeight(100);
        return calculateRoute(fromId, toId);
    }
    static void addNode(int id, String name){
        Node node = new Node(id, name);
        nodes.put(id, node);
    }
    static void addNode(String name){
        int id = nodes.size();
        Node node = new Node(id, name);
        nodes.put(id, node);
        nodesList.add(node);
    }
    static void addEdge(int id, String name, int fromId, int toId){
        Edge edge = new Edge(id, name, nodes.get(Integer.valueOf(fromId)), nodes.get(Integer.valueOf(toId)));
        edges.put(id, edge);
        edgesList.add(edge);
    }
    static void addEdge(String name, int fromId, int toId){
        int id = edges.keySet().size();
        Edge edge = new Edge(id, name, nodes.get(Integer.valueOf(fromId)), nodes.get(Integer.valueOf(toId)));
        edges.put(id, edge);
        edgesList.add(edge);
    }

    static PathToNode calculateRoute(int fromId, int toId){
        paths = FXCollections.observableMap(new TreeMap<>());
        unvisited = new ArrayList<>();
        int nNodes = nodes.keySet().size();

        for (int id :
                edges.keySet()) {
            unvisited.add(edges.get(id));
        }
        Edge[][] matrix = createMatrix(nNodes);
        PathToNode stub = new PathToNode(fromId);
        paths.put(fromId, stub);

        getNeighbors(matrix, fromId);
        System.out.println("------------TMP----------------");
        for (Edge e : paths.get(toId).getPath()) {
            System.out.println("Name: " + e.getName() + " From: " + e.getFrom().getId() + " To: " + e.getTo().getId());
        }
        System.out.println("Cost: " + paths.get(toId).getCost());

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
                    ArrayList<Edge> newPath = cloneList(paths.get(id).getPath());
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

    public static ArrayList<Edge> cloneList(ArrayList<Edge> list) {
        ArrayList<Edge> clone = new ArrayList<Edge>(list.size());
        for (Edge item : list) clone.add(item);
        return clone;
    }

    static void setup(){
        nodes = FXCollections.observableMap(new TreeMap<>());
        nodesList = FXCollections.observableList(new ArrayList<>());
        edges = FXCollections.observableMap(new TreeMap<>());
        edgesList = FXCollections.observableList(new ArrayList<>());
    }
}
