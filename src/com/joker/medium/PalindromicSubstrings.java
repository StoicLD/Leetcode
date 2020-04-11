package com.joker.medium;

public class PalindromicSubstrings
{
    public int countSubstrings(String s)
    {
        int count = 0;
        boolean[][] valid = new boolean[s.length()][s.length()];
        for (int length = 1; length <= s.length(); length++)
        {
            for (int j = 0; j < s.length() - length + 1; j++)
            {
                if (length == 1)
                    valid[j][j + length - 1] = true;
                else
                {
                    boolean equalChar = s.charAt(j) == s.charAt(j + length - 1);
                    if (length == 2)
                        valid[j][j + length - 1] = equalChar;
                    else
                        valid[j][j + length - 1] = equalChar && valid[j + 1][j + length - 2];
                }
                if (valid[j][j + length - 1])
                    count++;
            }
        }
        return count;
    }

    /**
     * 第二种方法，中心扩展
     * 我之前想了这个方法，然而有一点很关键的是
     * 从不同中心扩展的字符串是不等价的！！！
     * 我之前还想着不同中心扩展，可能起点和终点一样
     * @param S
     * @return
     */
    public int countSubstrings2(String S)
    {
        int N = S.length(), ans = 0;
        for (int center = 0; center <= 2 * N - 1; ++center)
        {
            int left = center / 2;
            int right = left + center % 2;
            while (left >= 0 && right < N && S.charAt(left) == S.charAt(right))
            {
                ans++;
                left--;
                right++;
            }
        }
        return ans;
    }


/*    public boolean extendFromSides(int begin, int end, String s)
    {
        int left = begin, right = end;
        while (left <= right)
        {
            if(s.charAt(begin) != s.charAt(end))
                return false;
            left++;
            right--;
        }
        return true;
    }*/
}
