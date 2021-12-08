package graph;

import java.util.ArrayList;
import java.util.List;

public class Node {
    public int value;
    public int inDegree;
    public int outDegree;

    public List <Node> ajcnt;
    public List <Edge> edges;

    public Node (int value) {
        this.value = value;
        inDegree = 0;
        outDegree = 0;
        ajcnt = new ArrayList<>();
        edges = new ArrayList<>();
    }
}
