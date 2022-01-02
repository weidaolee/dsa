package tree.traverse;

import java.util.Comparator;

import java.util.LinkedList;
import java.util.List;

import java.util.PriorityQueue;

import tree.TreeNode;

public class VerticalTraversal {

    public List <List<Integer>> verticalTraversal (TreeNode root) {
        if (root == null) {
            return null;
        }

        List<List<Integer>> res = new LinkedList<>();
        preOrder(root, new Info(0, 0, root.val));
        while (!queue.isEmpty()) {
            int col = queue.peek().col;
            List <Integer> colList = new LinkedList<>();
            while (!queue.isEmpty() && col == queue.peek().col) {
                colList.add(queue.poll().val);
            }
            res.add(colList);
        }
        return res;
    }

    PriorityQueue <Info> queue = new PriorityQueue<>(new VerticalNodeComparator());
    public void preOrder (TreeNode root, Info info) {
        if (root == null) {
            return;
        }
        queue.add(info);
        if (root.left != null) {
            preOrder(root.left, new Info(info.col - 1, info.row + 1, root.left.val));
        }
        if (root.right != null) {
            preOrder(root.right, new Info(info.col + 1, info.row + 1, root.right.val));
        }
    }

    public static class Info {
        int col;
        int row;
        int val;

		public Info(int col, int row, int val) {
			this.col = col;
			this.row = row;
			this.val = val;
		}
    }

    public static class VerticalNodeComparator implements Comparator <Info> {
		public int compare(Info o1, Info o2) {
			if (o1.col != o2.col) {
                return o1.col - o2.col;
            } else if (o1.row != o2.row ) {
                return o1.row - o2.row;
            } else {
                return o1.val - o2.val;
            }
		}
    }
}
