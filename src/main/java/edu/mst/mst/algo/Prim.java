package edu.mst.mst.algo;

import edu.mst.mst.model.Edge;

import java.util.*;

public class Prim {
    public static class Result {
        public List<Edge> edges;
        public double cost;
        public long ops;
        public double ms;
    }

    public static Result run(List<String> nodes, List<Edge> edges) {
        Result r = new Result();
        r.edges = new ArrayList<>();
        r.cost = 0.0;
        r.ops = 0;

        if (nodes.isEmpty()) { r.ms = 0; return r; }


        Map<String, List<Edge>> adj = new HashMap<>();
        for (String n : nodes) adj.put(n, new ArrayList<>());
        for (Edge e : edges) {
            adj.get(e.from).add(e);
            adj.get(e.to).add(new Edge(e.to, e.from, e.weight));
        }

        String start = nodes.get(0);
        Set<String> visited = new HashSet<>();
        visited.add(start);

        class Item {
            double w; String u; String v;
            Item(double w, String u, String v){this.w=w;this.u=u;this.v=v;}
        }
        PriorityQueue<Item> heap = new PriorityQueue<>(Comparator.comparingDouble(i -> i.w));
        for (Edge e : adj.get(start)) { heap.add(new Item(e.weight, start, e.to)); r.ops++; }

        long t0 = System.nanoTime();
        while (!heap.isEmpty() && visited.size() < nodes.size()) {
            Item it = heap.poll(); r.ops++;
            r.ops++;
            if (visited.contains(it.v)) continue;

            r.edges.add(new Edge(it.u, it.v, it.w));
            r.cost += it.w;
            visited.add(it.v);

            for (Edge ne : adj.get(it.v)) {
                r.ops++;
                if (!visited.contains(ne.to)) {
                    heap.add(new Item(ne.weight, it.v, ne.to)); r.ops++;
                }
            }
        }
        r.ms = (System.nanoTime() - t0) / 1_000_000.0;
        return r;
    }
}
