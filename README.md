# Assignment 4 – Graph Traversal and Representation System

## --. Project Overview

This project implements a **graph data structure** in Java and applies two traversal algorithms — BFS and DFS — to graphs of varying sizes. As a bonus, i implemented a **Dijkstra's Shortest Path** algorithm.

- **Vertex** — a single node with a unique integer id.
- **Edge** — a directed, weighted connection from one vertex to another.
- **BFS** — explores the graph level by level, visiting all neighbours before going deeper.
- **DFS** — explores the graph by going as deep as possible along each branch before backtracking.
- **Dijkstra** — finds the shortest (lowest-cost) path from one vertex to all others.

---

## --. Class Descriptions

### Vertex - represents a node in the graph. Stores a unique id and provides a getter and `toString()`.

### Edge - represents a directed, weighted connection. Stores `source`, `destination`, and `weight`. A second constructor defaults weight to 1 so unweighted usage still works.

### Graph - the core data structure. Uses an adjacency list (`Map<Vertex, List<Edge>>`) to store weighted edges. Each vertex maps to a list of edges leaving it — efficient for memory.

| Method | Description |
|---|---|
| `addVertex(Vertex v)` | Registers a vertex in the graph |
| `addEdge(int from, int to, int weight)` | Creates a directed weighted edge |
| `addEdge(int from, int to)` | Creates a directed edge with default weight 1 |
| `printGraph()` | Prints the full weighted adjacency list |
| `bfs(int startId)` | Performs BFS and prints traversal order |
| `bfs(int startId, boolean silent)` | Performs BFS silently (used during timing runs) |
| `dfs(int startId)` | Performs DFS and prints traversal order |
| `dfs(int startId, boolean silent)` | Performs DFS silently (used during timing runs) |
| `dijkstra(int startId)` | Finds shortest paths from the given vertex |

### Experiment 
Builds graphs of sizes 10, 30, and 100 vertices. Runs both traversals **5 times** each — printing traversal order only on the first run and running silently for the rest — then averages the timing results and prints a comparison table.

### Main - entry point. Demonstrates the small graph structure, traversal order, and Dijkstra results, then triggers the full performance experiment.

---

## --. Algorithm Descriptions

### Breadth-First Search

First, mark the start vertex as visited; add it to a queue. While the queue is not empty:
   - dequeue the front vertex and record it.
   - for each unvisited neighbour, mark it visited and enqueue it.

**Time complexity:** O(V + E) — every vertex and edge is processed exactly once.

---

### Depth-First Search

First, push the start vertex onto a stack; mark it visited. Then, while the stack is not empty:
   - pop the top vertex and record it.
   - for each unvisited neighbour, mark it visited and push it.

**Time complexity:** O(V + E) — every vertex and edge is processed exactly once.

---

### Dijkstra's Algorithm (Bonus)

**Step-by-step:**
1. Set distance to the start vertex = 0; all others = infinity.
2. Keep a `visited[]` boolean array, all false initially.
3. Repeat V times:
   - Pick the unvisited vertex `u` with the smallest known distance.
   - Mark `u` as visited (its distance is now finalised).
   - For every edge `u → neighbour` with weight `w`: if `dist[u] + w < dist[neighbour]`, update `dist[neighbour]` (this is called **relaxation**).
4. Print the shortest distance from start to every vertex.

**Time complexity:** O(V²) with arrays (our implementation). Can be improved to O((V + E) log V) with a priority queue.

---

## --. Experimental Results

Graphs were built with a chain structure. Each size was tested 5 times and the average execution time is reported. Traversal order is printed only on the first run; runs 2–5 are silent so output stays clean.

| Graph Size (V) | Avg BFS Time (ns) | Avg DFS Time (ns) |
|:-:|:-:|:-:|
| 10 | 35000 | 26999 |
| 30 | 82167 | 72299 |
| 100 | 200600 | 246833 |
| **Overall Average** | **105922** | **115377** |

### Observations

- Both algorithms scale with graph size, consistent with O(V + E).
- On the **30-vertex** graph, BFS and DFS are nearly equal, showing the difference is negligible at small-to-medium sizes.
- On the **100-vertex** graph, DFS becomes noticeably faster. This is because ArrayDeque stack operations have lower overhead than LinkedList queue operations in Java — not an algorithmic difference, both are still O(V + E).
- Averaging over 5 runs smooths out JVM fluctuations and gives a more reliable comparison than a single measurement.

---

## --. Screenshots





---

## F. Reflection

Implementing BFS and DFS made the difference between the two algorithms concrete in a way that reading alone cannot. BFS's queue guarantees that vertices are visited in order of distance from the source — it "fans out" evenly. DFS's stack means it commits to one path until it can go no further, then backtracks. This makes DFS naturally suited for problems that involve exhaustive path exploration (mazes, dependency resolution), while BFS shines when the goal is to find the shortest route in an unweighted graph.

The main implementation challenge was handling the visited set correctly for graphs with cycles. Without it, both algorithms would loop forever. Implementing Dijkstra's algorithm extended this further by introducing edge weights — the key insight being that BFS treats all edges as equal, while Dijkstra accounts for actual cost. The relaxation step was the most interesting part: repeatedly improving distance estimates until the optimal solution is locked in. Overall, working through these three algorithms from scratch gave a clear picture of how graph structure, traversal strategy, and edge weights each play a distinct role in algorithm design.

---

## --. Bonus – Dijkstra's algorithm

### What was modified

| File | Change |
|---|---|
| `Edge.java` | Added `weight` field, getter, updated constructor and `toString()` |
| `Graph.java` | Adjacency list updated to store `List<Edge>`; added weighted `addEdge`; added `dijkstra()` |
| `Main.java` | Added weighted edges to small graph; added Dijkstra demo section |



### Key design decisions
- The adjacency list was changed from `List<Vertex>` to `List<Edge>` so each connection carries its weight.
- A second `addEdge(from, to)` overload defaults weight to 1, keeping all BFS/DFS code unchanged.
- Dijkstra uses plain `int[]` arrays for distances and `boolean[]` for visited — no priority queue — as permitted by the bonus requirements.
