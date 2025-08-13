import java.util.*;

public class MultiLevelCacheSystem {

    static class CacheEntry {
        int key;
        String value;
        int freq; // 存取頻率
        long timestamp; // 最近存取時間

        public CacheEntry(int key, String value) {
            this.key = key;
            this.value = value;
            this.freq = 1;
            this.timestamp = System.nanoTime();
        }
    }

    static class CacheLevel {
        int capacity;
        int cost;
        Map<Integer, CacheEntry> map;
        PriorityQueue<CacheEntry> heap;

        public CacheLevel(int capacity, int cost) {
            this.capacity = capacity;
            this.cost = cost;
            this.map = new HashMap<>();
            // heap 根據 freq/時間排序，freq高優先，時間新優先
            this.heap = new PriorityQueue<>((a, b) -> {
                if (a.freq != b.freq)
                    return a.freq - b.freq; // 小 freq 在前
                return Long.compare(a.timestamp, b.timestamp); // 老時間在前
            });
        }

        public boolean contains(int key) {
            return map.containsKey(key);
        }

        public CacheEntry get(int key) {
            CacheEntry entry = map.get(key);
            if (entry != null) {
                entry.freq++;
                entry.timestamp = System.nanoTime();
                heap.remove(entry);
                heap.offer(entry);
            }
            return entry;
        }

        public void put(CacheEntry entry) {
            if (map.size() >= capacity) {
                // 移除 freq 最低且最舊的
                CacheEntry evict = heap.poll();
                map.remove(evict.key);
            }
            map.put(entry.key, entry);
            heap.offer(entry);
        }

        public void remove(int key) {
            if (map.containsKey(key)) {
                CacheEntry entry = map.remove(key);
                heap.remove(entry);
            }
        }

        public List<CacheEntry> getAllEntries() {
            return new ArrayList<>(map.values());
        }
    }

    private CacheLevel L1, L2, L3;

    public MultiLevelCacheSystem() {
        L1 = new CacheLevel(2, 1);
        L2 = new CacheLevel(5, 3);
        L3 = new CacheLevel(10, 10);
    }

    // 獲取資料
    public String get(int key) {
        CacheEntry entry = null;
        if (L1.contains(key))
            entry = L1.get(key);
        else if (L2.contains(key)) {
            entry = L2.get(key);
            promote(entry, L2, L1);
        } else if (L3.contains(key)) {
            entry = L3.get(key);
            promote(entry, L3, L2);
        }
        return entry != null ? entry.value : null;
    }

    // 放入資料
    public void put(int key, String value) {
        CacheEntry entry = new CacheEntry(key, value);
        if (L1.contains(key) || L2.contains(key) || L3.contains(key)) {
            // 已存在，更新值
            removeFromAllLevels(key);
        }
        // 嘗試放入 L1
        L1.put(entry);
        balanceLevels();
    }

    // 從所有層級移除
    private void removeFromAllLevels(int key) {
        L1.remove(key);
        L2.remove(key);
        L3.remove(key);
    }

    // 將 entry 從 lowerLevel 升到 higherLevel
    private void promote(CacheEntry entry, CacheLevel lower, CacheLevel higher) {
        lower.remove(entry.key);
        higher.put(entry);
    }

    // 平衡各層級
    private void balanceLevels() {
        // 從 L1 溢出 -> L2
        while (L1.map.size() > L1.capacity) {
            CacheEntry evict = L1.heap.poll();
            L1.map.remove(evict.key);
            L2.put(evict);
        }
        // 從 L2 溢出 -> L3
        while (L2.map.size() > L2.capacity) {
            CacheEntry evict = L2.heap.poll();
            L2.map.remove(evict.key);
            L3.put(evict);
        }
        // L3 超過容量 -> 移除最低 freq 最舊
        while (L3.map.size() > L3.capacity) {
            CacheEntry evict = L3.heap.poll();
            L3.map.remove(evict.key);
        }
    }

    // 顯示各層級狀態
    public void printLevels() {
        System.out.println("L1: " + getKeys(L1));
        System.out.println("L2: " + getKeys(L2));
        System.out.println("L3: " + getKeys(L3));
        System.out.println("---------------------------");
    }

    private List<Integer> getKeys(CacheLevel level) {
        List<Integer> keys = new ArrayList<>();
        for (CacheEntry e : level.heap)
            keys.add(e.key);
        return keys;
    }

    // 測試程式
    public static void main(String[] args) {
        MultiLevelCacheSystem cache = new MultiLevelCacheSystem();

        cache.put(1, "A");
        cache.put(2, "B");
        cache.put(3, "C");
        cache.printLevels(); // L1: [2,3], L2: [1], L3: []

        cache.get(1);
        cache.get(1);
        cache.get(2);
        cache.printLevels(); // 1被頻繁存取，應該升到L1

        cache.put(4, "D");
        cache.put(5, "E");
        cache.put(6, "F");
        cache.printLevels(); // 根據頻率和容量決定分布
    }
}
