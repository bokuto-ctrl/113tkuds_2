import java.util.*;

public class M11_HeapSortWithTie {
    static class Pair implements Comparable<Pair> {
        int score, idx;

        Pair(int s, int i) {
            score = s;
            idx = i;
        }

        @Override
        public int compareTo(Pair o) {
            if (this.score != o.score)
                return o.score - this.score; // Max-Heap
            return o.idx - this.idx; // 分數相同，索引小優先
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Pair[] arr = new Pair[n];

        for (int i = 0; i < n; i++) {
            int score = sc.nextInt();
            arr[i] = new Pair(score, i);
        }

        heapSort(arr);

        for (int i = 0; i < n; i++) {
            System.out.print(arr[i].score + (i == n - 1 ? "\n" : " "));
        }
    }

    public static void heapSort(Pair[] arr) {
        int n = arr.length;

        // Build heap
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }

        // Extract elements
        for (int i = n - 1; i > 0; i--) {
            Pair tmp = arr[0];
            arr[0] = arr[i];
            arr[i] = tmp;

            heapify(arr, i, 0);
        }
    }

    // Max-Heapify
    public static void heapify(Pair[] arr, int n, int i) {
        int largest = i;
        int l = 2 * i + 1, r = 2 * i + 2;

        if (l < n && arr[l].compareTo(arr[largest]) > 0)
            largest = l;
        if (r < n && arr[r].compareTo(arr[largest]) > 0)
            largest = r;

        if (largest != i) {
            Pair tmp = arr[i];
            arr[i] = arr[largest];
            arr[largest] = tmp;
            heapify(arr, n, largest);
        }
    }
}

/*
 * Time Complexity: O(n log n)
 * 說明：建堆 O(n)，每次提取 n 次，每次 heapify O(log n) → O(n log n)
 * Space Complexity: O(1) 原地排序
 */
