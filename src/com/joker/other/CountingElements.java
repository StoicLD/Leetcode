package com.joker.other;

import java.util.HashSet;
import java.util.Set;

public class CountingElements
{
    public int countElements(int[] arr)
    {
        Set<Integer> set = new HashSet<>();
        int count = 0;
        for(int i : arr)
            set.add(i);
        for(int i : arr)
        {
            if(set.contains(i + 1))
                count++;
        }
        return count;
    }
}
