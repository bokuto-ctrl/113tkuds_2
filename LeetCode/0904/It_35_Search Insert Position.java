class Solution {
    public int searchInsert(int[] nums, int target) {
        int left = 0, right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                return mid; // 找到直接回傳
            } else if (nums[mid] < target) {
                left = mid + 1; // 往右找
            } else {
                right = mid - 1; // 往左找
            }
        }

        return left; // 沒找到，left 就是插入位置
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.searchInsert(new int[] { 1, 3, 5, 6 }, 5)); // 2
        System.out.println(sol.searchInsert(new int[] { 1, 3, 5, 6 }, 2)); // 1
        System.out.println(sol.searchInsert(new int[] { 1, 3, 5, 6 }, 7)); // 4
    }
}

/*
 * 解題思路：
 * 1. 用二分搜尋找 target。
 * 2. 若找到，回傳索引。
 * 3. 若沒找到，left 會停在插入位置。
 * 時間 O(log n)，空間 O(1)。
 */