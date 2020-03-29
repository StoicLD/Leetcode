package com.joker.hard;

import java.util.Stack;

/**
 * This is for LeetCode 42
 */
public class TrappingRainWater
{
    public int trap(int[] height)
    {
        if (height.length == 0)
        {
            return 0;
        }
        Stack<Integer> stack = new Stack<>();
        int begin = 0;
        int waterSum = 0;
        int i = 0;
        for (; i < height.length; i++)
        {
            if (height[i] > 0)
            {
                begin = i;
                break;
            }
        }

        for (i += 1; i < height.length; i++)
        {
            if (height[i] >= height[begin])
            {
                waterSum += height[begin] * (i - begin - 1);
                while (!stack.empty())
                {
                    waterSum -= height[stack.pop()];
                }
                begin = i;
            }
            else
            {
                stack.push(i);
            }
        }

        //回倒
        if (begin != height.length - 1)
        {
            int end = height.length - 1;
            stack.pop();
            for (int j = end - 1; j >= begin; j--)
            {
                if (height[j] >= height[end])
                {
                    waterSum += height[end] * (end - j - 1);
                    while (!stack.empty() && stack.peek() != j)
                    {
                        waterSum -= height[stack.pop()];
                    }
                    if (!stack.empty())
                    {
                        stack.pop();
                    }
                    end = j;
                }
            }
        }
        return waterSum;
    }

    /**
     * 最原始最暴力的方法
     * @param height
     * @return
     */
    public int trapSlow(int[] height)
    {
        if(height.length == 0)
            return 0;
        int waterSum = 0;
        //针对每个点都往左找最大值，往右找最大值
        //然后取左右最大值两者中的最小值，减去当前高度，就是当前位置的water容量
        for(int i = 0; i < height.length; i++)
        {
            int leftMax = 0;
            int rightMax = 0;
            for(int j = i; j >= 0; j--)
            {
                if(leftMax < height[j])
                    leftMax = height[j];
            }

            for(int j = i; j < height.length; j++)
            {
                if(rightMax < height[j])
                    rightMax = height[j];
            }
            waterSum += Math.min(leftMax, rightMax) - height[i];
        }
        return waterSum;
    }

    int trapStack(int[] height)
    {
        int ans = 0, current = 0;
        Stack<Integer> st = new Stack<>();
        while (current < height.length)
        {
            while (!st.empty() && height[current] > height[st.peek()])
            {
                int peek = st.peek();
                st.pop();
                if (st.empty())
                    break;
                int distance = current - st.peek() - 1;
                //这里的计算模式很有意思，需要仔细平常
                //每一级都是阶梯差，总和是对的
                int bounded_height = Math.min(height[current], height[st.peek()]) - height[peek];
                ans += distance * bounded_height;
            }
            st.push(current++);
        }
        return ans;
    }

    /**
     * 采用左右两个指针的方法
     * @param A 输入数组
     * @return
     */
    public int trapOthers(int A[])
    {
        int left = 0;
        int right = A.length - 1;
        int res = 0;
        int maxleft = 0, maxright = 0;
        while (left <= right)
        {
            if (A[left] <= A[right])
            {
                if (A[left] >= maxleft)
                {
                    maxleft = A[left];
                }
                else
                {
                    res += maxleft - A[left];
                }
                left++;
            }
            else
            {
                if (A[right] >= maxright)
                {
                    maxright = A[right];
                }
                else
                {
                    res += maxright - A[right];
                }
                right--;
            }
        }
        return res;
    }

    /**
     * 采用DP的做法，很机智
     * 时间复杂度是O(n),空间复杂度是O(n)
     * @param height
     * @return
     */
    public int trapDP(int[] height)
    {
        if(height == null)
            return 0;
        int ans = 0;
        int size = height.length;
        int[] left_max = new int[size];
        int[] right_max = new int[size];
        left_max[0] = height[0];
        for (int i = 1; i < size; i++) {
            left_max[i] = Math.max(height[i], left_max[i - 1]);
        }
        right_max[size - 1] = height[size - 1];
        for (int i = size - 2; i >= 0; i--) {
            right_max[i] = Math.max(height[i], right_max[i + 1]);
        }
        for (int i = 1; i < size - 1; i++) {
            ans += Math.max(left_max[i], right_max[i]) - height[i];
        }
        return ans;
    }
}
