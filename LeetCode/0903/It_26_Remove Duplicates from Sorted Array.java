class Solution {
    public int removeDuplicates(int[] nums) {
        if (nums.length == 0)
            return 0;

        int k = 1; // 唯一元素的索引
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[i - 1]) {
                nums[k] = nums[i];
                k++;
            }
        }
        return k;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();

        int[] nums1 = { 1, 1, 2 };
        int k1 = sol.removeDuplicates(nums1);
        System.out.println(k1); // 2
        for (int i = 0; i < k1; i++)
            System.out.print(nums1[i] + " "); // 1 2
        System.out.println();

        int[] nums2 = { 0, 0, 1, 1, 1, 2, 2, 3, 3, 4 };
        int k2 = sol.removeDuplicates(nums2);
        System.out.println(k2); // 5
        for (int i = 0; i < k2; i++)
            System.out.print(nums2[i] + " "); // 0 1 2 3 4
    }
}
/*
 * 解題思路：
 * 1. 用雙指標：
 * - i：遍歷整個陣列
 * - k：追蹤唯一元素的位置
 * 2. 當 nums[i] != nums[i-1]，將 nums[i] 放到 nums[k]，k++
 * 3. 最後 k 就是唯一元素的數量，陣列前 k 個位置是唯一元素。
 * 時間複雜度 O(n)，空間複雜度 O(1)
 */