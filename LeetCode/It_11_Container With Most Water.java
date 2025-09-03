class Solution {
    public int maxArea(int[] height) {
        int left = 0, right = height.length - 1;
        int maxArea = 0;

        while (left < right) {
            int h = Math.min(height[left], height[right]);
            int w = right - left;
            maxArea = Math.max(maxArea, h * w);

            // 移動較短的指針
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }

        return maxArea;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] height1 = { 1, 8, 6, 2, 5, 4, 8, 3, 7 };
        int[] height2 = { 1, 1 };
        System.out.println(sol.maxArea(height1)); // 49
        System.out.println(sol.maxArea(height2)); // 1
    }
}
/*
 * 解題思路：
 * 1. 使用雙指針 left、right 指向陣列兩端。
 * 2. 計算當前容積 area = min(height[left], height[right]) * (right-left)。
 * 3. 更新最大面積 maxArea。
 * 4. 移動較短的指針向內，因為容積受短板限制。
 * 5. 重複直到 left >= right，返回 maxArea。
 */
