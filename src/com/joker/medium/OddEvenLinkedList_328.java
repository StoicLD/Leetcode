package com.joker.medium;

import com.joker.ListNode;

import java.util.List;

public class OddEvenLinkedList_328
{
    public ListNode oddEvenList(ListNode head)
    {
        /*
            Input: 2->1->3->5->6->4->7->NULL
            Output: 2->3->6->7->1->5->4->NULL
         */
        if(head == null || head.next == null)
            return head;
        ListNode oddPtr = head;
        ListNode evenPtr = head.next;
        ListNode evenHead = evenPtr;
        while (evenPtr != null && evenPtr.next != null)
        {
            oddPtr.next = evenPtr.next;
            evenPtr.next = oddPtr.next.next;
            oddPtr = oddPtr.next;
            evenPtr = evenPtr.next;
        }
        oddPtr.next = evenHead;
        return head;
    }
}
