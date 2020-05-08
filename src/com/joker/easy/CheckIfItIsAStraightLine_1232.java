package com.joker.easy;

public class CheckIfItIsAStraightLine_1232
{
    public boolean checkStraightLine(int[][] coordinates)
    {
        Double[] array = new Double[2];
        compute(array, coordinates[0][0], coordinates[0][1], coordinates[1][0], coordinates[1][1]);
        double preGradient = array[0];
        double preIntercept = array[1];
        for(int i = 2; i < coordinates.length; i += 2)
        {
            compute(array, coordinates[i - 1][0], coordinates[i - 1][1], coordinates[i][0], coordinates[i][1]);
            if(Double.isNaN(preGradient) &&
                    (!Double.isNaN(array[0]) || Math.abs(preIntercept - array[1]) >= 0.00001))
            {
                    return false;
            }
            else if(Double.isNaN(array[0]) || Math.abs(preGradient - array[0]) >= 0.00001
                        || Math.abs(preIntercept - array[1]) >= 0.00001)
            {
                return false;
            }

            preGradient = array[0];
            preIntercept = array[1];
        }
        return true;
    }

    public void compute(Double[] array, int x1, int y1, int x2, int y2)
    {
        if(x1 == x2)
        {
            array[0] = Double.NaN;
            array[1] = (double)x1;
        }
        else
        {
            array[0] = (double)(y2 - y1) / (double)(x2 - x1);
            array[1] = y1 - array[0] * x1;
        }
    }

    public static void main(String[] args)
    {
        int[][] coord = new int[][]{{4,8},{-2,8},{1,8},{8,8},{-5,8},{0,8},{7,8},{5,8}};
        int[][] coodr2 = new int[][]{{-1,1},{-6,-4},{-6,2},{2,0},{-1,-2},{0,-4}};
        CheckIfItIsAStraightLine_1232 c = new CheckIfItIsAStraightLine_1232();
        c.checkStraightLine(coodr2);
    }
}
