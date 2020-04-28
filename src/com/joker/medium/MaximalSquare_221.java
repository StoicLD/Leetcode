package com.joker.medium;

public class MaximalSquare_221
{
    /*
        static class Square
        {
            int width;
            int height;
            public Square()
            {
                width = 0;
                height = 0;
            }
            public Square(int _h, int _w)
            {
                width = _w;
                height = _h;
            }
        }
        public int maximalSquare(char[][] matrix)
        {
            if(matrix == null || matrix.length == 0 || matrix[0].length == 0)
                return 0;
            int m = matrix.length;
            int n = matrix[0].length;
            Square[][] dp = new Square[2][n];
            int result = 0;
            for(int i = m - 1; i >= 0; i--)
            {
                int num = i % 2;
                for(int j = n - 1; j >= 0; j--)
                {
                    if(matrix[i][j] == '1')
                    {
                        if (j + 1 < n && i + 1 < m)
                        {
                            Square right = dp[num][j + 1];
                            Square down = dp[1 - num][j];
                            if(down.height > right.height && down.width < right.width)
                                dp[num][j] = new Square(1 + down.height, 1 + down.width);
                            else
                                dp[num][j] = new Square(1 + down.height, 1 + right.width);
                        }
                        else if(j + 1 < n)
                            dp[num][j] = new Square(1, dp[num][j + 1].width + 1);
                        else if(i + 1 < m)
                        {
                            dp[num][j] = new Square(dp[1 - num][j].height + 1, 1);
                        }
                        else
                            dp[num][j] = new Square(1, 1);
                        Square sq = dp[num][j];
                        int size = Math.min(dp[num][j].height, dp[num][j].width);
                        result = Math.max(result, size * size);
                    }
                    else
                        dp[num][j] = new Square();
                }
            }
            return result;
        }
    */

    /**
     * DP的核心是，dp(i,j)=min(dp(i−1,j),dp(i−1,j−1),dp(i,j−1))+1.
     * @param matrix
     * @return
     */
    public int maximalSquare(char[][] matrix)
    {
        int rows = matrix.length, cols = rows > 0 ? matrix[0].length : 0;
        int[] dp = new int[cols + 1];
        int maxsqlen = 0, prev = 0;
        for (int i = 1; i <= rows; i++)
        {
            for (int j = 1; j <= cols; j++)
            {
                int temp = dp[j];
                if (matrix[i - 1][j - 1] == '1')
                {
                    dp[j] = Math.min(Math.min(dp[j - 1], prev), dp[j]) + 1;
                    maxsqlen = Math.max(maxsqlen, dp[j]);
                }
                else
                {
                    dp[j] = 0;
                }
                prev = temp;
            }
        }
        return maxsqlen * maxsqlen;
    }

    public static void main(String[] args)
    {
        char[][] test = new char[][]{{'1', '1', '1', '1', '1', '1', '1', '1'}, {'1', '1', '1', '1', '1', '1', '1', '0'}, {'1', '1', '1', '1', '1', '1', '1', '0'}, {'1', '1', '1', '1', '1', '0', '0', '0'}, {'0', '1', '1', '1', '1', '0', '0', '0'}};
        MaximalSquare_221 mSquare = new MaximalSquare_221();
        mSquare.maximalSquare(test);
    }
}
