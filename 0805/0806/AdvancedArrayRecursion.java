import java.util.Arrays;

class AdvancedArrayRecursion {

    // 1️⃣ 遞迴實作快速排序
    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pivotIdx = partition(arr, low, high);
            quickSort(arr, low, pivotIdx - 1);
            quickSort(arr, pivotIdx + 1, high);
        }
    }

    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1; // 小於 pivot 的元素最後一個 index
        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, high);
        return i + 1;
    }

    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    // 2️⃣ 遞迴合併兩個已排序陣列
    public static int[] mergeSortedArrays(int[] A, int[] B) {
        return mergeRecursive(A, B, 0, 0);
    }

    private static int[] mergeRecursive(int[] A, int[] B, int i, int j) {
        if (i == A.length)
            return Arrays.copyOfRange(B, j, B.length);
        if (j == B.length)
            return Arrays.copyOfRange(A, i, A.length);

        if (A[i] <= B[j]) {
            int[] rest = mergeRecursive(A, B, i + 1, j);
            return prepend(A[i], rest);
        } else {
            int[] rest = mergeRecursive(A, B, i, j + 1);
            return prepend(B[j], rest);
        }
    }

    private static int[] prepend(int val, int[] arr) {
        int[] res = new int[arr.length + 1];
        res[0] = val;
        System.arraycopy(arr, 0, res, 1, arr.length);
        return res;
    }

    // 3️⃣ 遞迴尋找第 k 小元素（QuickSelect）
    public static int kthSmallest(int[] arr, int k) {
        return quickSelect(arr, 0, arr.length - 1, k - 1);
    }

    private static int quickSelect(int[] arr, int low, int high, int k) {
        if (low == high)
            return arr[low];

        int pivotIndex = partition(arr, low, high);

        if (k == pivotIndex)
            return arr[k];
        else if (k < pivotIndex)
            return quickSelect(arr, low, pivotIndex - 1, k);
        else
            return quickSelect(arr, pivotIndex + 1, high, k);
    }

    // 4️⃣ 遞迴檢查子序列總和是否等於目標值
    public static boolean subsetSum(int[] arr, int target) {
        return subsetSumRecursive(arr, arr.length - 1, target);
    }

    private static boolean subsetSumRecursive(int[] arr, int idx, int target) {
        if (target == 0)
            return true;
        if (idx < 0)
            return false;

        // 選或不選 arr[idx]
        return subsetSumRecursive(arr, idx - 1, target) ||
                subsetSumRecursive(arr, idx - 1, target - arr[idx]);
    }

    // 🧪 主程式測試
    public static void main(String[] args) {
        // 1️⃣ 測試快速排序
        int[] arr1 = { 5, 2, 9, 1, 7 };
        quickSort(arr1, 0, arr1.length - 1);
        System.out.println("QuickSort: " + Arrays.toString(arr1)); // [1, 2, 5, 7, 9]

        // 2️⃣ 測試遞迴合併
        int[] sortedA = { 1, 4, 6 };
        int[] sortedB = { 2, 3, 5, 7 };
        System.out.println("Merged: " + Arrays.toString(mergeSortedArrays(sortedA, sortedB))); // [1, 2, 3, 4, 5, 6, 7]

        // 3️⃣ 測試第 k 小元素
        int[] arr2 = { 7, 10, 4, 3, 20, 15 };
        System.out.println("Kth smallest (k=3): " + kthSmallest(arr2, 3)); // 7

        // 4️⃣ 測試子序列總和
        int[] arr3 = { 3, 34, 4, 12, 5, 2 };
        System.out.println("Subset sum to 9 exists? " + subsetSum(arr3, 9)); // true
        System.out.println("Subset sum to 30 exists? " + subsetSum(arr3, 30)); // false
    }
}
