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
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode(0); // 虛擬頭節點
        ListNode curr = dummy;

        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                curr.next = list1;
                list1 = list1.next;
            } else {
                curr.next = list2;
                list2 = list2.next;
            }
            curr = curr.next;
        }

        // 連接剩餘節點
        if (list1 != null)
            curr.next = list1;
        if (list2 != null)
            curr.next = list2;

        return dummy.next;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        // 範例可以自行構建 ListNode 進行測試
    }
}

/*
 * 解題思路：
 * 1. 使用虛擬頭節點(dummy)避免處理空鏈錶的特殊情況。
 * 2. 同時遍歷 list1 和 list2，比較節點值，將較小的節點接到結果鏈錶。
 * 3. 遍歷結束後，將非空鏈錶的剩餘部分直接接到結果鏈錶尾。
 * 4. 返回 dummy.next 作為合併後的鏈錶頭。
 * 時間複雜度 O(n+m)，空間複雜度 O(1)。
 */