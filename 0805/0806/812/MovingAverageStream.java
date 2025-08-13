import java.util.*;

public class MovingAverageStream {

    private int size;
    private Queue<Integer> window;
    private long sum;

    // 雙 Heap 維護中位數
    private PriorityQueue<Integer> maxHeap; // 小的一半
    private PriorityQueue<Integer> minHeap; // 大的一半
    private Map<Integer, Integer> delayed; // 延遲刪除元素

    // TreeMap 用於快速查最小值和最大值
    private TreeMap<Integer, Integer> countMap;

    public MovingAverageStream(int size) {
        this.size = size;
        this.window = new LinkedList<>();
        this.sum = 0;
        this.maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        this.minHeap = new PriorityQueue<>();
        this.delayed = new HashMap<>();
        this.countMap = new TreeMap<>();
    }

    public double next(int val) {
        window.offer(val);
        sum += val;

        // 更新中位數雙 Heap
        if (maxHeap.isEmpty() || val <= maxHeap.peek()) {
            maxHeap.offer(val);
        } else {
            minHeap.offer(val);
        }
        balanceHeaps();

        // 更新 TreeMap
        countMap.put(val, countMap.getOrDefault(val, 0) + 1);

        // 若視窗超過 size，移除最舊元素
        if (window.size() > size) {
            int old = window.poll();
            sum -= old;
            remove(old);
            countMap.put(old, countMap.get(old) - 1);
            if (countMap.get(old) == 0)
                countMap.remove(old);
        }

        return (double) sum / window.size();
    }

    private void remove(int val) {
        // 延遲刪除
        delayed.put(val, delayed.getOrDefault(val, 0) + 1);

        // 清理 heap
        cleanHeap(maxHeap);
        cleanHeap(minHeap);

        balanceHeaps();
    }

    private void cleanHeap(PriorityQueue<Integer> heap) {
        while (!heap.isEmpty() && delayed.containsKey(heap.peek())) {
            int num = heap.peek();
            delayed.put(num, delayed.get(num) - 1);
            if (delayed.get(num) == 0)
                delayed.remove(num);
            heap.poll();
        }
    }

    private void balanceHeaps() {
        // 保持 maxHeap.size() >= minHeap.size()
        while (maxHeap.size() > minHeap.size() + 1) {
            minHeap.offer(maxHeap.poll());
            cleanHeap(maxHeap);
            cleanHeap(minHeap);
        }
        while (minHeap.size() > maxHeap.size()) {
            maxHeap.offer(minHeap.poll());
            cleanHeap(maxHeap);
            cleanHeap(minHeap);
        }
    }

    public double getMedian() {
        if (maxHeap.isEmpty() && minHeap.isEmpty())
            return 0.0;
        if (maxHeap.size() == minHeap.size()) {
            return (maxHeap.peek() + minHeap.peek()) / 2.0;
        } else {
            return maxHeap.peek();
        }
    }

    public int getMin() {
        return countMap.firstKey();
    }

    public int getMax() {
        return countMap.lastKey();
    }

    // 測試程式
    public static void main(String[] args) {
        MovingAverageStream ma = new MovingAverageStream(3);
        System.out.println(ma.next(1)); // 1.0
        System.out.println(ma.next(10)); // 5.5
        System.out.println(ma.next(3)); // 4.666...
        System.out.println(ma.next(5)); // 6.0
        System.out.println("Median: " + ma.getMedian()); // 5.0
        System.out.println("Min: " + ma.getMin()); // 3
        System.out.println("Max: " + ma.getMax()); // 10
    }
}
