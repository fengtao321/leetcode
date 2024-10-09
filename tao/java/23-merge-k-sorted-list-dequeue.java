import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode() {}
 * ListNode(int val) { this.val = val; }
 * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */

class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0) {
            return null;
        }

        Deque<ListNode> queue = new ArrayDeque<>();
        for (ListNode list : lists) {
            queue.add(list);
        }

        while (queue.size() > 1) {
            queue.add(mergeTwoLists(queue.pollFirst(), queue.pollFirst()));
        }

        return queue.pollFirst();
    }

    public ListNode mergeTwoLists(ListNode listA, ListNode listB) {
        if (listA == null || listB == null) {
            return listA == null ? listB : listA;
        }

        if (listA.val < listB.val) {
            listA.next = mergeTwoLists(listA.next, listB);
            return listA;
        } else {
            listB.next = mergeTwoLists(listA, listB.next);
            return listB;
        }
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}