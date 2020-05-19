package com.joker.medium;

import java.util.ArrayList;

public class OnlineStockSpan_901
{

}

class Pair
{
    public int key;
    public int value;
    public Pair(int k, int v)
    {
        key = k;
        value = v;
    }
}

class StockSpanner
{
    ArrayList<Pair> dp;
    public StockSpanner() {
        dp = new ArrayList<>();
    }

    public int next(int price)
    {
        int result = 1;
        int i = dp.size() - 1;
        while (i >= 0 && dp.get(i).key <= price)
        {
            result += dp.get(i).value;
            i -= dp.get(i).value;
        }
        dp.add(new Pair(price, result));
        return result;
    }
}
