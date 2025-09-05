import java.util.*;

class ListNode {
    int val;
    ListNode next;

    ListNode(int val) {
        this.val = val;
    }
}

public class LC25_ReverseKGroup_Shifts {
    public static ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || k == 1)
            return head;

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy, cur = head;

        int count = 0;
        while (cur != null) {
            count++;
            cur = cur.next;
        }

        cur = dummy.next;
        while (count >= k) {
            ListNode tail = cur;
            ListNode next = cur.next;
            // 反轉 k 個節點
            for (int i = 1; i < k; i++) {
                ListNode tmp = next.next;
                next.next = cur;
                cur = next;
                next = tmp;
            }
            // 連接上一段
            ListNode tmp = prev.next;
            prev.next = cur;
            tail.next = next;
            prev = tail;
            cur = tail.next;
            count -= k;
        }

        return dummy.next;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int k = sc.nextInt();
        sc.nextLine();
        String line = sc.nextLine().trim();
        if (line.isEmpty())
            return;

        String[] nums = line.split(" ");
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        for (String s : nums) {
            tail.next = new ListNode(Integer.parseInt(s));
            tail = tail.next;
        }

        ListNode head = reverseKGroup(dummy.next, k);
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
 * 1. 使用 dummy 節點指向頭。
 * 2. 迭代檢查是否有足夠的 k 個節點。
 * 3. 若有，將該區段原地反轉。
 * 4. 反轉後接回前段與後段，更新指標準備下一輪。
 * 5. 尾端不足 k 個節點保持原樣。
 * 6. 時間 O(n)，空間 O(1)。
 */
