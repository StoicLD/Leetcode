/**
 * 这个包是专门应对四月份的挑战题目的
 */
package com.joker.easy;

public class SingleNumber
{
    /**
     * 必须要用O(N)的时间复杂度和 O(1)的空间复杂度解决
     * @param nums
     * @return
     */
    public int singleNumber(int[] nums)
    {
        //完全没想到居然可以用异或来解决
        //并且异或是符合交换律的
        int result = 0;
        for (int i = 0; i<nums.length; i++)
        {
            result ^=nums[i];
        }
        return result;
    }
}