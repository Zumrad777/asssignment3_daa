package edu.mst.mst.algo;

import java.util.HashMap;
import java.util.Map;

public class DSU {
    private final Map<String, String> parent = new HashMap<>();
    private final Map<String, Integer> rank = new HashMap<>();
    public long ops = 0;

    public DSU(Iterable<String> nodes) {
        for (String n : nodes) {
            parent.put(n, n);
            rank.put(n, 0);
        }
    }

    public String find(String x) {
        ops++;
        if (!parent.get(x).equals(x)) {
            parent.put(x, find(parent.get(x)));
        }
        return parent.get(x);
    }

    public boolean union(String a, String b) {
        String ra = find(a), rb = find(b);
        ops++;
        if (ra.equals(rb)) return false;

        int rka = rank.get(ra), rkb = rank.get(rb);
        if (rka < rkb) {
            parent.put(ra, rb);
        } else if (rka > rkb) {
            parent.put(rb, ra);
        } else {
            parent.put(rb, ra);
            rank.put(ra, rka + 1);
        }
        ops++;
        return true;
    }
}
