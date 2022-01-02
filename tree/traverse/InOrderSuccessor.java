package tree.traverse;

import tree.TreeNode;

public class InOrderSuccessor {
    /**
       Special cases:
       如果 p 要有 successor, p 不能是整棵樹最右的 node
       1. 如果 tree 只有一個 node, p = root -> 最右邊的 node
       2. 如果 tree 有左樹，但 p = root 且沒有右樹 -> 最右邊的 node

       Normal cases:
       1. 如果 p 有右樹, successor 為 p 的右樹的最左的 node
       2. 如果 p 沒有右樹，問 p 的 parent p 是不是 left child, 如果是 parent 則是 successor，如果不是就一直往上問

       因為 BST node 結構沒有給 parent link, 所以以搜索的方式找到 successor:
       1. 從 root 出發，initial: cur = successor = root
       2. 如果 p 在左，cur 和 successor 都要向下移動, successor = cur, cur = cur.left, 並且紀錄曾左走一步
       3. 如果 p 在右, cur 往右走， successor 原地不動
       4. 重複 2, 3 找到 p 為止：如果 search 過程中從來沒有往左走，代表一直往右走，說明 p 是整棵樹最右的 node, successor = null

     */
    public TreeNode inorderSuccessor (TreeNode root, TreeNode p) {
        // special cases
        if (root == null || (root.left == null && root.right == null)) {
            return null;
        }

        // if p with right tree
        TreeNode successor;
        if (p.right != null) {
            // successor = leftest child of p.right tree
            successor = p.right;
            while (successor.left != null) {
                successor = successor.left;
            }
            return successor;
        }

        // root = p and root without right tree
        if (root == p && root.right == null) {
            return null;
        }

        // search p
        successor = root;
        TreeNode cur = successor;
        int leftStept = 0;
        while (cur.val != p.val && cur != null) {
            if (p.val < cur.val) {
                // successor is parent
                successor = cur;
                cur = cur.left;
                leftStept ++;
            } else if (p.val > cur.val) {
                // successor is an acentor but not parent
                cur = cur.right;
            }
        }
        // if leftStept = 0, then p is the rightest node of whole tree, successor is null
        return leftStept > 0 ? successor : null;
    }

    public Node withParentInorderSuccessor(Node node) {
        /**
          回想 inorder traversal, 我們把一個 node 所有的 left childern 全部
          push 到 stack 裡後, 再開始 pop 第一個來處裡，然後馬上又把 right 的 left children
          做一樣的事情。

          所以，
          case 1: 如果 node 有 right tree, 則 node 的下一個是 right tree 的最左 child.
          case 2: 如果 node 無 right tree, 如果自己是左節點，則自己的下一個是 parent.
          case 3: 如果 node 無 right tree, 如果自己不是是左節點，則往上問自己是不是左節點，直到自己是為止，其 parent 是 下一個.
          case 4: 如果 case 3 直到 root 都不成立，則 node 為 最後一個節點，其下一個為 null.
        */

        if (node == null) {
            return null;
        }

        Node successor;
        if (node.right != null) {
            successor = node.right;
            while (successor.left != null) {
                successor = successor.left;
            }
            return successor;
        } else {
            while (node.parent != null && node.parent.left != node) {
                node = node.parent;
            }
            return node.parent; // node.parent = {null, successor}
        }
    }
    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node parent;
    }
}

