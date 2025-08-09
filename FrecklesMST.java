// Little Richie connects the freckles on his Dad’s back to form a picture
// of the Liberty Bell. Consider Dad’s back to be a plane with freckles at various (x, y)
// locations. Your job is to tell Richie how to connect the dots so as to minimize the amount of
// ink used. Richie connects the dots by drawing straight lines between pairs, without lifting the
// pen between lines. When Richie is done there must be a sequence of connected lines from
// any freckle to any other freckle.
//  Consider the distance between freckles as input in the form of matrix from the user.
//  Apply minimum spanning tree algorithm and print the names of connected freckles
// (F1, F2, F3, etc.) along with the distance between them and a total value.


import java.util.Scanner;

public class FrecklesMST {
    public static void primMST(int[][] graph, int n) {
        int[] parent = new int[n]; // Stores the MST structure
        int[] key = new int[n];    // Minimum weight to reach a vertex
        boolean[] mstSet = new boolean[n]; // Tracks vertices in MST

        // Initialize all keys as infinite
        for (int i = 0; i < n; i++) {
            key[i] = Integer.MAX_VALUE;
            mstSet[i] = false;
        }

        // Start from the first vertex
        key[0] = 0;
        parent[0] = -1; // First node is always root of MST

        for (int count = 0; count < n - 1; count++) {
            // Find the vertex with the smallest key value not yet in MST
            int u = minKey(key, mstSet, n);
            mstSet[u] = true;

            // Update the key values of adjacent vertices
            for (int v = 0; v < n; v++) {
                if (graph[u][v] != 0 && !mstSet[v] && graph[u][v] < key[v]) {
                    parent[v] = u;
                    key[v] = graph[u][v];
                }
            }
        }

        printMST(parent, graph, n);
    }

    public static int minKey(int[] key, boolean[] mstSet, int n) {
        int min = Integer.MAX_VALUE, minIndex = -1;
        for (int v = 0; v < n; v++) {
            if (!mstSet[v] && key[v] < min) {
                min = key[v];
                minIndex = v;
            }
        }
        return minIndex;
    }

    public static void printMST(int[] parent, int[][] graph, int n) {
        int total = 0;
        System.out.println("Edges in the Minimum Spanning Tree:");
        for (int i = 1; i < n; i++) {
            System.out.println("F" + (parent[i] + 1) + " - F" + (i + 1) + " : " + graph[i][parent[i]]);
            total += graph[i][parent[i]];
        }
        System.out.println("Total ink used: " + total);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of freckles: ");
        int n = sc.nextInt();

        int[][] graph = new int[n][n];
        System.out.println("Enter the distance matrix:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                graph[i][j] = sc.nextInt();
            }
        }

        primMST(graph, n);
    }
}
