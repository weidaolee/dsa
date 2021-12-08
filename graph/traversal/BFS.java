package graph.traversal;
import graph.*;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class BFS {
    /**
     * 1. 從 node v 開始, process v, v 入 queue
     * 2. poll v, 處裡所有 v 的所有沒處理過的 adjacent nodes，處理完後全部入 queue
     * 3. repeat 2 直到 queue empty
     */

    public static void traversal (Node node) {
        if (node == null) {
            return;
        }

        Queue <Node> queue = new LinkedList<>();
        HashSet <Node> set = new HashSet<>(); // 紀錄已經訪問過的 node

        queue.add(node);
        set.add(node);

        while (!queue.isEmpty()) {
            Node v = queue.poll();
            process(v);
            for (Node a : node.ajcnt) {
                if (!set.contains(a)) { // 如果訪問過了, 就不再訪問
                    queue.add(a);
                    set.add(a);
                }
            }
        }
    }

    public static void process(Node node) {

    }
}
