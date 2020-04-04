package com.joker.easy;

import com.joker.hard.MaximalRectangle;

public class MaximumSubarray
{
    /**
     * 第一种 O(N) 的方法
     *
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums)
    {
        if (nums == null || nums.length == 0)
            return 0;
        int start = 0, end = 0;
        int result = nums[0];
        for (int i = 1; i < nums.length; i++)
        {
            int currMax = nums[i];
            int temp = 0;
            int newStart = i;
            for (int j = i; j > end; j--)
            {
                temp += nums[j];
                if (currMax < temp)
                {
                    currMax = temp;
                    newStart = j;
                }
            }
            if (currMax > result)
            {
                if (temp + result > currMax)
                {
                    //合并
                    end = i;
                    result = temp + result;
                }
                else
                {
                    start = newStart;
                    end = i;
                    result = currMax;
                }
            }
            else if (temp > 0)
            {
                result = temp + result;
                end = i;
            }
        }
        return result;
    }

    /**
     * 巧妙的DP方法
     *
     * @param A 输入数组
     * @return 最大连续子数组
     */
    public int maxSubArrayDP(int[] A)
    {
        int n = A.length;
        int[] dp = new int[n];//dp[i] means the maximum subarray ending with A[i];
        dp[0] = A[0];
        int max = dp[0];

        for (int i = 1; i < n; i++)
        {
            //相当于如果前一个序列不是正的，就不加上去了
            //关键就是找到递归方程（状态转移方程）
            dp[i] = A[i] + (Math.max(dp[i - 1], 0));
            max = Math.max(max, dp[i]);
        }

        return max;
    }


    public static void main(String[] args)
    {
        int[] array = {1, 2, -1, -2, 2, 1, -2, 1, 4, -5, 4};
        MaximumSubarray ms = new MaximumSubarray();
        System.out.println(ms.maxSubArray(array));
    }

}
