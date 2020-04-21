package com.joker.medium;

import com.joker.TreeNode;

public class ConstructBinarySearchTreeFromPreorderTraversal_1008
{
    static int index = 0;
    public TreeNode bstFromPreorder(int[] preorder)
    {
        index = 0;
        return helper(preorder, Integer.MAX_VALUE, Integer.MIN_VALUE);
    }

    public TreeNode helper(int[] preorder, int max, int min)
    {
        if(preorder == null || index < 0 || index >= preorder.length || preorder[index] > max || preorder[index] < min)
            return null;
        TreeNode node = new TreeNode(preorder[index]);
        index++;
        node.left = helper(preorder, Math.min(node.val, max), min);
        node.right = helper(preorder, max, Math.max(node.val, min));
        return node;
    }

}
