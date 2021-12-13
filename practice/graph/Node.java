package practice.graph;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class Node {
    public int value;
    public int inGegree;
    public int outDegree;

    public List <Node> ajcns;
    public List <Edge> edges;

    public Map <Collection<Object>, Object> records;

	public Node(final int value) {
		this.value = value;
        inGegree = 0;
        outDegree = 0;
        ajcns = new ArrayList<>();
        edges = new ArrayList<>();
	}
}
