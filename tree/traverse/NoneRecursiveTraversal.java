package tree.traverse;
import tree.*;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class NoneRecursiveTraversal {

    public void process (TreeNode node) {}

    public void preOrder(TreeNode root) {
        /*
          H - L - R

          1. push node to the stack
          2. pop up one node N, and process N
          3. push R child to the stack, and then push L child to the stack. (because FILO: R - L -> L - R)
          4. repeat 1 - 4 until the stack is empty.
         */
        if (root == null) {
            return;
        }

        Stack <TreeNode> stack = new Stack<>();
        TreeNode node;
        stack.push(root);
        while (!stack.isEmpty()) {
            node = stack.pop();
            process(node);
            if (node.right != null) {
                stack.push(node.right);
            }

            if (node.left != null) {
                stack.push(node.left);
            }
        }

        return;
    }

    public void inOrder(TreeNode root) {
        /*
          L - H - R

          1. push all the left child to the stack.
          2. pop and process one node.
          3. do the same thing to the R child.

         */
        Stack <TreeNode> stack = new Stack<>();
        TreeNode node;

        node = root;
        while (!stack.isEmpty() || node != null) {
            if (node != null) {
                /* push all the left child to the stack */
                stack.push(node);
                node = node.left;
            } else {
                /* pop and process one node */
                node = stack.pop();
                process(node);

                /* do the same thing to the right child */
                node = node.right;
            }
        }
        return ;
    }



    public void postOrder(TreeNode root) {
        /*
          R - L - H
          if (node == null) return null;

          recursion (node.left)
          recursion (node.right)
          process (node);

          prepare two stacks, a stack and a collector

          1. push node to the stack
          2. pop up one node, push to the collector
          3. push L child to the stack, and then push R child to the stack.
          (because FIFO -> FIFO: stack (L - R) -> collector -> (R - L))

         */
        if (root == null) {
            return;
        }

        Stack <TreeNode> stack = new Stack<>();
        Stack <TreeNode> collector = new Stack<>();
        TreeNode node;
        stack.push(root);
        while (!stack.isEmpty()) {
            node = stack.pop();
            collector.push(node);   // FILO H
            if (node.left != null) {
                stack.push(node.left); // FIFO
            }
            if (node.right != null) {
                stack.push(node.right); // FIFO
            }
        }

        while (!collector.isEmpty()) {
            node = collector.pop();
            process(node);
        }
        return;
    }

    public void levelOrder(TreeNode root) {
        Queue <TreeNode> queue = new LinkedList<>();
        queue.add(root);

        TreeNode node;
        while (!queue.isEmpty()) {
            node = queue.poll();
            process(node);
            if (node.left != null) {
                queue.add(node.left);
            }

            if (node.right != null) {
                queue.add(node.right);
            }
        }
    }
}
