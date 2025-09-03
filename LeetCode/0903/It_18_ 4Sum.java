import java.util.*;

public List<List<Integer>> fourSum(int[] nums, int target) {
    List<List<Integer>> res = new ArrayList<>();
    if (nums == null || nums.length < 4)
        return res;

    Arrays.sort(nums);
    int n = nums.length;

    for (int i = 0; i < n - 3; i++) {
        if (i > 0 && nums[i] == nums[i - 1])
            continue; // 避免重複

        for (int j = i + 1; j < n - 2; j++) {
            if (j > i + 1 && nums[j] == nums[j - 1])
                continue; // 避免重複

            int left = j + 1, right = n - 1;
            while (left < right) {
                long sum = (long) nums[i] + nums[j] + nums[left] + nums[right]; // 用 long 避免溢出

                if (sum == target) {
                    res.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
                    while (left < right && nums[left] == nums[left + 1])
                        left++; // 跳過重複
                    while (left < right && nums[right] == nums[right - 1])
                        right--; // 跳過重複
                    left++;
                    right--;
                } else if (sum < target) {
                    left++;
                } else {
                    right--;
                }
            }
        }
    }

    return res;
}

public static void main(String[] args) {
    Solution sol = new Solution();
    System.out.println(sol.fourSum(new int[] { 1, 0, -1, 0, -2, 2 }, 0));
    // [[-2,-1,1,2], [-2,0,0,2], [-1,0,0,1]]
    System.out.println(sol.fourSum(new int[] { 2, 2, 2, 2, 2 }, 8));
    // [[2,2,2,2]]
}

/*
 * 解題思路：
 * 1. 先對陣列排序，方便跳過重複元素並使用雙指針。
 * 2. 外兩層迴圈遍歷前兩個元素 nums[i], nums[j]。
 * 3. 內層用雙指針 left 和 right 尋找剩下兩個元素，使總和等於 target。
 * 4. sum == target → 加入結果集，並跳過重複元素；sum < target → left++; sum > target →
 * right--。
 * 5. 返回所有不重複的四元組。
 */