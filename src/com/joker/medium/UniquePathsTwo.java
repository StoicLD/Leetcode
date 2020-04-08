package com.joker.medium;

import javax.crypto.Cipher;

public class UniquePathsTwo
{
    public int uniquePathsWithObstacles(int[][] obstacleGrid)
    {
        if(obstacleGrid == null || obstacleGrid.length == 0 || obstacleGrid[0].length == 0)
            return 0;
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        if(obstacleGrid[0][0] == 1 || obstacleGrid[m - 1][n - 1] == 1)
            return 0;
        //可以优化空间复杂度到 O(n)，现在的写法是 O(m * n)的
        int[][] dp = new int[2][n + 1];
        dp[0][1] = 1;
        for(int i = 1; i <= m; i++)
        {
            for(int j = 1; j <= n; j++)
            {
                int curr = i % 2;
                int pre = (i - 1) % 2;
                if(obstacleGrid[i - 1][j - 1] == 0)
                {
                    dp[curr][j] = dp[pre][j] + dp[curr][j - 1];
                }
                else
                {
                    dp[curr][j] = 0;
                }
            }
        }
        return dp[m % 2][n];
    }
}
