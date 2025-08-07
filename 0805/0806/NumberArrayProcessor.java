import java.util.*;

class NumberArrayProcessor {

    // 1️⃣ 移除陣列中的重複元素
    public static int[] removeDuplicates(int[] arr) {
        Set<Integer> set = new LinkedHashSet<>();
        for (int num : arr) {
            set.add(num);
        }
        // 將 Set 轉換回陣列
        int[] result = new int[set.size()];
        int i = 0;
        for (int num : set) {
            result[i++] = num;
        }
        return result;
    }

    // 2️⃣ 合併兩個已排序的陣列
    public static int[] mergeSortedArrays(int[] a, int[] b) {
        int[] merged = new int[a.length + b.length];
        int i = 0, j = 0, k = 0;

        while (i < a.length && j < b.length) {
            if (a[i] <= b[j]) {
                merged[k++] = a[i++];
            } else {
                merged[k++] = b[j++];
            }
        }
        // 將剩餘元素加入
        while (i < a.length)
            merged[k++] = a[i++];
        while (j < b.length)
            merged[k++] = b[j++];
        return merged;
    }

    // 3️⃣ 找出陣列中出現頻率最高的元素
    public static int findMostFrequent(int[] arr) {
        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int num : arr) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }

        int maxFreq = 0;
        int mostFrequent = arr[0];

        for (Map.Entry<Integer, Integer> entry : freqMap.entrySet()) {
            if (entry.getValue() > maxFreq) {
                maxFreq = entry.getValue();
                mostFrequent = entry.getKey();
            }
        }
        return mostFrequent;
    }

    // 4️⃣ 將陣列分割成兩個總和近似相等的子陣列（貪心法）
    public static List<List<Integer>> splitArray(int[] arr) {
        Arrays.sort(arr);
        List<Integer> part1 = new ArrayList<>();
        List<Integer> part2 = new ArrayList<>();

        int sum1 = 0, sum2 = 0;

        // 從大到小分配到較小總和的那組
        for (int i = arr.length - 1; i >= 0; i--) {
            if (sum1 <= sum2) {
                part1.add(arr[i]);
                sum1 += arr[i];
            } else {
                part2.add(arr[i]);
                sum2 += arr[i];
            }
        }

        List<List<Integer>> result = new ArrayList<>();
        result.add(part1);
        result.add(part2);
        return result;
    }

    // ✅ 輸出陣列內容
    public static void printArray(String label, int[] arr) {
        System.out.print(label + ": ");
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    // ✅ 主程式測試
    public static void main(String[] args) {
        int[] arr1 = { 1, 2, 2, 3, 4, 4, 5 };
        int[] arr2 = { 2, 4, 6, 8 };
        int[] arr3 = { 1, 3, 3, 3, 2, 2, 5, 5, 5, 5, 4 };

        // 測試 1：移除重複元素
        int[] unique = removeDuplicates(arr1);
        printArray("移除重複元素後", unique);

        // 測試 2：合併排序陣列
        int[] merged = mergeSortedArrays(new int[] { 1, 3, 5 }, new int[] { 2, 4, 6 });
        printArray("合併後的陣列", merged);

        // 測試 3：找出出現次數最多的元素
        int mostFrequent = findMostFrequent(arr3);
        System.out.println("出現最多次的元素：" + mostFrequent);

        // 測試 4：分割為兩個總和近似的子陣列
        int[] arr4 = { 1, 2, 3, 4, 5, 6, 7 };
        List<List<Integer>> split = splitArray(arr4);
        System.out.println("分割結果：");
        System.out.println("子陣列1：" + split.get(0));
        System.out.println("子陣列2：" + split.get(1));
    }
}
