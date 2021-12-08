package graph.shortestpath;
import graph.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;;

public class Dijkstra {
    /**
     * 指定 Graph 上一個 node o, 求 o 到所有 nodes 的 shortest path:
     1. initial: distance(o, o) = 0, distance(o, v) = MAX_VALUE
     2. 從 o 開始，把 o 所有的 edges 取出，依次判斷是否更新 o 抵達的每個 adjacent node 的最小距離總和
     3. 選擇距離最小的 adjacent node 重複 2-3, 直到所有 node 都遍歷過
     */

    public HashMap<Node, Integer> dijkstra(Graph graph, Node node) {

        // initial:
        HashMap <Node, Integer> distanceMap = new HashMap<>();
        distanceMap.put(node, 0);

        // 選擇過的點不必再考慮
        HashSet <Node> selectedNodes = new HashSet<>();

        Node chosenNode = getNextChosenNode(distanceMap, selectedNodes);
        while (chosenNode != null) {
            int distance = distanceMap.get(chosenNode);
            for (Edge e : chosenNode.edges) {
                Node v = e.to;
                // 如果 v 是沒見過的 node, 則直接更新 distance map
                if (!distanceMap.containsKey(v)) {
                    distanceMap.put(v, distance + e.weight);
                }
                // 如果 v 是鍵過的 node, 判斷 distance map 中 v 的 shortest path value 是否要更新
                // 可加 else
                distanceMap.put(v, Math.min(distanceMap.get(v), distance + e.weight));
            }

            selectedNodes.add(chosenNode);
            chosenNode = getNextChosenNode(distanceMap, selectedNodes);
        }
        return distanceMap;
    }

    public Node getNextChosenNode(HashMap<Node, Integer> distanceMap, HashSet<Node> selectedNodes) {
        /**
         the next chosen node should satisfy:
           1. 路徑總和最小
           2. 沒被選過
         */
        Node nextNode = null;
        int minDistance = Integer.MAX_VALUE;

        for (Entry <Node,Integer> entry : distanceMap.entrySet()) {
            Node v = entry.getKey();
            int distance = entry.getValue();

            if (distance < minDistance && !selectedNodes.contains(v)) {
                minDistance = distance;
                nextNode = v;
            }
        }
        return nextNode;
    }
}
