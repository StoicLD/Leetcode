package com.joker.medium;

import com.joker.hard.TrappingRainWater;

import java.util.List;

public class Triangle_120
{
    public int minimumTotal(List<List<Integer>> triangle)
    {
        //默认 triangle.size() == triangle[triangle.size() - 1].size()
        if(triangle == null || triangle.size() == 0)
            return 0;
        int[] dp = new int[triangle.size()];
        int i = triangle.size() - 1;
        for(int j = 0; j < triangle.get(i).size(); j++)
        {
            dp[j] = triangle.get(i).get(j);
        }

        for(i--; i >=0; i--)
        {
            for(int j = 0; j < triangle.get(i).size(); j++)
            {
                dp[j] = Math.min(dp[j], dp[j + 1]) + triangle.get(i).get(j);
            }
        }
        return dp[0];
    }
}
