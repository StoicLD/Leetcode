package com.joker.medium;

import javax.crypto.Cipher;

/**
 * This is for LeetCode 117 Populating Next Right Pointers in Each Node II
 */
public class PopulatingNextRightPointersInEachNode2
{
    private static class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }
    public Node connect(Node root)
    {
        Node pre = root;
        Node curr = null;
        while (pre != null)
        {
            curr = pre;
            Node prepareForNext = null;
            while (curr != null)
            {
                if(curr.left != null && curr.right != null)
                {
                    curr.left.next = curr.right;
                    if(prepareForNext != null)
                    {
                        prepareForNext.next = curr.left;
                    }
                    prepareForNext = curr.right;
                }
                else
                {
                    if(curr.left != null)
                    {
                        if(prepareForNext != null)
                            prepareForNext.next = curr.left;
                        prepareForNext = curr.left;
                    }
                    else if(curr.right != null)
                    {
                        if(prepareForNext != null)
                            prepareForNext.next = curr.right;
                        prepareForNext = curr.right;
                    }
                }
                curr = curr.next;
            }

            while (pre != null)
            {
                if (pre.left != null)
                {
                    pre = pre.left;
                    break;
                }
                else if (pre.right != null)
                {
                    pre = pre.right;
                    break;
                }
                pre = pre.next;
            }
        }
        return root;
    }

    //based on level order traversal
    public void connectOthers(Node root) {
        //这个方法比我的要精妙一些
        //我是在每次大循环结束需要切换到下一层的时候，在用一个while循环来寻找下一层的第一个节点
        //但是这个步骤实际上在内部循环连接next的时候就可以顺带的做掉了
        Node head = null; //head of the next level
        Node prev = null; //the leading node on the next level
        Node cur = root;  //current node of current level

        while (cur != null) {

            while (cur != null) { //iterate on the current level
                //left child
                if (cur.left != null) {
                    if (prev != null) {
                        prev.next = cur.left;
                    } else {
                        head = cur.left;
                    }
                    prev = cur.left;
                }
                //right child
                if (cur.right != null) {
                    if (prev != null) {
                        prev.next = cur.right;
                    } else {
                        head = cur.right;
                    }
                    prev = cur.right;
                }
                //move to next node
                cur = cur.next;
            }

            //move to next level
            cur = head;
            head = null;
            prev = null;
        }

    }
}
