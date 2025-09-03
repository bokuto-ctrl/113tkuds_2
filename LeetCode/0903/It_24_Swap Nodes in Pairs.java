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
    public ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(0, head);
        ListNode prev = dummy;

        while (prev.next != null && prev.next.next != null) {
            ListNode first = prev.next;
            ListNode second = first.next;

            // 交換節點
            first.next = second.next;
            second.next = first;
            prev.next = second;

            // 移動 prev 指針到下一對
            prev = first;
        }

        return dummy.next;
    }

    // 輔助方法：列印鏈錶
    public void printList(ListNode head) {
        while (head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Solution sol = new Solution();

        ListNode head1 = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4))));
        ListNode head2 = null;
        ListNode head3 = new ListNode(1);
        ListNode head4 = new ListNode(1, new ListNode(2, new ListNode(3)));

        sol.printList(sol.swapPairs(head1)); // 2 1 4 3
        sol.printList(sol.swapPairs(head2)); // (空)
        sol.printList(sol.swapPairs(head3)); // 1
        sol.printList(sol.swapPairs(head4)); // 2 1 3
    }
}

/*
 * 解題思路：
 * 1. 使用 dummy 節點簡化頭節點處理。
 * 2. prev.next 和 prev.next.next 為每一對要交換的節點。
 * 3. 將兩個節點交換指向，然後 prev 移到下一對。
 * 4. 重複直到剩餘節點不足兩個。
 * 時間複雜度 O(n)，空間複雜度 O(1)。
 */