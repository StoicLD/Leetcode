package com.joker.other;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;

public class FirstUniqueNumber
{
    @Test
    public void mainTest()
    {
        int[] nums = new int[]{1,2,3,4};
        FirstUnique fu = new FirstUnique(nums);
        Assert.assertEquals(1, fu.showFirstUnique());
        fu.add(2);
        Assert.assertEquals(1, fu.showFirstUnique());
        fu.add(1);
        Assert.assertEquals(3, fu.showFirstUnique());

        int[] nums2 = new int[]{7,7,7,7,7};
        FirstUnique fu2 = new FirstUnique(nums2);
        Assert.assertEquals(-1, fu2.showFirstUnique());
        fu2.add(7);
        Assert.assertEquals(-1, fu2.showFirstUnique());
        fu2.add(3);
        Assert.assertEquals(3, fu2.showFirstUnique());
        fu2.add(3);
        Assert.assertEquals(-1, fu2.showFirstUnique());

    }
}

class FirstUnique
{
    private static class Node
    {
        int value;
        Node next;
        Node pre;
        public Node(int _v)
        {
            value = _v;
            pre = null;
            next = null;
        }

        public Node(int _v, Node _pre, Node _next)
        {
            value = _v;
            pre = _pre;
            next = _next;
        }
    }

    Node head;
    Node tail;
    HashMap<Integer, Node> map;
    public FirstUnique(int[] nums)
    {
        map = new HashMap<>();
        if(nums == null || nums.length == 0)
            return;
        /*
        head = new Node(nums[0]);
        tail = head;*/
        head = null;
        tail = null;
        //Node nodePointer = null;
        for(int i : nums)
        {
            if(map.containsKey(i))
            {
                deleteNode(map.get(i));
                //这一步可有可无
            }
            else
            {
                /*Node node = new Node(i, nodePointer, null);
                addNode(node);
                if(nodePointer != null)
                    nodePointer.next = node;
                nodePointer = node;
                */
                add(i);
            }
        }
    }

    public int showFirstUnique()
    {
        return head != null ? head.value : -1;
    }

    public void add(int value)
    {
        Node node = new Node(value);
        addNode(node);
    }

    private void addNode(Node node)
    {
        if(map.containsKey(node.value))
        {
            deleteNode(map.get(node.value));
        }
        else
        {
            if(head == null)
                head = node;
            if(tail != null)
                tail.next = node;
            node.pre = tail;
            tail = node;
            map.put(node.value, node);
        }
    }

    private void deleteNode(Node node)
    {
        Node pre = node.pre;
        Node next = node.next;
        if(node == head)
            head = next;
        if(node == tail)
            tail = pre;
        if(pre != null)
            pre.next = next;
        if(next != null)
            next.pre = pre;
        node.pre = null;
        node.next = null;
    }
}