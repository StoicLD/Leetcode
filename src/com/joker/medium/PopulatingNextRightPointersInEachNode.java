package com.joker.medium;

/**
 * This is for LeetCode 116 Populating Next Right Pointers in Each Node
 */
public class PopulatingNextRightPointersInEachNode
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
    };

    /**
     * 默认输入时perfect tree
     * @param root 根节点
     * @return
     */
    public Node connect(Node root)
    {
        if(root == null)
            return root;
        Node pre = root;
        Node curr = null;
        while (pre.left != null)
        {
            curr = pre;
            while (curr != null)
            {
                curr.left.next = curr.right;
                if(curr.next != null)
                {
                    curr.right.next = curr.next.left;
                }
                curr = curr.next;
            }
            pre = pre.left;
        }
        return root;
    }

    public Node connectRecursive(Node root)
    {
        return connectHelper(root, null);
    }

    public Node connectHelper(Node node, Node next)
    {
        if(node == null)
            return node;
        node.next = next;
        connectHelper(node.left, node.right);
        if(next != null)
            connectHelper(node.right, next.left);
        else
            connectHelper(node.right, null);
        return node;
    }

}
