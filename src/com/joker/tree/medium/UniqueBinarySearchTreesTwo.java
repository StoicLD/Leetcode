package com.joker.tree.medium;

import java.util.*;

import static java.util.List.of;

public class UniqueBinarySearchTreesTwo
{
    /**
     * 先试一下浅拷贝的版本看看能不能过
     * @param n 输入规模
     * @return 包含有n个节点的全部结构上不同的BST的集合
     */
    public List<TreeNode> generateTrees(int n)
    {
        if(n == 0)
            return new LinkedList<>();
        List<List<TreeNode> > constructTreeList = new LinkedList<>();
        //这里添加一个空链表，是为了满足
        LinkedList<TreeNode> s = new LinkedList<>();
        s.add(null);
        constructTreeList.add(s);
        constructTreeList.add(new LinkedList<>(Arrays.asList(new TreeNode(1))));

        for(int i = 2; i <= n; i++)
        {
            List<TreeNode> currList = new LinkedList<>();
            for(int j = 0; j < i; j++)
            {
                //C(0) * C(n - 1)的自由组合
                List<TreeNode> list1 = constructTreeList.get(j);
                List<TreeNode> list2 = constructTreeList.get(i - 1 - j);

                for(int leftIndex = 0; leftIndex < list1.size(); leftIndex++)
                {
                    TreeNode leftRoot = list1.get(leftIndex);
                    for(int rightIndex = 0; rightIndex < list2.size(); rightIndex++)
                    {
                        TreeNode root = new TreeNode(i);
                        root.left = copyTree(leftRoot);
                        root.right = copyTree(list2.get(rightIndex));
                        currList.add(root);
                    }
                }
            }
            constructTreeList.add(currList);
        }
        
        //完成构造之后，先序遍历
        List<TreeNode> result = constructTreeList.get(n);
        for(TreeNode root : result)
        {
            Stack<TreeNode> stack = new Stack<>();
            int val = 1;
            TreeNode curr = root;
            while (curr != null || !stack.isEmpty())
            {
                while (curr != null)
                {
                    stack.push(curr);
                    curr = curr.left;
                }
                curr = stack.pop();
                curr.val = val;
                val++;
                curr = curr.right;
            }
        }
        return result;
    }

    public TreeNode copyTree(TreeNode root)
    {
        if(root == null)
            return null;
        TreeNode copyRoot = new TreeNode(root.val);
        copyRoot.left = copyTree(root.left);
        copyRoot.right = copyTree(root.right);
        return copyRoot;
    }

    /**
     * 这事实上是一种DP的方法
     * @param n 输入规模
     * @return 包含有n个节点的全部结构上不同的BST的集合
     */
    public List<TreeNode> generateTrees2(int n)
    {
        if(n == 0)
            return new LinkedList<>();
        //每个元素表示节点总数为x(x小于等于n)的BST所拥有的全部子树
        List<List<TreeNode> > constructTreeList = new ArrayList<>(n + 1);
        //这里添加一个空的链表，是为了后续代码不做corner case的处理
        LinkedList<TreeNode> s = new LinkedList<>();
        s.add(null);
        constructTreeList.add(s);
        constructTreeList.add(new LinkedList<>(Arrays.asList(new TreeNode(1))));

        //从最小的BST开始构造
        for(int i = 2; i <= n; i++)
        {
            List<TreeNode> currList = new LinkedList<>();
            for(int j = 0; j < i; j++)
            {
                //C(0) * C(n - 1)的自由组合
                //表示当前节点的左子树从list1中选择
                List<TreeNode> list1 = constructTreeList.get(j);
                //当前节点的右子树从list2中选择
                List<TreeNode> list2 = constructTreeList.get(i - 1 - j);

                for(int leftIndex = 0; leftIndex < list1.size(); leftIndex++)
                {
                    //当前节点的左子树
                    TreeNode leftRoot = list1.get(leftIndex);
                    for(int rightIndex = 0; rightIndex < list2.size(); rightIndex++)
                    {
                        //因为左子树大小为j，而左子树所有节点比root小，右子树所有节点比右子树大。
                        //因此root的值是j+1
                        TreeNode root = new TreeNode(j + 1);
                        //左子树无需调整
                        root.left = copyTreeGiveValue(leftRoot, 0);
                        currList.add(root);
                        //右子树所有数值需要加上j+1（表示比左子树和root所有节点都要大）
                        //如果不进行这个操作，右子树是从1到i-j-1的数值组成的BST
                        root.right = copyTreeGiveValue(list2.get(rightIndex), j + 1);
                    }
                }
            }
            constructTreeList.add(currList);
        }

        //完成构造之后，先序遍历
        return constructTreeList.get(n);
    }

    public TreeNode copyTreeGiveValue(TreeNode root, int addValue)
    {
        if(root == null)
            return null;
        TreeNode node = new TreeNode(root.val + addValue);
        node.left = copyTreeGiveValue(root.left, addValue);
        node.right = copyTreeGiveValue(root.right, addValue);
        return node;
    }


    public static void main(String[] args)
    {
        UniqueBinarySearchTreesTwo ub = new UniqueBinarySearchTreesTwo();
        ub.generateTrees2(3);
    }

}
