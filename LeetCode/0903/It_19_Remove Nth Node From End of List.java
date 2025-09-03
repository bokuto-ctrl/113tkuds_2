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
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // 使用虛擬頭節點，避免刪除頭節點的特殊情況
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode first = dummy;
        ListNode second = dummy;

        // 先讓 first 指針走 n+1 步
        for (int i = 0; i <= n; i++) {
            first = first.next;
        }

        // 同時移動 first 和 second，直到 first 到尾
        while (first != null) {
            first = first.next;
            second = second.next;
        }

        // 刪除倒數第 n 個節點
        second.next = second.next.next;

        return dummy.next;
    }
}

/*
 * 解題思路：
 * 1. 使用虛擬頭節點(dummy)來處理刪除頭節點的情況。
 * 2. 設置兩個指針 first 和 second，先讓 first 前進 n+1 步。
 * 3. 同時移動 first 和 second，直到 first 到達尾部。
 * 4. second.next 即為要刪除的節點，直接跳過它。
 * 5. 回傳 dummy.next 作為新的頭節點。
 * 時間複雜度 O(n)，空間複雜度 O(1)。
 */