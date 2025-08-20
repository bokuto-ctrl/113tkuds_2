import java.util.*;

public class M01_BuildHeap {
    // 判斷是否為 max-heap
    static boolean isMax;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 讀取 heap 類型
        String type = sc.next();
        isMax = type.equals("max");

        // 讀取陣列大小
        int n = sc.nextInt();
        int[] heap = new int[n];

        // 讀取元素
        for (int i = 0; i < n; i++) {
            heap[i] = sc.nextInt();
        }

        // 自底向上建堆
        buildHeap(heap, n);

        // 輸出結果
        for (int i = 0; i < n; i++) {
            System.out.print(heap[i]);
            if (i < n - 1)
                System.out.print(" ");
        }
    }

    // 建堆：從最後一個非葉節點開始往下 Heapify
    public static void buildHeap(int[] arr, int n) {
        for (int i = (n / 2) - 1; i >= 0; i--) {
            heapifyDown(arr, n, i);
        }
    }

    // Heapify (下沉)
    public static void heapifyDown(int[] arr, int n, int i) {
        int extreme = i; // max-heap -> 最大值索引; min-heap -> 最小值索引
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        // 左子節點比較
        if (left < n && compare(arr[left], arr[extreme])) {
            extreme = left;
        }
        // 右子節點比較
        if (right < n && compare(arr[right], arr[extreme])) {
            extreme = right;
        }

        // 若需要交換
        if (extreme != i) {
            int temp = arr[i];
            arr[i] = arr[extreme];
            arr[extreme] = temp;
            heapifyDown(arr, n, extreme);
        }
    }

    // 比較函式：依 max/min 決定
    public static boolean compare(int child, int parent) {
        if (isMax) {
            return child > parent; // Max-Heap
        } else {
            return child < parent; // Min-Heap
        }
    }
}

/*
 * 時間複雜度分析：
 * ----------------------------------
 * 1. buildHeap() 從最後一個非葉節點開始呼叫 heapifyDown。
 * 2. heapifyDown 最差情況為 O(log n)，但不是每個節點都要執行到最深。
 * 3. 事實上總成本近似：
 * n/2 節點下沉最多 1 層
 * n/4 節點下沉最多 2 層
 * n/8 節點下沉最多 3 層
 * ...
 * 總和 = O(n)
 * 
 * 因此，自底向上建堆的整體時間複雜度為 O(n)。
 * 空間複雜度：O(1) （原地建堆）
 * ----------------------------------
 */
