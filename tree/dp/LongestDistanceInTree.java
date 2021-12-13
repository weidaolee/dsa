package tree.dp;
import tree.TreeNode;

public class LongestDistanceInTree {
    /**
     * Assignment:
     * 求出 tree 中 node 與 node 之間最常距離 \n
     *
     * Idea:
     *    1. root 在 longest path 上：
     *        以該 root 的 longest distance = left height + right height + 1
     *    2. root 不在 longest path 上
     *        以該 root 的 longest path, 要麼在左樹上，要麼在右樹上
     */
    public int longestDistance (TreeNode root) {
        return getInfo(root).distance;
    }


    public static class Info {
        int distance;
        int height;

		public Info(int distance, int height) {
			this.distance = distance;
			this.height = height;
		}
    }

    public Info getInfo (TreeNode root) {
       if (root == null) {
           return new Info(0, 0);
       }

       Info left = getInfo(root.left);
       Info right = getInfo(root.right);

       int d1 = left.distance;
       int d2 = right.distance;
       int d3 = left.height + 1 + right.height;

       int currentDistance = Math.max(d3, Math.max(d1, d2));
       int currentHeight = Math.max(left.height, right.height) + 1;

       return new Info (currentDistance, currentHeight);
    }
}
