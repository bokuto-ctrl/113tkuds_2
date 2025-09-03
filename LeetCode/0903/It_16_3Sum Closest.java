import java.util.Arrays;

class Solution {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums); // 先排序
        int n = nums.length;
        int closest = nums[0] + nums[1] + nums[2]; // 初始化最近和

        for (int i = 0; i < n - 2; i++) {
            int left = i + 1, right = n - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (Math.abs(sum - target) < Math.abs(closest - target)) {
                    closest = sum; // 更新最接近的和
                }

                if (sum < target) {
                    left++;
                } else if (sum > target) {
                    right--;
                } else {
                    return sum; // 精確匹配
                }
            }
        }

        return closest;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.threeSumClosest(new int[] { -1, 2, 1, -4 }, 1)); // 2
        System.out.println(sol.threeSumClosest(new int[] { 0, 0, 0 }, 1)); // 0
    }
}

/*
 * 解題思路：
 * 1. 對陣列排序，方便使用雙指針。
 * 2. 遍歷每個元素 nums[i]，用 left=i+1、right=末尾 找三數之和。
 * 3. 計算 sum 與 target 差值，更新最接近的和 closest。
 * 4. sum < target → left++; sum > target → right--; sum == target → 直接返回。
 * 5. 遍歷完成返回最接近的 sum。
 */
