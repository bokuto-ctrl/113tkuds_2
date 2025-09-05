import java.util.*;

class ListNode {
    int val;
    ListNode next;

    ListNode(int val) {
        this.val = val;
    }
}

public class LC24_SwapPairs_Shifts {
    public static ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;

        while (prev.next != null && prev.next.next != null) {
            ListNode a = prev.next;
            ListNode b = a.next;

            // 交換 a 和 b
            prev.next = b;
            a.next = b.next;
            b.next = a;

            // 移動 prev 到下一對
            prev = a;
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String line = sc.nextLine().trim();
        sc.close();
        if (line.isEmpty())
            return;

        String[] nums = line.split(" ");
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        for (String s : nums) {
            tail.next = new ListNode(Integer.parseInt(s));
            tail = tail.next;
        }

        ListNode swapped = swapPairs(dummy.next);
        ListNode cur = swapped;
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
 * 1. 使用 dummy 節點指向頭，prev 指向前一節點。
 * 2. 迴圈遍歷鏈表，每次檢查 a 和 a.next。
 * 3. 若存在一對節點，交換位置：prev.next = b, a.next = b.next, b.next = a。
 * 4. 更新 prev 指向 a，繼續下一對。
 * 5. 奇數長度最後節點保持不變。
 * 6. 時間 O(n)，空間 O(1)。
 */
