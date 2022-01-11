package list.skiplist.app;

import java.util.ArrayList;
import java.util.List;

public class SlidingWindowMedian {
    /**
     * Link:
     * https://leetcode-cn.com/problems/sliding-window-median/
     *
     */

    public double[] medianSlidingWindow(int[] nums, int k) {
        double [] ans = new double[nums.length - k + 1];
        SkipListSet <Element> orderedList = new SkipListSet<>();

        int R, L;

        for (R = 0; R < nums.length; R ++) {
            L = R - k + 1;
            orderedList.add(new Element(R, nums[R]));
            if (orderedList.size() == k) {
                if (k % 2 == 1) {
                    Element element = orderedList.get(k / 2);
                    ans[L] = element.value;
                } else {
                    SkipListSet.Node<Element> pred = orderedList.findPredecessorByIndex(k / 2);
                    double a = pred.element.value;
                    double b = pred.nextNodes.get(0).element.value;
                    ans[L] = a / 2 + b / 2;
                }
            }
            if (L >= 0) {
                orderedList.remove(new Element(L, nums[L]));
            }

        }

        return ans;
    }

    private static class Element implements Comparable <Element> {
        int index;
        double value;

		public Element(int index, double value) {
			this.index = index;
			this.value = value;
		}

		@Override
		public int compareTo(Element o) {
			if (value != o.value) {
                return (int) (value - o.value);
            } else {
                return index - o.index;
            }
		}
    }
}


class SkipListSet <E extends Comparable <E>> {
    public static final double PROBABILITY = 0.5;
    public Node <E> head;
    public int headLevel;
    public int size;

	public SkipListSet() {
        head = new Node<>();
        head.nextNodes.add(null);
        head.linkLength.add(1);
        headLevel = 0;
        size = 0;
	}

    public void add (E element) {
        if (element == null) {
            return;
        }

        IndexAndNode <E> pair = findPredecessor(element);
        Node <E> pred = pair.node;
        Node <E> next = pred.nextNodes.get(0);

        if (next != null && next.equals(element)) {
            return;
        }

        size ++;
        int nodeLevel = pickRandomLevel();
        int nodeIndex = pair.index + 1;
        Node <E> node = new Node<>(element);
        for (int level = 0; level <= nodeLevel; level ++) {
            node.nextNodes.add(null);
            node.linkLength.add(1);
        }

        int predIndex = -1;
        pred = head;
        next = null;
        for (int level = headLevel; level >= 0; level --) {
            next = pred.nextNodes.get(level);
            while (next != null && next.lessThen(element)) {
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

    public void remove (E element) {
        if (element == null) {
            return;
        }

        IndexAndNode <E> pair = findPredecessor(element);
        Node <E> pred = pair.node;
        Node <E> next = pred.nextNodes.get(0);

        if (next == null || !next.equals(element)) {
            return;
        }

        size --;
        int nodeLevel = next.nextNodes.size() - 1;

        pred = head;
        next = null;
        for (int level = headLevel; level >= 0; level --) {
            next = pred.nextNodes.get(level);
            while (next != null && next.lessThen(element)) {
                pred = next;
                next = pred.nextNodes.get(level);
            }

            int len = pred.linkLength.get(level);
            if (level > nodeLevel) {
                pred.linkLength.set(level, len - 1);
            } else {
                pred.linkLength.set(level, len - 1 + next.linkLength.get(level));
                pred.nextNodes.set(level, next.nextNodes.get(level));
            }
        }
        decreaseHeadLevel();
    }

    public void increaseHeadLevel (int index, Node <E> node) {
        int nodeLevel = node.nextNodes.size() - 1;

        while (headLevel < nodeLevel ) {
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


    public int pickRandomLevel () {
        int nodeLevel = 0;
        while (Math.random() < 0.5) {
            nodeLevel ++;
        }
        return nodeLevel;
    }

    public E get (int index) {
        if (index < 0 || index >= size) {
            return null;
        }

        Node <E> pred = findPredecessorByIndex(index);
        return pred.nextNodes.get(0).element;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean contains (E element) {
        if (element == null) {
            return false;
        }

        IndexAndNode <E> pair = findPredecessor(element);
        Node <E> pred = pair.node;

        return pred.nextNodes.get(0) != null;
    }

    public IndexAndNode <E> findPredecessor (E element) {
        Node <E> pred = head;
        Node <E> next = null;

        int predIndex = -1;
        for (int level = headLevel; level >= 0; level --) {
            next = pred.nextNodes.get(level);
            while (next != null && next.lessThen(element)) {
                predIndex += pred.linkLength.get(level);
                pred = next;
                next = pred.nextNodes.get(level);
            }
        }
        return new IndexAndNode<>(predIndex, pred);
    }

    public Node <E> findPredecessorByIndex (int index) {
        if (index < 0 || index >= size) {
            return null;
        }

        Node <E> pred = head;
        Node <E> next = null;

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

    public static class IndexAndNode <E extends Comparable <E>> {
        int index;
        Node <E> node;
		public IndexAndNode(int index, Node<E> node) {
			this.index = index;
			this.node = node;
		}

    }

    public static class Node <E extends Comparable <E>> {
        E element;
        List <Node <E>> nextNodes;
        List <Integer> linkLength;

		public Node (E element) {
			this.element = element;
            nextNodes = new ArrayList<>();
            linkLength = new ArrayList<>();
		}

        public Node () {
            nextNodes = new ArrayList<>();
            linkLength = new ArrayList<>();
        }

        public boolean lessThen (E otherElement) {
            return otherElement != null && (element == null || element.compareTo(otherElement) < 0);
        }

        public boolean equals (E otherElement) {
            return (element == null && otherElement == null)
                || (element != null && otherElement != null && element.compareTo(otherElement) == 0);
        }
    }
}
