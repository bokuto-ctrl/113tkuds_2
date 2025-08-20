import java.util.*;

public class M02_YouBikeNextArrival {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 讀取站點班次數
        int n = sc.nextInt();
        sc.nextLine(); // 吃掉換行

        // 轉換成分鐘存入陣列
        int[] times = new int[n];
        for (int i = 0; i < n; i++) {
            String t = sc.nextLine();
            times[i] = toMinutes(t);
        }

        // 查詢時間
        String queryStr = sc.nextLine();
        int query = toMinutes(queryStr);

        // 二分搜尋找第一個 > query 的位置
        int ansIndex = binarySearchNext(times, query);

        if (ansIndex == -1) {
            System.out.println("No bike");
        } else {
            System.out.println(toHHMM(times[ansIndex]));
        }
    }

    // 時間字串 HH:mm → 總分鐘數
    public static int toMinutes(String time) {
        String[] parts = time.split(":");
        int hh = Integer.parseInt(parts[0]);
        int mm = Integer.parseInt(parts[1]);
        return hh * 60 + mm;
    }

    // 分鐘數 → HH:mm 格式
    public static String toHHMM(int totalMinutes) {
        int hh = totalMinutes / 60;
        int mm = totalMinutes % 60;
        return String.format("%02d:%02d", hh, mm);
    }

    // 二分搜尋：找第一個 > query 的索引
    public static int binarySearchNext(int[] arr, int query) {
        int left = 0, right = arr.length - 1;
        int ans = -1;

        while (left <= right) {
            int mid = (left + right) / 2;
            if (arr[mid] > query) {
                ans = mid; // 暫存答案
                right = mid - 1; // 往左繼續找更小的
            } else {
                left = mid + 1; // 向右找
            }
        }
        return ans;
    }
}
