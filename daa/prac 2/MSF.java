import java.io.*;
import java.util.*;

public class MSF {
    static int[][] graph = new int[20][20];
    static boolean[] visited = new boolean[20];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("graph.txt"));
        for (int i = 0; i < 20; i++) {
            String[] row = br.readLine().trim().split("\\s+");
            for (int j = 0; j < 20; j++) {
                graph[i][j] = Integer.parseInt(row[j]);
            }
        }
        br.close();

        List<List<Integer>> comps = getComponents();
        int idx = 1;
        for (List<Integer> comp : comps) {
            System.out.println("Component " + idx + ": " + comp);
            List<String> mst = prim(comp);
            System.out.println("MST for Component " + idx + ": " + mst);
            idx++;
        }
    }

    static List<List<Integer>> getComponents() {
        List<List<Integer>> comps = new ArrayList<>();
        Arrays.fill(visited, false);
        for (int i = 0; i < 20; i++) {
            if (!visited[i]) {
                List<Integer> comp = new ArrayList<>();
                dfs(i, comp);
                comps.add(comp);
            }
        }
        return comps;
    }

    static void dfs(int u, List<Integer> comp) {
        visited[u] = true;
        comp.add(u);
        for (int v = 0; v < 20; v++) {
            if (graph[u][v] > 0 && !visited[v]) {
                dfs(v, comp);
            }
        }
    }

    static List<String> prim(List<Integer> comp) {
        int n = comp.size();
        int[] key = new int[n];
        int[] parent = new int[n];
        boolean[] mstSet = new boolean[n];
        Arrays.fill(key, Integer.MAX_VALUE);
        key[0] = 0;
        parent[0] = -1;

        for (int count = 0; count < n - 1; count++) {
            int u = -1, min = Integer.MAX_VALUE;
            for (int i = 0; i < n; i++) {
                if (!mstSet[i] && key[i] < min) {
                    min = key[i];
                    u = i;
                }
            }
            mstSet[u] = true;
            for (int j = 0; j < n; j++) {
                int weight = graph[comp.get(u)][comp.get(j)];
                if (weight > 0 && !mstSet[j] && weight < key[j]) {
                    parent[j] = u;
                    key[j] = weight;
                }
            }
        }

        List<String> edges = new ArrayList<>();
        for (int i = 1; i < n; i++) {
            edges.add(comp.get(parent[i]) + "-" + comp.get(i) + " : " + graph[comp.get(parent[i])][comp.get(i)]);
        }
        return edges;
    }
}
