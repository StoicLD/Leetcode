package com.joker.medium;

public class BitwiseAndOfNumbersRange_201
{
    public int rangeBitwiseAnd(int m, int n)
    {
        //默认 m < n
        int culSum = 0;
        int result = 0;
        while (culSum <= n && culSum >= 0)
        {
            int currCompare = n & (Integer.MAX_VALUE - (culSum + 1));
            if (currCompare == n)
            {
                culSum = culSum * 2 + 1;
                continue;
            }
            currCompare |= culSum;
            if (currCompare > n || currCompare < m)
                result += (culSum + 1);
            culSum = culSum * 2 + 1;
        }
        return result;
    }

    /**
     * 每次上下界都右移一位
     * @param m 下界
     * @param n 上界
     * @return 结果
     */
    public int rangeBitwiseAnd2(int m, int n)
    {
        if (m == 0)
        {
            return 0;
        }
        int moveFactor = 1;
        while (m != n)
        {
            m >>= 1;
            n >>= 1;
            moveFactor <<= 1;
        }
        return m * moveFactor;
    }

    public static void main(String[] args)
    {
        BitwiseAndOfNumbersRange_201 bit = new BitwiseAndOfNumbersRange_201();
        System.out.println(bit.rangeBitwiseAnd(0, 2147483647));
    }

}
