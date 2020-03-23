package com.joker.binarySearch.medium;

/**
 * This is for leetCode 34 Find First and Last Position of Element in Sorted Array
 */
public class FindFirstAndLastPositionOfElementInSortedArray
{
    public int[] searchRange(int[] nums, int target)
    {
        if(nums == null || nums.length == 0 || nums[0] > target || nums[nums.length - 1] < target)
            return new int[]{-1,-1};
        //找两次，先找start，再找end
        int start = -1;
        int end = -1;
        int low = 0;
        int high = nums.length - 1;
        while (low <= high)
        {
            int mid = (low + high) / 2;
            if(nums[low] == target)
            {
                start = low;
                break;
            }
            if(nums[mid] == target)
            {
                start = mid;
                high = mid - 1;
            }
            else if(nums[mid] < target)
            {
                low = mid + 1;
            }
            else
            {
                high = mid - 1;
            }
        }

        //第二遍，找end
        low = start;
        high = nums.length - 1;
        while (low <= high && low > -1)
        {
            int mid = (low + high) / 2;
            if(nums[high] == target)
            {
                end = high;
                break;
            }
            if(nums[mid] == target)
            {
                end = mid;
                low = mid + 1;
            }
            else if(nums[mid] < target)
            {
                low = mid + 1;
            }
            else
            {
                high = mid - 1;
            }
        }
        return new int[]{start, end};
    }

    public static void main(String[] args)
    {

    }
}
