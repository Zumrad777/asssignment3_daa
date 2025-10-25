package edu.mst.mst.algo;

import edu.mst.mst.model.Edge;

import java.util.*;
import java.util.stream.Collectors;

public class Kruskal {
    public static class Result {
        public List<Edge> edges;
        public double cost;
        public long ops;
        public double ms;
    }

    public static Result run(List<String> nodes, List<Edge> edges) {
        long opsScan = 0;

        List<Edge> sorted = edges.stream()
                .sorted(Comparator.comparingDouble(e -> e.weight))
                .collect(Collectors.toList());

        DSU dsu = new DSU(nodes);
        List<Edge> mst = new ArrayList<>();
        double total = 0.0;

        long t0 = System.nanoTime();
        for (Edge e : sorted) {
            opsScan++;
            if (dsu.union(e.from, e.to)) {
                mst.add(e);
                total += e.weight;
                if (mst.size() == nodes.size() - 1) break;
            }
        }
        double ms = (System.nanoTime() - t0) / 1_000_000.0;

        Result r = new Result();
        r.edges = mst;
        r.cost = total;
        r.ops = dsu.ops + opsScan;
        r.ms = ms;
        return r;
    }
}
