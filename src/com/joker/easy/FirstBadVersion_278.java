package com.joker.easy;

public class FirstBadVersion_278
{
    public int firstBadVersion(int n)
    {
        //二分
        long low = 0;
        long high = n;
        int result = n;
        while (low <= high)
        {
            if (isBadVersion((int) low))
                return (int) low;
            long mid = (low + high) / 2;
            //默认这种情况下，high一定是bad的
            if (isBadVersion((int) mid))
            {
                high = mid - 1;
            }
            else
            {
                low = mid + 1;
            }
            result = (int) low;
        }
        // 1 1 1 1 1 0 0
        return result;
    }

    public int firstBadVersion2(int n)
    {
        int left = 1;
        int right = n;
        while (left < right)
        {
            int mid = left + (right - left) / 2;
            if (isBadVersion(mid))
            {
                right = mid;
            }
            else
            {
                left = mid + 1;
            }
        }
        return left;
    }

    boolean isBadVersion(int version)
    {
        return true;
    }
}
