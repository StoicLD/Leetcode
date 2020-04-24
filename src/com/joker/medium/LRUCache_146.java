package com.joker.medium;

import org.junit.Assert;

import java.util.HashMap;

/**
 * 实现LRU Cache
 * 也就是当cache满的时候，优先排除/挤兑掉 最远使用的那个位置
 * 使用HashMap 和 Double LinkedList来实现
 * HashMap可以是get是常数级别的时间复杂度
 * HashMap的Key还是Key，但是Value是一个DoubleLinkedList的节点（拥有key和value两个属性，以及next和pre两个分别
 * 指向前一个和后一个的属性）
 * 这样我们put和get已有元素的时候可以直接找到对应的Node，然后把这个Node放到LinkedList的队尾
 * 当Cache已满的时候，可以直接删除头部节点，同样的把新的node放到LinkedList的尾部
 */
public class LRUCache_146
{
    private static class LinkNode
    {
        int value;
        int key;
        LinkNode next;
        LinkNode pre;
        public LinkNode(int _key, int _value, LinkNode _pre, LinkNode _next)
        {
            key = _key;
            value = _value;
            pre = _pre;
            next = _next;
        }
    }

    HashMap<Integer, LinkNode> map;
    LinkNode head;
    LinkNode tail;
    int size;
    public LRUCache_146(int capacity)
    {
        map = new HashMap<>();
        size = capacity;
        head = null;
        tail = null;
    }

    public int get(int key)
    {
        LinkNode node = map.get(key);
        if(node == null)
            return -1;
        else
        {
            //修改队列，从中间位置移动到队尾
            if(node != tail)
            {
                if(node == head)
                    head = node.next;

                LinkNode currPre = node.pre;
                LinkNode currNext = node.next;
                if(currPre != null)
                    currPre.next = currNext;
                if(currNext != null)
                    currNext.pre = currPre;
                tail.next = node;
                node.next = null;
                node.pre = tail;
                tail = node;
            }
            return node.value;
        }
    }

    public void put(int key, int value)
    {
        if(get(key) != -1)
        {
            LinkNode curr = map.get(key);
            curr.value = value;
            if(curr == tail)
                return;
            //从位置中移除，放到队尾
            LinkNode curPre = curr.pre;
            LinkNode currNext = curr.next;
            if(curPre != null)
                curPre.next = currNext;
            if(currNext != null)
                currNext.pre = curPre;
            if(curr == head)
                head = currNext;
            tail = curr;
        }
        else
        {
            if(map.size() == size)
            {
                //要删除一个节点，头部去掉
                LinkNode newNode = new LinkNode(key, value, tail, null);
                map.remove(head.key);

                tail.next = newNode;
                tail = newNode;
                map.put(key, newNode);

                LinkNode deleteHead = head;
                head = head.next;
                deleteHead.next = null;
                head.pre = null;
            }
            else
            {
                //正常状况，直接加在尾部，需要考虑第一个添加的问题
                if(head == null)
                {
                    head = new LinkNode(key, value, null, null);
                    tail = head;
                    map.put(key, head);
                }
                else
                {
                    tail.next = new LinkNode(key, value, tail, null);
                    tail = tail.next;
                    map.put(key, tail);
                }
            }
        }
    }

    public static void main(String[] args)
    {
        LRUCache_146 lru1 = new LRUCache_146(1);
        lru1.put(1, 1);
        Assert.assertEquals(1, lru1.get(1));
        lru1.put(2, 2);
        Assert.assertEquals(-1, lru1.get(1));
        Assert.assertEquals(2, lru1.get(2));

        LRUCache_146 lru2 = new LRUCache_146(3);
        lru2.put(1, 1);
        lru2.put(2, 2);
        lru2.put(3, 3);
        Assert.assertEquals(2, lru2.get(2));
        Assert.assertEquals(1, lru2.get(1));
        lru2.put(4, 4);
        Assert.assertEquals(-1, lru2.get(3));
        Assert.assertEquals(4, lru2.get(4));

        lru2.put(4, 11);
        Assert.assertEquals(11, lru2.get(4));

    }

}
