package com.joker.easy;

public class NumberComplement_476
{
    public int findComplement(int num)
    {
        int bits = num;
        int i = 0;
        while (bits > 0)
        {
            bits >>= 1;
            i++;
        }
        int xorNumber = Integer.MAX_VALUE >> (31 - i);
        return num ^ xorNumber;
    }
}
