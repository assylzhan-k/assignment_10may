import java.util.*;
public class Graph {
    private Map<Vertex, List<Vertex>> adjacencyList;
    private Map<Integer, Vertex> vertexMap;
    public Graph() {
        adjacencyList = new LinkedHashMap<>();
        vertexMap = new HashMap<>();
    }
    public void addVertex(Vertex v) {
        if (!adjacencyList.containsKey(v)) {
            adjacencyList.put(v, new ArrayList<>());
            vertexMap.put(v.getId(), v);
        }}
    public void addEdge(int from, int to) {
        Vertex src  = vertexMap.get(from);
        Vertex dest = vertexMap.get(to);
        if (src == null || dest == null) {
            System.out.println("warning: vertex " + from + " or " + to + " not found.");
            return; }
        adjacencyList.get(src).add(dest); }
    public List<Vertex> getNeighbors(Vertex v) {
        return adjacencyList.getOrDefault(v, new ArrayList<>());
    }
    public Vertex getVertex(int id) {
        return vertexMap.get(id);
    }
    public Collection<Vertex> getVertices() {
        return adjacencyList.keySet();
    }
    public void printGraph() {
        System.out.println("=== graph adjacency list ===");
        for (Map.Entry<Vertex, List<Vertex>> entry : adjacencyList.entrySet()) {
            System.out.print(entry.getKey() + " -> [");
            List<Vertex> neighbors = entry.getValue();
            for (int i = 0; i < neighbors.size(); i++) {
                System.out.print(neighbors.get(i));
                if (i < neighbors.size() - 1) System.out.print(", "); }
            System.out.println("]"); }
        System.out.println(); }
    public void bfs(int startId) {
        Vertex start = vertexMap.get(startId);
        if (start == null) { System.out.println("start vertex not found."); return; }
        Set<Vertex>   visited = new LinkedHashSet<>();
        Queue<Vertex> queue   = new LinkedList<>();
        visited.add(start);
        queue.offer(start);
        System.out.print("BFS from " + startId + ": ");
        while (!queue.isEmpty()) {
            Vertex current = queue.poll();
            System.out.print(current.getId() + " ");
            for (Vertex neighbor : getNeighbors(current)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.offer(neighbor);
                }}}
        System.out.println(); }
    public void dfs(int startId) {
        Vertex start = vertexMap.get(startId);
        if (start == null) { System.out.println("start vertex not found."); return; }
        Set<Vertex>   visited = new LinkedHashSet<>();
        Deque<Vertex> stack   = new ArrayDeque<>();
        stack.push(start);
        visited.add(start);
        System.out.print("DFS from " + startId + ": ");
        while (!stack.isEmpty()) {
            Vertex current = stack.pop();
            System.out.print(current.getId() + " ");
            List<Vertex> neighbors = getNeighbors(current);
            for (int i = neighbors.size() - 1; i >= 0; i--) {
                Vertex neighbor = neighbors.get(i);
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    stack.push(neighbor);
                }}} System.out.println(); }}