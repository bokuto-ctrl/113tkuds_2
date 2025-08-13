import java.util.PriorityQueue;
import java.util.Comparator;
import java.util.ArrayList;

class Task {
    String name;
    int priority;
    long timestamp; // 用來確保同優先級按加入順序執行

    public Task(String name, int priority, long timestamp) {
        this.name = name;
        this.priority = priority;
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return name + " (優先級: " + priority + ")";
    }
}

public class PriorityQueueWithHeap {
    private PriorityQueue<Task> queue;
    private long counter; // 時間戳遞增計數器

    public PriorityQueueWithHeap() {
        // 大頂堆（priority 越大越先），同 priority 按 timestamp 排
        queue = new PriorityQueue<>(Comparator
                .comparingInt((Task t) -> -t.priority)
                .thenComparingLong(t -> t.timestamp));
        counter = 0;
    }

    // 添加任務
    public void addTask(String name, int priority) {
        queue.offer(new Task(name, priority, counter++));
    }

    // 執行下一個最高優先級的任務
    public Task executeNext() {
        return queue.poll();
    }

    // 查看下一個要執行的任務
    public Task peek() {
        return queue.peek();
    }

    // 修改任務優先級
    public void changePriority(String name, int newPriority) {
        ArrayList<Task> tempList = new ArrayList<>();
        boolean found = false;

        while (!queue.isEmpty()) {
            Task t = queue.poll();
            if (t.name.equals(name) && !found) {
                t.priority = newPriority;
                found = true;
            }
            tempList.add(t);
        }

        // 重新加入所有任務（相當於重建 heap）
        for (Task t : tempList) {
            queue.offer(t);
        }

        if (!found) {
            System.out.println("任務 \"" + name + "\" 不存在");
        }
    }

    // 測試程式
    public static void main(String[] args) {
        PriorityQueueWithHeap pq = new PriorityQueueWithHeap();

        // 添加任務
        pq.addTask("備份", 1);
        pq.addTask("緊急修復", 5);
        pq.addTask("更新", 3);

        System.out.println("下一個要執行的任務：" + pq.peek());

        System.out.println("執行順序：");
        while (pq.peek() != null) {
            System.out.println(pq.executeNext());
        }

        // 測試 changePriority
        pq.addTask("掃描", 2);
        pq.addTask("下載", 4);
        pq.changePriority("掃描", 6); // 把掃描變成最高優先級
        System.out.println("\n修改優先級後的執行順序：");
        while (pq.peek() != null) {
            System.out.println(pq.executeNext());
        }
    }
}
