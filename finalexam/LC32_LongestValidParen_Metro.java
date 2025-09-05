import java.util.*;

public class LC32_LongestValidParen_Metro {
    public static int longestValidParentheses(String s) {
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(-1); // 棧底放基準索引
        int maxLen = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == '(') {
                // 進站事件，記錄索引
                stack.push(i);
            } else { // c == ')'
                stack.pop();
                if (stack.isEmpty()) {
                    // 遇到孤立的出站事件 → 設新基準
                    stack.push(i);
                } else {
                    // 有效片段長度 = 當前索引 - 棧頂
                    maxLen = Math.max(maxLen, i - stack.peek());
                }
            }
        }
        return maxLen;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine().trim();
        sc.close();

        System.out.println(longestValidParentheses(s));
    }
}
/*
 * 解題思路：
 * 1. 用索引棧存 '(' 的位置，棧底放 -1 作為基準。
 * 2. 遇 ')' 時 pop，如果棧空就更新基準；否則計算長度 i - 棧頂。
 * 3. 遍歷整串更新最大合法長度。
 * 4. 時間 O(n)，空間 O(n)。
 */
