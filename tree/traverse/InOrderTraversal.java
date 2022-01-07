package tree.traverse;
import tree.*;

import java.util.ArrayList;
import java.util.List;

public class InOrderTraversal {
    List<Integer>  list = new ArrayList<>();

    public List<Integer> inorderTraversal(TreeNode root) {
        recursion(root);
        return list;
    }

    private void recursion(TreeNode node) {
        if (node == null) {
            return;
        }
        recursion(node.left);
        list.add(node.val);
        recursion(node.right);
    }
}
