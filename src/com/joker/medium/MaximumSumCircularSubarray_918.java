package com.joker.medium;

public class MaximumSumCircularSubarray_918
{
    /**
     * O(N)时间和空间复杂度的一种做法
     * 基本思路我是想到了，但是细节查了很多，还是要好好的看一下算法导论
     * 首先是最大子数组算法需要直到
     *
     * @param A 输入数组
     * @return 最大值
     */
    public int maxSubarraySumCircular1(int[] A)
    {
        int N = A.length;
        //获取最大值
        int ans = A[0], cur = A[0];
        for (int i = 1; i < N; ++i)
        {
            cur = A[i] + Math.max(cur, 0);
            ans = Math.max(ans, cur);
        }

        // ans is the answer for 1-interval subarrays.
        // Now, let's consider all 2-interval subarrays.
        // For each i, we want to know
        // the maximum of sum(A[j:]) with j >= i+2

        // rightsums[i] = A[i] + A[i+1] + ... + A[N-1]
        //rightsump[i] 是从i 累加到 N - 1的全部值
        int[] rightsums = new int[N];
        rightsums[N - 1] = A[N - 1];
        for (int i = N - 2; i >= 0; --i)
            rightsums[i] = rightsums[i + 1] + A[i];

        // maxright[i] = max_{j >= i} rightsums[j]
        // maxright[i] 是从i开始到N - 1的区间中最大子数组的值
        int[] maxright = new int[N];
        maxright[N - 1] = A[N - 1];
        for (int i = N - 2; i >= 0; --i)
            maxright[i] = Math.max(maxright[i + 1], rightsums[i]);

        //从左侧开始累加的值，
        //              1,   3, -10, -2, 20
        //maxright      20, 20, 20,  20, 20
        //leftsum       1,   4,  -6, -8, 12
        //ans = 20

        //              1,   3, -10, -2, 10
        //maxright      10, 10, 10,  10, 10
        //leftsum       1,   4,  -6, -8,  4
        //ans = 10
        int leftsum = 0;
        for (int i = 0; i < N - 2; ++i)
        {
            leftsum += A[i];
            //这里的疑问是为什么要i + 2
            ans = Math.max(ans, leftsum + maxright[i + 2]);
        }

        return ans;
    }
}
