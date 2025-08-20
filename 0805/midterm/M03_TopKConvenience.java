import java.util.*;

public class M03_TopKConvenience {
    static class Item {
        String name;
        int qty;
        int idx; // 用來維持輸入順序穩定性（當數量相同時）

        Item(String name, int qty, int idx) {
            this.name = name;
            this.qty = qty;
            this.idx = idx;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int K = sc.nextInt();

        // 儲存所有輸入
        List<Item> all = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String name = sc.next();
            int qty = sc.nextInt();
            all.add(new Item(name, qty, i));
        }

        // Min-Heap，依照 (qty升序, idx升序) 排序
        PriorityQueue<Item> heap = new PriorityQueue<>(new Comparator<Item>() {
            public int compare(Item a, Item b) {
                if (a.qty != b.qty)
                    return a.qty - b.qty; // 小的在前
                return a.idx - b.idx; // 維持輸入順序
            }
        });

        // 維護大小為 K 的 Min-Heap
        for (Item item : all) {
            if (heap.size() < K) {
                heap.offer(item);
            } else if (item.qty > heap.peek().qty ||
                    (item.qty == heap.peek().qty && item.idx < heap.peek().idx)) {
                heap.poll();
                heap.offer(item);
            }
        }

        // 把 heap 內容取出 → 降序排列 (qty大到小, idx小到大)
        List<Item> result = new ArrayList<>(heap);
        Collections.sort(result, new Comparator<Item>() {
            public int compare(Item a, Item b) {
                if (a.qty != b.qty)
                    return b.qty - a.qty; // 大的在前
                return a.idx - b.idx; // 輸入順序優先
            }
        });

        // 輸出
        for (Item it : result) {
            System.out.println(it.name + " " + it.qty);
        }
    }
}

/*
 * 時間複雜度：
 * - 插入 n 筆資料到大小為 K 的 Min-Heap：O(n log K)
 * - 排序 K 筆結果：O(K log K)
 * 整體：O(n log K)
 * 
 * 空間複雜度：O(n)（輸入資料），Heap O(K)
 */
