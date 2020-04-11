package com.joker.medium;

public class LongestPalindromicSubsequence
{
    /**
     * 这道题目的核心就是找到状态转移方程。
     * 也就是
     * dp[i][j] = dp[i + 1][j - 1] if s.charAt(i) == s.charAt(j)
     * dp[i][j] = Max(dp[i + 1][j], dp[i][j - 1])
     * @param s 输入字符串
     * @return 最大回文子串长度
     */
    public int longestPalindromeSubseq(String s)
    {
        if (s.length() == 0)
            return 0;
        int[][] dp = new int[s.length()][s.length()];
        int maximum = 0;
        for (int length = 1; length <= s.length(); length++)
        {
            for (int i = 0; i < s.length() - length + 1; i++)
            {
                //length = length + 1
                int j = i + length - 1;
                if (length == 1)
                    dp[i][j] = 1;
                else if (s.charAt(i) == s.charAt(j))
                {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                }
                else
                {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
                if (dp[i][j] > maximum)
                    maximum = dp[i][j];
            }
        }
        return maximum;
    }

    public int longestPalindromeSubseq2(String s)
    {
        int[][] dp = new int[s.length()][s.length()];

        for (int i = s.length() - 1; i >= 0; i--)
        {
            dp[i][i] = 1;
            for (int j = i + 1; j < s.length(); j++)
            {
                if (s.charAt(i) == s.charAt(j))
                {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                }
                else
                {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[0][s.length() - 1];
    }
}
