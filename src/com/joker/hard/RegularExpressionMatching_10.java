package com.joker.hard;

import org.junit.Assert;

public class RegularExpressionMatching_10
{
    public boolean isMatch(String s, String p)
    {
        // "a*" 这样的输入默认是不存在的
        int i = s.length() - 1, j = p.length() - 1;
        for (; i >= 0 && j >= 0; )
        {
            if (p.charAt(j) == '*')
            {
                j--;
                if (j < 0)
                    throw new IllegalArgumentException();
                char pChar = p.charAt(j);
                char sChar = s.charAt(i);
                if (pChar == '.')
                    pChar = sChar;
                if (pChar != sChar)
                {
                    i++;
                    continue;
                }
                int pCount = -1;
                int sCount = 0;
                while (i >= 0 && s.charAt(i) == sChar)
                {
                    sCount++;
                    i--;
                }
                while (j >= 0 && p.charAt(j) == sChar)
                {
                    pCount++;
                    j--;
                }
                if (sCount < pCount)
                    return false;
            }
            else if (s.charAt(i) != p.charAt(j) && p.charAt(j) != '.')
            {
                return false;
            }
            else
            {
                i--;
                j--;
            }
        }
        //尾部处理
        if (i >= 0)
            return false;
        while (j >= 0 && p.charAt(j) == '*')
        {
            j -= 2;
        }
        if (j >= 0)
            return false;
        return true;
    }

    /* 方法二
    * 这类问题先想一个暴力解法。
    * 如果使用DP，实际上就是在暴力解的基础上，看问题是否具有最优子结构。
    * 然后把问题的重复部分用一个memo记录下来，从而达到用空间换时间的效果
    * 暴力的解法：
    *   当前一个pattern字符与当前一个text字符match（这个pattern字符不能是'*'）
    *   当前两个pattern字符不与当前text字符match（这个pattern字符为任意字符 + '*' 字符，也就是跳过
    *   当前两个pattern字符与当前text字符match（继续使用当前的pattern字符）
    *   dp(i, j)表示当前text字符位置是i，当前pattern位置是j
    */
    enum Result
    {
        TRUE, FALSE
    }

    Result[][] memo;

    public boolean isMatch2(String text, String pattern)
    {
        memo = new Result[text.length() + 1][pattern.length() + 1];
        return dp(0, 0, text, pattern);
    }

    public boolean dp(int i, int j, String text, String pattern)
    {
        if (memo[i][j] != null)
        {
            return memo[i][j] == Result.TRUE;
        }
        boolean ans;
        if (j == pattern.length())
        {
            ans = i == text.length();
        }
        else
        {
            //first_match指直接的match，不带星号的match
            boolean first_match = (i < text.length() &&
                    (pattern.charAt(j) == text.charAt(i) || pattern.charAt(j) == '.'));

            if (j + 1 < pattern.length() && pattern.charAt(j + 1) == '*')
            {
                //dp(i, j + 2)表示跳过当前带*号的2元pattern
                //do(i + 1, j)表示当前带*号的匹配1个s的字符（位于i）
                ans = (dp(i, j + 2, text, pattern) ||
                        first_match && dp(i + 1, j, text, pattern));
            }
            else
            {
                //不带星号，匹配下一个。注意这里的&&顺序，如果first_match已经是false了就不会继续递归了
                ans = first_match && dp(i + 1, j + 1, text, pattern);
            }
        }
        memo[i][j] = ans ? Result.TRUE : Result.FALSE;
        return ans;
    }

    public static void main(String[] args)
    {
        String s1 = "aab";
        String p1 = "c*a*b";
        RegularExpressionMatching_10 rem = new RegularExpressionMatching_10();
        Assert.assertTrue(rem.isMatch(s1, p1));

        String s2 = "aab";
        String p2 = "b*a*c*a*b";


        String s3 = "aab";
        String p3 = ".*";


        String s4 = "aab";
        String p4 = ".*";
    }

}
