package tree.dp;

import tree.TreeNode;

public class HoseRoberIII {
    /**f
     * Link:
     * https://leetcode-cn.com/problems/house-robber-iii/
     *
     */

    public int rob(TreeNode root) {
        Info info = getInfo(root);
        return Math.max(info.pickThis, info.skipThis);

    }

    private Info getInfo(TreeNode root) {
        if (root == null) {
            return new Info(0, 0);
        }

        Info left = getInfo(root.left);
        Info right = getInfo(root.right);

        int pickThis = root.val + left.skipThis + right.skipThis;
        int skipThis = Math.max(left.pickThis, left.skipThis) + Math.max(right.pickThis, right.skipThis);

        return new Info(pickThis, skipThis);
    }

    private static class Info {
        int pickThis;
        int skipThis;
		public Info(int pickThis, int skipThis) {
			this.pickThis = pickThis;
			this.skipThis = skipThis;
		}
    }
}
