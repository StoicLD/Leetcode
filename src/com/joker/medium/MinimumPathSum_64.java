package com.joker.medium;

/**
 * This is for LC 64 Minimum Path Sum
 */
public class MinimumPathSum_64
{
    public int minPathSum(int[][] grid)
    {
        if(grid == null || grid.length == 0 || grid[0].length == 0)
            return 0;
        int m = grid.length;
        int n = grid[0].length;
        int[] dp = new int[n];
        for(int i = 0; i < m; i++)
        {
            dp[0] = grid[i][0] + dp[0];
            for(int j = 1; j < n; j++)
            {
                if(i == 0)
                    dp[j] = dp[j - 1] + grid[i][j];
                else
                    dp[j] = Math.min(dp[j], dp[j - 1]) + grid[i][j];
            }
        }
        return dp[n - 1];
    }
}
