package com.joker.medium;

import com.joker.ListNode;

import java.util.Stack;

public class AddTwoNumbersII_445
{
    public ListNode addTwoNumbers(ListNode l1, ListNode l2)
    {
        ListNode node1 = l1;
        ListNode node2 = l2;
        Stack<ListNode> stack1 = new Stack<>();
        Stack<ListNode> stack2 = new Stack<>();
        while (node1 != null || node2 != null)
        {
            if(node1 != null)
            {
                stack1.push(node1);
                node1 = node1.next;
            }
            if(node2 != null)
            {
                stack2.push(node2);
                node2 = node2.next;
            }
        }
        int pre = 0;
        ListNode preNode = null;
        ListNode nextNode = null;
        while (!stack1.empty() || !stack2.empty())
        {
            int val1 = !stack1.empty() ? stack1.pop().val : 0;
            int val2 = !stack2.empty() ? stack2.pop().val : 0;
            int curr = pre + val1 + val2;
            preNode = new ListNode(curr % 10);
            preNode.next = nextNode;
            pre = curr / 10;
            nextNode = preNode;
        }
        if(pre != 0)
        {
            preNode = new ListNode(pre);
            preNode.next = nextNode;
        }
        return preNode;
    }
}
