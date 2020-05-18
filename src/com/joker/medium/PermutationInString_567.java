package com.joker.medium;

import java.util.LinkedList;
import java.util.List;

public class PermutationInString_567
{
    public boolean checkInclusion(String s1, String s2)
    {
        if(s2.length() < s1.length())
            return false;
        int[] pattern = new int[26];
        int[] currPattern = new int[26];

        for(int i = 0; i < s1.length(); i++)
        {
            int index = s1.charAt(i) - 'a';
            pattern[index]++;
        }
        int sameChars = 0;
        for(int j = 0; j < s1.length(); j++)
        {
            int index = s2.charAt(j) - 'a';
            currPattern[index]++;
            if(pattern[index] >= currPattern[index])
                sameChars++;
        }

        if(sameChars == s1.length())
            return true;
        for(int k = 0; k + s1.length() < s2.length(); k++)
        {
            int frontIndex = s2.charAt(k) - 'a';
            int endIndex = s2.charAt(k + s1.length()) - 'a';
            currPattern[frontIndex]--;
            if(pattern[frontIndex] >= currPattern[frontIndex] + 1)
                sameChars--;
            currPattern[endIndex]++;
            if(pattern[endIndex] >= currPattern[endIndex])
                sameChars++;
            if(sameChars == s1.length())
                return true;
        }
        return false;
    }
}
