package com.joker.medium;

import java.util.LinkedList;
import java.util.List;

public class FindAllAnagramsInAString_438
{
    /**
     * 滑动窗口解法
     * @param s
     * @param p
     * @return
     */
    public List<Integer> findAnagrams(String s, String p)
    {
        List<Integer> result = new LinkedList<>();
        if(s.length() < p.length())
            return result;
        int[] pattern = new int[26];
        int[] currPattern = new int[26];

        for(int i = 0; i < p.length(); i++)
        {
            int index = p.charAt(i) - 'a';
            pattern[index]++;
        }
        int sameChars = 0;
        for(int j = 0; j < p.length(); j++)
        {
            int index = s.charAt(j) - 'a';
            currPattern[index]++;
            if(pattern[index] >= currPattern[index])
                sameChars++;
        }

        if(sameChars == p.length())
            result.add(0);

        for(int k = 0; k + p.length() < s.length(); k++)
        {
            int frontIndex = s.charAt(k) - 'a';
            int endIndex = s.charAt(k + p.length()) - 'a';
            currPattern[frontIndex]--;
            if(pattern[frontIndex] >= currPattern[frontIndex] + 1)
                sameChars--;
            currPattern[endIndex]++;
            if(pattern[endIndex] >= currPattern[endIndex])
                sameChars++;
            if(sameChars == p.length())
                result.add(k + 1);
        }
        return result;
    }
}
