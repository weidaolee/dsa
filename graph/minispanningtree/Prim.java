package graph.minispanningtree;
import graph.*;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class Prim {
    /**
       從任意 node v 開始
       1. v 的 edges 代表已經被點亮的 edges，我們要從點亮的 edges 中取出 weight 最小的 e，同時要滿足 e 所走到的點 u, 是沒訪問過的。
       2. 透過 e 走到新的 node u, 重複 1
    */
    public Set<Edge> minimunSpanningTree (Graph graph) {

        PriorityQueue <Edge> priorityQueue = new PriorityQueue<>(new Edge.EdgeComparator());

        HashSet <Node> visitedSet = new HashSet<>();

        Set<Edge> res = new HashSet<>();
        for (Node v : graph.nodes.values()) { // 避免不連通的 graph
            if (visitedSet.contains(v)) {
                continue;
            }
            visitedSet.add(v);
            for (Edge e : v.edges) {
                priorityQueue.add(e);
            }

            while (!priorityQueue.isEmpty()) {
                Edge e = priorityQueue.poll();
                Node u = e.to;  // e = <v, u>
                if (visitedSet.contains(u)) {
                    continue;
                    // 如果這個 weight 最小的 e 會導向已經走過的 u ，則直接跳過此次循環
                    // continue 會直接回到從 priority queue 中取 e 的步驟
                }
                visitedSet.add(u);
                res.add(e);

                // 將新的 node u 所觸及的 edges 加入 priority queue
                for (Edge newEdge : u.edges) {
                     // 可再加入一個 old edge set 避免重複的 edge
                    priorityQueue.add(newEdge);
                }
            }
        }
        return res;
    }
}
