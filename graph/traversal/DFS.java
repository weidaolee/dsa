package graph.traversal;
import graph.*;

import java.util.HashSet;
import java.util.Stack;

public class DFS {
    /**
     * 1. 從 node v 開始，process v, 把 push v
     * 2. pop v 從 v 的 adjacent node 挑一個沒處裡過的 node a 處裡
     * 3. 把 v, a 照順序 push 回 stack
     * 4. repeat 2 - 3，直到 stack empty
     */

    public static void traversal(Node node) {
        if (node == null) {
            return;
        }

        Stack <Node> stack = new Stack<>();
        HashSet <Node> set = new HashSet<>();

        process(node);
        set.add(node);
        stack.push(node);

        while (!stack.isEmpty()) {
            Node v = stack.pop();
            for (Node a: v.ajcnt) {
                if (!set.contains(a)) {
                    process(a); // 先處裡 a，並註冊
                    set.add(a);
                    stack.push(v); // 如果 a 是沒被訪問過的，把 v push 回 stack
                    stack.push(a); // 再把 v push 進 stack, 這使得 stack 內
                    break;         // 構成從 v 開始的最深 chain
                }
            }
        }
    }

    public static void process(Node node) {

    }
}
