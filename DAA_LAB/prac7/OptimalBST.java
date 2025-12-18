import java.util.*;

public class OptimalBST {
    
    static void display(double[][] vec, int n) {
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < i - 1; j++) {
                System.out.print("------ ");
            }
            for (int j = i - 1; j < n; j++) {
                System.out.printf("%.4f ", vec[i][j]);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        double[] p = {0, 0.1, 0.2, 0.4, 0.3};
        double[] q = {0.05, 0.1, 0.05, 0.05, 0.1};
        int n = q.length;

        double[][] w = new double[n + 1][n];
        double[][] e = new double[n + 1][n];
        double[][] r = new double[n][n];

        for (int i = 1; i <= n; i++) {
            for (int j = i - 1; j < n; j++) {
                if (i == j + 1) {
                    w[i][j] = q[j];
                } else {
                    w[i][j] = w[i][j - 1] + q[j] + p[j];
                }
            }
        }

        System.out.println("W matrix:");
        display(w, n);

        for (int k = 0; k < n; k++) {
            int i = 1, j = k;
            while (i <= n - k && j < n) {
                if (i - 1 == j) {
                    e[i][j] = q[j];
                } else {
                    double mini = Double.MAX_VALUE;
                    int pIdx = i + 1, qIdx = i - 1;
                    while (pIdx <= j + 1 && qIdx < j) {
                        if ((e[pIdx][j] + e[i][qIdx]) < mini) {
                            mini = e[pIdx][j] + e[i][qIdx];
                            r[i][j] = qIdx + 1;
                        }
                        pIdx++;
                        qIdx++;
                    }
                    e[i][j] = mini + w[i][j];
                }
                i++;
                j++;
            }
        }

        System.out.println("E matrix:");
        display(e, n);

        System.out.println("R matrix:");
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < i; j++) {
                System.out.print("------ ");
            }
            for (int j = i; j < n; j++) {
                System.out.printf("%.4f ", r[i][j]);
            }
            System.out.println();
        }

        System.out.println("\nMinimum expected cost of the Optimal Binary Search Tree: " + e[1][n - 1]);
    }
}
