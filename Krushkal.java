//! krushkal ALGORITHM

//edge in sorted orede
// pick smallest to increasing value
//chck if in same set if not add 

//find 
//returns parent of node
import java.util.*;

class Edge implements Comparable<Edge> {
    int u, v, weight;

    Edge(int u, int v, int weight) {
        this.u = u;
        this.v = v;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge other) {
        return this.weight - other.weight; // sort by weight ascending
    }
}

public class Krushkal {

    // Find with path compression
    public static int find(int[] parent, int i) {
        if (parent[i] != i)
            parent[i] = find(parent, parent[i]);
        return parent[i];
    }

    // union by rank
    public static void union(int[] parent, int[] rank, int x, int y) {
        int xroot = find(parent, x);
        int yroot = find(parent, y);

        if (rank[xroot] < rank[yroot])
            parent[xroot] = yroot;
        else if (rank[xroot] > rank[yroot])
            parent[yroot] = xroot;
        else {
            parent[yroot] = xroot;
            rank[xroot]++;
        }
    }

    public static void krushkal(int cost[][]) {
        int n = cost.length;
        List<Edge> edges = new ArrayList<>();

        // all edges
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (cost[i][j] != 9999) {
                    edges.add(new Edge(i, j, cost[i][j]));
                }
            }
        }
        // sort the edges
        Collections.sort(edges);

        int parent[] = new int[n];
        int rank[] = new int[n];

        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        List<Edge> mst = new ArrayList<>();
        int totalCost = 0;

        for (Edge e : edges) {
            int uRoot = find(parent, e.u);
            int vRoot = find(parent, e.v);

            if (uRoot != vRoot) {
                mst.add(e);
                totalCost += e.weight;
                union(parent, rank, uRoot, vRoot);
            }
        }

        // Print MST edges
        System.out.println("Edges in MST:");
        for (Edge e : mst) {
            System.out.println(e.u + " - " + e.v + " | cost = " + e.weight);
        }
        System.out.println("Total MST cost = " + totalCost);

    }

    public static void main(String[] args) {
        int INF = 9999;
        int[][] cost = {
                { INF, 2, INF, 6, INF },
                { 2, INF, 3, 8, 5 },
                { INF, 3, INF, INF, 7 },
                { 6, 8, INF, INF, 9 },
                { INF, 5, 7, 9, INF }
        };

        krushkal(cost);
    }
}
