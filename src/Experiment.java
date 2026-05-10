public class Experiment {
    private long[][] results;
    private int[]    sizes;
    public Experiment() {
        sizes   = new int[]{10, 30, 100};
        results = new long[sizes.length][2];
    }
    public void runTraversals(Graph g) {
        long bfsStart = System.nanoTime();
        g.bfs(0);
        long bfsEnd   = System.nanoTime();
        System.out.println("  BFS time: " + (bfsEnd - bfsStart) + " ns");
        long dfsStart = System.nanoTime();
        g.dfs(0);
        long dfsEnd   = System.nanoTime();
        System.out.println("  DFS time: " + (dfsEnd - dfsStart) + " ns");
    }
    public void runMultipleTests() {
        for (int s = 0; s < sizes.length; s++) {
            int n = sizes[s];
            System.out.println("\n--- graph with " + n + " vertices ---");
            Graph g = buildGraph(n);
            long bfsStart = System.nanoTime();
            g.bfs(0);
            long bfsEnd   = System.nanoTime();
            results[s][0] = bfsEnd - bfsStart;
            long dfsStart = System.nanoTime();
            g.dfs(0);
            long dfsEnd   = System.nanoTime();
            results[s][1] = dfsEnd - dfsStart;
            System.out.println("  BFS time: " + results[s][0] + " ns");
            System.out.println("  DFS time: " + results[s][1] + " ns");
        }}
    public void printResults() {
        System.out.println("\n===================");
        System.out.println("  performance comparison table");
        System.out.println("=====================");
        System.out.printf("%-15s %-20s %-20s%n", "graph size", "BFS time (ns)", "DFS time (ns)");
        System.out.println("----------------------------------------");
        for (int s = 0; s < sizes.length; s++) {
            System.out.printf("%-15d %-20d %-20d%n",
                    sizes[s], results[s][0], results[s][1]);
        }
        System.out.println("==============\n");
    }
    private Graph buildGraph(int n) {
        Graph g = new Graph();
        for (int i = 0; i < n; i++) {
            g.addVertex(new Vertex(i));
        }
        for (int i = 0; i < n - 1; i++) {
            g.addEdge(i, i + 1);
        }
        for (int i = 0; i + 3 < n; i += 3) {
            g.addEdge(i, i + 3);
        }
        if (n >= 10) {
            g.addEdge(n - 1, 0);
            g.addEdge(n / 2, 1);
        }
        return g;
    }}