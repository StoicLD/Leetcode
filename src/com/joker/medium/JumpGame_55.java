package com.joker.medium;

public class JumpGame_55
{
    /**
     * O(n2)的做法
     * @param nums
     * @return
     */
    public boolean canJump(int[] nums)
    {
        if(nums == null || nums.length == 0)
            return false;
        int n = nums.length;
        boolean[] dp = new boolean[n];
        dp[n - 1] = true;
        for(int i = n - 2; i >= 0; i--)
        {
            int length = Math.min(n - 1, i + nums[i]);
            for(int j = i + 1; j <= length; j++)
            {
                if(dp[j])
                {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[0];
    }

    /**
     * O(N)的DP算法
     * @param nums
     * @return
     */
    public boolean canJump2(int[] nums)
    {
        if(nums == null || nums.length <= 1)
            return true;
        int n = nums.length;
        int[] dp = new int[n];      //存满足可达路径的最小值
        dp[n - 1] = 1;
        for(int i = n - 2; i >= 0; i--)
        {
            if(nums[i] >= dp[i + 1])
                dp[i] = 1;
            else
                dp[i] = dp[i + 1] + 1;
        }
        return nums[0] >= dp[1];
    }

    /**
     * 只需要 O(N)时间和 O(1)空间
     * @param nums
     * @return
     */
    public boolean canJump3(int[] nums)
    {
        if(nums == null || nums.length <= 1)
            return true;
        int n = nums.length;
        int lastMin = 1;
        for(int i = n - 2; i >= 0; i--)
        {
            if(nums[i] >= lastMin)
                lastMin = 1;
            else
                lastMin += 1;
        }
        return lastMin == 1;
    }
}
