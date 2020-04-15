package com.joker.medium;

public class ProductOfArrayExceptSelf_238
{
    public int[] productExceptSelf(int[] nums)
    {
        //遍历两遍
        int[] result = new int[nums.length];
        result[result.length - 1] = 1;
        for(int i = nums.length - 2; i >= 0; i--)
        {
            result[i] = result[i + 1] * nums[i + 1];
        }
        int product = 1;
        for(int j = 1; j < nums.length; j++)
        {
            result[j] = result[j] * product;
            product *= nums[j];
        }
        return result;
    }
}
