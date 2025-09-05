import java.util.*;

public class LC01_TwoSum_THSRHoliday {
    public static int[] twoSumSeats(int[] seats, int target) {
        // key: 需要的數量 target - x, value: 索引
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < seats.length; i++) {
            if (map.containsKey(seats[i])) {
                return new int[] { map.get(seats[i]), i };
            }
            map.put(target - seats[i], i);
        }
        return new int[] { -1, -1 };
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int target = sc.nextInt();
        int[] seats = new int[n];
        for (int i = 0; i < n; i++)
            seats[i] = sc.nextInt();

        int[] res = twoSumSeats(seats, target);
        System.out.println(res[0] + " " + res[1]);
    }
}

/*
 * 解題思路：
 * 1. 使用 HashMap<需要的座位數, 索引>。
 * 2. 遍歷陣列 seats：
 * - 若當前座位數 x 存在於 map 的 key，表示之前有班次在等待 x，直接返回索引。
 * - 否則，記錄 map.put(target - x, i)，表示還需要 target - x。
 * 3. 一次遍歷完成即可找到任意一組符合的班次。
 * 時間複雜度 O(n)，空間 O(n)。
 */