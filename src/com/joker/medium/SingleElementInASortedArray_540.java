package com.joker.medium;

import org.junit.Assert;

public class SingleElementInASortedArray_540
{
    public int singleNonDuplicate(int[] nums)
    {
        int low = 0;
        int high = nums.length - 1;
        while (low <= high)
        {
            if(low == high)
                break;
            int mid = low + (high - low) / 2;
            if(nums[mid - 1] == nums[mid])
            {
                if((mid - low - 1) % 2 == 1)
                    high = mid - 2;
                else
                    low = mid + 1;
            }
            else if(nums[mid + 1] == nums[mid])
            {
                if((mid - low) % 2 == 1)
                    high = mid - 1;
                else
                    low = mid + 2;
            }
            else
                return nums[mid];
        }
        return nums[low];
    }

    public static void main(String[] args)
    {
        int[] nums1 = new int[]{1,1,2};
        int[] nums2 = new int[]{1,1,2,2,3,3,4,4,5};
        int[] nums3 = new int[]{0,1,1,2,2,3,3};
        int[] nums4 = new int[]{1};
        int[] nums5 = new int[]{1,1,2,2,3,4,4,5,5,6,6,7,7,8,8,9,9};

        SingleElementInASortedArray_540 se = new SingleElementInASortedArray_540();
        Assert.assertEquals(2, se.singleNonDuplicate(nums1));
        Assert.assertEquals(5, se.singleNonDuplicate(nums2));
        Assert.assertEquals(0, se.singleNonDuplicate(nums3));
        Assert.assertEquals(1, se.singleNonDuplicate(nums4));
        Assert.assertEquals(3, se.singleNonDuplicate(nums5));

    }

}
