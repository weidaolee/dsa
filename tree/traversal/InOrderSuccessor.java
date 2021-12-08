package tree;


public class InOrderSuccessor {
    public Node inorderSuccessor(Node node) {
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
}

class Node {
    public int val;
    public Node left;
    public Node right;
    public Node parent;
}
