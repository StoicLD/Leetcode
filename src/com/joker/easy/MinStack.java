package com.joker.easy;

import java.util.Stack;

public class MinStack
{
    private static class Node
    {
        int val;
        int min;

        public Node(int _val, int _min)
        {
            val = _val;
            min = _min;
        }
    }

    Stack<Node> innerStack;

    /**
     * initialize your data structure here.
     */
    public MinStack()
    {
        innerStack = new Stack<>();
    }

    public void push(int x)
    {
        if(innerStack.size() == 0)
            innerStack.push(new Node(x, x));
        else
            innerStack.push(new Node(x, Math.min(innerStack.peek().min, x)));
    }

    public void pop()
    {
        innerStack.pop();
    }

    public int top()
    {
        return innerStack.peek().val;
    }

    public int getMin()
    {
        return innerStack.peek().min;
    }
}
