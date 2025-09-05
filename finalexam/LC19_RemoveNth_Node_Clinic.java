import java.util.*;

class ListNode {
    int val;
    ListNode next;

    ListNode(int val) {
        this.val = val;
    }
}

public class LC19_RemoveNth_Node_Clinic {
    public static ListNode removeNthFromEnd(ListNode head, int k) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode fast = dummy;
        ListNode slow = dummy;

        // fast 先走 k 步
        for (int i = 0; i < k; i++) {
            fast = fast.next;
        }

        // 同步移動到末尾
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }

        // 刪除 slow.next
        slow.next = slow.next.next;
        return dummy.next;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        for (int i = 0; i < n; i++) {
            tail.next = new ListNode(sc.nextInt());
            tail = tail.next;
        }
        int k = sc.nextInt();
        sc.close();

        ListNode head = removeNthFromEnd(dummy.next, k);
        ListNode cur = head;
        while (cur != null) {
            System.out.print(cur.val);
            if (cur.next != null)
                System.out.print(" ");
            cur = cur.next;
        }
    }
}
/*
 * 解題思路：
 * 1. 使用快慢指標（fast, slow）。
 * 2. fast 先走 k 步，之後快慢指標同步移動。
 * 3. 當 fast 到尾，slow 指向待刪節點前一個，調整指標刪除目標節點。
 * 4. 一次遍歷完成，時間 O(n)，空間 O(1)。
 */
