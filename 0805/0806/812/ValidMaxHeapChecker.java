public class ValidMaxHeapChecker {

    // 檢查是否為有效的 Max Heap
    public static boolean isValidMaxHeap(int[] arr) {
        int n = arr.length;

        // 遍歷所有非葉子節點 (最後一個非葉子節點是 (n-2)/2)
        for (int i = 0; i <= (n - 2) / 2; i++) {
            int left = 2 * i + 1; // 左子節點
            int right = 2 * i + 2; // 右子節點

            // 檢查左子節點
            if (left < n && arr[left] > arr[i]) {
                System.out.println("false (索引 " + left + " 的 " + arr[left] + " 大於父節點 " + arr[i] + ")");
                return false;
            }

            // 檢查右子節點
            if (right < n && arr[right] > arr[i]) {
                System.out.println("false (索引 " + right + " 的 " + arr[right] + " 大於父節點 " + arr[i] + ")");
                return false;
            }
        }
        return true;
    }

    // 測試程式
    public static void main(String[] args) {
        int[][] testCases = {
                { 100, 90, 80, 70, 60, 75, 65 }, // true
                { 100, 90, 80, 95, 60, 75, 65 }, // false
                { 50 }, // true
                {} // true
        };

        for (int[] arr : testCases) {
            boolean result = isValidMaxHeap(arr);
            if (result) {
                System.out.println("true");
            }
            System.out.println("-----------------");
        }
    }
}
