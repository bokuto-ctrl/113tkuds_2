import java.util.*;

class Solution {
    public int longestValidParentheses(String s) {
        int maxLen = 0;
        Stack<Integer> stack = new Stack<>();
        stack.push(-1); // 基準點

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                stack.push(i);
            } else {
                stack.pop();
                if (stack.isEmpty()) {
                    stack.push(i); // 更新基準點
                } else {
                    maxLen = Math.max(maxLen, i - stack.peek());
                }
            }
        }
        return maxLen;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.longestValidParentheses("(()")); // 2
        System.out.println(sol.longestValidParentheses(")()())")); // 4
        System.out.println(sol.longestValidParentheses("")); // 0
    }
}
/*
 * 解題思路：
 * 1. 用 stack 存左括號索引，初始放 -1 作基準。
 * 2. 遍歷字串：
 * - '(' 入棧
 * - ')' 出棧，若棧空則更新基準，否則計算長度更新 maxLen
 * 3. 回傳 maxLen
 * 時間 O(n)，空間 O(n)
 */