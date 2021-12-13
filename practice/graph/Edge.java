package practice.graph;

import java.util.Comparator;

public class Edge {

    public Node from;
    public Node to;
    public int weight;

	public Edge(Node from, Node to, int weight) {
		this.from = from;
		this.to = to;
		this.weight = weight;
	}

	public Edge(Node from, Node to) {
		this.from = from;
		this.to = to;
	}

    public static class EdgeComparator implements Comparator <Edge> {
		@Override
		public int compare(Edge o1, Edge o2) {
			return o1.weight - o2.weight;
		}
    }
}
