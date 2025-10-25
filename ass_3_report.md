# Assignment 3: Optimization of a City Transportation Network (MST)

## Input Summary
Loaded 1 graph(s) from `ass_3_input.json`.

### Graph 1
- **Vertices:** 5, **Edges:** 7

**Prim's MST** — total cost: **11.0**, ops: **29**, time: **0,027 ms**

| From | To | Weight |
|---|---:|---:|
| A | B | 3.0 |
| B | C | 2.0 |
| C | D | 5.0 |
| D | E | 1.0 |

**Kruskal's MST** — total cost: **11.0**, ops: **27**, time: **0,020 ms**

| From | To | Weight |
|---|---:|---:|
| D | E | 1.0 |
| B | C | 2.0 |
| A | B | 3.0 |
| C | D | 5.0 |

**MST Cost Match:**  (identical total cost)

## Comparison & Analysis
- **Prim** good for dense graphs; **Kruskal** efficient for sparse.
- Operation counts show relative efficiency.

## References
- Cormen, Leiserson, Rivest, Stein. *Introduction to Algorithms*.
- Tarjan (1975). Union–Find efficiency.
