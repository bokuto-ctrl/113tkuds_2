import java.util.*;

public class LC15_3Sum_THSRStops {
    public static int longestValidParentheses(String s) {
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(-1); // 棧底基準
        int maxLen = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == '(') {
                stack.push(i);
            } else {
                stack.pop();
                if (stack.isEmpty()) {
                    // 遇到孤立 ')' → 更新基準
                    stack.push(i);
                } else {
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
 * 1. 將陣列排序。
 * 2. 固定一個元素，剩餘用左右雙指針找出和為目標的組合。
 * 3. 找到組合後跳過重複元素避免重複輸出。
 * 4. 遍歷完成即得到所有不重複三元組。
 * 5. 時間 O(n^2)，空間 O(1)（不計輸出）。
 */
