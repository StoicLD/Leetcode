package com.joker.easy;

import com.joker.TreeNode;

public class DiameterOfBinaryTree
{
    private int maxDepth;
    public int diameterOfBinaryTree(TreeNode root)
    {
        maxDepth = 0;
        diameterOfBinaryTreeHelper(root);
        return Math.max(maxDepth - 1, 0);
    }

    public int diameterOfBinaryTreeHelper(TreeNode node)
    {
        if(node == null)
            return 0;
        else
        {
            int leftDepth = diameterOfBinaryTreeHelper(node.left);
            int rightDepth = diameterOfBinaryTreeHelper(node.right);
            if(leftDepth + rightDepth + 1 > maxDepth)
                maxDepth = leftDepth + rightDepth + 1;
            return Math.max(leftDepth, rightDepth) + 1;
        }
    }



}
