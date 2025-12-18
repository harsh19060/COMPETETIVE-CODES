import java.util.*;

public class prims {
    int[] near = new int[5];
    int[][] T = new int[4][3];
    int n = 5;

    public void primss(int Cost[][]) {
        // INITIALISE ALL
        Arrays.fill(near, -1);
        int min = Integer.MAX_VALUE;
        int o = 0, oo = 0;

        // FIND FIRST VERTEX
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (Cost[i][j] < min) {
                    min = Cost[i][j];     // FIXED: update min
                    o = i;
                    oo = j;
                }
            }
        }

        // update near array for o and oo
        near[o] = 0;
        near[oo] = 0;

        // ENTER FIRST EDGE IN T
        T[0][0] = o;
        T[0][1] = oo;
        T[0][2] = Cost[o][oo];

        // UPDATE THE NEAR ARRAY
        for (int i = 0; i < n; i++) {
            if (near[i] != 0) {
                if (Cost[o][i] < Cost[oo][i]) {
                    near[i] = o;
                } else
                    near[i] = oo;
            }
        }

        // CARRY THE SAME THING N-2 TIMES
        for (int i = 1; i < n - 1; i++) {   // FIXED: i < n-1
            min = Integer.MAX_VALUE;

            // traverse the near array to find the smallest edge
            for (int j = 0; j < n; j++) {
                if (near[j] != 0) {  // if the vertex is not already selected
                    if (Cost[near[j]][j] < min) {
                        min = Cost[near[j]][j];
                        oo = j;
                        o = near[j];
                    }
                }
            }

            // put the obtained vertex info in T
            T[i][0] = o;
            T[i][1] = oo;
            T[i][2] = Cost[o][oo];

            // update near array again with newly obtained vertex
            near[oo] = 0;  // FIXED: mark the new vertex as included

            for (int j = 0; j < n; j++) {
                if (near[j] != 0) {
                    if (Cost[near[j]][j] > Cost[oo][j]) {
                        near[j] = oo;
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        int INF = 9999;

        int Cost[][] = {
                {INF, 2, INF, 6, INF},
                {2, INF, 3, 8, 5},
                {INF, 3, INF, INF, 7},
                {6, 8, INF, INF, 9},
                {INF, 5, 7, 9, INF}
        };

        prims obj = new prims();
        obj.primss(Cost);

        System.out.println("MST is:");
        for (int i = 0; i < obj.T.length; i++) {
            System.out.println(obj.T[i][0] + " -- " + obj.T[i][1] + " cost = " + obj.T[i][2]);
        }
    }
}
