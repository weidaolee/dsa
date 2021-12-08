package tree.bst;
import tree.TreeNode;

public class BinarySearchTree {

    public void insert (TreeNode root, TreeNode node) {

    }


    public TreeNode search (TreeNode root, int key) {

        if (root == null || root.val == key) {
            return root;
        }
        if (key < root.val) {
            return search(root.left, key);
        }

        if (key > root.val) {
            return search(root.right, key);
        }
        return root;
    }


}
