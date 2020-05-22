package com.joker.medium;

import java.util.ArrayList;

public class CountSquareSubmatricesWithAllOnes_1127
{
    public int countSquares(int[][] matrix)
    {
        //F(i, j)k 以(i,j)为左下角，k为side的square
        //F(i, j)k = F(i, j - 1)k-1 && F(i - 1, j)k-1 && F(i - 1, j - 1)k-1
        // right j++, down i++
        int m = matrix.length;
        int n = matrix[0].length;
        //只要知道最大的side就可以了
        //如果F(i,j)3 有，那么F(i,j)2 一定有
        int[][] dp = new int[m][n];
        int result = 0;
        for (int i = 0; i < m; i++)
        {
            for (int j = 0; j < n; j++)
            {
                if (i == 0 || j == 0)
                    dp[i][j] = matrix[i][j];
                else if (matrix[i][j] != 0)
                {
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
                }
                result += dp[i][j];
            }
        }
        return result;
    }


    /**
     * 只需要O(1)空间就可以了。
     * 直接修改原数据就可以了
     *
     * @param A
     * @return
     */
    public int countSquares2(int[][] A)
    {
        int res = 0;
        for (int i = 0; i < A.length; ++i)
        {
            for (int j = 0; j < A[0].length; ++j)
            {
                if (A[i][j] > 0 && i > 0 && j > 0)
                {
                    A[i][j] = Math.min(A[i - 1][j - 1], Math.min(A[i - 1][j], A[i][j - 1])) + 1;
                }
                res += A[i][j];
            }
        }
        return res;
    }

    /**
     * 少一点判定条件运行时间更短
     * @param matrix
     * @return
     */
    public int countSquares3(int[][] matrix)
    {
        //use cumulative sum array
        int m = matrix.length;
        int n = matrix[0].length;
        int count = matrix[0][0];
        for (int i = 1; i < m; i++)
        {
            count += matrix[i][0];
        }
        for (int j = 1; j < n; j++)
        {
            count += matrix[0][j];
        }
        for (int i = 1; i < m; i++)
        {
            for (int j = 1; j < n; j++)
            {
                if (matrix[i][j] != 0)
                {
                    matrix[i][j] = Math.min(matrix[i - 1][j - 1], Math.min(matrix[i - 1][j], matrix[i][j - 1])) + 1;
                    count += matrix[i][j];
                }
            }
        }
        return count;
    }

    public static void main(String[] args)
    {
        int[][] test = new int[][]{{0, 1, 1, 1}, {1, 1, 1, 1}, {0, 1, 1, 1}};
        CountSquareSubmatricesWithAllOnes_1127 ct = new CountSquareSubmatricesWithAllOnes_1127();
        ct.countSquares(test);
    }
}
