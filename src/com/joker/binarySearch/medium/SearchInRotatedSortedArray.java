package com.joker.binarySearch.medium;


/**
 * This is for leetCode 33 Search in Rotated Sorted Array
 */
public class SearchInRotatedSortedArray
{
    public int search(int[] nums, int target)
    {
        if(nums.length == 0)
            return -1;
        int low = 0;
        int high = nums.length - 1;
        while (low <= high)
        {
            int mid = (low + high) / 2;
            if(nums[mid] == target)
                return mid;
            if(low == high)
                return -1;
            //至少有两个元素，low < high
            //保证没有元素越界 针对mid + 1
            int LL = low;
            int LR = mid;
            int RL = mid + 1;
            int RR = high;
            if(nums[LL] <= nums[LR])
            {
                if(nums[LL] <= target && target <= nums[LR])
                {
                    low = LL;       //为了保持整齐
                    high = LR;
                }
                else
                {
                    low = RL;
                    high = RR;
                }
            }
            else
            {
                if(nums[RL] <= target && target <= nums[RR])
                {
                    low = RL;
                    high = RR;
                }
                else
                {
                    low = LL;
                    high = LR;
                }
            }
        }
        return -1;
    }

    public int search2(int[] nums, int target)
    {
        if(nums.length == 0)
            return -1;
        int low = 0;
        int high = nums.length - 1;
        while (low <= high)
        {
            int mid = (low + high) / 2;
            if(nums[mid] == target)
                return mid;
            if(low == high)
                return -1;
            //至少有两个元素，low < high
            //保证没有元素越界 针对mid + 1
            if(nums[low] <= nums[mid])
            {
                if(nums[low] <= target && target <= nums[mid])
                {
                    high = mid;
                }
                else
                {
                    low = mid + 1;
                }
            }
            else
            {
                if(nums[mid + 1] <= target && target <= nums[high])
                {
                    low = mid + 1;
                }
                else
                {
                    high = mid;
                }
            }
        }
        return -1;
    }

    int searchOthers1(int A[], int target) {
        int n = A.length;
        int lo=0,hi=n-1;
        // find the index of the smallest value using binary search.
        // Loop will terminate since mid < hi, and lo or hi will shrink by at least 1.
        // Proof by contradiction that mid < hi: if mid==hi, then lo==hi and loop would have been terminated.
        while(lo<hi){
            int mid=(lo+hi)/2;
            if(A[mid]>A[hi]) lo=mid+1;
            else hi=mid;
        }
        // lo==hi is the index of the smallest value and also the number of places rotated.
        int rot=lo;
        lo=0;hi=n-1;
        // The usual binary search and accounting for rotation.
        while(lo<=hi){
            int mid=(lo+hi)/2;
            int realmid=(mid+rot)%n;
            if(A[realmid]==target)return realmid;
            if(A[realmid]<target)lo=mid+1;
            else hi=mid-1;
        }
        return -1;
    }

    public static void main(String[] args)
    {
        SearchInRotatedSortedArray  sirs = new SearchInRotatedSortedArray();
        int result1 = sirs.search2(new int[]{4,5,6,7,0,1,2,3}, 3);
        int result2 = sirs.search2(new int[]{4,5,6,7,0,1,2,3}, 0);
        int result3 = sirs.search2(new int[]{4,5,6,7,0,1,2,3}, 4);
        int result4 = sirs.search2(new int[]{4,5,6,7,0,1,2,3}, 6);

        System.out.println(result1);
        System.out.println(result2);
        System.out.println(result3);
        System.out.println(result4);
    }
}
