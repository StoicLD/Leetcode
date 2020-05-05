package com.joker.easy;

import java.util.HashMap;

public class FirstUniqueCharacterInAString_387
{
    private static class LinkNode
    {
        char value;
        int position;
        LinkNode next;
        LinkNode pre;

        public LinkNode(char _value, int pos)
        {
            position = pos;
            value = _value;
            next = null;
            pre = null;
        }


        public LinkNode(char _value, int pos, LinkNode _pre, LinkNode _next)
        {
            position = pos;
            value = _value;
            pre = _pre;
            next = _next;
        }
    }

    private HashMap<Character, LinkNode> map;
    private LinkNode head;
    private LinkNode tail;

    public int firstUniqChar(String s)
    {
        map = new HashMap<>();
        if (s == null || s.length() == 0)
            return -1;
        head = null;
        tail = null;
        int i = 0;
        for (char c : s.toCharArray())
        {
            if (map.containsKey(c))
            {
                deleteLinkNode(map.get(c));
            }
            else
            {
                add(c, i);
            }
            i++;
        }
        if (head != null)
            return map.get(head.value).position;
        else
            return -1;
    }

    public void add(char value, int pos)
    {
        LinkNode node = new LinkNode(value, pos);
        addLinkNode(node);
    }

    private void addLinkNode(LinkNode node)
    {
        if (map.containsKey(node.value))
        {
            deleteLinkNode(map.get(node.value));
        }
        else
        {
            if (head == null)
                head = node;
            if (tail != null)
                tail.next = node;
            node.pre = tail;
            tail = node;
            map.put(node.value, node);
        }
    }

    private void deleteLinkNode(LinkNode node)
    {
        LinkNode pre = node.pre;
        LinkNode next = node.next;
        if (node == head)
            head = next;
        if (node == tail)
            tail = pre;
        if (pre != null)
            pre.next = next;
        if (next != null)
            next.pre = pre;
        node.pre = null;
        node.next = null;
    }

    public int firstUniqCharOthers(String s)
    {
        HashMap<Character, Integer> count = new HashMap<Character, Integer>();
        int n = s.length();
        // build hash map : character and how often it appears
        for (int i = 0; i < n; i++)
        {
            char c = s.charAt(i);
            count.put(c, count.getOrDefault(c, 0) + 1);
        }

        // find the index
        for (int i = 0; i < n; i++)
        {
            if (count.get(s.charAt(i)) == 1)
                return i;
        }
        return -1;
    }
}
