package com.joker.medium;

import org.junit.Assert;

import java.util.Stack;

public class RemoveKDigits_402
{
    public String removeKdigits(String num, int k)
    {
        if(k == num.length())
            return "0";
        int start = 0;
        StringBuilder result = new StringBuilder();
        while (k > 0 && k != num.length() - start)
        {
            int min = num.charAt(start) - '0';
            int removeIndex = start;
            for(int i = start; i <= k + start && i < num.length(); i++)
            {
                int number = num.charAt(i) - '0';
                if(min > number)
                {
                    min = number;
                    removeIndex = i;
                }
            }
            k = k - (removeIndex - start);
            start = removeIndex + 1;
            result.append(min);
        }
        if(start < num.length() && k == 0)
            result.append(num.substring(start));
        // "0200" --> "0200" --> 200 --> "200"
        while (result.length() > 0)
        {
            if(result.charAt(0) != '0')
                break;
            result.deleteCharAt(0);
        }
        return result.toString().equals("") ? "0" : result.toString();
    }

    /**
     * 一种使用Stack的好方法
     * 核心是当stack顶部元素比当前数值s.charAt(i)大的时候
     * 持续的弹出stack内的元素直到stack.peek()比s.charAt(i)小
     * @param num
     * @param k
     * @return
     */
    public String removeKdigits2(String num, int k) {
        int len = num.length();
        //corner case
        if(k==len)
            return "0";

        Stack<Character> stack = new Stack<>();
        int i =0;
        /*
         演示 "3141287", k = 3
         stack from bottom to peek
         3 --> 1 --> 1,4 --> 1,1 --> 1,1,2 --> 1,1,2,8 --> 1,1,2,7
        */
        while(i<num.length()){
            //whenever meet a digit which is less than the previous digit, discard the previous one
            while(k>0 && !stack.isEmpty() && stack.peek()>num.charAt(i)){
                stack.pop();
                k--;
            }
            stack.push(num.charAt(i));
            i++;
        }

        // corner case like "1111"
        while(k>0){
            stack.pop();
            k--;
        }

        //construct the number from the stack
        StringBuilder sb = new StringBuilder();
        while(!stack.isEmpty())
            sb.append(stack.pop());
        sb.reverse();

        //remove all the 0 at the head
        while(sb.length()>1 && sb.charAt(0)=='0')
            sb.deleteCharAt(0);
        return sb.toString();
    }

    public static void main(String[] args)
    {
        RemoveKDigits_402 rk = new RemoveKDigits_402();
        Assert.assertEquals("0", rk.removeKdigits("00000000", 3));

        Assert.assertEquals("0", rk.removeKdigits("00100", 3));
        Assert.assertEquals("108", rk.removeKdigits("412108", 3));
        Assert.assertEquals("1219", rk.removeKdigits("1432219", 3));
        Assert.assertEquals("200", rk.removeKdigits("10200", 1));
        Assert.assertEquals("0", rk.removeKdigits("10", 2));
        Assert.assertEquals("0", rk.removeKdigits("180901", 6));

    }

}

