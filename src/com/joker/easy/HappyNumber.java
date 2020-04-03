package com.joker.easy;

import java.util.HashSet;
import java.util.Set;

public class HappyNumber
{
    public boolean isHappy(int n)
    {
        Set<Integer> set = new HashSet<>();
        int result = n;
        while (result != 1)
        {
            int nextNumber = 0;
            int currNumber;
            while (result != 0)
            {
                currNumber = result % 10;
                nextNumber += currNumber * currNumber;
                result /= 10;
            }
            result = nextNumber;
            if (set.contains(result))
                break;
            set.add(result);
        }
        if (result == 1)
            return true;
        return false;
    }

    /**
     * 第二种思路，使用类似于链表中龟兔赛跑的那个思路
     * 我们设置两个指针位置，slow是正常的一次进一步，fast则是一次进两步
     * 如果存在循环，比方说 n 步一个循环，那么当slow走到 n/2 的时候
     * fast将会到达 n， 这样判断一下就知道循环了，可以避免使用额外的空间
     * @param n
     * @return
     */
    boolean isHappy2(int n)
    {
        int slow, fast;
        slow = fast = n;
        do
        {
            slow = digitSquareSum(slow);
            fast = digitSquareSum(fast);
            fast = digitSquareSum(fast);
            if (fast == 1)
                return true;
        } while (slow != fast);
        return false;
    }

    int digitSquareSum(int n)
    {
        int sum = 0, tmp;
        while (n != 0)
        {
            tmp = n % 10;
            sum += tmp * tmp;
            n /= 10;
        }
        return sum;
    }
}
