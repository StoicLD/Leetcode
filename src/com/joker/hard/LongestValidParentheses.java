package com.joker.hard;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;

public class LongestValidParentheses
{
    /*    public int longestValidParentheses(String s)
        {
            int result = 0;
            for(int i = 0; i < s.length(); i++)
            {
                if(s.charAt(i) == '(')
                {
                    int currLength = 0;
                    while (i + 1 < s.length())
                    {
                        if(s.charAt(i) == '(' && s.charAt(i + 1) == ')')
                            currLength += 2;
                        else
                        {
                            if(currLength > 0)
                                i--;
                            break;
                        }
                        i += 2;
                    }
                    if(result < currLength)
                        result = currLength;
                }
            }
            return result;
        }*/
    public enum validType
    {
        valid,
        nonValid,
        waste
    }

    private static class Group
    {
        validType valid;
        int number;

        public Group(int _number, validType _isValid)
        {
            number = _number;
            valid = _isValid;
        }
    }

    /**
     * 比较特殊的一个想法
     * 一共分成三种group
     * 把 "(()())" 算作是valid的，可以计入最终比较
     * 把 "((((" 连续的左括号算作是nonValid，但是是潜在的可能被消除，转化位valid的存在
     * 把 "))))" 连续的右括号算作是waste的，这部分不会被统计
     * 当遇到 ")" 且前面没有 "(" 的集合的时候才会被计入一个waster group
     * 否则会消除最靠近前面的 nonvalid组，并且valid组的长度 +2
     *
     * @param s
     * @return
     */
    public int longestValidParentheses(String s)
    {
        LinkedList<Group> list = new LinkedList<>();
        int result = 0;
        int topNonValidIndex = -1;
        int topValidIndex = -1;

        //Group topNonValid = null;
        //Group topValid = null;
        int i = 0;
        for (; i < s.length(); i++)
        {
            if (s.charAt(i) == '(')
                break;
        }

        for (; i < s.length(); i++)
        {
            char c = s.charAt(i);
            //默认只有 '(' 和 ')'
            if (c == '(')
            {
                if (list.size() != 0)
                {
                    //需要记录最靠近顶端的非valid Group
                    if (topNonValidIndex >= 0 && topNonValidIndex == list.size() - 1)
                    {
                        list.get(topNonValidIndex).number++;
                    }
                    else
                    {
                        list.addLast(new Group(1, validType.nonValid));
                        topNonValidIndex = list.size() - 1;
                        //topNonValid = list.getLast();
                    }
                }
                else
                {
                    list.addLast(new Group(1, validType.nonValid));
                    topNonValidIndex = list.size() - 1;
                    //topNonValid = list.getLast();
                }
            }
            else
            {
                // ')' 意味着消去non-valid以及添加，合并Group，以及维护max
                if (topNonValidIndex < 0)
                {
                    //这种情况应该是不可能存在的
                    //throw new IllegalStateException();
                    list.addLast(new Group(1, validType.waste));
                }
                else
                {
                    //topNonValid.number--;
                    Group topValid = null;
                    if (topValidIndex >= 0)
                    {
                        if (topValidIndex == list.size() - 1)
                        {
                            topValid = list.getLast();
                            topValid.number += 2;
                        }
                        else
                        {
                            list.addLast(new Group(2, validType.valid));
                            topValidIndex = list.size() - 1;
                            topValid = list.getLast();
                        }
                    }
                    else
                    {
                        list.addLast(new Group(2, validType.valid));
                        topValidIndex = list.size() - 1;
                        topValid = list.getLast();
                    }

                    list.get(topNonValidIndex).number--;
                    //意味着这个non-valid Group要去除，需要合并
                    if (list.get(topNonValidIndex).number <= 0)
                    {
                        if (topNonValidIndex + 1 < list.size() && topNonValidIndex - 1 >= 0
                                && list.get(topNonValidIndex + 1).valid == validType.valid && list.get(topNonValidIndex - 1).valid == validType.valid)
                        {
                            //左右合并
                            Group right = list.get(topNonValidIndex + 1);
                            Group left = list.get(topNonValidIndex - 1);
                            right.number += left.number;
                            list.remove(topNonValidIndex);
                            list.remove(topNonValidIndex - 1);
                        }
                        else
                        {
                            list.remove(topNonValidIndex);
                        }
                        //更新topNonValidIndex 和 topValidIndex
                        topNonValidIndex = -1;
                        topValidIndex = -1;
                        for (int j = list.size() - 1; j >= 0; j--)
                        {
                            if (topNonValidIndex == -1 && list.get(j).valid == validType.nonValid)
                                topNonValidIndex = j;
                            if (topValidIndex == -1 && list.get(j).valid == validType.valid)
                            {
                                topValidIndex = j;
                                topValid = list.get(j);
                            }
                        }
                    }

                    //是否更新最大值
                    if (topValid.number > result)
                        result = list.get(topValidIndex).number;
                }
            }
        }
        return result;
    }

    /**
     * 参考LeetCode的官方解答，DP的方法
     * https://leetcode.com/problems/longest-valid-parentheses/solution/
     *
     * @param s
     * @return
     */
    public int longestValidParenthesesDP(String s)
    {
        if (s.length() == 0)
            return 0;
        int[] dp = new int[s.length()];
        int result = 0;
        for (int i = 0; i < s.length(); i++)
        {
            if (s.charAt(i) == '(')
            {
                dp[i] = 0;
            }
            else if (i >= 1)
            {
                if (s.charAt(i - 1) == '(')
                    dp[i] = i >= 2 ? dp[i - 2] + 2 : 2;
                else
                {
                    int preIndex = i - dp[i - 1] - 1;
                    if (preIndex >= 0 && s.charAt(preIndex) == '(')
                        dp[i] = preIndex - 1 >= 0 ? dp[preIndex - 1] + dp[i - 1] + 2 : dp[i - 1] + 2;
                    else
                        dp[i] = 0;
                }
            }
            if (result < dp[i])
                result = dp[i];
        }
        return result;
    }

    public int longestValidParenthesesStack(String s)
    {
        int maxans = 0;
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        for (int i = 0; i < s.length(); i++)
        {
            if (s.charAt(i) == '(')
                stack.push(i);
            else
            {
                stack.pop();
                if (stack.empty())
                    stack.push(i);
                else
                    maxans = Math.max(maxans, i - stack.peek());
            }
        }
        return maxans;
    }

    /**
     * 一个很nb的算法。
     * 从两头开始扫描，只需要记录两个节点left和right即可
     * @param s
     * @return
     */
    public int longestValidParenthesesSmart(String s)
    {
        int result = 0;
        int left = 0;
        int right = 0;
        for(int i = 0; i < s.length(); i++)
        {
            if(s.charAt(i) == '(')
                left++;
            else
                right++;
            if(left == right)
            {
                if(left * 2 > result)
                    result = left * 2;
            }
            else if(left < right)
            {
                left = 0;
                right = 0;
            }
        }

        left = 0;
        right = 0;
        for(int i = s.length() - 1; i >= 0; i--)
        {
            if(s.charAt(i) == ')')
                right++;
            else
                left++;
            if(left == right)
            {
                if(left * 2 > result)
                    result = left * 2;
            }
            else if(left > right)
            {
                left = 0;
                right = 0;
            }
        }
        return result;
    }


    public static void main(String[] args)
    {
        String s = ")()(()(()))";
        LongestValidParentheses lvp = new LongestValidParentheses();
        System.out.println(lvp.longestValidParenthesesSmart(s));
    }
}
