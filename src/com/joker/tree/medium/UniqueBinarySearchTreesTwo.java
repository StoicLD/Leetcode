package com.joker.tree.medium;

import java.lang.reflect.GenericArrayType;
import java.util.*;

import static java.util.List.of;

public class UniqueBinarySearchTreesTwo
{
    /**
     * 先试一下浅拷贝的版本看看能不能过
     * @param n
     * @return
     */
    public List<TreeNode> generateTrees(int n)
    {
        if(n == 0)
            return new LinkedList<>();
        List<List<TreeNode> > constructTreeList = new LinkedList<>();
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

    public static void main(String[] args)
    {
        UniqueBinarySearchTreesTwo ub = new UniqueBinarySearchTreesTwo();
        ub.generateTrees(3);
    }

}
