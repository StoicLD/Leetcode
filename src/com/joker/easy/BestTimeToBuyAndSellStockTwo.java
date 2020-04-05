package com.joker.easy;

/**
 * 122. Best Time to Buy and Sell Stock II
 */
public class BestTimeToBuyAndSellStockTwo
{
    public int maxProfit(int[] prices)
    {
        if (prices == null || prices.length <= 1)
            return 0;
        int profit = 0;
        int buyAt = 0;
        boolean findBuy = false;
        for (int i = 0; i < prices.length - 1; i++)
        {
            if (prices[i] < prices[i + 1] && !findBuy)
            {
                findBuy = true;
                buyAt = i;
            }
            else if (prices[i] >= prices[i + 1] && findBuy)
            {
                findBuy = false;
                profit += prices[i] - prices[buyAt];
            }
        }
        if (findBuy)
            profit += prices[prices.length - 1] - prices[buyAt];
        return profit;
    }

    /**
     * 蠢了，这要这几行就够了
     * @param prices 价格数组
     * @return 最大利润
     */
    public int maxProfit2(int[] prices)
    {
        int total = 0;
        for (int i = 0; i < prices.length - 1; i++)
        {
            if (prices[i + 1] > prices[i])
                total += prices[i + 1] - prices[i];
        }

        return total;
    }
}
