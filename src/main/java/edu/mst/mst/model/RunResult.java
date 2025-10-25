package edu.mst.mst.model;

import java.util.Map;

public class RunResult {
    public int graph_id;
    public Map<String, Integer> input_stats;
    public AlgorithmResult prim;
    public AlgorithmResult kruskal;

    public RunResult(int graph_id, Map<String, Integer> input_stats,
                     AlgorithmResult prim, AlgorithmResult kruskal) {
        this.graph_id = graph_id;
        this.input_stats = input_stats;
        this.prim = prim;
        this.kruskal = kruskal;
    }
}
