package graph.traverse;
import graph.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class TopologySort {
    /**
      可以先處裡的 node 必須是 in degree = 0 的 node,
      並且，每當處理完一個 node, 要把以它當作 prerequest 的 in degree - 1

      準備一個 map, 用於紀錄 Node -> In Degree
      準被一個 zero queue, 它只允許 in degree = 0 的 node 進入
      每處理完一個 node,把 adjacents 的 indegree - 1
    */

    public List<Node> topologySort (Graph graph) {
        if (graph == null) {
            return null;
        }

        HashMap <Node, Integer> inMap = new HashMap<>();
        Queue <Node> zeroInDegreeQueue = new LinkedList<>();

        for (Node v : graph.nodes.values()) {
            inMap.put(v, v.inDegree);
            if (v.inDegree == 0) {
                zeroInDegreeQueue.add(v);
            }
        }

        List <Node> res = new ArrayList<>();
        Node v;
        while (!zeroInDegreeQueue.isEmpty()) {
            v = zeroInDegreeQueue.poll();
            res.add(v);
            for (Node a : v.ajcns) {
                inMap.put(a, inMap.get(a) - 1);
                if (inMap.get(a) == 0) {
                    zeroInDegreeQueue.add(a);
                }
            }
        }
        return res;
    }
}
