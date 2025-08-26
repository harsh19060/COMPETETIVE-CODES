import java.util.*;

class que5 {

    private int[] parent;

    private int find(int x) {
        if (parent[x] != x)
            parent[x] = find(parent[x]);
        return parent[x];
    }

    private void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        if (rootX != rootY)
            parent[rootX] = rootY;
    }

    public int removeStones(int[][] stones) {
        int n = stones.length;
        parent = new int[n];
        for (int i = 0; i < n; i++) parent[i] = i;


        for (int i = 0; i < n; i++) {
            for (int j = i+1; j < n; j++) {
                if (stones[i][0] == stones[j][0] || stones[i][1] == stones[j][1]) {
                    union(i, j);
                }
            }
        }

        Set<Integer> components = new HashSet<>();
        for (int i = 0; i < n; i++) {
            components.add(find(i));
        }

        return n - components.size();
    }

    public static void main(String[] args) {
        que5 sol = new que5();
        int[][] stones = {{0,0}, {0,2}, {1,3}, {3,1}, {3,2}, {4,3}};
        System.out.println(sol.removeStones(stones)); // Output: 4
    }
}
