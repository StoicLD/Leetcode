package com.joker.medium;

import com.joker.TreeNode;

import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Stack;

/**
 * This is for LeetCode 103 Binary Tree Zigzag Level Order Traversal
 */
public class BinaryTreeZigzagLevelOrderTraversal
{
    /**
     * 使用两个stack解决，一个从左到右，一个从右到左
     *
     * @param root 根节点
     * @return list of lists
     */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root)
    {
        List<List<Integer>> result = new LinkedList<>();
        if (root == null)
            return result;
        //use two stack
        Stack<TreeNode> leftToRight = new Stack<>();
        Stack<TreeNode> rightToLeft = new Stack<>();
        leftToRight.add(root);
        while (!leftToRight.empty() || !rightToLeft.empty())
        {
            List<Integer> list = new LinkedList<>();
            if (!leftToRight.empty())
            {
                while (!leftToRight.empty())
                {
                    TreeNode node = leftToRight.pop();
                    list.add(node.val);
                    if (node.left != null)
                        rightToLeft.add(node.left);
                    if (node.right != null)
                        rightToLeft.add(node.right);
                }
            }
            else
            {
                while (!rightToLeft.empty())
                {
                    TreeNode node = rightToLeft.pop();
                    list.add(node.val);
                    if (node.right != null)
                        leftToRight.add(node.right);
                    if (node.left != null)
                        leftToRight.add(node.left);
                }
            }
            result.add(list);
        }
        return result;
    }

    /**
     * 第二种递归的解法，需要确定层数
     * 针对奇数偶数的不同，添加到list的头部或者尾部
     * @param root 根节点
     * @return list of lists
     */
    public List<List<Integer>> zigzagLevelOrderRecursive(TreeNode root)
    {
        List<List<Integer>> result = new LinkedList<>();
        zigzagHelper(root, 1, result);
        return result;
    }

    public void zigzagHelper(TreeNode node, int level, List<List<Integer>> list)
    {
        if (node == null)
            return;
        if (level > list.size())
            list.add(new LinkedList<>());
        if (level % 2 == 0)
        {
            //从左往右
            //插入到头部
            list.get(level - 1).add(0, node.val);
        }
        else
        {
            //从右往左
            //插入到尾部
            list.get(level - 1).add(node.val);
        }
        zigzagHelper(node.left, level + 1, list);
        zigzagHelper(node.right, level + 1, list);
    }
}
