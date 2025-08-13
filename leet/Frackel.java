import  java.util.*;

public class Frackel {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of freckles: ");
        int n = sc.nextInt();

        double[][] graph = new double[n][n];

        System.out.println("Enter the distance matrix:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                graph[i][j] = sc.nextDouble();
            }
        }

        primMST(graph, n);
    }

    static void primMST(double[][] graph, int n) {
        boolean[] inMST = new boolean[n];
        double[] key = new double[n];
        int[] parent = new int[n]; 

        for (int i = 0; i < n; i++) {
            key[i] = Double.MAX_VALUE;
            parent[i] = -1;
        }

        key[0] = 0;

        for (int count = 0; count < n - 1; count++) {
            int u = minKey(key, inMST, n);
            inMST[u] = true;

            for (int v = 0; v < n; v++) {
                if (graph[u][v] != 0 && !inMST[v] && graph[u][v] < key[v]) {
                    parent[v] = u;
                    key[v] = graph[u][v];
                }
            }
        }

        printMST(parent, graph, n);
    }

    static int minKey(double[] key, boolean[] inMST, int n) {
        double min = Double.MAX_VALUE;
        int minIndex = -1;

        for (int v = 0; v < n; v++) {
            if (!inMST[v] && key[v] < min) {
                min = key[v];
                minIndex = v;
            }
        }
        return minIndex;
    }

    static void printMST(int[] parent, double[][] graph, int n) {
        double totalWeight = 0;
        System.out.println("\nEdges in the Minimum Spanning Tree:");
        for (int i = 1; i < n; i++) {
            System.out.printf("F%d - F%d : %.2f\n", parent[i] + 1, i + 1, graph[i][parent[i]]);
            totalWeight += graph[i][parent[i]];
        }
        System.out.printf("Total distance: %.2f\n", totalWeight);
    }
}
