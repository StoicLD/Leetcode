package com.joker.medium;

/**
 * This is LeetCode 96 Unique Binary Search Trees
 * @author Joker
 * @version 1.0
 * @since 2020.3.15
 */
public class UniqueBinarySearchTrees
{
    //递归通式应该是这样的 R(N) = R(0) * R(N - 1) + R(1) * R(N - 2) + ... + R(N-1) * R(0)
    int[] record;
    public int numTrees(int n)
    {
        record = new int[n + 1];
        record[0] = record[1] = 1;
        return numTreesHelper(n);
    }

    //使用递归展开的方法实际上是会爆栈的
    public int numTreesHelper(int n)
    {
        if(n <= 1)
        {
            return record[n];
        }
        else if(record[n] != 0)
        {
            return record[n];
        }
        else
        {
            int result = 0;
            for(int i = 0; i <= n - 1; i++)
            {
                result += numTreesHelper(i) * numTreesHelper(n - 1 - i);
            }
            record[n] = result;
            return result;
        }
    }

    /**
     * 使用Dynamic Programming也就是动态规划的方式
     * 代码简洁优雅很多
     * @param n 输入规模
     * @return 不同结构的BST的数目
     */
    public int numTreesDP(int n)
    {
        int[] recordDP = new int[n + 1];
        recordDP[0] = recordDP[1] = 1;
        for(int i = 2; i <= n; i++)
        {
            for(int j = 0; j < i; j++)
            {
                recordDP[i] += recordDP[j] * recordDP[i - j - 1];
            }
        }
        return recordDP[n];
    }


    public static void main(String[] args)
    {
        UniqueBinarySearchTrees ubst = new UniqueBinarySearchTrees();
//        System.out.println(ubst.numTrees(3));
//        System.out.println(ubst.numTrees(4));
//        System.out.println(ubst.numTrees(5));
//        System.out.println(ubst.numTrees(6));
//        System.out.println(ubst.numTrees(10));
//
//        System.out.println(ubst.numTreesDP(3));
//        System.out.println(ubst.numTreesDP(4));
//        System.out.println(ubst.numTreesDP(5));
//        System.out.println(ubst.numTreesDP(6));
//        System.out.println(ubst.numTreesDP(10));


        long startTime = System.nanoTime();
        System.out.println(ubst.numTreesDP(15));
        long endTime = System.nanoTime();
        System.out.println(endTime - startTime);


        startTime = System.nanoTime();
        System.out.println(ubst.numTrees(15));
        endTime = System.nanoTime();
        System.out.println(endTime - startTime);

    }
}
