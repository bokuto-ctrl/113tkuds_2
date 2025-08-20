import java.util.*;

public class M12_MergeKTimeTables {
    static class Node implements Comparable<Node> {
        int time, listIdx, elemIdx;

        Node(int t, int l, int e) {
            time = t;
            listIdx = l;
            elemIdx = e;
        }

        @Override
        public int compareTo(Node o) {
            return this.time - o.time; // Min-Heap
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int K = sc.nextInt();

        List<List<Integer>> lists = new ArrayList<>();
        for (int i = 0; i < K; i++) {
            int len = sc.nextInt();
            List<Integer> tmp = new ArrayList<>();
            for (int j = 0; j < len; j++)
                tmp.add(sc.nextInt());
            lists.add(tmp);
        }

        List<Integer> merged = mergeKLists(lists);

        for (int i = 0; i < merged.size(); i++) {
            System.out.print(merged.get(i) + (i == merged.size() - 1 ? "\n" : " "));
        }
    }

    public static List<Integer> mergeKLists(List<List<Integer>> lists) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        List<Integer> result = new ArrayList<>();

        // 初始化，每個列表推入第一個元素
        for (int i = 0; i < lists.size(); i++) {
            if (!lists.get(i).isEmpty()) {
                pq.offer(new Node(lists.get(i).get(0), i, 0));
            }
        }

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            result.add(cur.time);

            int nextIdx = cur.elemIdx + 1;
            if (nextIdx < lists.get(cur.listIdx).size()) {
                pq.offer(new Node(lists.get(cur.listIdx).get(nextIdx), cur.listIdx, nextIdx));
            }
        }
        return result;
    }
}
