package list.skiplist;

import java.util.ArrayList;
import java.util.List;

public class SkipListMap <K extends Comparable<K>, V> {
    public static final double PROBABILITY = 0.5;
    public Node <K, V> head;
    public int size;
    public int headLevel;

    public SkipListMap () {
        size = 0;
        headLevel = 0;
        head = new Node<>();
        head.nextNodes.add(null);
        head.linkLength.add(1);
    }

    public void put (K key, V value) {
        if (key == null) {
            return;
        }

        IndexAndNode <K, V> pair = findPredecessor(key);

        Node <K, V> pred = pair.node;
        Node <K, V> next = pred.nextNodes.get(0);
        if (next != null && next.equals(key)) {
            next.val = value;
            return;
        }

        size ++;
        int nodeLevel = pickRandomLevel();

        Node <K, V> node = new Node<>(key, value);
        for (int level = nodeLevel; level >= 0; level --) {
            node.nextNodes.add(null);
            node.linkLength.add(1);
        }

        int nodeIndex = pair.index + 1;
        int predIndex = -1;

        pred = head;
        next = null;
        for (int level = headLevel; level >= 0; level --) {
            next = pred.nextNodes.get(level);
            while(next != null && next.lessThan(key)) {
                predIndex += pred.linkLength.get(level);
                pred = next;
                next = pred.nextNodes.get(level);
            }
            int len = pred.linkLength.get(level);
            if (level > nodeLevel) {
                pred.linkLength.set(level, len + 1);
            } else {
                node.nextNodes.set(level, pred.nextNodes.get(level));

                node.linkLength.set(level, len + 1 - (nodeIndex - predIndex));
                pred.linkLength.set(level, nodeIndex - predIndex);

                pred.nextNodes.set(level, node);
            }
        }
        increaseHeadLevel(nodeIndex, node);

    }

    public V remove (K key) {
        if (key == null) {
            return null;
        }

        IndexAndNode <K, V> pair = findPredecessor(key);

        Node <K, V> pred = pair.node;
        Node <K, V> node = pred.nextNodes.get(0);
        Node <K, V> next;

        if (node == null || !node.equals(key)) {
            return null;
        }

        size --;

        V val = node.val;

        int nodeLevel = node.nextNodes.size() - 1;
        pred = head;
        next = null;
        for (int level = headLevel; level >= 0; level --) {
            next = pred.nextNodes.get(level);
            while (next != null && next.lessThan(key)) {
                pred = next;
                next = pred.nextNodes.get(level);
            }
            int len = pred.linkLength.get(level);
            if (level > nodeLevel) {
                pred.linkLength.set(level, len - 1);
            } else {
                pred.linkLength.set(level, len + next.linkLength.get(level) - 1);
                pred.nextNodes.set(level, next.nextNodes.get(level));
            }
        }

        decreaseHeadLevel();

        return val;
    }

    private int pickRandomLevel () {
        int level = 0;
        while (Math.random() < PROBABILITY) {
            level ++;
        }
        return level;
    }

    public V get (K key) {
        if (key == null) {
            return null;
        }

        IndexAndNode <K, V> pair = findPredecessor(key);

        Node <K, V> pred = pair.node;
        Node <K, V> next = pred.nextNodes.get(0);
        return next != null && next.equals(key) ? next.val : null;
    }

    public void increaseHeadLevel (int index, Node <K, V> node) {
        int nodeLevel = node.nextNodes.size() - 1;

        while (headLevel < nodeLevel) {
            headLevel ++;
            head.nextNodes.add(node);
            head.linkLength.add(index + 1);

            node.nextNodes.set(headLevel, null);
            node.linkLength.set(headLevel, size - index);
        }
    }

    public void decreaseHeadLevel () {
        while (headLevel > 0 && head.nextNodes.get(headLevel) == null) {
            head.nextNodes.remove(headLevel);
            head.linkLength.remove(headLevel);
            headLevel --;
        }
    }

    public boolean containsKey (K key) {
        if (key == null) {
            return false;
        }

        IndexAndNode <K, V> pair = findPredecessor(key);

        Node <K, V> pred = pair.node;
        Node <K, V> next = pred.nextNodes.get(0);
        return next != null && next.equals(key);
    }

    public IndexAndNode <K,V> findPredecessor (K key) {
        Node <K, V> pred = head;
        Node <K, V> next = null;

        int index = -1;
        for (int level = headLevel; level >= 0; level --) {
            next = pred.nextNodes.get(level);
            while (next != null && next.lessThan(key)) {
                index += pred.linkLength.get(level);

                pred = next;
                next = pred.nextNodes.get(level);
            }
        }
        return new IndexAndNode<>(index, pred);
    }

    public Node <K, V> findPredecessorByIndex (int index) {
        Node <K, V> pred = head;
        Node <K, V> next = null;

        int predIndex = -1;

        for (int level = headLevel; level >= 0; level --) {
            next = pred.nextNodes.get(level);
            while (next != null && predIndex + pred.linkLength.get(level) < index) {
                predIndex += pred.linkLength.get(level);
                pred = next;
                next = pred.nextNodes.get(level);
            }
        }
        return pred;
    }

    public void printAll () {
        for (int level = headLevel; level >= 0; level --) {
            System.out.printf("Level  %d%n", level);
            Node <K, V> cur = head;
            while (cur != null) {
                System.out.printf("Key:  %s,  Value:  %s,  Length:  %d%n",
                                  cur.key, cur.val, cur.linkLength.get(level));
                cur = cur.nextNodes.get(level);
            }
        }
    }

    public static class IndexAndNode <K extends Comparable<K>, V> {
        int index;
        Node <K, V> node;

		public IndexAndNode(int index, Node<K, V> node) {
			this.index = index;
			this.node = node;
		}
    }

    public static class Node <K extends Comparable<K>, V> {
        public K key;
        public V val;
        public List <Node <K, V>> nextNodes;
        public List <Integer> linkLength;

		public Node(K key, V val) {
			this.key = key;
			this.val = val;
            nextNodes = new ArrayList<>();
            linkLength = new ArrayList<>();
		}

		public Node() {
            key = null;
            val = null;
            nextNodes = new ArrayList<>();
            linkLength = new ArrayList<>();
		}

        public boolean lessThan(K otherKey) {
            return otherKey != null && (key == null || key.compareTo(otherKey) < 0);
        }

        public boolean equals(K otherKey) {
            return (key == null && otherKey == null)
                || (key != null && otherKey != null && key.compareTo(otherKey) == 0);
        }
    }
}
