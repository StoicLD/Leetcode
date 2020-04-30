package com.joker.other;

import com.joker.TreeNode;

public class CheckIfAStringIsAValidSequence
{
    public boolean isValidSequence(TreeNode root, int[] arr)
    {
        return isValidHelper(root, arr, 0);
    }

    public boolean isValidHelper(TreeNode node, int[] arr, int start)
    {
        if(start >= arr.length || node == null)
            return false;
        if(node.val != arr[start])
            return false;
        if(start == arr.length - 1 && node.left == null && node.right == null)
            return true;
        return isValidHelper(node.left, arr, start + 1) || isValidHelper(node.right, arr, start + 1);
    }

}
