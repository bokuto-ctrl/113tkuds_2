class Solution {
    public int[] searchRange(int[] nums, int target) {
        int first = findBound(nums, target, true);
        int last = findBound(nums, target, false);
        return new int[] { first, last };
    }

    private int findBound(int[] nums, int target, boolean isFirst) {
        int left = 0, right = nums.length - 1, bound = -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                bound = mid;
                if (isFirst) {
                    right = mid - 1; // 繼續往左找
                } else {
                    left = mid + 1; // 繼續往右找
                }
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return bound;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(java.util.Arrays.toString(sol.searchRange(new int[] { 5, 7, 7, 8, 8, 10 }, 8))); // [3,4]
        System.out.println(java.util.Arrays.toString(sol.searchRange(new int[] { 5, 7, 7, 8, 8, 10 }, 6))); // [-1,-1]
        System.out.println(java.util.Arrays.toString(sol.searchRange(new int[] {}, 0))); // [-1,-1]
    }
}

/*
 * 解題思路：
 * 1. 題目要求 O(log n)，適合用二分搜尋。
 * 2. 先用二分搜尋找 target 的第一個位置（往左找）。
 * 3. 再用二分搜尋找 target 的最後一個位置（往右找）。
 * 4. 若找不到，回傳 [-1, -1]。
 * 時間 O(log n)，空間 O(1)。
 */