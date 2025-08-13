import java.util.ArrayList;

public class BasicMinHeapPractice {
    private ArrayList<Integer> heap;

    public BasicMinHeapPractice() {
        heap = new ArrayList<>();
    }

    // 插入元素
    public void insert(int val) {
        heap.add(val); // 先放到最後
        heapifyUp(heap.size() - 1); // 往上調整
    }

    // 取出最小元素
    public int extractMin() {
        if (isEmpty()) {
            throw new IllegalStateException("Heap is empty");
        }
        int min = heap.get(0);
        int last = heap.remove(heap.size() - 1);
        if (!isEmpty()) {
            heap.set(0, last);
            heapifyDown(0); // 往下調整
        }
        return min;
    }

    // 查看最小元素（不刪除）
    public int getMin() {
        if (isEmpty()) {
            throw new IllegalStateException("Heap is empty");
        }
        return heap.get(0);
    }

    // 返回 heap 大小
    public int size() {
        return heap.size();
    }

    // 是否為空
    public boolean isEmpty() {
        return heap.isEmpty();
    }

    // 往上調整
    private void heapifyUp(int index) {
        int parentIndex = (index - 1) / 2;
        while (index > 0 && heap.get(index) < heap.get(parentIndex)) {
            swap(index, parentIndex);
            index = parentIndex;
            parentIndex = (index - 1) / 2;
        }
    }

    // 往下調整
    private void heapifyDown(int index) {
        int size = heap.size();
        while (true) {
            int left = 2 * index + 1;
            int right = 2 * index + 2;
            int smallest = index;

            if (left < size && heap.get(left) < heap.get(smallest)) {
                smallest = left;
            }
            if (right < size && heap.get(right) < heap.get(smallest)) {
                smallest = right;
            }
            if (smallest != index) {
                swap(index, smallest);
                index = smallest;
            } else {
                break;
            }
        }
    }

    // 交換元素
    private void swap(int i, int j) {
        int temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }

    // 測試程式
    public static void main(String[] args) {
        BasicMinHeapPractice minHeap = new BasicMinHeapPractice();

        // 插入順序：15, 10, 20, 8, 25, 5
        int[] nums = { 15, 10, 20, 8, 25, 5 };
        for (int n : nums) {
            minHeap.insert(n);
        }

        // 預期 extractMin 順序：5, 8, 10, 15, 20, 25
        System.out.println("ExtractMin 順序：");
        while (!minHeap.isEmpty()) {
            System.out.print(minHeap.extractMin() + " ");
        }
    }
}
