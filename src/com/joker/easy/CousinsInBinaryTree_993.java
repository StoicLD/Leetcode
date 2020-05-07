package com.joker.easy;

import com.joker.TreeNode;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;

public class CousinsInBinaryTree_993
{
    public boolean isCousins(TreeNode root, int x, int y)
    {
        if (root == null)
            return false;
        Queue<Integer> numQueue = new ArrayDeque<>();
        Queue<TreeNode> nodeQueue = new ArrayDeque<>();
        TreeNode[] nodes = new TreeNode[2];
        //TreeNode firstNode = null;
        //nodes[0] = firstNode;
        int[] firstDepth = new int[]{-1, 0};
        numQueue.add(0);
        nodeQueue.add(root);
        while (!nodeQueue.isEmpty() && !numQueue.isEmpty())
        {
            TreeNode node = nodeQueue.poll();
            int depth = numQueue.poll();
            TreeNode leftNode = node.left;
            TreeNode rightNode = node.right;
            if (leftNode != null && rightNode != null)
            {
                if ((leftNode.val == x || leftNode.val == y) && (rightNode.val == x || rightNode.val == y))
                {
                    return false;
                }
            }
            if (leftNode != null)
            {
                if (CousinsHelper(leftNode, depth, nodes, firstDepth, x, y))
                    return true;
                else
                {
                    numQueue.add(depth + 1);
                    nodeQueue.add(leftNode);
                }
            }
            if (rightNode != null)
            {
                if (CousinsHelper(rightNode, depth, nodes, firstDepth, x, y))
                    return true;
                else
                {
                    numQueue.add(depth + 1);
                    nodeQueue.add(rightNode);
                }
            }
        }
        return false;
    }

    public boolean CousinsHelper(TreeNode node, int depth, TreeNode[] nodes, int[] firstDepth, int x, int y)
    {
        if (node.val == x || node.val == y)
        {
            if (nodes[0] != null)
            {
                if (depth + 1 == firstDepth[0])
                    return true;
            }
            else
            {
                nodes[0] = node;
                firstDepth[0] = depth + 1;
            }
        }
        return false;
    }

    /**
     * 简洁实用的层序遍历
     * @param root 根节点
     * @param A 数字A
     * @param B 数字B
     * @return 是否存在Cousin
     */
    public boolean isCousins2(TreeNode root, int A, int B)
    {
        if (root == null)
            return false;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty())
        {
            int size = queue.size();
            boolean isAexist = false;
            boolean isBexist = false;
            for (int i = 0; i < size; i++)
            {
                TreeNode cur = queue.poll();
                if (cur.val == A)
                    isAexist = true;
                if (cur.val == B)
                    isBexist = true;
                if (cur.left != null && cur.right != null)
                {
                    if (cur.left.val == A && cur.right.val == B)
                    {
                        return false;
                    }
                    if (cur.left.val == B && cur.right.val == A)
                    {
                        return false;
                    }
                }
                if (cur.left != null)
                {
                    queue.offer(cur.left);
                }
                if (cur.right != null)
                {
                    queue.offer(cur.right);
                }
            }
            if (isAexist && isBexist)
                return true;
        }
        return false;
    }
}
