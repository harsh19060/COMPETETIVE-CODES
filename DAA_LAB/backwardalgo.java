import java.util.*;

public class backwardalgo {
    static final int N = 4;
    static final int INF = 10000000;

    static int[][] dist = {
        {0, 10, 15, 20},
        {5, 0, 9, 10},
        {6, 13, 0, 12},
        {8, 8, 9, 0}
    };

    public static void main(String[] args) {
        int[][] dp = new int[1 << N][N];
        for (int[] row : dp) Arrays.fill(row, INF);
        dp[1][0] = 0;

        for (int mask = 0; mask < (1 << N); mask++) {
            if ((mask & 1) == 0) continue;
            for (int last = 0; last < N; last++) {
                if ((mask & (1 << last)) == 0) continue;
                if (last == 0 && mask != 1) continue;
                int pmask = mask ^ (1 << last);
                if (pmask == 0) {
                    if (last != 0) dp[mask][last] = Math.min(dp[mask][last], dist[0][last]);
                } else {
                    for (int prev = 0; prev < N; prev++) {
                        if ((pmask & (1 << prev)) == 0) continue;
                        dp[mask][last] = Math.min(dp[mask][last], dp[pmask][prev] + dist[prev][last]);
                    }
                }
            }
        }

        int full = (1 << N) - 1;
        int minCost = INF;
        for (int last = 1; last < N; last++)
            minCost = Math.min(minCost, dp[full][last] + dist[last][0]);

        System.out.println("Minimum Cost of Travelling: " + minCost);
    }
}
