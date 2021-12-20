package tree.traversal;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import tree.TreeNode;

public class PathSumII {


    List<List<Integer>> res = new LinkedList<>();
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return res;
        }

        Deque <Integer> path = new LinkedList<>();
        dfs(root, targetSum, 0, path);


        return res;
    }

    public void dfs (TreeNode root, int rest, int depth, Deque<Integer> path) {
        /**
         * 如果只在 leaves poll last, 到了別的 branch 會殘留很多不屬於該 branch 的 nodes
         * 所以以 depth 紀錄當前長度，如果 path.size != depth 時，poll last 直到相等為止
         */
        if (root == null) {
            return;
        }
        while (path.size() != depth) {
            // 靈魂代碼
            path.pollLast();
        }

        path.addLast(root.val);
        if (root.left == null && root.right == null) {
            if (rest == root.val) {
                res.add(new LinkedList<>(path));
            }
        }

        dfs(root.left, rest - root.val, depth + 1,  path);
        dfs(root.right, rest - root.val, depth + 1, path);
    }

    public void dfs (TreeNode root, int rest, Deque<Integer> path) {
        /**
         * 在遍歷完左和右後再進行 poll last
         * 詳情請 print 每次 poll last 的結果。
         */

        if (root == null) {
            return;
        }

        path.addLast(root.val);
        if (root.left == null && root.right == null) {
            if (rest == root.val) {
                res.add(new LinkedList<>(path));
            }
        }

        dfs(root.left, rest - root.val, path);
        dfs(root.right, rest - root.val, path);
        System.out.println(path.pollLast());
    }
}
