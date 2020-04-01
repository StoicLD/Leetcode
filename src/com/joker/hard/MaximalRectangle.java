package com.joker.hard;

import javax.imageio.event.IIOWriteProgressListener;
import java.util.Arrays;
import java.util.Stack;

public class MaximalRectangle
{
    /**
     * 暴力的O(N2)方法
     *
     * @param matrix 输入矩阵
     * @return
     */
    public int maximalRectangle(char[][] matrix)
    {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return 0;
        int m = matrix.length;
        int n = matrix[0].length;
        int maxRect = 0;
        for (int i = 0; i < m; i++)
        {
            for (int j = 0; j < n; j++)
            {
                int currMax = 0;
                int minWidth = Integer.MAX_VALUE;
                //遍历每行
                //x是高度坐标
                //y是宽度坐标
                for (int x = i; x < m; x++)
                {
                    int width = 0;
                    for (int y = j; y < n; y++)
                    {
                        if (matrix[x][y] == '1')
                            width++;
                        else
                            break;
                    }
                    minWidth = Math.min(minWidth, width);
                    //x - i - 1是height高度
                    currMax = Math.max(currMax, minWidth * (x - i + 1));
                }
                maxRect = Math.max(currMax, maxRect);
            }
        }
        return maxRect;
    }

    public int maximalRectangle2(char[][] matrix)
    {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return 0;
        int m = matrix.length;
        int n = matrix[0].length;
        int maxRect = 0;
        Stack<Integer> stack = new Stack<>();
        int[][] continuous = new int[m][n];
        for (int i = 0; i < m; i++)
        {
            for (int j = 0; j < n; j++)
            {
                while (j < n && matrix[i][j] == '1')
                {
                    stack.push(j);
                    j++;
                }
                int size = 1;
                while (!stack.empty())
                {
                    continuous[i][stack.pop()] = size;
                    size++;
                }
            }
            int size = 1;
            while (!stack.empty())
            {
                continuous[i][stack.pop()] = size;
                size++;
            }
        }

        for (int i = 0; i < m; i++)
        {
            for (int j = 0; j < n; j++)
            {
                if (continuous[i][j] != 0)
                {
                    int currMax = 0;
                    int minWidth = Integer.MAX_VALUE;
                    for (int x = i; x < m; x++)
                    {
                        minWidth = Math.min(minWidth, continuous[x][j]);
                        currMax = Math.max(currMax, minWidth * (x - i + 1));
                    }
                    maxRect = Math.max(currMax, maxRect);
                }
            }
        }
        return maxRect;
    }

    /**
     * 很巧妙的DP方法
     * 参见LeetCode 85.的解答，这道题和84是有相似之处的
     * 可以使用直方图的思路来理解。
     *  height[i] record the current number of countinous '1' in column i;
     *  left[i] record the left most index j which satisfies that for any index k from j to  i, height[k] >= height[i];
     *  right[i] record the right most index j which satifies that for any index k from i to  j, height[k] >= height[i];
     *  by understanding the definition, we can easily figure out we need to update maxArea with value (height[i] * (right[i] - left[i] + 1));
     * matrix
     * 0 0 0 1 0 0 0
     * 0 0 1 1 1 0 0
     * 0 1 1 1 1 1 0
     *
     * height
     * 0 0 0 1 0 0 0
     * 0 0 1 2 1 0 0
     * 0 1 2 3 2 1 0
     *
     * left
     * 0 0 0 3 0 0 0
     * 0 0 2 3 2 0 0
     * 0 1 2 3 2 1 0
     *
     * right
     * 7 7 7 4 7 7 7
     * 7 7 5 4 5 7 7
     * 7 6 5 4 5 4 7
     *
     * result
     * 0 0 0 1 0 0 0
     * 0 0 3 2 3 0 0
     * 0 5 6 3 6 5 0
     * @param matrix 输入矩阵
     * @return 最大矩形面积
     */
    public int maximalRectangleDP(char[][] matrix)
    {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) return 0;
        int m = matrix.length, n = matrix[0].length, maxArea = 0;
        //注意，left，right，height都是只有n的大小
        //因此每一行遍历设置的时候，是保存有上一行的信息的
        int[] left = new int[n];            //表示相同的高度最左边的那个坐标
        int[] right = new int[n];           //表示相同的高度最右边的那个坐标
        int[] height = new int[n];          //该处的高度
        Arrays.fill(right, n - 1);
        for (int i = 0; i < m; i++)
        {
            int rB = n - 1;
            for (int j = n - 1; j >= 0; j--)
            {
                if (matrix[i][j] == '1')
                {
                    right[j] = Math.min(right[j], rB);
                }
                else
                {
                    right[j] = n - 1;
                    rB = j - 1;
                }
            }
            int lB = 0;
            for (int j = 0; j < n; j++)
            {
                if (matrix[i][j] == '1')
                {
                    left[j] = Math.max(left[j], lB);
                    height[j]++;
                    maxArea = Math.max(maxArea, height[j] * (right[j] - left[j] + 1));
                }
                else
                {
                    height[j] = 0;
                    left[j] = 0;
                    lB = j + 1;
                }
            }
        }
        return maxArea;
    }

    public static void main(String[] args)
    {
        char[][] matrix = {{'1', '0', '1', '0', '0'}, {'1', '0', '1', '1', '1'}, {'1', '1', '1', '1', '1'}, {'1', '0', '0', '1', '0'}};
        MaximalRectangle mr = new MaximalRectangle();
        System.out.print(mr.maximalRectangle2(matrix));
    }

}
