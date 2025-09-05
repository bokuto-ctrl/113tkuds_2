import java.util.*;

public class LC34_SearchRange_DelaySpan {
    public static int[] searchRange(int[] nums, int target) {
        int n = nums.length;
        int left = 0, right = n - 1;
        int first = -1, last = -1;

        // 找左界
        int l = 0, r = n - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] >= target)
                r = mid - 1;
            else
                l = mid + 1;
        }
        if (l >= n || nums[l] != target)
            return new int[] { -1, -1 };
        first = l;

        // 找右界
        l = 0;
        r = n - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] <= target)
                l = mid + 1;
            else
                r = mid - 1;
        }
        last = r;

        return new int[] { first, last };
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int target = sc.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++)
            nums[i] = sc.nextInt();
        sc.close();

        int[] res = searchRange(nums, target);
        System.out.println(res[0] + " " + res[1]);
    }
}
/*
 * 解題思路：
 * 1. 對已排序陣列使用兩次二分搜尋。
 * 2. 第一次找左界：找到第一個 ≥ target 的位置。
 * 3. 第二次找右界：找到第一個 > target 的位置，再減一。
 * 4. 若未找到 target，回傳 -1 -1。
 * 5. 時間 O(log n)，空間 O(1)。
 */
