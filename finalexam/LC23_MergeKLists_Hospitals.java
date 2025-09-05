import java.util.*;

class ListNode {
    int val;
    ListNode next;

    ListNode(int val) {
        this.val = val;
    }
}

public class LC23_MergeKLists_Hospitals {
    public static ListNode mergeKLists(List<ListNode> lists) {
        PriorityQueue<ListNode> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.val));
        for (ListNode node : lists) {
            if (node != null)
                pq.offer(node);
        }

        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;

        while (!pq.isEmpty()) {
            ListNode minNode = pq.poll();
            tail.next = minNode;
            tail = tail.next;
            if (minNode.next != null)
                pq.offer(minNode.next);
        }

        return dummy.next;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int k = sc.nextInt();
        sc.nextLine();

        List<ListNode> lists = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            String[] line = sc.nextLine().trim().split(" ");
            ListNode dummy = new ListNode(0);
            ListNode tail = dummy;
            for (String s : line) {
                int val = Integer.parseInt(s);
                if (val == -1)
                    break;
                tail.next = new ListNode(val);
                tail = tail.next;
            }
            lists.add(dummy.next);
        }
        sc.close();

        ListNode merged = mergeKLists(lists);
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
 * 1. 將 k 條已排序鏈表的非空頭節點放入最小堆（PriorityQueue）。
 * 2. 每次從堆取出最小節點，接到結果鏈表尾部。
 * 3. 若取出的節點後面還有節點，將其加入堆中。
 * 4. 重複直到堆空，得到合併後的升序鏈表。
 * 5. 時間 O(N log k)，空間 O(k)。
 */
