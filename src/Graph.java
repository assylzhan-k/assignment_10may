import java.util.*;
public class Graph {
    private Map<Vertex, List<Edge>> adjacencyList;
    private Map<Integer, Vertex> vertexMap;
    public Graph() {
        adjacencyList = new LinkedHashMap<>();
        vertexMap     = new HashMap<>();
    }
    public void addVertex(Vertex v) {
        if (!adjacencyList.containsKey(v)) {
            adjacencyList.put(v, new ArrayList<>());
            vertexMap.put(v.getId(), v);
        }}
    public void addEdge(int from, int to, int weight) {
        Vertex src  = vertexMap.get(from);
        Vertex dest = vertexMap.get(to);
        if (src == null || dest == null) {
            System.out.println("warning: vertex " + from + " or " + to + " not found.");
            return;
        }
        adjacencyList.get(src).add(new Edge(src, dest, weight));
    }
    public void addEdge(int from, int to) {
        addEdge(from, to, 1);
    }
    public List<Edge> getEdges(Vertex v) {
        return adjacencyList.getOrDefault(v, new ArrayList<>());
    }
    public List<Vertex> getNeighbors(Vertex v) {
        List<Vertex> neighbors = new ArrayList<>();
        for (Edge e : getEdges(v)) {
            neighbors.add(e.getDestination());
        }
        return neighbors;
    }
    public Vertex getVertex(int id) {
        return vertexMap.get(id);
    }
    public int size() {
        return vertexMap.size();
    }
    public Collection<Vertex> getVertices() {
        return adjacencyList.keySet();
    }
    public void printGraph() {
        System.out.println("=== graph adjacency list ===");
        for (Map.Entry<Vertex, List<Edge>> entry : adjacencyList.entrySet()) {
            System.out.print(entry.getKey() + " -> [");
            List<Edge> edges = entry.getValue();
            for (int i = 0; i < edges.size(); i++) {
                Edge e = edges.get(i);
                System.out.print(e.getDestination() + "(w=" + e.getWeight() + ")");
                if (i < edges.size() - 1) System.out.print(", ");
            }
            System.out.println("]");
        }
        System.out.println();
    }
    public void bfs(int startId) {
        bfs(startId, false);
    }
    public void bfs(int startId, boolean silent) {
        Vertex start = vertexMap.get(startId);
        if (start == null) { System.out.println("start vertex not found."); return; }
        Set<Vertex>   visited = new LinkedHashSet<>();
        Queue<Vertex> queue   = new LinkedList<>();
        visited.add(start);
        queue.offer(start);
        if (!silent) System.out.print("BFS from " + startId + ": ");
        while (!queue.isEmpty()) {
            Vertex current = queue.poll();
            if (!silent) System.out.print(current.getId() + " ");
            for (Vertex neighbor : getNeighbors(current)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.offer(neighbor);
                }}}
        if (!silent) System.out.println();
    }
    public void dfs(int startId) {
        dfs(startId, false);
    }
    public void dfs(int startId, boolean silent) {
        Vertex start = vertexMap.get(startId);
        if (start == null) { System.out.println("start vertex not found."); return; }
        Set<Vertex>   visited = new LinkedHashSet<>();
        Deque<Vertex> stack   = new ArrayDeque<>();
        stack.push(start);
        visited.add(start);
        if (!silent) System.out.print("DFS from " + startId + ": ");
        while (!stack.isEmpty()) {
            Vertex current = stack.pop();
            if (!silent) System.out.print(current.getId() + " ");
            List<Vertex> neighbors = getNeighbors(current);
            for (int i = neighbors.size() - 1; i >= 0; i--) {
                Vertex neighbor = neighbors.get(i);
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    stack.push(neighbor);
                }}}
        if (!silent) System.out.println();
    }
    public void dijkstra(int startId) {
        int n = size();
        Map<Integer, Integer> idToIndex = new HashMap<>();
        Vertex[] indexToVertex = new Vertex[n];
        int idx = 0;
        for (Vertex v : adjacencyList.keySet()) {
            idToIndex.put(v.getId(), idx);
            indexToVertex[idx] = v;
            idx++;
        }
        Vertex start = vertexMap.get(startId);
        if (start == null) { System.out.println("start vertex not found."); return; }
        int[]     dist    = new int[n];
        boolean[] visited = new boolean[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[idToIndex.get(startId)] = 0;
        for (int i = 0; i < n; i++) {
            int u = -1;
            for (int j = 0; j < n; j++) {
                if (!visited[j] && (u == -1 || dist[j] < dist[u])) {
                    u = j;
                }}
            if (dist[u] == Integer.MAX_VALUE) break;
            visited[u] = true;
            Vertex current = indexToVertex[u];
            for (Edge e : getEdges(current)) {
                int v      = idToIndex.get(e.getDestination().getId());
                int newDist = dist[u] + e.getWeight();
                if (newDist < dist[v]) {
                    dist[v] = newDist;
                }}}
        System.out.println("Dijkstra shortest paths from vertex " + startId + ":");
        for (int i = 0; i < n; i++) {
            String distStr = (dist[i] == Integer.MAX_VALUE) ? "unreachable" : String.valueOf(dist[i]);
            System.out.println("  -> vertex " + indexToVertex[i].getId() + " : " + distStr);
        }
        System.out.println();
    }}