package edu.mst.mst.model;

public class Edge {
    public String from;
    public String to;
    public double weight;

    public Edge() {}
    public Edge(String from, String to, double weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }
}
