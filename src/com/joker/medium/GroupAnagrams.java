package com.joker.medium;

import java.util.*;

public class GroupAnagrams
{
    public List<List<String>> groupAnagrams(String[] strs)
    {
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs)
        {
            String reOrderStr = reOrder(str);
            if (!map.containsKey(reOrderStr))
            {
                map.put(reOrderStr, new LinkedList<>());
            }
            map.get(reOrderStr).add(str);
        }
        List<List<String>> result = new LinkedList<>();
        for (Map.Entry<String, List<String>> entry : map.entrySet())
        {
            result.add(entry.getValue());
        }
        return result;
    }

    /**
     * 简单的选择排序string的字母
     * 从 bca --> abc
     *
     * @param s 输入字符串
     * @return 重排序之后的字符串
     */
    public static String reOrder(String s)
    {
        // eat --> aet
        StringBuilder result = new StringBuilder();
        StringBuilder ss = new StringBuilder(s);
        while (ss.length() != 0)
        {
            char minC = ss.charAt(0);
            int deleteIndex = 0;
            for (int i = 0; i < ss.length(); i++)
            {
                if (ss.charAt(i) < minC)
                {
                    minC = ss.charAt(i);
                    deleteIndex = i;
                }
            }
            ss.deleteCharAt(deleteIndex);
            result.append(minC);
        }
        return result.toString();
    }

    /**
     * 一样的思路，但是使用Java自身的API
     *
     * @param strs 输入String数组
     * @return 结果
     */
    public List<List<String>> groupAnagrams2(String[] strs)
    {
        if (strs == null || strs.length == 0)
            return new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>();
        for (String s : strs)
        {
            char[] ca = s.toCharArray();
            Arrays.sort(ca);
            String keyStr = String.valueOf(ca);
            if (!map.containsKey(keyStr))
                map.put(keyStr, new ArrayList<>());
            map.get(keyStr).add(s);
        }
        return new ArrayList<>(map.values());
    }

    /**
     * 第三种方法，
     * @param strs 输入字符串数组
     * @return  结果
     */
    public List<List<String>> groupAnagrams3(String[] strs)
    {
        if (strs == null || strs.length == 0)
            return new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>();
        for (String s : strs)
        {
            char[] ca = new char[26];
            for (char c : s.toCharArray())
                ca[c - 'a']++;
            String keyStr = String.valueOf(ca);
            if (!map.containsKey(keyStr))
                map.put(keyStr, new ArrayList<>());
            map.get(keyStr).add(s);
        }
        return new ArrayList<>(map.values());
    }

    public static void main(String[] args)
    {
        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
        GroupAnagrams ga = new GroupAnagrams();
        ga.groupAnagrams3(strs);
        char[] cc = {1,0,0,0,1,0,0,1};
        System.out.print(String.valueOf(cc));
    }

}
