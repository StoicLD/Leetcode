package com.joker.tree.hard;

import com.joker.tree.medium.TreeNode;

import java.util.*;

public class RecoverBinarySearchTree
{
    /**
     * 第一种方法，直接采用inorder遍历加入数组，然后
     * 使用两个指针，分别从头尾开始相对方向遍历jiance
     * @param root 根节点
     */
    public void recoverTree(TreeNode root)
    {
        List<TreeNode> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        if(root == null || (root.left == null && root.right == null))
            return;
        TreeNode currNode = root;
        //inorder遍历
        while(currNode != null || !stack.empty())
        {
            while (currNode != null)
            {
                stack.push(currNode);
                currNode = currNode.left;
            }
            currNode = stack.pop();
            list.add(currNode);
            currNode = currNode.right;
        }
        int leftPointer = 0;
        int rightPointer = list.size() - 1;
        for(; leftPointer < list.size() - 1; leftPointer++)
        {
            if(list.get(leftPointer).val > list.get(leftPointer + 1).val)
            {
                break;
            }
        }
        for(; rightPointer > 0; rightPointer--)
        {
            if(list.get(rightPointer).val < list.get(rightPointer - 1).val)
            {
                break;
            }
        }
        if(leftPointer != rightPointer)
        {
            TreeNode largerNode = list.get(leftPointer);
            TreeNode smallerNode = list.get(rightPointer);
            int temp = largerNode.val;
            largerNode.val = smallerNode.val;
            smallerNode.val = temp;
        }
//        for(TreeNode node : list)
//        {
//            System.out.println(node.val);
//        }
    }

    public void recoverTree2(TreeNode root)
    {
        Stack<TreeNode> stack = new Stack<>();
        if(root == null || (root.left == null && root.right == null))
            return;
        TreeNode currNode = root;
        TreeNode preNode = null;
        TreeNode leftSwapNode = null;
        TreeNode rightSwapNode = null;
        //inorder遍历
        while(currNode != null || !stack.empty())
        {
            while (currNode != null)
            {
                stack.push(currNode);
                currNode = currNode.left;
            }
            currNode = stack.pop();
            if(preNode != null)
            {
                if(preNode.val > currNode.val)
                {
                    if(leftSwapNode == null)
                    {
                        leftSwapNode = preNode;
                        rightSwapNode = currNode;
                    }
                    else
                    {
                        rightSwapNode = currNode;
                        break;
                    }
                }
            }
            preNode = currNode;
            currNode = currNode.right;
        }

        if(leftSwapNode != null && rightSwapNode != null)
        {
            int temp = leftSwapNode.val;
            leftSwapNode.val = rightSwapNode.val;
            rightSwapNode.val = temp;
        }
    }

    /**
     * 使用MorrisTraversal，只占据O(1)的空间
     * 在inorder遍历的同时临时的改变树的结构（找到predecessor直接前继）
     * 让上一个节点的right变成curr当前节点
     * @param root
     */
    public void recoverTree3(TreeNode root)
    {
        if(root == null || (root.left == null && root.right == null))
            return;
        TreeNode curr = root;
        //这个pre并不是直接前驱，而是上一个节点
        TreeNode pre = null;
        //这个是真正的直接前驱
        TreeNode preValueNode = null;
        TreeNode firstSwapNode = null;
        TreeNode secondSwapNode = null;
        while (curr != null)
        {
            if(curr.left == null)
            {
                if(preValueNode != null && preValueNode.val > curr.val)
                {
                    if(firstSwapNode == null)
                    {
                        firstSwapNode = preValueNode;
                        secondSwapNode = curr;
                    }
                    else
                    {
                        secondSwapNode = curr;
                    }
                }
                preValueNode = curr;
                curr = curr.right;
            }
            else
            {
                pre = curr.left;
                while (pre.right != null && pre.right != curr)
                {
                    pre = pre.right;
                }

                //临时的修改树结构
                if(pre.right == null)
                {
                    pre.right = curr;
                    curr = curr.left;
                }
                else
                {
                    //撤销之前修改的树结构
                    pre.right = null;
                    //这里是inorder visit curr的地方，做处理
                    if(preValueNode != null && preValueNode.val > curr.val)
                    {
                        if(firstSwapNode == null)
                        {
                            firstSwapNode = preValueNode;
                            secondSwapNode = curr;
                        }
                        else
                        {
                            //这里不能break，不然会导致撤销不了
                            secondSwapNode = curr;
                        }
                    }
                    preValueNode = curr;
                    curr = curr.right;
                }
            }
        }
        if(firstSwapNode != null && secondSwapNode != null)
        {
            int temp = firstSwapNode.val;
            firstSwapNode.val = secondSwapNode.val;
            secondSwapNode.val = temp;
        }
    }

    /**
     * 同样使用使用MorrisTraversal，但是略有一点不同
     * @param root 树的根节点
     */
    public void recoverTreeOthers(TreeNode root) {
        TreeNode pre = null;
        TreeNode first = null, second = null;
        // Morris Traversal
        TreeNode temp = null;
        while(root!=null){
            if(root.left!=null){
                // connect threading for root
                temp = root.left;
                while(temp.right!=null && temp.right != root)
                    temp = temp.right;
                // the threading already exists
                if(temp.right!=null){
                    if(pre!=null && pre.val > root.val){
                        if(first==null){first = pre;second = root;}
                        else{second = root;}
                    }
                    pre = root;

                    temp.right = null;
                    root = root.right;
                }else{
                    // construct the threading
                    temp.right = root;
                    root = root.left;
                }
            }else{
                if(pre!=null && pre.val > root.val){
                    if(first==null){first = pre;second = root;}
                    else{second = root;}
                }
                pre = root;
                root = root.right;
            }
        }
        // swap two node values;
        if(first!= null && second != null){
            int t = first.val;
            first.val = second.val;
            second.val = t;
        }
    }

    public static void main(String[] args)
    {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(3);
        root.left.right = new TreeNode(2);
        RecoverBinarySearchTree rbst = new RecoverBinarySearchTree();
        rbst.recoverTree3(root);
    }

}
