package list.singly;
/**
  離開 while 前要檢查是否需要新增節點，因為進位可能會一直進位下去直到進位項確實 = 0

  special cases:
  [1]
  [9, 9, 9, 9, 9, 9, 9, 9]
 */
public class AddTwoNumbers {
    public ListNode addTwoNumbers (ListNode l1, ListNode l2) {
        ListNode head = l1;

        ListNode pre = null;

        int sum = 0;
        int carry = 0;
        while (l1 != null && l2 != null) {
            sum = l1.val + l2.val + carry;
            if (sum > 9) {
                sum %= 10;
                carry = 1;
                l1.next = l1.next == null ? new ListNode(0) : l1.next;
                l2.next = l2.next == null ? new ListNode(0) : l2.next;
            } else {
                carry = 0;
            }

            l1.val = sum;
            pre = l1;
            l1 = l1.next;
            l2 = l2.next;
        }

        if (l1 != null) {
            pre.next = l2;
        }

        // if (l1 == null && l2 == null) {
        //     pre.next = carry == 1 ? new ListNode(1) : null;
        //     return head;
        // }

        // ListNode list = l1 == null ? l2 : l1;
        // pre.next = list;
        // while (carry == 1 && list != null) {
        //     if (list.val < 9) {
        //         list.val ++;
        //         carry = 0;
        //     } else {
        //         list.val = 0;
        //         pre = list;
        //         list = list.next;
        //     }
        // }

        // if (carry == 1) {
        //     pre.next = new ListNode(1);
        // }
        return head;
    }
}
