package graph;

import java.util.ArrayList;
import java.util.List;

public class Node {
    public int value;
    public int inDegree;
    public int outDegree;

    public List <Node> ajcns;
    public List <Edge> edges;

    public Node (int value) {
        this.value = value;
        inDegree = 0;
        outDegree = 0;
        ajcns = new ArrayList<>();
        edges = new ArrayList<>();
    }
}
