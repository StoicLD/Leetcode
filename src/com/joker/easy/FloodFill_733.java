package com.joker.easy;

import java.util.Stack;
public class FloodFill_733
{
    int m;
    int n;
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor)
    {
        if(image == null || sr < 0 || sc < 0 || image.length <= sr || image[0].length <= sc)
            return null;
        m = image.length;
        n = image[0].length;
        int originColor = image[sr][sc];
        if(originColor != newColor)
            dfs(sr, sc, image, newColor, originColor);
        return image;
    }

    public void dfs(int x, int y, int[][] image, int newColor, int originColor)
    {
        image[x][y] = newColor;
        if(check(x - 1, y, image, originColor))
            dfs(x - 1, y, image, newColor, originColor);
        if(check(x + 1, y, image, originColor))
            dfs(x + 1, y, image, newColor, originColor);
        if(check(x, y - 1, image, originColor))
            dfs(x, y - 1, image, newColor, originColor);
        if(check(x, y + 1, image, originColor))
            dfs(x, y + 1, image, newColor, originColor);
    }

    public boolean check(int x, int y, int[][] image, int color)
    {
        return x >= 0 && x < m && y >= 0 && y < n && image[x][y] == color;
    }
}
