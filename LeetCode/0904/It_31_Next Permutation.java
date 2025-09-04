class Solution {
    public void nextPermutation(int[] nums) {
        int n = nums.length;

        // 1. 從右往左找第一個「下降點」(nums[i] < nums[i+1])
        int i = n - 2;
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }

        // 2. 如果找到下降點 i，從右往左找第一個比 nums[i] 大的數，交換
        if (i >= 0) {
            int j = n - 1;
            while (j >= 0 && nums[j] <= nums[i]) {
                j--;
            }
            swap(nums, i, j);
        }

        // 3. 將 i+1 到結尾反轉，讓它變成升序
        reverse(nums, i + 1, n - 1);
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    private void reverse(int[] nums, int left, int right) {
        while (left < right) {
            swap(nums, left, right);
            left++;
            right--;
        }
    }

    public static void main(String[] args) {
        Solution sol = new Solution();

        int[] nums1 = { 1, 2, 3 };
        sol.nextPermutation(nums1);
        System.out.println(java.util.Arrays.toString(nums1)); // [1, 3, 2]

        int[] nums2 = { 3, 2, 1 };
        sol.nextPermutation(nums2);
        System.out.println(java.util.Arrays.toString(nums2)); // [1, 2, 3]

        int[] nums3 = { 1, 1, 5 };
        sol.nextPermutation(nums3);
        System.out.println(java.util.Arrays.toString(nums3)); // [1, 5, 1]
    }
}
/*
 * 解題思路：
 * 1. 從右往左找到第一個下降點 i，使得 nums[i] < nums[i+1]。
 * 2. 若找不到，表示序列已是最大排列，直接反轉成最小排列。
 * 3. 從右往左找到第一個大於 nums[i] 的元素 j，交換 nums[i] 和 nums[j]。
 * 4. 將 i 之後的區間反轉，使其變成最小遞增序列。
 */