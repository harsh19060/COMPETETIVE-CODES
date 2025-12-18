public class hamiltoncyclye {

    int n = 5;
    int[][] g = {
            {0, 1, 0, 1, 0},
            {1, 0, 1, 1, 1},
            {0, 1, 0, 0, 1},
            {1, 1, 0, 0, 1},
            {0, 1, 1, 1, 0}
    };
    int[] path;

    hamiltoncyclye() {
        path = new int[n];
        for (int i = 0; i < n; i++) {
            path[i] = 0;
        }
        path[0] = 1; // Start from vertex 1 (using 1-based indexing for clarity)
    }

    public void Hcycle(int k) {
        while (true) {
            NextValue(k);
            if (path[k] == 0)
                return; // no more possible vertex
            if (k == n - 1) {
                // check if last vertex connects back to first
                if (g[path[k] - 1][path[0] - 1] == 1) {
                    printSolution();
                }
            } else {
                Hcycle(k + 1);
            }
        }
    }

    public void NextValue(int k) {
        while (true) {
            path[k] = (path[k] + 1) % (n + 1); // next vertex number (1..n)
            if (path[k] == 0)
                return; // tried all vertices, no solution

            // check adjacency
            if (g[path[k - 1] - 1][path[k] - 1] != 0) {
                int j;
                // check if already in path
                for (j = 0; j < k; j++) {
                    if (path[j] == path[k])
                        break;
                }
                // if not repeated vertex
                if (j == k) {
                    // if last vertex, ensure it connects to first
                    if ((k < n - 1) || ((k == n - 1) && (g[path[k] - 1][path[0] - 1] != 0))) {
                        return;
                    }
                }
            }
        }
    }

    public void printSolution() {
        System.out.print("Hamiltonian Cycle: ");
        for (int i = 0; i < n; i++)
            System.out.print(path[i] + " ");
        System.out.println(path[0]);
    }

    public static void main(String[] args) {
        hamiltoncyclye hc = new hamiltoncyclye();
        hc.Hcycle(1);
    }
}
