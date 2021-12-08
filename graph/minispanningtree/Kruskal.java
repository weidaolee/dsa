package graph.minispanningtree;
import graph.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class Kruskal {
    /**
     * 給一個 Graph, 構建一個 sub graph 其 edge 上的總和為最小：
     * 每次取出 weight 最小的 edge e, 檢查如果加上 e 是否會形成 ring
     * 如果不會形成環就加上 e, 直到遍歷所有 edges
     *
     * 判斷是否形成 ring 的方法：
     * 1. 每一個 node 各自形成一個 set
     * 2. 每得到一個 edge e = <u, v>, 檢查 e 上的 u, v 是否屬於同一個 set,
     *    如果是，則代表加上 e 會形成 ring： 如果不是, 將 u, v 所在的 set 合併.
     * 3. repeat 2 直到遍歷所有 edge
     *
     * Note: Kruskal 可以找出甚至非連通的 Graph
     */

    public Set<Edge> minimumSpanningTree (Graph graph) {
        NodeSets nodeSets = new NodeSets(graph.nodes.values());
        PriorityQueue <Edge> priorityqueue = new PriorityQueue <>(new Edge.EdgeComparator()); // need a comparator

        for (Edge e : graph.edges) {
            priorityqueue.add(e);
        }

        Set <Edge> res = new HashSet<>();
        while (!priorityqueue.isEmpty()) {
            Edge e = priorityqueue.poll();
            if (!nodeSets.isInSameSet(e.from, e.to)) {
                res.add(e);
                nodeSets.union(e.from, e.to);
            }
        }
        return res;
    }

    public static class NodeSets {
        public HashMap <Node, HashSet<Node>> setMap = new HashMap<>();

		public NodeSets(Collection<Node> collection) {
			for (Node v : collection) {
                HashSet<Node> set = new HashSet<>();
                set.add(v);
                setMap.put(v, set);
            }

		}

        public boolean isInSameSet(Node from, Node to) {
            return setMap.get(from) == setMap.get(to);
        }

        public void union(Node from, Node to) {
            HashSet<Node> fromSet = setMap.get(from);
            HashSet<Node> toSet = setMap.get(to);

            HashSet<Node> smallerSet;
            HashSet<Node> biggerSst;

            if (fromSet.size() < toSet.size()) {
                smallerSet = fromSet;
                biggerSst = toSet;
            } else {
                smallerSet = toSet;
                biggerSst = fromSet;
            }

            for (Node v : smallerSet) {
                biggerSst.add(v);
                setMap.put(v, biggerSst);
            }
        }
    }
}
