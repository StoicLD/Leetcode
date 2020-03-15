package com.joker.tree.medium;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * This is LeetCode 94 Binary Tree Inorder Traversal
 *
 * @author Joker
 * @version 1.0
 * @since 2020.3.14
 */
public class BinaryTreeInorderTraversal
{
    /*
        Given a binary tree, return the inorder traversal of its nodes' values.
        Example:
        Input: [1,null,2,3]
           1
            \
             2
            /
           3

        Output: [1,3,2]
     */
    public static class TreeNode
    {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x)
        {
            val = x;
        }
    }

    /**
     * 方法一，递归的方法。写一个helper method然后递归就完事了
     * @param root 根节点
     * @return inorder中序遍历的结果
     */
    public List<Integer> inorderTraversalRecursive(TreeNode root)
    {
        List<Integer> list = new LinkedList<>();
        if(root == null)
            return list;
        recursiveHelper(root.left, list);
        list.add(root.val);
        recursiveHelper(root.right, list);
        return list;
    }

    public void recursiveHelper(TreeNode node, List<Integer> list)
    {
        if(node == null)
            return;
        recursiveHelper(node.left, list);
        list.add(node.val);
        recursiveHelper(node.right, list);
    }

    /**
     * 方法二，使用两个Stack，具体解析见文档
     * 基本思路是使用Stack来模拟递归栈，leftStack中的节点表明其left节点还未访问过
     * rightStack中的节点表示right节点还未访问过，但是left节点都已经访问过，因此弹出的时候
     * 可以直接加入结果中
     * @param root 根节点
     * @return inorder中序遍历的结果
     */
    public List<Integer> inorderTraversal(TreeNode root)
    {
        List<Integer> list = new LinkedList<>();
        if(root == null)
            return list;
        Stack<TreeNode> rightStack = new Stack<>();
        Stack<TreeNode> leftStack = new Stack<>();
        leftStack.push(root);
        while(!leftStack.empty() || !rightStack.empty())
        {
            if(!leftStack.empty())
            {
                TreeNode leftRemainNode = leftStack.pop();
                if (leftRemainNode.left != null)
                    leftStack.push(leftRemainNode.left);
                rightStack.push(leftRemainNode);
            }
            else if(!rightStack.empty())
            {
                TreeNode addNode = rightStack.pop();
                list.add(addNode.val);
                if(addNode.right != null)
                    leftStack.push(addNode.right);
            }
        }
        return list;
    }


    public static void main(String[] args)
    {

    }
}
