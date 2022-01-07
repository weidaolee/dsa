package tree.traverse;

import tree.TreeNode;

import java.util.List;
import java.util.ArrayList;

public class FindLeaves {
    /**
     * Link:
     * https://leetcode-cn.com/problems/find-leaves-of-binary-tree/
     *
     * 1. post-order 走到 leaveas
     * 2. 把這一輪的 leavees 蒐集起來
     * 3. return null 使得 leaves 被釋放掉
     * 4. 對 左樹 和 右樹 做 cutleaves
     * 5. return root
     * 重複以上的過程，直到 root = null
     * 分析:
     * N + N/2 + N/4 + ... + 1
     * = N * (2 ^ 0 + 2 ^ -1 + 2 ^ -2 + ... + 0)
     * = N * (1 - 1 / 2) ^ -1
     * = 2 N => O (N)
     */

    public List<List<Integer>> findLeaves (TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();

        if (root == null) {
            return res;
        }

        while (root != null) {
            List <Integer> leavesNodes = new ArrayList<>();
            root = cutLeaves(root, leavesNodes);
            res.add(leavesNodes);
        }

        return res;
    }

    private TreeNode cutLeaves(TreeNode root, List<Integer> leavesNodes) {
        if (root == null) {
            return null;
        }
        if (root.left == null && root.right == null) {
            leavesNodes.add(root.val);
            return null;
        }
        root.left = cutLeaves(root.left, leavesNodes);
        root.right = cutLeaves(root.right, leavesNodes);
        return root;
    }
}
