import java.util.*;

class ListNode {
    int val;
    ListNode next;

    ListNode(int val) {
        this.val = val;
    }
}

public class LC21_MergeTwoLists_Clinics {
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;

        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                tail.next = l1;
                l1 = l1.next;
            } else {
                tail.next = l2;
                l2 = l2.next;
            }
            tail = tail.next;
        }

        // 接上剩餘節點
        if (l1 != null)
            tail.next = l1;
        if (l2 != null)
            tail.next = l2;

        return dummy.next;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        ListNode dummy1 = new ListNode(0), tail1 = dummy1;
        for (int i = 0; i < n; i++) {
            tail1.next = new ListNode(sc.nextInt());
            tail1 = tail1.next;
        }

        ListNode dummy2 = new ListNode(0), tail2 = dummy2;
        for (int i = 0; i < m; i++) {
            tail2.next = new ListNode(sc.nextInt());
            tail2 = tail2.next;
        }
        sc.close();

        ListNode merged = mergeTwoLists(dummy1.next, dummy2.next);
        ListNode cur = merged;
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
 * 1. 使用 dummy 節點 + tail 指針。
 * 2. 兩條已排序鏈表同時遍歷，每次接較小節點。
 * 3. 遍歷完成後將剩餘非空鏈表接到尾部。
 * 4. 時間 O(n+m)，空間 O(1)。
 */
