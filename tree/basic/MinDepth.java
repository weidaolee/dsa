package tree.basic;
import tree.*;

import java.util.LinkedList;
import java.util.Queue;

public class MinDepth {
    public int minDepth(TreeNode root) {
        return dfs(root);
    }

    public int dfs(TreeNode node) {
        if (node == null) {
            return 0;
        }

        if (node.left == null && node.right == null) {
            return 1;
        }

        int leftDepth = dfs(node.left);
        int rightDepth = dfs(node.right);
        if (node.left == null || node.right == null) {
            return leftDepth + rightDepth + 1;
        }

        return leftDepth < rightDepth ? leftDepth + 1 : rightDepth + 1;

    }

    class QueueNode {
        TreeNode node;
        int depth;

        public QueueNode(TreeNode node, int depth) {
            this.node = node;
            this.depth = depth;
        }
    }

    public int bfs(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue <QueueNode> q = new LinkedList<>();
        q.add(new QueueNode(root, 1));
        QueueNode qNode;
        int depth;
        while (!q.isEmpty()) {
            qNode = q.poll();
            depth = qNode.depth;
            if (qNode.node.left == null && qNode.node.right == null) {
                return depth;
            }

            if (qNode.node.left != null) {
                q.add(new QueueNode(qNode.node.left, depth + 1));
            }
            if (qNode.node.right != null) {
                q.add(new QueueNode(qNode.node.right, depth + 1));
            }
        }

        return 0;
    } 
}
