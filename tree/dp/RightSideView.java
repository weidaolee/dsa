package tree.dp;

import java.util.LinkedList;
import java.util.List;

import tree.TreeNode;

public class RightSideView {
    public List <Integer> rightSideView (TreeNode root) {
        dfs(root, 0);
        return path;
    }

    List <Integer> path = new LinkedList<>();
    private void dfs (TreeNode root, int depth) {
        if (root == null) {
            return;
        }
        if (depth == path.size()) {
            path.add(root.val);
        }
        dfs(root.right, depth + 1);
        dfs(root.left, depth + 1);
    }

    private List <Integer> dp (TreeNode root) {
        if (root == null) {
            return new LinkedList<>();
        }
        List <Integer> leftPath = dp(root.left);
        List <Integer> rightPath = dp(root.right);

        if (leftPath.size() <= rightPath.size()) {
            leftPath = null;
        } else {
            for (int i = 0; i < rightPath.size(); i++) {
                leftPath.remove(0);
            }
            rightPath.addAll(leftPath);
        }
        rightPath.add(0, root.val);
        return rightPath;
    }

}
