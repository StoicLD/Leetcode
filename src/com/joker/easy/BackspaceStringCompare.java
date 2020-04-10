package com.joker.easy;

import java.util.Stack;

public class BackspaceStringCompare
{
    public boolean backspaceCompare(String S, String T)
    {
        Stack<Character> stackS = new Stack<>();
        Stack<Character> stackT = new Stack<>();
        for(char c : S.toCharArray())
        {
            if(c == '#')
            {
                if (!stackS.empty())
                    stackS.pop();
            }
            else
                stackS.push(c);
        }

        for(char c : T.toCharArray())
        {
            if(c == '#')
            {
                if (!stackT.empty())
                    stackT.pop();
            }
            else
                stackT.push(c);
        }
        if(stackT.size() != stackS.size())
            return false;
        while (!stackS.empty() && !stackT.empty())
        {
            if(stackS.pop() != stackT.pop())
                return false;
        }
        return true;
    }

    public boolean backspaceCompare2(String S, String T)
    {
        int i = S.length() - 1, j = T.length() - 1;
        while (i >= 0 || j >= 0)
        {
            int sSkip = 0;
            int tSkip = 0;
            while (i >= 0)
            {
                if(S.charAt(i) == '#')
                    sSkip++;
                else if(sSkip > 0)
                    sSkip--;
                else
                    break;
                i--;
            }

            while (j >= 0)
            {
                if(T.charAt(j) == '#')
                    tSkip++;
                else if(tSkip > 0)
                    tSkip--;
                else
                    break;
                j--;
            }

            if(i >= 0 && j >= 0)
            {
                if (S.charAt(i) != T.charAt(j))
                    return false;
                else
                {
                    i--;
                    j--;
                }
            }
            else if(i >= 0 && S.charAt(i) != '#')
                return false;
            else if(j >= 0 && T.charAt(j) != '#')
                return false;
        }
        return true;
    }
}
