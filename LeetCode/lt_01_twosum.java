import java.util.HashMap;

class Solution {
    public static int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int need = target - nums[i];
            if (map.containsKey(need)) {
                return new int[] { map.get(need), i };
            }
            map.put(nums[i], i);
        }
        return null;
    }

    public static void main(String[] args) {
        int[] nums = { 2, 7, 11, 15 };
        int target = 9;

        int[] result = twoSum(nums, target); // ✅ 可以直接呼叫
        System.out.println("答案: " + result[0] + ", " + result[1]);
    }
}
/*
 * 解題思路：
 * 1. 題目要求找到兩個數字相加等於 target。
 * 2. 使用 HashMap 儲存「數值 → 索引」，查找是否有另一個數值能與當前數字配對。
 * 3. 時間複雜度 O(n)，只需一次迴圈即可完成。
 */