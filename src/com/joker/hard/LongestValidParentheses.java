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


    public int longestValidParentheses(String s)
    {
        LinkedList<Group> list = new LinkedList<>();
        int result = 0;
        int topNonValidIndex = -1;
        int topValidIndex = -1;

        //Group topNonValid = null;
        //Group topValid = null;
        int i = 0;
        for(; i < s.length(); i++)
        {
            if(s.charAt(i) == '(')
                break;
        }

        for(; i < s.length(); i++)
        {
            char c = s.charAt(i);
            //默认只有 '(' 和 ')'
            if(c == '(')
            {
                if(list.size() != 0)
                {
                    //需要记录最靠近顶端的非valid Group
                    if(topNonValidIndex >= 0 && topNonValidIndex == list.size() - 1)
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
                if(topNonValidIndex < 0)
                {
                    //这种情况应该是不可能存在的
                    //throw new IllegalStateException();
                    list.addLast(new Group(1, validType.waste));
                }
                else
                {
                    //topNonValid.number--;
                    Group topValid = null;
                    if(topValidIndex >= 0)
                    {
                        if(topValidIndex == list.size() - 1)
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
                    if(list.get(topNonValidIndex).number <= 0)
                    {
                        if(topNonValidIndex + 1 < list.size() && topNonValidIndex - 1 >= 0
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
                        for(int j = list.size() - 1; j >= 0; j--)
                        {
                            if(topNonValidIndex == -1 && list.get(j).valid == validType.nonValid)
                                topNonValidIndex = j;
                            if(topValidIndex == -1 && list.get(j).valid == validType.valid)
                            {
                                topValidIndex = j;
                                topValid = list.get(j);
                            }
                        }
                    }

                    //是否更新最大值
                    if(topValid.number > result)
                        result = list.get(topValidIndex).number;
                }
            }
        }
        return result;
    }

    public static void main(String[] args)
    {
       String s = ")()())()()(";
       LongestValidParentheses lvp = new LongestValidParentheses();
       System.out.println(lvp.longestValidParentheses(s));
    }
}
