package linkedlist.singly;

import java.util.HashMap;

public class CopyRandomPointerLinkedList {
    public Node copyRandomList(Node head) {
        /*
         */
        if (head == null) {
            return null;
        }

        Node cur = head;
        Node nxt;
        while (cur != null) {
            nxt = cur.next;
            cur.next = new Node (cur.val);
            cur.next.next = nxt;
            cur = nxt;
        }

        cur = head;
        Node cln;
        while (cur != null) {
            cln = cur.next;
            cln.random = cur.random != null ? cur.random.next : null;
            cur = cur.next.next;
        }

        Node res = head.next;
        cur = head;
        cln = null;
        nxt = null;
        while (cur != null) {
            cln = cur.next;              // [1] -> [1'] -> [2] -> [2'] -> null
            nxt = cur.next.next;
            cur.next = nxt;     // 恢復原鏈表的 next
            cln.next = nxt != null ? nxt.next : null;
            cur = nxt;
        }
        return res;
    }
    

    public Node copyRandomListHash(Node head) {
        /*
          使用 HashMap, <K, V> = <Orignal Node, New Node>
          然後遍歷原鏈表，依次取出原鏈表對應的新節點，
          透過 ori.next 查詢對應的新節點得到 new.next
          透過 ori.random 查詢對應的新節點得到 new.random
         */

        HashMap <Node, Node> map = new HashMap<>();
        if (head == null) {
            return null;
        }

        Node cur = head;
        while (cur != null) {
            map.put(cur, new Node (cur.val));
            cur = cur.next;
        }

        cur = head;
        while (cur != null) {
            map.get(cur).next = map.get(cur.next);
            map.get(cur).random = map.get(cur.random);

            cur = cur.next;
        }

        return map.get(head);
    }
}

class Node{
    int val;
    Node next = null;
    Node random = null;

	public Node(int val) {
		this.val = val;
	}

	public Node(int val, Node next, Node random) {
		this(val);
        this.next = next;
        this.random = random;
	}
}
