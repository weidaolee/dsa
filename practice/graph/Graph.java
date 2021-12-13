package practice.graph;

import java.util.Map;

public class Graph {
    Map <Integer, Node> nodes;
    Map <Integer, Edge> edges;

	public Graph(Map<Integer, Node> nodes, Map<Integer, Edge> edges) {
		this.nodes = nodes;
		this.edges = edges;
	}
}
