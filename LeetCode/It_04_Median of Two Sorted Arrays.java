class Solution {
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // 確保 nums1 是較短的數組
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1);
        }

        int m = nums1.length;
        int n = nums2.length;
        int left = 0, right = m;
        int halfLen = (m + n + 1) / 2;

        while (left <= right) {
            int i = (left + right) / 2; // nums1 切割點
            int j = halfLen - i; // nums2 切割點

            int nums1LeftMax = (i == 0) ? Integer.MIN_VALUE : nums1[i - 1];
            int nums1RightMin = (i == m) ? Integer.MAX_VALUE : nums1[i];

            int nums2LeftMax = (j == 0) ? Integer.MIN_VALUE : nums2[j - 1];
            int nums2RightMin = (j == n) ? Integer.MAX_VALUE : nums2[j];

            if (nums1LeftMax <= nums2RightMin && nums2LeftMax <= nums1RightMin) {
                // 找到正確切割
                if ((m + n) % 2 == 0) {
                    return (Math.max(nums1LeftMax, nums2LeftMax) +
                            Math.min(nums1RightMin, nums2RightMin)) / 2.0;
                } else {
                    return Math.max(nums1LeftMax, nums2LeftMax);
                }
            } else if (nums1LeftMax > nums2RightMin) {
                right = i - 1;
            } else {
                left = i + 1;
            }
        }

        throw new IllegalArgumentException("Input arrays are not sorted properly.");
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.findMedianSortedArrays(new int[] { 1, 3 }, new int[] { 2 })); // 2.0
        System.out.println(sol.findMedianSortedArrays(new int[] { 1, 2 }, new int[] { 3, 4 })); // 2.5
    }
}
/*
 * 1.保證 nums1 是較短的陣列。
 * 2.設 i 為 nums1 左邊數量，j = (m+n+1)/2 - i 為 nums2 左邊數量。
 * 3.若 nums1LeftMax <= nums2RightMin && nums2LeftMax <= nums1RightMin → 找到切割。
 * 否則調整 i（往左或往右）。
 * 4.奇數長度 → max(nums1LeftMax, nums2LeftMax)偶數長度 → (max(nums1LeftMax,
 * nums2LeftMax) + min(nums1RightMin, nums2RightMin)) / 2.0
 */