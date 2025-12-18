import java.util.*;
public class BellmanFordAlgo {
    static final int INF = 9999;

    // Algorithm Bellman Ford Algo (v, cost, dist, n)
    public static void bellmanFord(int v, int[][] cost, int[] dist, int n) {

        // STEP-1 : Initialize dist[i] = cost[v][i]
        for (int i = 1; i <= n; i++) {
            dist[i] = cost[v][i];   // 1-D array
        }
        // STEP-2 : k = 2 to n-1
        for (int k = 2; k <= n - 1; k++) {
            // For each vertex 'u' such that u != v and 'u' has at least one incoming edge
            for (int u = 1; u <= n; u++) {

                if (u == v) continue;

                // For each <i, u> in the graph  (i → u incoming edge)
                for (int i = 1; i <= n; i++) {

                    if (cost[i][u] != INF) {

                        // If dist[u] > dist[i] + cost[i][u] then relax
                        if (dist[u] > dist[i] + cost[i][u]) {
                            dist[u] = dist[i] + cost[i][u];
                        }
                    }
                }
            }}}

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of vertices: ");
        int n = sc.nextInt();
        int[][] cost = new int[n + 1][n + 1];
        int[] dist = new int[n + 1];
        System.out.println("Enter cost adjacency matrix (use " + INF + " for INF):");
        for (int i = 1; i <= n; i++)
            for (int j = 1; j <= n; j++)
                cost[i][j] = sc.nextInt();
        System.out.print("Enter the source vertex: ");
        int v = sc.nextInt();
        bellmanFord(v, cost, dist, n);
        System.out.println("\nShortest distances from source " + v + ":");
        for (int i = 1; i <= n; i++)
            System.out.println("dist[" + i + "] = " + dist[i]);
        sc.close();
    }
}
