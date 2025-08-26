import java.util.*;

class que4 {
    private List<List<Integer>> adj;
    private int[] tin, low;
    private boolean[] visited;
    private int timer;
    private List<List<Integer>> bridges;

    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {

        adj = new ArrayList<>();
        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());
        for (List<Integer> edge : connections) {
            int u = edge.get(0), v = edge.get(1);
            adj.get(u).add(v);
            adj.get(v).add(u);
        }

        tin = new int[n];
        low = new int[n];
        visited = new boolean[n];
        timer = 0;
        bridges = new ArrayList<>();
        
        for (int i = 0; i < n; i++) {
            if (!visited[i]) dfs(i, -1);
        }

        return bridges;
    }

    private void dfs(int u, int parent) {
        visited[u] = true;
        tin[u] = low[u] = timer++;

        for (int v : adj.get(u)) {
            if (v == parent) continue; // skip parent edge
            if (!visited[v]) {
                dfs(v, u);
                low[u] = Math.min(low[u], low[v]);

                if (low[v] > tin[u]) {
                    bridges.add(Arrays.asList(u, v)); // (u,v) is a bridge
                }
            } else {
                low[u] = Math.min(low[u], tin[v]);
            }
        }
    }

    public static void main(String[] args) {
        que4 sol = new que4();

        int n = 11;
        List<List<Integer>> connections = new ArrayList<>();
        connections.add(Arrays.asList(4,5));
        connections.add(Arrays.asList(5,6));
        connections.add(Arrays.asList(8,10));

        System.out.println(sol.criticalConnections(n, connections));
    }
}
