package linkedlist.singly;


public class LinkedList {
    ListNode head;

	public LinkedList(ListNode head) {
		this.head = head;
	}

    public void append (ListNode node) {
        ListNode cur = head;

        while (cur.next != null) {
            cur = cur.next;
        }
        cur.next = node;
    }

    public void remove (int val) {
        if (head == null) {
            System.out.println("Empty list.");
            return;
        }

        if (head.val == val) {
            head = head.next;
            return;
        }

        // cur 只走到要刪掉的前一個
        ListNode cur = head;
        while (cur.next != null && cur.next.val != val) {
            cur = cur.next;
        }

        if (cur.next == null) {
            System.out.println("Failed, no value of " + val + " in the list.");
            return;
        }

        // cur 来到了前一个
        cur.next = cur.next.next;
    }

    public void removeAll(int val) {
        if (head == null) {
            System.out.println("Empty list.");
            return;
        }

        while (head != null && head.val == val) {
            head = head.next;
        }

        ListNode pre = head;
        ListNode cur = head;
        while (cur != null) {
            if (cur.val == val) {
                pre.next = cur.next;
                cur = cur.next;
                continue;
            }
            pre = cur;
            cur = cur.next;
        }
    }

    public void insert (int val, ListNode node) {
        if (head == null) {
            System.out.println("Empty list.");
            return;
        }

        ListNode cur = head;
        while (cur.val != val) {
            cur = cur.next;
            if (cur == null) {
                System.out.println("Failed, no value of " + val + " in the list.");
                return;
            }
        }

        node.next = cur.next;
        cur.next = node;
    }

    public void reverse () {
        if (head == null) {
            return;
        }

        ListNode pre = null;
        ListNode cur = head;
        ListNode nxt;

        while (cur != null) {
            nxt = cur.next;
            cur.next = pre;
            pre = cur;
            cur = nxt;
        }
        head = pre;
    }

    public void display() {
        display(head);
    }

    private void display (ListNode node) {
        System.out.println("value: " + node.val);
        if (node.next == null) { return; }
        display(node.next);
    }

    public static void main(String[] args) {
        LinkedList list = new LinkedList(new ListNode(0));
        for (int i = 1; i < 1; i++) {
            list.append(new ListNode(i));
        }

        list.reverse();
        list.display();


    }
}
