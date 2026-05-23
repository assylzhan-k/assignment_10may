public class Main {
    public static void main(String[] args) {
        System.out.println("====================");
        System.out.println("  PART---1: small graph (10 ver) – structure & traversal");
        System.out.println("====================\n");
        Graph small = new Graph();
        for (int i = 0; i < 10; i++) {
            small.addVertex(new Vertex(i));
        }
        int[][] smallEdges = {
                {0, 1}, {0, 2}, {1, 3}, {1, 4},
                {2, 5}, {2, 6}, {3, 7}, {4, 8},
                {5, 9}, {6, 3}, {7, 0}, {8, 5}
        };
        for (int[] e : smallEdges) {
            small.addEdge(e[0], e[1]);
        }
        small.printGraph();
        System.out.println("--- traversals on small graph ---");
        Experiment smallExp = new Experiment();
        smallExp.runTraversals(small);
        System.out.println("\n===================");
        System.out.println("  BONUS: Dijkstra's shortest path from vertex 0");
        System.out.println("=====================\n");
        small.dijkstra(0);
        System.out.println("\n===================");
        System.out.println("  PART--2: performance experiments");
        System.out.println("=====================");
        Experiment exp = new Experiment();
        exp.runMultipleTests();
        exp.printResults();
    }}