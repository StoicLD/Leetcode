package com.joker.hard;

import java.util.Stack;

/**
 * This is for LeetCode 84 Largest Rectangle in Histogram
 */
public class LargestRectangleInHistogram
{
    public int largestRectangleArea(int[] heights)
    {
        if (heights.length == 0)
        {
            return 0;
        }
        int largestArea = 0;
        for (int i = 0; i < heights.length; i++)
        {
            int tempArea = heights[i];
            int currentHeight = heights[i];
            for (int j = i + 1; j < heights.length; j++)
            {
                if (heights[j] < currentHeight)
                {
                    if (tempArea > largestArea)
                    {
                        largestArea = tempArea;
                    }
                    tempArea = (j - i + 1) * heights[j];
                    currentHeight = heights[j];
                }
                else
                {
                    tempArea += currentHeight;
                }
            }
            if (tempArea > largestArea)
            {
                largestArea = tempArea;
            }
        }
        return largestArea;
    }


    /**
     * 剪切掉了部分情况
     *
     * @param heights
     * @return
     */
    public int largestRectangleArea2(int[] heights)
    {
        if (heights.length == 0)
        {
            return 0;
        }
        int largestArea = 0;
        for (int i = 0; i < heights.length; i++)
        {
            int tempArea = heights[i];
            int currentHeight = heights[i];
            if (i == 0 || heights[i - 1] <= heights[i])
            {
                for (int j = i + 1; j < heights.length; j++)
                {
                    if (heights[j] < currentHeight)
                    {
                        if (tempArea > largestArea)
                        {
                            largestArea = tempArea;
                        }
                        tempArea = (j - i + 1) * heights[j];
                        currentHeight = heights[j];
                    }
                    else
                    {
                        tempArea += currentHeight;
                    }
                }
            }
            if (tempArea > largestArea)
            {
                largestArea = tempArea;
            }
        }
        return largestArea;
    }

    /**
     * DP解法，很强。
     * 两个步骤，一个是拆分，一个是求解
     *
     * @param height
     * @return
     */
    public static int largestRectangleAreaDP(int[] height)
    {
        if (height == null || height.length == 0)
        {
            return 0;
        }
        int[] lessFromLeft = new int[height.length]; // idx of the first bar the left that is lower than current
        int[] lessFromRight = new int[height.length]; // idx of the first bar the right that is lower than current
        lessFromRight[height.length - 1] = height.length;
        lessFromLeft[0] = -1;

        for (int i = 1; i < height.length; i++)
        {
            int p = i - 1;

            while (p >= 0 && height[p] >= height[i])
            {
                p = lessFromLeft[p];
            }
            lessFromLeft[i] = p;
        }

        for (int i = height.length - 2; i >= 0; i--)
        {
            int p = i + 1;

            while (p < height.length && height[p] >= height[i])
            {
                p = lessFromRight[p];
            }
            lessFromRight[i] = p;
        }

        int maxArea = 0;
        for (int i = 0; i < height.length; i++)
        {
            maxArea = Math.max(maxArea, height[i] * (lessFromRight[i] - lessFromLeft[i] - 1));
        }

        return maxArea;
    }


    // The main function to find the maximum rectangular area under given
    // histogram with n bars
    public static int largestRectangleAreaStack(int hist[], int n)
    {
        // Create an empty stack. The stack holds indexes of hist[] array
        // The bars stored in stack are always in increasing order of their
        // heights.
        Stack<Integer> s = new Stack<>();

        int max_area = 0; // Initialize max area
        int tp; // To store top of stack
        int area_with_top; // To store area with top bar as the smallest bar

        // Run through all bars of given histogram
        int i = 0;
        while (i < n)
        {
            // If this bar is higher than the bar on top stack, push it to stack
            if (s.empty() || hist[s.peek()] <= hist[i])
            {
                s.push(i++);
            }

            // If this bar is lower than top of stack, then calculate area of rectangle
            // with stack top as the smallest (or minimum height) bar. 'i' is
            // 'right index' for the top and element before top in stack is 'left index'
            else
            {
                tp = s.peek(); // store the top index
                s.pop(); // pop the top

                // Calculate the area with hist[tp] stack as smallest bar
                //这一步是神来之笔，尤其是当判定stack为空的时候，直接呈上至今为止的宽度
                area_with_top = hist[tp] * (s.empty() ? i : i - s.peek() - 1);

                // update max area, if needed
                if (max_area < area_with_top)
                {
                    max_area = area_with_top;
                }
            }
        }

        // Now pop the remaining bars from stack and calculate area with every
        // popped bar as the smallest bar
        while (s.empty() == false)
        {
            tp = s.peek();
            s.pop();
            area_with_top = hist[tp] * (s.empty() ? i : i - s.peek() - 1);

            if (max_area < area_with_top)
            {
                max_area = area_with_top;
            }
        }

        return max_area;

    }

}
