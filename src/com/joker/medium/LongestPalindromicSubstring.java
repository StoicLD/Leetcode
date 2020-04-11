package com.joker.medium;

public class LongestPalindromicSubstring
{
    private int[][] allValid;

    public String longestPalindrome(String s)
    {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < s.length(); i++)
        {
            StringBuilder str1 = new StringBuilder();
            //i - 1, i + 1为对称起点向两侧扩散
            str1.append(s.charAt(i));
            int j = i - 1;
            int k = i + 1;
            for (; j >= 0 && k < s.length(); j--, k++)
            {
                if (s.charAt(j) == s.charAt(k))
                {
                    str1.append(s.charAt(k));
                    str1.insert(0, s.charAt(j));
                }
                else
                    break;
            }

            //i - 1, i 为对称起点向两侧扩散
            StringBuilder str2 = new StringBuilder();
            //i - 1, i + 1为对称起点向两侧扩散
            k = i;
            j = i - 1;
            for (; j >= 0 && k < s.length(); j--, k++)
            {
                if (s.charAt(j) == s.charAt(k))
                {
                    str2.append(s.charAt(k));
                    str2.insert(0, s.charAt(j));
                }
                else
                    break;
            }
            if (str1.length() > str2.length() && str1.length() > result.length())
                result = str1;
            else if (str2.length() >= str1.length() && str2.length() > result.length())
                result = str2;
        }
        return result.toString();
    }

    /**
     * 一种DP的方法，如果说 f(i,j)指的是以下标i为起点，下表j为终点的字符串是否为回文串
     * 那么 f(i,j) = f(i + 1, j - 1) && s.charAt(i) == s.charAt(j)
     * @param s 输入字符串
     * @return 最长回文子串
     */
    public String longestPalindrome2(String s)
    {
        if (s.length() == 0)
            return s;
        boolean[][] valid = new boolean[s.length()][s.length()];
        int begin = 0, end = 0;
        for (int i = 0; i < s.length(); i++)
        {
            for (int j = 0; j <= i; j++)
            {
                if (s.charAt(i) == s.charAt(j))
                {
                    if (i - j > 1)
                        valid[j][i] = valid[j + 1][i - 1];
                    else
                        valid[j][i] = true;
                }
                else
                    valid[j][i] = false;
                if (valid[j][i] && end - begin < i - j)
                {
                    begin = j;
                    end = i;
                }
            }
        }
        return s.substring(begin, end + 1);
    }

    public String longestPalindrome3(String s)
    {
        if (s.length() == 0)
            return s;
        allValid = new int[s.length()][s.length()];
        for (int length = s.length(); length > 0; length--)
        {
            for (int i = 0; i <= s.length() - length; i++)
            {
                if (longestPalindromeHelper(i, length + i - 1, s) == 1)
                {
                    return s.substring(i, length + i);
                }
            }
        }
        return "";
    }

    public int longestPalindromeHelper(int begin, int end, String s)
    {
        if (end < begin)
            return -1;
        //没探索过
        if (allValid[begin][end] == 0)
        {
            if (s.charAt(begin) == s.charAt(end))
            {
                //最底部条件
                if (end - begin <= 1)
                    allValid[begin][end] = 1;
                else if (longestPalindromeHelper(begin + 1, end - 1, s) == 1)
                    allValid[begin][end] = 1;
                else
                    allValid[begin][end] = -1;
            }
            else
                allValid[begin][end] = -1;
        }
        return allValid[begin][end];
    }

    public String longestPalindrome4(String s)
    {
        int n = s.length();
        String res = "";
        boolean[][] dp = new boolean[n][n];
        for (int i = n - 1; i >= 0; i--)
        {
            for (int j = i; j < n; j++)
            {
                dp[i][j] = s.charAt(i) == s.charAt(j) && (j - i < 3 || dp[i + 1][j - 1]);

                if (dp[i][j] && (res.isEmpty() || j - i + 1 > res.length()))
                {
                    res = s.substring(i, j + 1);
                }
            }
        }
        return res;
    }

    /**
     * 和我第一种思路挺像的，都是从中心两种方式想两侧开花
     * 然后比较结果，但是这个只用了 O(1) 常数级的空间
     *
     * @param s
     * @return
     */
    public String longestPalindrome5(String s)
    {
        if (s == null || s.length() < 1)
            return "";
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++)
        {
            int len1 = expandAroundCenter(s, i, i);
            int len2 = expandAroundCenter(s, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > end - start)
            {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    private int expandAroundCenter(String s, int left, int right)
    {
        int L = left, R = right;
        while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R))
        {
            L--;
            R++;
        }
        return R - L - 1;
    }


    public String preProcess(String s)
    {
        int n = s.length();
        if (n == 0)
        {
            return "^$";
        }
        String ret = "^";
        for (int i = 0; i < n; i++)
            ret += "#" + s.charAt(i);
        ret += "#$";
        return ret;
    }

    // 马拉车算法
    public String longestPalindrome6(String s)
    {
        String T = preProcess(s);
        int n = T.length();
        int[] P = new int[n];
        int C = 0, R = 0;
        for (int i = 1; i < n - 1; i++)
        {
            int i_mirror = 2 * C - i;
            if (R > i)
            {
                P[i] = Math.min(R - i, P[i_mirror]);// 防止超出 R
            }
            else
            {
                P[i] = 0;// 等于 R 的情况
            }

            // 碰到之前讲的三种情况时候，需要利用中心扩展法
            while (T.charAt(i + 1 + P[i]) == T.charAt(i - 1 - P[i]))
            {
                P[i]++;
            }

            // 判断是否需要更新 R
            if (i + P[i] > R)
            {
                C = i;
                R = i + P[i];
            }

        }

        // 找出 P 的最大值
        int maxLen = 0;
        int centerIndex = 0;
        for (int i = 1; i < n - 1; i++)
        {
            if (P[i] > maxLen)
            {
                maxLen = P[i];
                centerIndex = i;
            }
        }
        int start = (centerIndex - maxLen) / 2; //最开始讲的求原字符串下标
        return s.substring(start, start + maxLen);
    }


    public static void main(String[] args)
    {
        String s = "cbbaabbd";
        LongestPalindromicSubstring lps = new LongestPalindromicSubstring();
        System.out.print(lps.longestPalindrome6(s));
    }
}
