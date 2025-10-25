

# **Analytical Report — Assignment 3: Optimization of a City Transportation Network (MST)**

## **1. Overview**

The purpose of this work was to apply **Prim’s** and **Kruskal’s** algorithms to find the **Minimum Spanning Tree (MST)** of a weighted undirected graph that models a city’s transportation network.
The main goal: connect all districts with the **minimum total construction cost**.

Both algorithms were implemented in Java.
Input data were read from a JSON file (`ass_3_input.json`), and results were written to `ass_3_output_generated.json` and `ass_3_report.md`.

---

## **2. Experimental Results**

### **2.1 Input Graph**

* **Number of vertices (districts):** 5
* **Number of edges (potential roads):** 7
* Edge weights represent road construction costs.

Example edges:

```
A–B (3), A–C (4), B–C (2), B–D (6), C–D (5), C–E (7), D–E (1)
```

---

### **2.2 Results Summary**

| Algorithm   | Total Cost | Operations | Execution Time (ms) | Notes                      |
| ----------- | ---------- | ---------- | ------------------- | -------------------------- |
| **Prim**    | 11.000     | 29         | 0.027               | Uses heap + adjacency list |
| **Kruskal** | 11.000     | 27         | 0.020               | Uses sorting + DSU         |

 Both algorithms produced **identical MST total cost**, confirming correctness.

---

### **2.3 MST Example (Prim’s output)**

| From | To | Weight |
| ---- | -- | ------ |
| D    | E  | 1      |
| B    | C  | 2      |
| A    | B  | 3      |
| C    | D  | 5      |

**Total Cost:** 11.0

---

## **3. Interpretation of Results**

* Both algorithms constructed valid MSTs with the same total weight (11), which confirms the theoretical property that MST is **unique in terms of total cost**, even if its structure differs slightly.
* The **number of operations** was close: Prim (29) vs Kruskal (27).
  The small difference comes from Prim’s repeated heap updates vs Kruskal’s union–find operations.
* **Execution time** also correlates with theoretical complexity:

  * Prim’s: `O(E log V)` due to heap operations.
  * Kruskal’s: `O(E log E)` from sorting edges.
* On this small dataset, Kruskal appeared slightly faster because the overhead of heap management in Prim dominates when the graph is small.

---

## **4. Comparative Analysis**

| Aspect                      | **Prim’s Algorithm**                              | **Kruskal’s Algorithm**                     |
| --------------------------- | ------------------------------------------------- | ------------------------------------------- |
| **Approach**                | Grows tree from one vertex using a priority queue | Sorts edges and builds MST using union–find |
| **Data Structure**          | Min-heap + adjacency list                         | Disjoint Set Union (DSU)                    |
| **Complexity**              | `O(E log V)`                                      | `O(E log E)`                                |
| **Best For**                | Dense graphs                                      | Sparse graphs                               |
| **Implementation**          | More code logic (heap + visited)                  | Simpler to implement                        |
| **Edge Representation**     | Works best with adjacency list                    | Works best with edge list                   |
| **Performance (this case)** | 29 ops, 0.027 ms                                  | 27 ops, 0.020 ms                            |

**Observation:**
Kruskal slightly outperformed Prim in operation count and runtime due to the small and moderately sparse graph, aligning with theoretical expectations.

---

## **5. Conclusions**

* Both algorithms successfully identified the same MST with a total cost of **11.0**.
* The **efficiency difference** is minimal on small inputs, but:

  * **Prim’s** scales better on **dense** graphs (many edges).
  * **Kruskal’s** scales better on **sparse** graphs (fewer edges).
* The measured performance confirms textbook time complexities:

  * `Prim → O(E log V)`
  * `Kruskal → O(E log E)`
* In real-world city planning, **Prim’s algorithm** would be preferable when each district connects to many others (dense infrastructure), while **Kruskal’s algorithm** suits networks where only a few possible roads exist between areas.

---




**Zumrad Sherbadalova**
Course: *Design and Analysis of Algorithms*


---



