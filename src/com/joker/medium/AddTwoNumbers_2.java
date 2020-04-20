package com.joker.medium;

import com.joker.ListNode;

public class AddTwoNumbers_2
{
    /**
     * @param l1 非空LinkedList1
     * @param l2 非空LinkedList2
     * @return
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2)
    {
        ListNode node1 = l1.next;
        ListNode node2 = l2.next;
        ListNode head = new ListNode((l1.val + l2.val) % 10);
        ListNode nodePointer = head;
        int pre = (l1.val + l2.val) / 10;
        while (node1 != null && node2 != null)
        {
            int curr = pre + node1.val + node2.val;
            nodePointer.next = new ListNode(curr % 10);
            pre = curr / 10;
            nodePointer = nodePointer.next;
            node1 = node1.next;
            node2 = node2.next;
        }
        ListNode remainNode = node1 == null ? node2 : node1;
        while (remainNode != null)
        {
            nodePointer.next = new ListNode((pre + remainNode.val) % 10);
            pre = (pre + remainNode.val) / 10;
            nodePointer = nodePointer.next;
            remainNode = remainNode.next;
        }
        if (pre != 0)
        {
            nodePointer.next = new ListNode(pre);
        }
        return head;
    }

    public ListNode addTwoNumbers2(ListNode l1, ListNode l2)
    {
        ListNode dummyHead = new ListNode(0);
        ListNode p = l1, q = l2, curr = dummyHead;
        int carry = 0;
        while (p != null || q != null)
        {
            int x = (p != null) ? p.val : 0;
            int y = (q != null) ? q.val : 0;
            int sum = carry + x + y;
            carry = sum / 10;
            curr.next = new ListNode(sum % 10);
            curr = curr.next;
            if (p != null)
                p = p.next;
            if (q != null)
                q = q.next;
        }
        if (carry > 0)
        {
            curr.next = new ListNode(carry);
        }
        return dummyHead.next;
    }
}
