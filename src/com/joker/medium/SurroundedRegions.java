package com.joker.medium;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 * This is for LeetCode 130 Surrounded Regions
 */
public class SurroundedRegions
{
    private static class Point
    {
        int x;
        int y;
        public Point(int _x, int _y)
        {
            x = _x;
            y = _y;
        }
    }

    int m,n;
    int[][] visited;
    //第一种解法，直接采用DFS解决
    public void solve(char[][] board)
    {
        if(board == null || board.length == 0 || board[0].length == 0)
            return;
        m = board.length;
        n = board[0].length;
        visited = new int[m][n];
        for(int i = 0; i < board.length; i++)
        {
            for(int j = 0; j < board[i].length; j++)
            {
                if(visited[i][j] != 1)
                {
                    List<Point> list = new LinkedList<>();
                    if(dfs(i, j, board, list) && board[i][j] != 'X')
                    {
                        for(Point point : list)
                        {
                            board[point.x][point.y] = 'X';
                        }
                    }
                    list = null;
                }
            }
        }
    }

    public static boolean check(int x, int y, int m, int n)
    {
        if(x < 0 || x >= m || y < 0 || y >= n)
            return false;
        return true;
    }

    public boolean dfs(int x, int y, char[][] borad, List<Point> list)
    {
        if(!check(x, y, m, n))
            return false;
        if(visited[x][y] == 1)
            return true;
        visited[x][y] = 1;
        if(borad[x][y] == 'X')
            return true;
        boolean result = true;
        result = dfs(x + 1, y, borad, list) && result;
        result = dfs(x - 1, y, borad, list) && result;
        result = dfs(x, y + 1, borad, list) && result;
        result = dfs(x, y - 1, borad, list) && result;
        if(result)
            list.add(new Point(x, y));
        return result;
    }

    /**
     * 这种思路就是先把围着边界的一圈所有为'O'的全部DFS一遍，
     * 把与边界O连接的所有O变为一个临时符号，比如这里的 '*'
     * 然后在把整个board全部遍历一遍
     * '*'即不能被转变的'O'，全部变回'O'
     * 剩下的'O'即可以被转变的，都转变为'X'
     * @param board
     */
   public void solve2(char[][] board) {
        if (board.length == 0 || board[0].length == 0)
            return;
        if (board.length < 2 || board[0].length < 2)
            return;
        int m = board.length, n = board[0].length;
        //Any 'O' connected to a boundary can't be turned to 'X', so ...
        //Start from first and last column, turn 'O' to '*'.
        for (int i = 0; i < m; i++) {
            if (board[i][0] == 'O')
                boundaryDFS(board, i, 0);
            if (board[i][n-1] == 'O')
                boundaryDFS(board, i, n-1);
        }
        //Start from first and last row, turn '0' to '*'
        for (int j = 0; j < n; j++) {
            if (board[0][j] == 'O')
                boundaryDFS(board, 0, j);
            if (board[m-1][j] == 'O')
                boundaryDFS(board, m-1, j);
        }
        //post-prcessing, turn 'O' to 'X', '*' back to 'O', keep 'X' intact.
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'O')
                    board[i][j] = 'X';
                else if (board[i][j] == '*')
                    board[i][j] = 'O';
            }
        }
    }
    //Use DFS algo to turn internal however boundary-connected 'O' to '*';
    private void boundaryDFS(char[][] board, int i, int j) {
        if (i < 0 || i > board.length - 1 || j <0 || j > board[0].length - 1)
            return;
        if (board[i][j] == 'O')
            board[i][j] = '*';
        if (i > 1 && board[i-1][j] == 'O')
            boundaryDFS(board, i-1, j);
        if (i < board.length - 2 && board[i+1][j] == 'O')
            boundaryDFS(board, i+1, j);
        if (j > 1 && board[i][j-1] == 'O')
            boundaryDFS(board, i, j-1);
        if (j < board[i].length - 2 && board[i][j+1] == 'O' )
            boundaryDFS(board, i, j+1);
    }


/*    public void solve2(char[][] board)
    {
        if(board == null || board.length == 0 || board[0].length == 0)
            return;
        m = board.length;
        n = board[0].length;
        visited = new int[m][n];
        Queue<Point> queue = new LinkedList<>();
        for(int i = 0; i < m; i++)
        {
            for(int j = 0; j < n; j++)
            {
                if(visited[i][j] != 1 && board[i][j] != 'X')
                {
                    List<Point> list = new LinkedList<>();
                    queue.offer(new Point(i, j));
                    boolean findBorder = false;
                    while (queue.peek() != null)
                    {
                        Point currPoint = queue.poll();
                        int x = currPoint.x;
                        int y = currPoint.y;
                        if(board[x][y] == 'X' || visited[x][y] == 1)
                            continue;
                        visited[x][y] = 1;
                        if(!findBorder)
                            list.add(currPoint);
                        if(!check(x + 1, y, m, n))
                            findBorder = true;
                        else if(visited[x][y] != 1)
                            queue.offer(new Point(x + 1, y));

                        if(!check(x - 1, y, m, n) && visited[x][y] != 1)
                            findBorder = true;
                        else if(visited[x][y] != 1)
                            queue.offer(new Point(x - 1, y));

                        if(!check(x, y + 1, m, n) && visited[x][y] != 1)
                            findBorder = true;
                        else if(visited[x][y] != 1)
                            queue.offer(new Point(x, y + 1));

                        if(!check(x, y - 1, m, n) && visited[x][y] != 1)
                            findBorder = true;
                        else if(visited[x][y] != 1)
                            queue.offer(new Point(x, y - 1));
                    }
                    if(!findBorder)
                    {
                        for(Point point : list)
                        {
                            board[point.x][point.y] = 'X';
                        }
                    }
                }
            }
        }
    }*/

    public static void printBoard(char[][] board)
    {
        for(int i = 0; i < board.length; i++)
        {
            for(int j = 0; j < board[i].length; j++)
            {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args)
    {
        char[][] board = new char[][]{{'O','O','O','O','X','X'},{'O','O','O','O','O','O'},{'O','X','O','X','O','O'},{'O','X','O','O','X','O'},{'O','X','O','X','O','O'},{'O','X','O','O','O','O'}};
        SurroundedRegions sr = new SurroundedRegions();
        printBoard(board);
        System.out.println();
        sr.solve2(board);
        printBoard(board);
    }

}
