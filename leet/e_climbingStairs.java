//!CLIMBING STAIRS 
//EASY RECURRSION
// You are climbing a staircase. It takes n steps to reach the top.

// Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?

import java.util.Arrays;

public class e_climbingStairs {

    public int recurssion(int n , int[] dp){
        if(n==1) return 1;
        if(n==2) return 2;

        if (dp[n] != -1) return dp[n];

        dp[n] = recurssion(n-1,dp)+recurssion(n-2,dp);
        return dp[n];
    }

    public int climbStairs(int n) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, -1); 
        return recurssion(n,dp);
    }
}