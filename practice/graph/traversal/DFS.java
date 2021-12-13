package practice.graph.traversal;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

import practice.graph.Node;

public class DFS {

    public void traversal (Node node) {
        if (node == null) {
            return;
        }

        Stack <Node> stack = new Stack<>();
        Set <Node> visitedNodes = new HashSet<>();

        Node v = node;
        process(v);
        visitedNodes.add(v);
        stack.push(v);

        while (!stack.isEmpty()) {
            v = stack.pop();
            for (Node a : v.ajcns) {
                if (!visitedNodes.contains(a)) {
                    process(a);
                    visitedNodes.add(a);
                    stack.push(v);
                    stack.push(a);
                    break;
                }
            }
        }
    }

    public void process (Node node) {

    }
}
