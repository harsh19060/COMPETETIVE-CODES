//! PRIMS ALGORITHM CODE 

//choose the minimm distance edge 
//from the chosen vertuices measure the lest coost to all the other vertises 
//then choose the closest vertice again 
//keep repeating untill all the vertices are choosen 

import java.util.*;

public class PRIMS {

    public static void prims(int cost[][]) {
        int n = cost.length;
        int l = -1, k = -1;
        int[] near = new int[n];
        Arrays.fill(near, -1);
        int t[][] = new int[n - 1][3];
        int INF = 9999;

        // select minimum edge
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j && cost[i][j] != INF && min > cost[i][j]) {
                    min = cost[i][j];
                    l = i;
                    k = j;
                }
            }
        }

        // first edge
        t[0][0] = l;
        t[0][1] = k;
        t[0][2] = cost[l][k];

        near[l] = 0;
        near[k] = 0;

        for (int i = 0; i < n; i++) {
            if (near[i] != 0 && near[i] != -1) {
                if (cost[l][i] < cost[k][i]) {
                    near[i] = l;
                } else {
                    near[i] = k;
                }
            }
        }

        // repeat n-2 times
        for (int i = 1; i < n - 1; i++) {
            min = Integer.MAX_VALUE;
            for (int j = 0; j < n; j++) {
                if (near[j] != 0 && near[j] != -1) {
                    if (cost[j][near[j]] < min) {
                        min = cost[j][near[j]];
                        k = j;
                    }
                }
            }

            t[i][0] = k;
            t[i][1] = near[k];
            t[i][2] = cost[k][near[k]];
            near[k] = 0;

            for (int j = 0; j < n; j++) {
                if (near[j] != 0 && near[j] != -1) {
                    if (cost[j][near[j]] > cost[j][k]) {
                        near[j] = k;
                    }
                }
            }
        }

        // print
        System.out.println("Edges in MST:");
        int total = 0;
        for (int i = 0; i < n - 1; i++) {
            System.out.println(t[i][0] + " - " + t[i][1] + " | cost = " + t[i][2]);
            total += t[i][2];
        }
        System.out.println("Total MST Cost = " + total);
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
        prims(cost);
    }
}
