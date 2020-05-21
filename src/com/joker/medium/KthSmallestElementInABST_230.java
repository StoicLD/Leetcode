package com.joker.medium;

import com.joker.TreeNode;

import java.util.Stack;

public class KthSmallestElementInABST_230
{
    //这就是个中序遍历的事
    public int kthSmallest(TreeNode root, int k)
    {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode ptrNode = root;
        int kthSmallest = 0;
        while (!stack.empty() || ptrNode != null)
        {
            while (ptrNode != null)
            {
                stack.push(ptrNode);
                ptrNode = ptrNode.left;
            }
            TreeNode kthSmallestNode = stack.pop();

            kthSmallest++;
            if(kthSmallest == k)
                return kthSmallestNode.val;
            ptrNode = kthSmallestNode.right;
        }
        return root.val;
    }

}
