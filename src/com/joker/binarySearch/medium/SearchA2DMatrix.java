package com.joker.binarySearch.medium;

public class SearchA2DMatrix
{
    public boolean searchMatrix(int[][] matrix, int target)
    {
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return false;
        int lowY = 0;
        int highY = matrix.length - 1;
        int m = matrix.length;
        int n = matrix[0].length;
        while (lowY <= highY)
        {
            int midY = (lowY + highY) / 2;
            int[] array = matrix[midY];
            if(array[0] > target)
            {
                highY = midY - 1;
            }
            else if(array[n - 1] < target)
            {
                lowY = midY + 1;
            }
            else
            {
                int lowX = 0;
                int highX = n - 1;
                while (lowX <= highX)
                {
                    int midX = (lowX + highX) / 2;
                    if(array[midX] == target)
                        return true;
                    else if(array[midX] < target)
                    {
                        lowX = midX + 1;
                    }
                    else
                    {
                        highX = midX - 1;
                    }
                }
                break;
            }
        }
        return false;
    }
}
