class Solution {
    public int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target)
                return mid;

            // 左半有序
            if (nums[left] <= nums[mid]) {
                if (target >= nums[left] && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
            // 右半有序
            else {
                if (target > nums[mid] && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.search(new int[] { 4, 5, 6, 7, 0, 1, 2 }, 0)); // 4
        System.out.println(sol.search(new int[] { 4, 5, 6, 7, 0, 1, 2 }, 3)); // -1
        System.out.println(sol.search(new int[] { 1 }, 0)); // -1
    }
}

/*
 * 解題思路：
 * 1. 使用改良二分搜尋，時間 O(log n)。
 * 2. 檢查 mid 是否為目標。
 * 3. 判斷哪一半有序：
 * - 左半有序 → 檢查 target 是否在左半，若是縮右邊，否則縮左邊。
 * - 右半有序 → 檢查 target 是否在右半，若是縮左邊，否則縮右邊。
 * 4. 若沒找到，回傳 -1。
 * 時間 O(log n)，空間 O(1)。
 */