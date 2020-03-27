package com.joker.medium;

import com.joker.TreeNode;

/**
 * This is for LeetCode 129 Sum Root to Leaf Numbers
 */
public class SumRootToLeafNumbers
{
    public int sumNumbers(TreeNode root)
    {
        return sumNumbersHelper(root, 0);
    }

    public int sumNumbersHelper(TreeNode node, int preDigits)
    {
        if(node == null)
            return 0;
        int nextDigits = preDigits * 10 + node.val;
        if(node.left == null && node.right == null)
            return nextDigits;
        return sumNumbersHelper(node.left, nextDigits) + sumNumbersHelper(node.right, nextDigits);
    }

}
