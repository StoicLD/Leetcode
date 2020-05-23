package com.joker.medium;

import java.util.*;
import java.util.stream.Stream;

public class SortCharactersByFrequency_451
{
    public String frequencySort(String s)
    {
        SortedMap<Character, Integer> map = new TreeMap<>();
        for (char c : s.toCharArray())
        {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        Stream<Map.Entry<Character, Integer>> sorted =
                map.entrySet().stream()
                        .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()));

        StringBuilder sb = new StringBuilder();
        sorted.forEach(item -> {
            sb.append(String.valueOf(item.getKey()).repeat(Math.max(0, map.get(item.getKey()))));
        });
        return sb.toString();
    }

    /**
     * 预先填充好的方法
     * @param s
     * @return
     */
    public String frequencySort2(String s)
    {
        if (s == null || s.isEmpty())
            return s;
        //
        int[] count = new int[256];
        for (char ch : s.toCharArray())
            count[ch]++;
        List<Character>[] buckets = new ArrayList[s.length() + 1];
        for (int i = 0; i < count.length; i++)
        {
            if (count[i] != 0)
            {
                if (buckets[count[i]] == null)
                    buckets[count[i]] = new ArrayList<>();
                buckets[count[i]].add((char) i);
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = buckets.length - 1; i >= 0; i--)
        {
            if (buckets[i] != null)
            {
                for (int j = 0; j < buckets[i].size(); j++)
                {
                    char temp = buckets[i].get(j);
                    for (int k = 0; k < i; k++)
                    {
                        sb.append(temp);
                    }
                }
            }
        }
        return sb.toString();
    }

    public static void main(String[] args)
    {
        SortCharactersByFrequency_451 sc = new SortCharactersByFrequency_451();
        sc.frequencySort("bbaaaadsc");
    }

}
