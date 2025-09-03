class Solution {
    public int removeElement(int[] nums, int val) {
        int k = 0; // 下一個非 val 的位置
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[k] = nums[i];
                k++;
            }
        }
        return k;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();

        int[] nums1 = { 3, 2, 2, 3 };
        int k1 = sol.removeElement(nums1, 3);
        System.out.println(k1); // 2
        for (int i = 0; i < k1; i++)
            System.out.print(nums1[i] + " "); // 2 2
        System.out.println();

        int[] nums2 = { 0, 1, 2, 2, 3, 0, 4, 2 };
        int k2 = sol.removeElement(nums2, 2);
        System.out.println(k2); // 5
        for (int i = 0; i < k2; i++)
            System.out.print(nums2[i] + " "); // 0 1 3 0 4 (順序可變)
    }
}
/*
 * 解題思路：
 * 1. 用雙指標法：
 * - i 遍歷整個陣列 nums
 * - k 追蹤下一個非 val 的位置
 * 2. 當 nums[i] != val 時，把 nums[i] 放到 nums[k]，k++
 * 3. 遍歷完成後，k 就是剩餘不等於 val 的元素數量，陣列前 k 個元素即為結果
 * 時間複雜度 O(n)，空間複雜度 O(1)
 */