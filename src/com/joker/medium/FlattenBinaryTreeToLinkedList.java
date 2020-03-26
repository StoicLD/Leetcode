package com.joker.medium;

import com.joker.TreeNode;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * This is for LeetCode 114 Flatten Binary Tree to Linked List
 */
public class FlattenBinaryTreeToLinkedList
{
    private TreeNode prev = null;

    public void flatten(TreeNode root)
    {
        if (root == null)
            return;
        LinkedList<TreeNode> nodes = new LinkedList<>();
        flattenHelper(root, nodes);
        TreeNode pre = root;
        for (Iterator<TreeNode> it = nodes.listIterator(1); it.hasNext(); )
        {
            TreeNode temp = pre;
            temp.left = null;
            pre = it.next();
            temp.right = pre;
        }
    }

    public void flattenHelper(TreeNode node, LinkedList<TreeNode> nodes)
    {
        if (node == null)
            return;
        nodes.add(node);
        flattenHelper(node.left, nodes);
        flattenHelper(node.right, nodes);
    }

    public void flatten2(TreeNode root)
    {
        flattenHelper2(root);
    }

    public TreeNode flattenHelper2(TreeNode node)
    {
        if(node == null)
            return null;
        if(node.left != null)
        {
            //next不会为空的，至少是自己
            TreeNode next = flattenHelper2(node.left);
            next.right = node.right;
            TreeNode currNext = flattenHelper2(node.right);
            node.right = node.left;
            node.left = null;
            if(currNext != null)
                return currNext;
            else
                return next;
        }
        else if(node.right != null)
        {
            TreeNode next = flattenHelper2(node.right);
            return next;
        }
        else
            return node;
    }

    public void flattenOthers(TreeNode root) {
        if (root == null)
            return;
        flatten(root.right);
        flatten(root.left);
        root.right = prev;
        root.left = null;
        prev = root;
    }

    public static void main(String[] args)
    {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.right = new TreeNode(3);
        root.left.right.right = new TreeNode(4);
        root.left.right.right.left =new TreeNode(5);
        FlattenBinaryTreeToLinkedList f = new FlattenBinaryTreeToLinkedList();
        f.flatten(root);
    }

}
