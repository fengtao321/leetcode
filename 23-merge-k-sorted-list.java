import java.util.ArrayList;
import java.util.Arrays;

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
        if(lists.length == 0 ) {
            return null;
        }
        if(lists.length == 1 ) {
            return lists[0];
        }
        ArrayList<ListNode> arrayLists = new ArrayList<>(Arrays.asList(lists));
        ListNode dummyNode = new ListNode();
        
        int i = 0;
        while (i < (arrayLists.size() - 1)) {
            ListNode header = dummyNode;
            ListNode node = header;
            mergeTwoLists(arrayLists.get(i), arrayLists.get(i + 1), node);
            arrayLists.add(header.next);
            i = i + 2;
        }
    
        return dummyNode.next;
    }

    public void mergeTwoLists(ListNode listA, ListNode listB, ListNode head) {
        if (listA == null || listB == null) {
            head.next = listA == null ? listB : listA;
            return;
        }

        if (listA.val < listB.val) {
            head.next = listA;
            mergeTwoLists(listA.next, listB, head.next);
            return;
        } else {
            head.next = listB;
            mergeTwoLists(listA, listB.next, head.next);
            return;
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