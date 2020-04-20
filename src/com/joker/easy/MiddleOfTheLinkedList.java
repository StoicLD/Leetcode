package com.joker.easy;

import com.joker.ListNode;

public class MiddleOfTheLinkedList
{
    public ListNode middleNode(ListNode head)
    {
        if(head == null)
            return null;
        int length = 0;
        ListNode node = head;
        while (node != null)
        {
            node = node.next;
            length++;
        }
        node = head;
        int i = 1;
        while (i <= length / 2)
        {
            node = node.next;
            i++;
        }
        return node;
    }

    /**
     * 两个pointer的解法，一个fast，一个slow
     * @param head
     * @return
     */
    public ListNode middleNode2(ListNode head)
    {
        ListNode slowPointer = head;
        ListNode fastPointer = head;
        while (fastPointer != null)
        {
            fastPointer = fastPointer.next;
            if(fastPointer != null)
                fastPointer = fastPointer.next;
            else
                return slowPointer;
            slowPointer = slowPointer.next;
        }
        return slowPointer;
    }
}
