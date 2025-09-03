import java.util.*;

class Solution {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();

        for (char c : s.toCharArray()) {
            if (c == '(')
                stack.push(')');
            else if (c == '{')
                stack.push('}');
            else if (c == '[')
                stack.push(']');
            else {
                // 遇到閉括號，檢查是否與棧頂匹配
                if (stack.isEmpty() || stack.pop() != c)
                    return false;
            }
        }

        // 最後棧應該為空
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        Solution sol = new Solution();

        System.out.println(sol.isValid("()")); // true
        System.out.println(sol.isValid("()[]{}")); // true
        System.out.println(sol.isValid("(]")); // false
        System.out.println(sol.isValid("([])")); // true
        System.out.println(sol.isValid("([)]")); // false
    }
}

/*
 * 解題思路：
 * 1. 使用 Stack 儲存開括號對應的閉括號。
 * 2. 遇到開括號 '('、'{'、'[' 時，將對應閉括號推入 Stack。
 * 3. 遇到閉括號時，檢查 Stack 是否為空或與棧頂元素匹配。
 * - 不匹配或空則返回 false。
 * 4. 遍歷結束後，如果 Stack 為空，則括號有效，否則無效。
 * 時間複雜度 O(n)，空間複雜度 O(n)。
 */
