import java.util.*;

class Solution {
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>(); // 初始化結果集
        Arrays.sort(nums); // 先排序

        int n = nums.length;

        for (int i = 0; i < n - 2; i++) {
            // 避免重複三元組
            if (i > 0 && nums[i] == nums[i - 1])
                continue;

            int left = i + 1, right = n - 1;

            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];

                if (sum == 0) {
                    res.add(Arrays.asList(nums[i], nums[left], nums[right]));

                    // 跳過重複元素
                    while (left < right && nums[left] == nums[left + 1])
                        left++;
                    while (left < right && nums[right] == nums[right - 1])
                        right--;

                    left++;
                    right--;
                } else if (sum < 0) {
                    left++;
                } else {
                    right--;
                }
            }
        }

        return res;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums1 = { -1, 0, 1, 2, -1, -4 };
        int[] nums2 = { 0, 1, 1 };
        int[] nums3 = { 0, 0, 0 };

        System.out.println(sol.threeSum(nums1)); // [[-1,-1,2],[-1,0,1]]
        System.out.println(sol.threeSum(nums2)); // []
        System.out.println(sol.threeSum(nums3)); // [[0,0,0]]
    }
}
/*
 * 解題思路：
 * 1. 先對陣列排序，方便跳過重複元素並使用雙指針。
 * 2. 遍歷每個元素 nums[i]，用 left=i+1 和 right=末尾 進行雙指針尋找三數之和。
 * 3. 若 sum == 0 → 加入結果集，並跳過重複元素；sum < 0 → left++; sum > 0 → right--。
 * 4. 最後返回所有不重複的三元組。
 */
