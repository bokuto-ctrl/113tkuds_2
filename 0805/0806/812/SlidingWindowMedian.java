import java.util.*;

public class SlidingWindowMedian {

    private PriorityQueue<Integer> maxHeap; // 小的一半 (大頂堆)
    private PriorityQueue<Integer> minHeap; // 大的一半 (小頂堆)

    public SlidingWindowMedian() {
        maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        minHeap = new PriorityQueue<>();
    }

    // 插入元素
    private void add(int num) {
        if (maxHeap.isEmpty() || num <= maxHeap.peek()) {
            maxHeap.offer(num);
        } else {
            minHeap.offer(num);
        }
        balanceHeaps();
    }

    // 移除元素
    private void remove(int num) {
        if (num <= maxHeap.peek()) {
            maxHeap.remove(num); // 注意 remove 是 O(n)，可用 TreeMap 優化
        } else {
            minHeap.remove(num);
        }
        balanceHeaps();
    }

    // 平衡兩個 heap 的大小
    private void balanceHeaps() {
        while (maxHeap.size() > minHeap.size() + 1) {
            minHeap.offer(maxHeap.poll());
        }
        while (minHeap.size() > maxHeap.size()) {
            maxHeap.offer(minHeap.poll());
        }
    }

    // 取得中位數
    private double getMedian() {
        if (maxHeap.size() == minHeap.size()) {
            return (maxHeap.peek() + minHeap.peek()) / 2.0;
        } else {
            return maxHeap.peek();
        }
    }

    // 計算滑動視窗中位數
    public double[] medianSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        if (n == 0)
            return new double[0];
        double[] result = new double[n - k + 1];

        for (int i = 0; i < n; i++) {
            add(nums[i]);
            if (i >= k - 1) {
                result[i - k + 1] = getMedian();
                remove(nums[i - k + 1]);
            }
        }

        return result;
    }

    // 測試程式
    public static void main(String[] args) {
        SlidingWindowMedian solver = new SlidingWindowMedian();

        int[] nums1 = { 1, 3, -1, -3, 5, 3, 6, 7 };
        int k1 = 3;
        System.out.println("滑動中位數 (K=3)：");
        System.out.println(Arrays.toString(solver.medianSlidingWindow(nums1, k1)));

        int[] nums2 = { 1, 2, 3, 4 };
        int k2 = 2;
        SlidingWindowMedian solver2 = new SlidingWindowMedian(); // 每次新實例
        System.out.println("滑動中位數 (K=2)：");
        System.out.println(Arrays.toString(solver2.medianSlidingWindow(nums2, k2)));
    }
}
