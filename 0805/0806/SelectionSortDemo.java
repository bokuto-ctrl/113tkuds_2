import java.util.Arrays;

class SelectionSortDemo {

    // 選擇排序
    public static void selectionSort(int[] arr) {
        int comparisons = 0;
        int swaps = 0;
        int n = arr.length;

        System.out.println("=== 選擇排序開始 ===");
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;

            for (int j = i + 1; j < n; j++) {
                comparisons++;
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }

            if (minIndex != i) {
                int temp = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = temp;
                swaps++;
            }

            System.out.println("第 " + (i + 1) + " 輪: " + Arrays.toString(arr));
        }

        System.out.println("比較次數：" + comparisons);
        System.out.println("交換次數：" + swaps);
        System.out.println("=== 選擇排序結束 ===\n");
    }

    // 氣泡排序
    public static void bubbleSort(int[] arr) {
        int comparisons = 0;
        int swaps = 0;
        int n = arr.length;

        System.out.println("=== 氣泡排序開始 ===");
        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;

            for (int j = 0; j < n - 1 - i; j++) {
                comparisons++;
                if (arr[j] > arr[j + 1]) {
                    // 交換
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swaps++;
                    swapped = true;
                }
            }

            System.out.println("第 " + (i + 1) + " 輪: " + Arrays.toString(arr));
            if (!swapped)
                break;
        }

        System.out.println("比較次數：" + comparisons);
        System.out.println("交換次數：" + swaps);
        System.out.println("=== 氣泡排序結束 ===\n");
    }

    // 主程式
    public static void main(String[] args) {
        int[] original = { 64, 25, 12, 22, 11 };

        System.out.println("原始陣列: " + Arrays.toString(original));

        // 使用選擇排序
        int[] arr1 = Arrays.copyOf(original, original.length);
        selectionSort(arr1);

        // 使用氣泡排序
        int[] arr2 = Arrays.copyOf(original, original.length);
        bubbleSort(arr2);
    }
}
