import java.util.*;

class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        backtrack(res, "", 0, 0, n);
        return res;
    }

    private void backtrack(List<String> res, String curr, int open, int close, int n) {
        // 如果當前字串長度達到 2*n，加入結果
        if (curr.length() == 2 * n) {
            res.add(curr);
            return;
        }

        // 可以加左括號
        if (open < n) {
            backtrack(res, curr + "(", open + 1, close, n);
        }

        // 可以加右括號（必須保證閉括號數 <= 開括號數）
        if (close < open) {
            backtrack(res, curr + ")", open, close + 1, n);
        }
    }

    public static void main(String[] args) {
        Solution sol = new Solution();

        System.out.println(sol.generateParenthesis(3)); // ["((()))","(()())","(())()","()(())","()()()"]
        System.out.println(sol.generateParenthesis(1)); // ["()"]
    }
}

/*
 * 解題思路：
 * 1. 使用回溯法生成括號組合。
 * 2. 維護當前字串 curr，以及已放入的左括號 open 和右括號 close。
 * 3. 可以加 '(' 的條件：open < n。
 * 4. 可以加 ')' 的條件：close < open。
 * 5. 當 curr 長度為 2*n 時，加入結果。
 * 時間複雜度 O(4^n / sqrt(n))，空間複雜度 O(n)。
 */