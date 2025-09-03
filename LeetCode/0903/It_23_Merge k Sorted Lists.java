
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
import java.util.*;

class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0)
            return null;

        // 小根堆，按節點值排序
        PriorityQueue<ListNode> pq = new PriorityQueue<>(lists.length, (a, b) -> a.val - b.val);

        // 將非空鏈錶的頭節點加入堆
        for (ListNode node : lists) {
            if (node != null)
                pq.offer(node);
        }

        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;

        while (!pq.isEmpty()) {
            ListNode node = pq.poll(); // 取最小節點
            curr.next = node;
            curr = curr.next;

            if (node.next != null) {
                pq.offer(node.next); // 將下一個節點加入堆
            }
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

        ListNode l1 = new ListNode(1, new ListNode(4, new ListNode(5)));
        ListNode l2 = new ListNode(1, new ListNode(3, new ListNode(4)));
        ListNode l3 = new ListNode(2, new ListNode(6));

        ListNode[] lists = { l1, l2, l3 };

        ListNode merged = sol.mergeKLists(lists);
        sol.printList(merged); // 輸出: 1 1 2 3 4 4 5 6
    }
}

/*
 * 解題思路：
 * 1. 使用小根堆管理各鏈錶的當前節點。
 * 2. 每次取出最小節點，連接到結果鏈錶尾部。
 * 3. 若該節點有 next，將 next 放入堆。
 * 4. 重複直到堆為空。
 * 時間複雜度 O(N log k)，空間複雜度 O(k)。
 */