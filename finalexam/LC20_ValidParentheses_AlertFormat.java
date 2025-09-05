import java.util.*;

public class LC20_ValidParentheses_AlertFormat {
    public static boolean isValid(String s) {
        // 建立閉括號對應的開括號
        Map<Character, Character> map = new HashMap<>();
        map.put(')', '(');
        map.put(']', '[');
        map.put('}', '{');

        Deque<Character> stack = new ArrayDeque<>();

        for (char c : s.toCharArray()) {
            if (map.containsValue(c)) {
                // 開括號 → push
                stack.push(c);
            } else if (map.containsKey(c)) {
                // 閉括號 → 檢查棧頂是否匹配
                if (stack.isEmpty() || stack.peek() != map.get(c)) {
                    return false;
                }
                stack.pop();
            } else {
                // 非法字元（題目保證不會出現）
                return false;
            }
        }

        // 最後 stack 必須為空
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine().trim();
        sc.close();

        System.out.println(isValid(s));
    }
}
/*
 * 解題思路：
 * 1. 用 Stack 存開括號。
 * 2. 遇到閉括號時檢查 Stack 頂部是否匹配，匹配就 pop。
 * 3. 遍歷完成後 Stack 空表示合法，否則不合法。
 */
