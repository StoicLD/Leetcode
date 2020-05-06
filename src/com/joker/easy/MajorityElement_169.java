package com.joker.easy;

import java.util.HashMap;
import java.util.Iterator;

public class MajorityElement_169
{
    public int majorityElement(int[] nums)
    {
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i : nums)
        {
            map.put(i, map.getOrDefault(i, 0) + 1);
            if(map.get(i) >= (nums.length + 1) / 2)
                return i;
        }
        return 0;
    }

    /**
     * 一种分治的方法
     * 递归的把数组分为两部分，分别统计两部分中的主元素
     * （除了最开始的那个函数，后续的主元素实际上不见得是主元素）
     * 比如说 1,2,3,4 , 4,4,4,4
     * 左边的部分递归的时候，返回的几个数字实际上都算不上主元素
     * @param nums 数组
     * @return 主元素
     */
    public int majorityElement2(int[] nums) {
        return majorityElementRec(nums, 0, nums.length-1);
    }
    private int countInRange(int[] nums, int num, int lo, int hi) {
        int count = 0;
        for (int i = lo; i <= hi; i++) {
            if (nums[i] == num) {
                count++;
            }
        }
        return count;
    }

    private int majorityElementRec(int[] nums, int lo, int hi) {
        // base case; the only element in an array of size 1 is the majority
        // element.
        if (lo == hi) {
            return nums[lo];
        }

        // recurse on left and right halves of this slice.
        int mid = (hi-lo)/2 + lo;
        int left = majorityElementRec(nums, lo, mid);
        int right = majorityElementRec(nums, mid+1, hi);

        // if the two halves agree on the majority element, return it.
        if (left == right) {
            return left;
        }

        // otherwise, count each element and return the "winner".
        int leftCount = countInRange(nums, left, lo, hi);
        int rightCount = countInRange(nums, right, lo, hi);

        return leftCount > rightCount ? left : right;
    }

    /**
     * O(N)时间复杂度，O(1)空间复杂度的最优算法
     * @param nums 输入数组
     * @return 主元素
     */
    public int majorityElement3(int[] nums) {
        int count = 0;
        Integer candidate = null;

        for (int num : nums) {
            if (count == 0) {
                candidate = num;
            }
            count += (num == candidate) ? 1 : -1;
        }

        return candidate;
    }


}
