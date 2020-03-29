package com.joker.easy;

import java.util.Stack;

/**
 * This is for LeetCode 20
 */
public class ValidParentheses
{
    private static Character b1 = '{';
    public boolean isValid(String s)
    {
        Stack<Character> stack = new Stack<>();
        for(int i = 0; i < s.length(); i++)
        {
            char c = s.charAt(i);
            if(c == '{' || c == '[' || c == '(')
            {
                stack.push(c);
            }
            else if(c == '}' || c == ']' || c == ')')
            {
                if(!stack.empty())
                {
                    char counter = stack.pop();
                    if((c == '}' && counter != '{') || (c == ']' && counter != '[') || (c == ')' && counter != '('))
                    {
                        return false;
                    }
                }
                else
                    return false;
            }
        }
        return stack.empty();
    }

    /**
     * 一种很巧妙的方法，反着来
     * @param s
     * @return
     */
    public boolean isValidOthers1(String s) {
        Stack<Character> stack = new Stack<Character>();
        for (char c : s.toCharArray()) {
            if (c == '(')
                stack.push(')');
            else if (c == '{')
                stack.push('}');
            else if (c == '[')
                stack.push(']');
            else if (stack.isEmpty() || stack.pop() != c)
                return false;
        }
        return stack.isEmpty();
    }
}
