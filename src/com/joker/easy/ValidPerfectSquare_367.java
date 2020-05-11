package com.joker.easy;

public class ValidPerfectSquare_367
{
    public boolean isPerfectSquare(int num)
    {
        // 16, 25, 91, 12301
        // 直接二分法
        if(num == 1 || num == 4)
            return true;
        long low = 1;
        long high = num / 2;
        while (low <= high)
        {
            long mid = (low + high) / 2;
            long currSqure = mid * mid;
            if(currSqure == num)
                return true;
            else if(currSqure > num)
                high = mid - 1;
            else
                low = mid + 1;
        }
        return false;
    }
}
