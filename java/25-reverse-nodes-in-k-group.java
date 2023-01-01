
// public class ListNode {
//     int val;
//     ListNode next;

//     ListNode() {
//     }

//     ListNode(int val) {
//         this.val = val;
//     }

//     ListNode(int val, ListNode next) {
//         this.val = val;
//         this.next = next;
//     }
// }

class Solution {

    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(0, head);
        ListNode groupPre = dummy;

        while (true) {
            ListNode kth = getKListNode(groupPre, k);
            if (kth == null)
                break;

            ListNode groupNext = kth.next;

            // define the previous group
            ListNode prev = kth.next;
            ListNode curr = groupPre.next;

            while (curr != groupNext) {
                ListNode tmp = curr.next;
                curr.next = prev;
                prev = curr;
                curr = tmp;
            }

            ListNode tmp = groupPre.next;
            groupPre.next = kth;
            groupPre = tmp;
        }

        return dummy.next;
    }

    private ListNode getKListNode(ListNode head, int k) {
        if (k == 0 || head == null)
            return head;

        return getKListNode(head.next, k - 1);
    }
}