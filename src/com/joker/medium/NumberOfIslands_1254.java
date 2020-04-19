package com.joker.medium;

import javax.xml.stream.FactoryConfigurationError;

public class NumberOfIslands_1254
{
    int m;
    int n;
    //int[][] visit;
    /**
     * 朴素的DFS想法
     * @param grid
     * @return
     */
    public int numIslands(char[][] grid)
    {
        if(grid == null || grid.length == 0 || grid[0].length == 0)
            return 0;
        m = grid.length;
        n = grid[0].length;
        //visit = new int[m][n];
        int result = 0;
        for(int i = 0; i < m; i++)
        {
            for(int j = 0; j < n; j++)
            {
                if(grid[i][j] == '1')
                {
                    dfs(i, j, grid);
                    result++;
                }
            }
        }
        return result;
    }

    public void dfs(int i, int j, char[][] grid)
    {
        grid[i][j] = '0';
        if(check(i - 1, j, grid))
            dfs(i - 1, j, grid);

        if(check(i + 1, j, grid))
            dfs(i + 1, j, grid);

        if(check(i, j - 1, grid))
            dfs(i, j - 1, grid);

        if(check(i, j + 1, grid))
            dfs(i, j + 1, grid);
    }

    public boolean check(int i, int j, char[][] grid)
    {
        return i >= 0 && i < m && j >= 0 && j < n && grid[i][j] == '1';
    }
}
