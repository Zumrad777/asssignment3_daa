

# Assignment 3 — City Transportation Network Optimization (MST)

## Overview

This project implements and benchmarks two classic graph algorithms — **Prim’s** and **Kruskal’s** — to find the **Minimum Spanning Tree (MST)** of a weighted undirected graph.
The scenario models a **city road network**, where each district is a vertex and each possible road is an edge with a construction cost (weight).

The goal:

> Connect all districts with the minimum total cost while keeping the network connected.

---

## Algorithms

### Prim’s Algorithm

* Builds the MST starting from an arbitrary vertex.
* Repeatedly adds the smallest edge that connects a visited vertex to an unvisited one.
* Implemented using a **priority queue** (min-heap) for efficiency.
* Best suited for **dense graphs**.

### Kruskal’s Algorithm

* Sorts all edges by weight.
* Adds each edge if it doesn’t form a cycle (using **Disjoint Set Union**, union–find).
* Efficient for **sparse graphs**.

Both algorithms measure:

* Total MST cost
* Number of vertices and edges
* Number of key operations (comparisons, unions, etc.)
* Execution time (ms)

---

## Architecture

| Package              | Description                                                                  |
| -------------------- | ---------------------------------------------------------------------------- |
| `edu.mst.mst.algo`   | Core algorithm implementations: `Prim`, `Kruskal`, `DSU`.                    |
| `edu.mst.mst.model`  | Data structures for graph storage (`Edge`, `GraphCase`, `GraphInput`, etc.). |
| `edu.mst.mst.io`     | Handles JSON parsing and report generation (`ReportWriter`).                 |
| `src/main/resources` | Contains the input JSON file (`ass_3_input.json`).                           |
| `Main.java`          | Entry point that runs both algorithms and compares results.                  |

---

## Input Format (`ass_3_input.json`)

```json
{
  "graphs": [
    {
      "id": 1,
      "nodes": ["A", "B", "C", "D", "E"],
      "edges": [
        {"from": "A", "to": "B", "weight": 3},
        {"from": "A", "to": "C", "weight": 4},
        {"from": "B", "to": "C", "weight": 2},
        {"from": "B", "to": "D", "weight": 6},
        {"from": "C", "to": "D", "weight": 5},
        {"from": "C", "to": "E", "weight": 7},
        {"from": "D", "to": "E", "weight": 1}
      ]
    }
  ]
}
```

---

## Output

After execution, two files are generated:

```
ass_3_output_generated.json  # Detailed MST results for each graph
ass_3_report.md              # Summary + comparison of Prim vs Kruskal
```

Example console output:

```
Graph 1 (|V|=5, |E|=7)
Graph 1: Prim=11.000 (ops=29, 0.027ms), Kruskal=11.000 (ops=27, 0.020ms), match=true
```

 Both algorithms produce identical MST costs (as expected).

---

## Performance Snapshot

| Algorithm | Total Cost | Operations | Time (ms) | Notes                               |
| --------- | ---------- | ---------- | --------- | ----------------------------------- |
| Prim      | 11.000     | 29         | 0.027     | Better for dense graphs             |
| Kruskal   | 11.000     | 27         | 0.020     | Simpler and faster on sparse graphs |

---

## Discussion

* Both implementations return the same MST total cost → correctness verified.
* **Prim** is more efficient for dense graphs due to heap-based vertex exploration.
* **Kruskal** is more efficient for sparse graphs because it avoids heap operations and only sorts edges once.
* For small input sizes, differences are negligible, but Kruskal tends to run slightly faster.

---

## How to Run

1. Open the project in **IntelliJ IDEA** (Maven enabled).
2. Make sure the file `ass_3_input.json` is inside `src/main/resources/`.
3. Run `Main.java`.

    


## Author

**Zumrad Sherbadalova**
*Design and Analysis of Algorithms — Assignment 3*


