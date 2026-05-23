public class Experiment {
    private static final int RUNS = 5;
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
            System.out.println("\n--- graph with " + n + " vertices (" + RUNS + " runs) ---");
            long totalBfs = 0;
            long totalDfs = 0;
            for (int r = 0; r < RUNS; r++) {
                Graph g = buildGraph(n);
                boolean silent = (r > 0);
                long bfsStart = System.nanoTime();
                g.bfs(0, silent);
                long bfsEnd = System.nanoTime();
                totalBfs += (bfsEnd - bfsStart);
                long dfsStart = System.nanoTime();
                g.dfs(0, silent);
                long dfsEnd = System.nanoTime();
                totalDfs += (dfsEnd - dfsStart);
            }
            results[s][0] = totalBfs / RUNS;
            results[s][1] = totalDfs / RUNS;
            System.out.println("  avg BFS time: " + results[s][0] + " ns");
            System.out.println("  avg DFS time: " + results[s][1] + " ns");
        }}
    public void printResults() {
        long totalBfs = 0;
        long totalDfs = 0;
        for (int s = 0; s < sizes.length; s++) {
            totalBfs += results[s][0];
            totalDfs += results[s][1];
        }
        long avgBfs = totalBfs / sizes.length;
        long avgDfs = totalDfs / sizes.length;
        System.out.println("\n===================");
        System.out.println("  performance comparison table (avg of " + RUNS + " runs each)");
        System.out.println("=======================================================");
        System.out.printf("%-15s %-22s %-22s%n", "graph size", "avg BFS time (ns)", "avg DFS time (ns)");
        System.out.println("--------------------------");
        for (int s = 0; s < sizes.length; s++) {
            System.out.printf("%-15d %-22d %-22d%n",
                    sizes[s], results[s][0], results[s][1]);
        }
        System.out.println("--------------------------");
        System.out.printf("%-15s %-22d %-22d%n", "OVERALL AVG", avgBfs, avgDfs);
        System.out.println("=========================\n");
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