package com.joker.easy;

public class ClimbingStairs
{
    public int climbStairs(int n)
    {
        if(n <= 2)
            return n;
        int pre = 2;
        int preOfPre = 1;
        int result = 0;
        for(int i = 3; i <= n; i++)
        {
            result = pre + preOfPre;
            preOfPre = pre;
            pre = result;
        }
        return result;
    }
}
