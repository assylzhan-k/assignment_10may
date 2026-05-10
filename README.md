# Assignment 3 – Graph Traversal and Representation System

## Project Overview

This project implements a **graph data structure** in Java and applies two classic traversal algorithms — **Breadth-First Search (BFS)** and **Depth-First Search (DFS)** — to graphs of varying sizes.
---
## Algorithm Descriptions

### Breadth-first search (BFS)

**Step-by-step:**
1. Mark the start vertex as visited; add it to a **queue**.
2. While the queue is not empty:
   - Dequeue the front vertex and record it.
   - For each unvisited neighbour, mark it visited and enqueue it.
**Time complexity:** O(V + E) — every vertex and edge is processed once.

---

### Depth-First Search (DFS)

**Step-by-step:**
1. Push the start vertex onto a **stack**; mark it visited.
2. While the stack is not empty:
   - Pop the top vertex and record it.
   - For each unvisited neighbour, mark it visited and push it.

**Time complexity:** O(V + E) — every vertex and edge is processed once.

---

## Experimental Results

Graphs were built with a chain structure plus skip edges every 3 vertices and a few back edges, giving a realistic sparse graph.

| Graph Size (V) | BFS Time (ns) | DFS Time (ns) |
|:-:|:-:|:-:|
| 10 | 80917 | 192458 |
| 30 | 218583 | 178500 |
| 100 | 604041 | 490334 |

### Observations

- Both BFS and DFS scale roughly **linearly** with graph size, consistent with O(V + E).
- DFS is marginally faster in practice because stack operations
- The gap between BFS and DFS narrows on larger graphs, suggesting the dominant cost is edge traversal rather than the data-structure overhead.

---

## Screenshots

<img width="1470" height="956" alt="Screenshot 2026-05-10 at 07 20 17" src="https://github.com/user-attachments/assets/3143fbd6-0c59-4ef4-8327-41ea38231c29" />

<img width="1470" height="956" alt="Screenshot 2026-05-10 at 07 20 24" src="https://github.com/user-attachments/assets/e1a61912-d84d-4241-be5c-dadd80e8ebd2" />

<img width="1470" height="956" alt="Screenshot 2026-05-10 at 07 20 28" src="https://github.com/user-attachments/assets/78537dbb-c511-4904-b810-71e0489ce424" />

<img width="1470" height="956" alt="Screenshot 2026-05-10 at 07 21 12" src="https://github.com/user-attachments/assets/84b2518c-654d-4429-bf18-268d24707cf3" />

<img width="1470" height="956" alt="Screenshot 2026-05-10 at 07 21 48" src="https://github.com/user-attachments/assets/210dae0f-dfa7-41f3-8274-370dd5e9f128" />

<img width="1470" height="956" alt="Screenshot 2026-05-10 at 07 22 22" src="https://github.com/user-attachments/assets/68445ec2-df42-48c2-b039-4ac180be4aff" />

<img width="1470" height="956" alt="Screenshot 2026-05-10 at 07 22 26" src="https://github.com/user-attachments/assets/69c2624a-ca4b-41e2-8a12-bb96df2966c1" />

<img width="1470" height="956" alt="Screenshot 2026-05-10 at 07 23 21" src="https://github.com/user-attachments/assets/c06f77c9-47b9-492f-8626-efc6fd404342" />

<img width="1470" height="956" alt="Screenshot 2026-05-10 at 07 23 28" src="https://github.com/user-attachments/assets/642944cd-ba76-4081-b709-5459270eef93" />

<img width="1470" height="956" alt="Screenshot 2026-05-10 at 07 23 34" src="https://github.com/user-attachments/assets/f2271873-9381-4442-b8f3-1a7c55689145" />


---

## Reflection

Implementing BFS and DFS made the difference between the two algorithms concrete in a way that reading alone cannot. BFS's queue guarantees that vertices are visited in order of distance from the source — it "fans out" evenly. DFS's stack means it commits to one path until it can go no further, then backtracks. This makes DFS naturally suited for problems that involve exhaustive path exploration (mazes, dependency resolution), while BFS shines when the goal is to find the *shortest* route.

The main implementation challenge was handling the visited set correctly for graphs with cycles. Without it, both algorithms would loop forever. A secondary challenge was preserving natural neighbour order in DFS when using an iterative stack — pushing neighbours in reverse order was needed to match the intuitive left-to-right traversal one would get from a recursive DFS. Overall, working through these algorithms from scratch solidified my understanding of how graph structure directly influences the order and efficiency of traversal.
