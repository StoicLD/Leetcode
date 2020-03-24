package com.joker.medium;

import java.util.Stack;

/**
 * This is LeetCode 98 Validate Binary Search Tree
 *
 * @author Joker
 * @version 1.0
 * @since 2020.3.16
 */
public class ValidateBinarySearchTree
{
    /**
     * 第一种方法，递归判定
     * @param root 根节点
     * @return 是否是valid的BST
     */
    public boolean isValidBST(TreeNode root)
    {
        //这里记得用Long，因为输入会有Interger最大值
        return isValidBSTHelper(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    /**
     * Helper函数，记得
     * @param node 当前节点
     * @param minVal 当前node的最小值（后续所有值必须小于这个数）
     * @param maxVal 当前node的最大值（后续所有值必须大于这个数）
     * @return
     */
    public boolean isValidBSTHelper(TreeNode node, long minVal, long maxVal)
    {
        if(node == null)
            return true;
        boolean isValid = true;
        if(node.val <= minVal || node.val >= maxVal)
            return false;
        return isValidBSTHelper(node.left, minVal, node.val) && isValidBSTHelper(node.right, node.val, maxVal);
    }
        

    /**
     * 使用Stack来判断BST
     * @param root 根节点1
     * @return 是否valid
     */
    public boolean isValidBSTStack(TreeNode root)
    {
        if(root == null)
            return true;
        //放进栈中的不再访问右节点，同时visit过的节点不放入栈
        Stack<TreeNode> nodeStack = new Stack<>();
        TreeNode currNode = root;
        Integer currVal = null;
        while (currNode != null || !nodeStack.empty())
        {
            //这个时候说明要弹出栈了，栈里的节点只会添加右节点
            if(currNode == null)
            {
                TreeNode visitNode = nodeStack.pop();
                if(currVal != null && currVal >= visitNode.val)
                {
                    return false;
                }
                currVal = visitNode.val;
                currNode = visitNode.right;
            }
            else
            {
                nodeStack.push(currNode);
                currNode = currNode.left;
            }
        }
        return true;
    }


    /**
     * LeetCode上的高赞回答，实际上也是使用Stack inorder遍历BST
     * 但是效率貌似更高
     * @param root
     * @return
     */
    public boolean isValidBSTOthers(TreeNode root) {
        if (root == null) return true;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode pre = null;
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if(pre != null && root.val <= pre.val) return false;
            pre = root;
            root = root.right;
        }
        return true;
    }

    public static void main(String[] args)
    {
        TreeNode root = new TreeNode(34);
        TreeNode left1 = new TreeNode(-6);
        TreeNode left2 = new TreeNode(-21);
        root.left = left1;
        left1.left = left2;

        ValidateBinarySearchTree vbst = new ValidateBinarySearchTree();
        System.out.println(vbst.isValidBSTStack(root));
    }
}
