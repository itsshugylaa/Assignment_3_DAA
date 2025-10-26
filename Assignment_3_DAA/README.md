# **Assignment 3 – Minimum Spanning Tree (MST) Algorithms**

**Course:** Design and Analysis of Algorithms

**Student:** Shugyla Turganbek

**Group:** SE-2430

---

## **1. Objective**

The objective of this assignment is to implement and compare two classical algorithms used to find the **Minimum Spanning Tree (MST)** in a connected, undirected, weighted graph:

* **Prim’s Algorithm**
* **Kruskal’s Algorithm**

The main goals are:

* To understand the working principles of both algorithms.
* To compare their performance in terms of execution time and operation count.
* To verify correctness through identical MST costs for the same graphs.

---

## **2. Theoretical Background**

### **2.1. Minimum Spanning Tree (MST)**

For a connected, undirected, weighted graph ( G(V, E) ),
a **Minimum Spanning Tree** is a subset of edges that connects all vertices while minimizing the total weight and avoiding cycles.
It contains exactly ( V - 1 ) edges for ( V ) vertices.

---

### **2.2. Prim’s Algorithm**

**Idea:**
Prim’s algorithm starts from an arbitrary vertex and grows the MST by repeatedly selecting the smallest-weight edge that connects a visited vertex to an unvisited vertex.

**code:**

```
1. Choose any starting vertex.
2. While there are unvisited vertices:
     a. Select the edge with minimum weight that connects
        a visited vertex to an unvisited vertex.
     b. Add this edge to the MST.
3. Repeat until all vertices are included.
```

**Time Complexity:**

* Using adjacency matrix: ( O(V^2) )
* Using priority queue (min-heap): ( O(E \log V) )

**Space Complexity:** ( O(V + E) )

---

### **2.3. Kruskal’s Algorithm**

**Idea:**
Kruskal’s algorithm treats the graph as a forest and repeatedly adds the smallest edge that connects two different trees.
It uses the **Disjoint Set Union (DSU)** structure to detect cycles efficiently.

**code:**

```
1. Sort all edges in ascending order of weight.
2. Initialize DSU for all vertices.
3. For each edge (u, v) in sorted list:
      if find(u) ≠ find(v):
           union(u, v)
           add (u, v) to MST
4. Stop when MST contains (V - 1) edges.
```

**Time Complexity:** ( O(E \log E) )
**Space Complexity:** ( O(V) )

---

## **3. Implementation Details**
### **Project Structure**

```
Assignment_3_DAA/
│
├─ pom.xml
├─ README.md
├─ results/
│   └─ assign_3_output.json
│
├─ src/
│   ├─ main/
│   │   ├─ java/
│   │   │       ├─ Graph.java
│   │   │       ├─ Edge.java
│   │   │       ├─ DSU.java
│   │   │       ├─ PrimMST.java
│   │   │       ├─ KruskalMST.java
│   │   │       ├─ Result.java
│   │   │       ├─ DatasetGenerator.java
│   │   │       └─ Main.java
│   │   └─ resources/
│   │           └─ assign_3_input.json
│   └─ test/
│       └─ java/
│           ├─ MSTCorrectnessTest.java
│           └─ MSTPerformanceTest.java
```

### **Main Components**

| Class             | Description                                                    |
| ----------------- | -------------------------------------------------------------- |
| `Graph.java`      | Defines graph data structure and edges.                        |
| `Edge.java`       | Represents a single edge with source, destination, and weight. |
| `DSU.java`        | Implements Disjoint Set Union for Kruskal’s algorithm.         |
| `PrimMST.java`    | Implements Prim’s algorithm.                                   |
| `KruskalMST.java` | Implements Kruskal’s algorithm.                                |
| `Result.java`     | Stores output results (MST edges, total cost, etc.).           |
| `Main.java`       | Reads JSON input, executes algorithms, and writes output.      |

---

## **4. Input Dataset**

All input graphs are stored in **assign_3_input.json**.
Each dataset defines the graph type, vertices, and edges.

**Example (small dataset):**

```json
{
  "id": 1,
  "type": "small",
  "nodes": ["A", "B", "C", "D"],
  "edges": [
    {"from": "A", "to": "B", "weight": 3},
    {"from": "A", "to": "C", "weight": 1},
    {"from": "B", "to": "C", "weight": 7},
    {"from": "B", "to": "D", "weight": 5},
    {"from": "C", "to": "D", "weight": 2}
  ]
}
```

---

## **5. Experimental Results**

After executing the project in IntelliJ IDEA, the results were automatically written to
`results/assign_3_output.json`.

| Graph ID |  Type  | Vertices | Edges | Prim MST Cost | Kruskal MST Cost | Prim Time (ms) | Kruskal Time (ms) |
| -------- | :----: | :------: | :---: | :-----------: | :--------------: | :------------: | :---------------: |
| 1        |  Small |     4    |   5   |       6       |         6        |      0.33      |        1.31       |
| 2        | Medium |    10    |   13  |       48      |        48        |      0.03      |        0.03       |
| 3        |  Large |    20    |   22  |      117      |        117       |      0.04      |        0.05       |

Both algorithms returned the same MST cost for all datasets, confirming correctness.

---

## **6. Analysis and Discussion**

| Criterion                   | Prim’s Algorithm                         | Kruskal’s Algorithm                     |
| --------------------------- | ---------------------------------------- | --------------------------------------- |
| **Approach**                | Expands one tree by adding minimal edges | Builds a forest and connects components |
| **Data Structure**          | Priority Queue (Min-Heap)                | Disjoint Set Union (DSU)                |
| **Time Complexity**         | ( O(E \log V) )                          | ( O(E \log E) )                         |
| **Best for**                | Dense graphs                             | Sparse graphs                           |
| **Implementation**          | Slightly more complex                    | Easier to code                          |
| **Performance Observation** | Faster on dense graphs                   | Simpler on sparse graphs                |

**Observations:**

* Both algorithms achieved identical MST costs.
* Execution times are consistent with theoretical predictions.
* Prim’s algorithm showed slightly better performance for larger graphs.
* Kruskal’s algorithm is simpler to implement and efficient for sparse datasets.

---

## **7. Conclusion**

In this assignment, two fundamental MST algorithms — Prim’s and Kruskal’s — were successfully implemented and analyzed.
The results confirm that both algorithms are correct, efficient, and applicable for different types of graphs.

**Key conclusions:**

* MST results are identical for both algorithms.
* Execution time differences are minimal, depending on graph density.
* Theoretical and experimental results are consistent.
* The project meets all academic and technical requirements, including JSON input/output, Maven structure, and performance comparison.

Thus, the objective of the assignment has been fully achieved.

