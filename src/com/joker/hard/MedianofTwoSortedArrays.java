package com.joker.hard;

import org.junit.Assert;

/**
 * This is LeetCode 4 Median of Two Sorted Arrays
 */
public class MedianofTwoSortedArrays
{
    /**
     * 使用类似归并排序的思路
     *
     * @param nums1 数组1
     * @param nums2 数组2
     * @return
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2)
    {
        //根据题目假定输入种至少一个数组非空
        if (nums1 == null)
        {
            return (nums2[(nums2.length - 1) / 2] + nums2[nums2.length / 2]) / 2.0;
        }
        else if (nums2 == null)
        {
            return (nums1[(nums1.length - 1) / 2] + nums1[nums1.length / 2]) / 2.0;
        }
        int median1 = 0;
        int median2 = 0;
        int size = nums1.length + nums2.length;
        int medIndex1 = (size - 1) / 2;
        int medIndex2 = size / 2;

        int i = 0, j = 0;
        while (i < nums1.length && j < nums2.length)
        {
            if (nums1[i] <= nums2[j])
            {
                if ((i + j) == medIndex1)
                {
                    median1 = nums1[i];
                }
                if ((i + j) == medIndex2)
                {
                    median2 = nums1[i];
                    return (median1 + median2) / 2.0;
                }
                i++;
            }
            else
            {
                if ((i + j) == medIndex1)
                {
                    median1 = nums2[j];
                }
                if ((i + j) == medIndex2)
                {
                    median2 = nums2[j];
                    return (median1 + median2) / 2.0;
                }
                j++;
            }
        }

        //已经遍历完其中一个数组了，并且还没有遍历到全部两个median
        if (i >= nums1.length)
        {
            i--;
            //分两种情况
            //(1)已经找到了median1
            //(2)两个median都没找到
            if ((i + j) == medIndex1)
            {
                //median2在nums2里
                median2 = nums2[j];
            }
            else
            {
                median1 = nums2[medIndex1 - nums1.length];
                median2 = nums2[medIndex2 - nums1.length];
            }
        }
        else
        {
            j--;
            if ((i + j) == medIndex1)
            {
                //median2在nums2里
                median2 = nums1[i];
            }
            else
            {
                median1 = nums1[medIndex1 - nums2.length];
                median2 = nums1[medIndex2 - nums2.length];
            }
        }
        return (median1 + median2) / 2.0;
    }

    /**
     * BS代表Binary Search，二分搜索
     * @param nums1
     * @param nums2
     * @return
     */
    public double findMedianSortedArraysBS(int[] nums1, int[] nums2)
    {
        int N1 = nums1.length;
        int N2 = nums2.length;
        //保证nums2的长度最小
        if (N1 < N2)
        {
            return findMedianSortedArrays(nums2, nums1);    // Make sure A2 is the shorter one.
        }
        int lo = 0, hi = N2 * 2;
        while (lo <= hi)
        {
            //nums2的划分位置
            int mid2 = (lo + hi) / 2;   // Try Cut 2
            //nums1的划分位置
            int mid1 = N1 + N2 - mid2;  // Calculate Cut 1 accordingly

            //如果出现一边元素为0的情况，就要以最大值最小值替代
            double L1 = (mid1 == 0) ? Integer.MIN_VALUE : nums1[(mid1 - 1) / 2];    // Get L1, R1, L2, R2 respectively
            double L2 = (mid2 == 0) ? Integer.MIN_VALUE : nums2[(mid2 - 1) / 2];
            double R1 = (mid1 == N1 * 2) ? Integer.MAX_VALUE : nums1[(mid1) / 2];
            double R2 = (mid2 == N2 * 2) ? Integer.MAX_VALUE : nums2[(mid2) / 2];

            if (L1 > R2)
            {
                lo = mid2 + 1;        // A1's lower half is too big; need to move C1 left (C2 right)
            }
            else if (L2 > R1)
            {
                hi = mid2 - 1;    // A2's lower half too big; need to move C2 left.
            }
            else
            {
                return (Math.max(L1, L2) + Math.min(R1, R2)) / 2;    // Otherwise, that's the right cut.
            }
        }
        return -1;
    }

    public static void main(String[] args)
    {
        // 2.0
        int[] nums1 = {1, 3};
        int[] nums2 = {2};

        // 2.5
        int[] nums3 = {1, 2};
        int[] nums4 = {3, 4};

        // 8.5
        int[] nums5 = {1, 2, 3, 4};
        int[] nums6 = {7, 8, 9, 10, 11, 12, 13, 14};

        // 8
        int[] nums7 = {1, 2, 3, 4};
        int[] nums8 = {7, 8, 9, 10, 11, 12, 13};

        // 10
        int[] nums9 = {1, 5, 10, 15};
        int[] nums10 = {7, 8, 9, 10, 11, 12, 13, 14};

        MedianofTwoSortedArrays med = new MedianofTwoSortedArrays();
        Assert.assertEquals(2.0, med.findMedianSortedArraysBS(nums1, nums2), 0.0001);
        Assert.assertEquals(2.5, med.findMedianSortedArraysBS(nums3, nums4), 0.0001);
        Assert.assertEquals(8.5, med.findMedianSortedArraysBS(nums5, nums6), 0.0001);
        Assert.assertEquals(8.0, med.findMedianSortedArraysBS(nums7, nums8), 0.0001);
        Assert.assertEquals(10.0, med.findMedianSortedArraysBS(nums9, nums10), 0.0001);

    }
}
