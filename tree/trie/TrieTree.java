package tree.trie;

public class TrieTree {
    private Node root = new Node();

    public void insert (String string) {
        if (string == null) {
            return;
        }

        char [] chars = string.toCharArray();

        Node node = root;
        node.numPass ++;
        int idx = 0;
        for (int i = 0; i < chars.length; i++) {
            idx = chars[i] - 'a';
            if (node.nexts[idx] == null) {
                node.nexts[idx] = new Node();
            }
            node = node.nexts[idx];
            node.numPass ++;
        }
        node.numEnd ++;
    }

    public void delete (String string) {
        if (string == null || countString(string) < 0) {
            return;
        }

        char [] chars = string.toCharArray();

        Node node = root;
        node.numPass --;
        int idx = 0;
        for (int i = 0; i < chars.length; i++) {
            idx = chars[i] - 'a';
            if (--node.nexts[idx].numPass == 0) {
                node.nexts[idx] = null;
                return;
            }
            node = node.nexts[idx];
        }
        node.numEnd --;
    }

    public int countString(String string) {
        if (string == null) {
            return -1;
        }

        char [] chars = string.toCharArray();

        Node node = root;
        int idx = 0;
        for (int i = 0; i < chars.length; i++) {
            idx = chars[i] - 'a';
            if (node.nexts[idx] == null) {
                return 0;
            }
            node = node.nexts[idx];
        }
        return node.numEnd;
    }

    public int countWithPrefix(String prefix) {
        if (prefix == null) {
            return -1;
        }

        char [] chars = prefix.toCharArray();

        Node node = root;
        int idx = 0;
        for (int i = 0; i < chars.length; i++) {
            idx = chars[i] - 'a';
            if (node.nexts[idx] == null) {
                return 0;
            }
            node = node.nexts[i];
        }
        return node.numEnd;
    }

    public static class Node {
        public int numPass;
        public int numEnd;
        public Node [] nexts;

        public Node() {
            numPass = 0;
            numEnd = 0;
            nexts = new Node[26];
        }
    }
}
