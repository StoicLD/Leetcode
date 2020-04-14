package com.joker.medium;

public class ContiguousArray
{
    /**
     * 一个 O(N2) 的方法
     *
     * @param nums
     * @return
     */
    public int findMaxLength(int[] nums)
    {
        if (nums == null || nums.length == 0)
            return 0;
        //dp[i]表示起点为i，长度为length（一个变量）的子串的偏差值
        //偏差值 = 0表示1和0相等， > 0表示1多，小于0表示0多
        int[] dp = new int[nums.length];
        int result = 0;
        for (int i = 0; i < nums.length; i++)
        {
            dp[i] = nums[i] == 0 ? -1 : 1;
        }
        for (int length = 2; length <= nums.length; length++)
        {
            for (int i = 0; i < nums.length - length + 1; i++)
            {
                dp[i] = dp[i] + (nums[i + length - 1] == 0 ? -1 : 1);
                if (dp[i] == 0 && result < length)
                    result = length;
            }
        }
        return result;
    }

    /*    public int findMaxLength2(int[] nums)
    {
        if (nums == null || nums.length == 0)
            return 0;
        int[] dp = new int[nums.length];
        dp[0] = 0;
        int result = 0;
        for (int i = 1; i < nums.length; i++)
        {
            //不同的时候，要么i，i-1结合。要么与前一段的末尾结合
            int preIndex = i - dp[i - 1] - 1;
            int skip = 0;
            int nonSkip = 0;
            //non-skip, i 和 i - 1结合
            if(nums[i] != nums[i - 1])
            {
                nonSkip = 2;
                if(i - 2 >= 0)
                    nonSkip += dp[i - 2];
            }
            if(preIndex >= 0 && nums[preIndex] != nums[i])
            {
                skip = 2 + dp[i - 1];
                if(preIndex - 1 >= 0)
                    skip += dp[preIndex - 1];
            }
            dp[i] = Math.max(nonSkip, skip);
            result = Math.max(result, dp[i]);
        }
        return result;
    }*/


    public static void main(String[] args)
    {
        int[] a = {1, 0, 1, 1, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 1};
        ContiguousArray ca = new ContiguousArray();
        //System.out.println(ca.findMaxLength2(a));
    }
}
