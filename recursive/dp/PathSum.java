package recursive.dp;

import tree.TreeNode;

public class PathSum {
    public int pathSum(TreeNode root, int targetSum) {
        /**
         * Main idea:
         * 已經有求以 root 為起點的合法路徑數
         * 考慮跳過 root, 直接考慮 root.left, root.right 的合法路徑長
         * 全部加總就微微答案
         *
         * time : O (N ^ 2)
         * space: O (N)
         */

        if (root == null) {
            return 0;
        }

        int sum = rootSum(root, targetSum);
        sum += pathSum(root.left, targetSum);
        sum += pathSum(root.right, targetSum);
        return sum;
    }


    public int rootSum (TreeNode root, int targetSum) {
        /**
         * Main idea:
         * 求對於某個 root, 以該 root 的所有 path,
         * 滿足 path sum 為 target 的數量。
         *
         * Implementations:
         * 以 int count = 0; 紀錄所有合法路徑和
         *
         * base case:
         * root = null -> return 0;
         * root = target -> count ++;
         *
         * 算子樹上的合法路徑合：
         * 因為 root 已經計算過，所以子樹 target 為 target - root.val
         * 把子樹的合法路徑數 + root 合法路徑數 = 以 root 為起點的合法路徑數
         */

        int count = 0;
        if (root == null) {
            return 0;
        }

        if (root.val == targetSum) {
            count ++;
        }

        count += rootSum(root.left, targetSum - root.val);
        count += rootSum(root.right, targetSum - root.val);
        return count;
    }
}
