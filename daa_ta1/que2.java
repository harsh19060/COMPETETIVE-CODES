
class que2 {

    static int find(int[] parent, int x) {
        if (parent[x] != x)
            parent[x] = find(parent, parent[x]); 
        return parent[x];
    }

    static void union(int[] parent, int[] rank, int x, int y) {
        int rootX = find(parent, x);
        int rootY = find(parent, y);
        if (rootX != rootY) {
            if (rank[rootX] < rank[rootY]) {
                parent[rootX] = rootY;
            } else if (rank[rootX] > rank[rootY]) {
                parent[rootY] = rootX;
            } else {
                parent[rootY] = rootX;
                rank[rootX]++;
            }
        }
    }

    public int makeConnected(int n, int[][] edges) {
       
        int m = edges.length;

        //! edge case 1 // less no of edges 
        if (m < n - 1) return -1;

        int[] parent = new int[n];
        int[] rank = new int[n];
        for (int i = 0; i < n; i++) parent[i] = i;

        for (int[] e : edges) {
            union(parent, rank, e[0], e[1]);
        }

        int components = 0;
        for (int i = 0; i < n; i++) {
            if (find(parent, i) == i) components++;
        }

        return components - 1;
    }

    public static void main(String[] args) {
        que2 sol = new que2();
        int n = 9;
        int[][] edges = {{0,1},{0,2},{0,3},{1,2},{2,3},{4,5},{5,6},{7,8}};
        System.out.println(sol.makeConnected(n, edges)); 
    }
}
