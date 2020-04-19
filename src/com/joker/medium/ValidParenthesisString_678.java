package com.joker.medium;

public class ValidParenthesisString_678
{
    /**
     * 一种暴力的肯定会超时的想法
     * @param s
     * @return
     */
    public boolean checkValidString(String s)
    {
        return checkValidStringHelper(0, 0, 0, s);
    }

    public boolean checkValidStringHelper(int i, int left, int right, String s)
    {
        if(s == null || s.length() == 0)
            return true;
        if(i >= s.length())
            return left == right;
        if(left < right)
            return false;
        if(s.charAt(i) == '(')
            return checkValidStringHelper(i + 1, left + 1, right, s);
        else if(s.charAt(i) == ')')
            return checkValidStringHelper(i + 1, left, right + 1, s);
        else
        {
            //默认这种情况下是 '*'
            return checkValidStringHelper(i + 1, left, right + 1, s)
                    || checkValidStringHelper(i + 1, left + 1, right, s)
                    || checkValidStringHelper(i + 1, left, right, s);
        }
    }

    /**
     * NB的贪心方法，O(N)时间复杂度
     * @param s
     * @return
     */
    public boolean checkValidStringGreedy(String s)
    {
        int low = 0;
        int high = 0;
        // "(())((())()()( *)(* ()(())())())()()((()())((()))(*"
        // (2, 2) -> (1, 3) -> (0, 4) -> (-2, 2)
        for(char c : s.toCharArray())
        {
            if(c == '(')
            {
                low++;
                high++;
            }
            else if(c == ')')
            {
                low--;
                high--;
            }
            else
            {
                low--;
                high++;
            }
            if(high < 0)
                return false;
            low = Math.max(0, low);
        }
        return low == 0;
    }


}
