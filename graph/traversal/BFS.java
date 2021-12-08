package graph.traversal;
import graph.*;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class BFS {

    public static void traversal (Node node) {
        if (node == null) {
            return;
        }

        Queue <Node> queue = new LinkedList<>();
        HashSet <Node> set = new HashSet<>(); // 紀錄已經訪問過的 node

        queue.add(node);
        set.add(node);

        Node v;
        while (!queue.isEmpty()) {
            v = queue.poll();
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
