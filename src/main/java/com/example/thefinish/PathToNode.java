package com.example.thefinish;

import java.util.ArrayList;

public class PathToNode {
    public PathToNode(int destinationNode) {
        this.destinationNode = destinationNode;
        path = new ArrayList<>();
    }

    final int destinationNode;
    ArrayList<Edge> path;

    public ArrayList<Edge> getPath() {
        return path;
    }

    public void setPath(ArrayList<Edge> path) {
        this.path = path;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    private int cost;
}
