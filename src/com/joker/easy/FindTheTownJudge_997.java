package com.joker.easy;

import java.util.HashMap;

public class FindTheTownJudge_997
{
    public int findJudge(int N, int[][] trust)
    {
        if (N == 1 && (trust == null || trust.length == 0))
            return 1;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int[] pairs : trust)
        {
            int truter = pairs[0];
            int trutee = pairs[1];
            map.put(truter, -1);
            if (!map.containsKey(trutee) || map.get(trutee) != -1)
                map.put(trutee, map.getOrDefault(trutee, 0) + 1);
        }
        Integer result = null;
        for (int i : map.keySet())
        {
            if (map.get(i) == N - 1)
            {
                if (result != null)
                    return -1;
                else
                    result = i;
            }
        }
        return result == null ? -1 : result;
    }

    /**
     * 使用图的方法，其实和我的方法是差不多的
     * 出去--， 进入++
     * 出度和入度平衡。
     * 出度和入度，入度为N - 1的那个就是
     * @param N 输入规模
     * @param trust 信任数组
     * @return Judge是谁
     */
    public int findJudge2(int N, int[][] trust)
    {
        int[] count = new int[N + 1];
        for (int[] t : trust)
        {
            count[t[0]]--;
            count[t[1]]++;
        }
        for (int i = 1; i <= N; ++i)
        {
            if (count[i] == N - 1)
                return i;
        }
        return -1;
    }
}
