import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.List;

class HeapNode {
    int value;
    int arrayIndex; // 哪一個陣列
    int elementIndex; // 在該陣列的索引

    public HeapNode(int value, int arrayIndex, int elementIndex) {
        this.value = value;
        this.arrayIndex = arrayIndex;
        this.elementIndex = elementIndex;
    }
}

public class MergeKSortedArrays {

    public static int[] mergeKArrays(int[][] arrays) {
        PriorityQueue<HeapNode> minHeap = new PriorityQueue<>((a, b) -> a.value - b.value);
        List<Integer> resultList = new ArrayList<>();

        // 將每個陣列的第一個元素放入 heap
        for (int i = 0; i < arrays.length; i++) {
            if (arrays[i].length > 0) {
                minHeap.offer(new HeapNode(arrays[i][0], i, 0));
            }
        }

        // 合併過程
        while (!minHeap.isEmpty()) {
            HeapNode node = minHeap.poll();
            resultList.add(node.value);

            int nextIndex = node.elementIndex + 1;
            if (nextIndex < arrays[node.arrayIndex].length) {
                minHeap.offer(new HeapNode(arrays[node.arrayIndex][nextIndex], node.arrayIndex, nextIndex));
            }
        }

        // 轉換成陣列
        int[] result = new int[resultList.size()];
        for (int i = 0; i < resultList.size(); i++) {
            result[i] = resultList.get(i);
        }

        return result;
    }

    // 測試程式
    public static void main(String[] args) {
        int[][][] testCases = {
                { { 1, 4, 5 }, { 1, 3, 4 }, { 2, 6 } },
                { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } },
                { { 1 }, { 0 } }
        };

        for (int[][] arrays : testCases) {
            int[] merged = mergeKArrays(arrays);
            System.out.println(java.util.Arrays.toString(merged));
        }
    }
}
