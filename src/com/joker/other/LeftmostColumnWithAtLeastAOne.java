package com.joker.other;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

interface BinaryMatrix
{
    public int get(int x, int y);
    public List<Integer> dimensions();
}

class MyMatrix implements BinaryMatrix
{
    int[][] matrix;
    int count;
    public MyMatrix(int[][] _matrix)
    {
        count = 0;
        matrix = _matrix;
    }

    @Override
    public int get(int x, int y)
    {
        count++;
        return matrix[x][y];
    }

    @Override
    public List<Integer> dimensions()
    {
        return List.of(matrix.length, matrix[0].length);
    }
}

public class LeftmostColumnWithAtLeastAOne
{
    public int leftMostColumnWithOne(BinaryMatrix binaryMatrix)
    {
        List<Integer> dimensions = binaryMatrix.dimensions();
        if(dimensions == null || dimensions.size() != 2)
            return -1;
        int n = dimensions.get(0);
        int m = dimensions.get(1);
        //每一行二分法
        int result = Integer.MAX_VALUE;
        for(int i = 0; i < n; i++)
        {
            int low = 0;
            int high = m - 1;
            int curr = Integer.MAX_VALUE;
            while (low <= high)
            {
                int mid = (low + high) / 2;
                if(binaryMatrix.get(i, mid) == 1)
                {
                    high = mid - 1;
                    curr = Math.min(mid, curr);
                }
                else
                {
                    low = mid + 1;
                }
            }
            result = Math.min(result, curr);
        }
        return result == Integer.MAX_VALUE ? -1 : result;
    }

    public static void main(String[] args)
    {
        int[][] matrix = new int[][]{{0,0,0,0},{0,1,1,1},{0,0,1,1}};
        MyMatrix myMatrix = new MyMatrix(matrix);
        LeftmostColumnWithAtLeastAOne ls = new LeftmostColumnWithAtLeastAOne();
        ls.leftMostColumnWithOne(myMatrix);
        System.out.println(myMatrix.count);
    }

}
