package com.joker.easy;

import java.util.Collections;
import java.util.PriorityQueue;

public class LastStoneWeight
{
    public int lastStoneWeight(int[] stones)
    {
        if(stones.length == 0)
            return 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for(int i : stones)
        {
            pq.offer(i);
        }
        while (pq.size() >= 2)
        {
            int next = pq.poll() - pq.poll();
            pq.offer(next);
        }
        return pq.peek();
    }
}
