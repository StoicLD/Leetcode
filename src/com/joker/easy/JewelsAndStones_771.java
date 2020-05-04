package com.joker.easy;

import java.util.HashMap;

public class JewelsAndStones_771
{
    public int numJewelsInStones(String J, String S)
    {
        HashMap<Character, Integer> map = new HashMap<>();
        for(int i = 0; i < J.length(); i++)
        {
            map.put(J.charAt(i), 1);
        }
        int result = 0;
        for(int i = 0; i < S.length(); i++)
        {
            if(map.get(S.charAt(i)) != null)
            {
                result++;
            }
        }
        return result;
    }

    public int numJewelsInStones2(String J, String S)
    {
        int[] cnt = new int[128];
        for (char c : S.toCharArray())
            cnt[c]++;
        int ans = 0;
        for (char c : J.toCharArray())
            ans += cnt[c];
        return ans;
    }
}
