package com.joker.hard;

import com.joker.medium.TreeNode;
import com.joker.medium.ValidateBinarySearchTree;
import org.junit.Assert;

public class BinaryTreeMaximumPathSum
{
    static int maxSum;
    public int maxPathSum(TreeNode root)
    {
        if(root == null)
            return 0;
        maxSum = root.val;
        int rootSum = singlePathMaxSum(root.left) + singlePathMaxSum(root.right) + root.val;
        return Math.max(maxSum, rootSum);
    }

    public int singlePathMaxSum(TreeNode node)
    {
        //两个功能，一是计算以当前node为topNode的路径是否比maxSum大，是则更新
        //二是，返回包含当前node在内的单一路径最大值
        if(node == null)
            return 0;
        int leftSum = singlePathMaxSum(node.left);
        int rightSum = singlePathMaxSum(node.right);
        int currSum = leftSum + rightSum + node.val;
        if(currSum > maxSum)
            maxSum = currSum;
        return Math.max(0, Math.max(leftSum + node.val, rightSum + node.val));
    }

    public static void main(String[] args)
    {
        TreeNode root = new TreeNode(-10);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        BinaryTreeMaximumPathSum bt = new BinaryTreeMaximumPathSum();
        Assert.assertEquals(42, bt.maxPathSum(root));
    }
}
