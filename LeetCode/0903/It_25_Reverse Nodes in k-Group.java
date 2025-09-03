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
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || k == 1)
            return head;

        ListNode dummy = new ListNode(0, head);
        ListNode prevGroup = dummy;

        while (true) {
            // 檢查剩餘節點是否足夠 k 個
            ListNode kth = prevGroup;
            for (int i = 0; i < k && kth != null; i++) {
                kth = kth.next;
            }
            if (kth == null)
                break; // 不足 k 個，結束

            ListNode groupStart = prevGroup.next;
            ListNode nextGroup = kth.next;

            // 反轉當前 k 個節點
            ListNode prev = nextGroup;
            ListNode curr = groupStart;
            while (curr != nextGroup) {
                ListNode tmp = curr.next;
                curr.next = prev;
                prev = curr;
                curr = tmp;
            }

            // 接回反轉後的節點
            prevGroup.next = kth;
            prevGroup = groupStart;
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

        ListNode head1 = new ListNode(1, new ListNode(2, new ListNode(3,
                new ListNode(4, new ListNode(5)))));
        ListNode head2 = new ListNode(1, new ListNode(2, new ListNode(3,
                new ListNode(4, new ListNode(5)))));

        sol.printList(sol.reverseKGroup(head1, 2)); // 2 1 4 3 5
        sol.printList(sol.reverseKGroup(head2, 3)); // 3 2 1 4 5
    }
}

/*
 * 解題思路：
 * 1. 使用 dummy 節點簡化頭節點操作。
 * 2. prevGroup 指向前一組反轉後的尾節點。
 * 3. 檢查剩餘節點是否足夠 k 個，若不足則保留原順序。
 * 4. 使用迴圈反轉 k 個節點，將反轉後的鏈接接回原鏈表。
 * 5. 移動 prevGroup 到本組反轉後的尾節點，重複直到鏈表結束。
 * 時間複雜度 O(n)，空間複雜度 O(1)。
 */