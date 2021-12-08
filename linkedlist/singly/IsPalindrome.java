package linkedlist.singly;

public class IsPalindrome {
    public boolean isPalindrome(ListNode head) {
        if (head.next == null) {
            return true;
        }

        if (head.next.next == null) {
            return head.val == head.next.val ? true : false;
        }

        ListNode slow = head;
        ListNode fast = head;

        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        if (fast.next != null) {
            slow = slow.next;
            fast = fast.next;
        }

        ListNode pre = null;
        ListNode cur = slow;
        ListNode nxt = slow;

        while (cur != null) {
            nxt = cur.next;
            cur.next = pre;
            pre = cur;
            cur = nxt;
        }

        ListNode tail = pre;

        while (head != null && tail != null) {
            if (head.val != tail.val) {
                return false;
            }
            head = head.next;
            tail = tail.next;
        }

        return true;
    }
}
