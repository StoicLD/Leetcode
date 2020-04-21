package com.joker.medium;

import com.joker.ListNode;

public class SwapNodesInPairs_24
{
    public ListNode swapPairs(ListNode head)
    {
        if (head == null || head.next == null)
            return head;
        ListNode preNode = null;
        ListNode currNode = head;
        ListNode nextNode = head.next;
        head = nextNode;
        while (currNode != null && nextNode != null)
        {
            ListNode nextNext = nextNode.next;
            nextNode.next = currNode;
            currNode.next = nextNext;
            if(preNode != null)
                preNode.next = nextNode;
            preNode = currNode;
            currNode = nextNext;
            if(currNode != null)
                nextNode = currNode.next;
        }
        return head;
    }
}
