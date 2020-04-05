package com.joker.medium;

public class UniquePaths
{
    /**
     * 自底向上的DP解法
     * @param m 列数
     * @param n 行数
     * @return 所有的可能路径数
     */
    public int uniquePaths(int m, int n)
    {
        //可以优化空间复杂度到 O(n)，现在的写法是 O(m * n)的
        int[][] dp = new int[m + 1][n + 1];
        for(int i = 1; i <= m; i++)
        {
            for(int j = 1; j <= n; j++)
            {
                if(i == 1 || j == 1)
                    dp[i][j] = 1;
                else
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m][n];
    }

    public int uniquePaths2(int m, int n)
    {
        //可以优化空间复杂度到 O(n)，现在的写法是 O(m * n)的
        int[][] dp = new int[2][n + 1];
        for(int i = 1; i <= m; i++)
        {
            for(int j = 1; j <= n; j++)
            {
                int curr = i % 2;
                int pre = (i - 1) % 2;
                if(i == 1 || j == 1)
                    dp[curr][j] = 1;
                else
                    dp[curr][j] = dp[pre][j] + dp[curr][j - 1];
            }
        }
        return dp[m % 2][n];
    }

    public int uniquePaths3(int m, int n) {
        if(m == 1 || n == 1)
            return 1;
        m--;
        n--;
        if(m < n) {              // Swap, so that m is the bigger number
            m = m + n;
            n = m - n;
            m = m - n;
        }
        long res = 1;
        int j = 1;
        for(int i = m+1; i <= m+n; i++, j++){       // Instead of taking factorial, keep on multiply & divide
            res *= i;
            res /= j;
        }

        return (int)res;
    }
}
