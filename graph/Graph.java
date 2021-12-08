package graph;

import java.util.HashMap;
import java.util.HashSet;

public class Graph {
    /**
     * Graph G = <V, E>
     * Node V = <v1, ..., vn>
     * Edge E = <e1, ..., em>
     */
    public HashMap <Integer, Node> nodes;
    public HashSet <Edge> edges;

	public Graph() {
		nodes = new HashMap<>();
		edges = new HashSet<>();
	}
}
