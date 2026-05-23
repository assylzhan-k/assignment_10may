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

<img width="1470" height="956" alt="Screenshot 2026-05-23 at 12 28 59" src="https://github.com/user-attachments/assets/af90686c-003c-4aa3-935e-914e21d9e042" />
<img width="1470" height="956" alt="Screenshot 2026-05-23 at 12 29 02" src="https://github.com/user-attachments/assets/e1967e8f-0aa3-4a8a-b14a-eba550ddc068" />
<img width="1470" height="956" alt="Screenshot 2026-05-23 at 12 29 05" src="https://github.com/user-attachments/assets/a68e7af9-bfc2-46df-b6ae-f0d815ceb64c" />
<img width="1470" height="956" alt="Screenshot 2026-05-23 at 12 30 37" src="https://github.com/user-attachments/assets/9dd3bf51-bb23-4108-a061-6eb7e3b32196" />
<img width="1470" height="956" alt="Screenshot 2026-05-23 at 12 30 45" src="https://github.com/user-attachments/assets/d18b32b1-ba85-44a9-b9b7-984423da5795" />
<img width="1470" height="956" alt="Screenshot 2026-05-23 at 12 30 51" src="https://github.com/user-attachments/assets/9ae67ae7-bd7a-4d44-a9ea-9805e1af7aa5" />
<img width="1470" height="956" alt="Screenshot 2026-05-23 at 12 30 56" src="https://github.com/user-attachments/assets/e2ed5436-8597-4f44-9435-762a3196a619" />
<img width="1470" height="956" alt="Screenshot 2026-05-23 at 12 31 05" src="https://github.com/user-attachments/assets/730dbf73-2ae3-4d34-b817-e538bfd27935" />
<img width="1470" height="956" alt="Screenshot 2026-05-23 at 12 31 21" src="https://github.com/user-attachments/assets/39326a85-f5e8-43b8-a273-f0bb7d5914d4" />
<img width="1470" height="956" alt="Screenshot 2026-05-23 at 12 31 25" src="https://github.com/user-attachments/assets/d7905e66-937f-4cb3-9150-862dcd62ba53" />
<img width="1470" height="956" alt="Screenshot 2026-05-23 at 12 31 36" src="https://github.com/user-attachments/assets/cb4e5c5e-1338-4e07-adab-d2e5f06b3a8c" />
<img width="1470" height="956" alt="Screenshot 2026-05-23 at 12 31 43" src="https://github.com/user-attachments/assets/d5d5d4fd-590e-4d36-bf7b-aafc7ef7c6a9" />
<img width="1470" height="956" alt="Screenshot 2026-05-23 at 12 31 46" src="https://github.com/user-attachments/assets/c9095290-fd5c-4f60-ab82-69cd8965bc6e" />

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
