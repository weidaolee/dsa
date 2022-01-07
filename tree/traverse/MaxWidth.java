package tree.traverse;
import tree.*;

import java.util.LinkedList;
import java.util.Queue;

public class MaxWidth {

    public int getMaxWidth(TreeNode root) {
        Queue <TreeNode> queue = new LinkedList<>();

        queue.add(root);
        int maxWidth = 1;
        TreeNode node;
        while (!queue.isEmpty()) {
            node = queue.poll();

            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.left != null) {
                queue.add(node.right);
            }
            maxWidth = maxWidth < queue.size() ? queue.size() : maxWidth;
        }
        return maxWidth;
    }
}
