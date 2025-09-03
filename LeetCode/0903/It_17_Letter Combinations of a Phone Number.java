import java.util.*;

class Solution {
    private static final String[] KEYS = {
            "", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"
    };

    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();
        if (digits == null || digits.isEmpty())
            return res;
        backtrack(digits, 0, new StringBuilder(), res);
        return res;
    }

    private void backtrack(String digits, int index, StringBuilder path, List<String> res) {
        if (index == digits.length()) {
            res.add(path.toString());
            return;
        }
        String letters = KEYS[digits.charAt(index) - '0'];
        for (char c : letters.toCharArray()) {
            path.append(c);
            backtrack(digits, index + 1, path, res);
            path.deleteCharAt(path.length() - 1); // 回溯
        }
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.letterCombinations("23")); // [ad, ae, af, bd, be, bf, cd, ce, cf]
        System.out.println(sol.letterCombinations("")); // []
        System.out.println(sol.letterCombinations("2")); // [a, b, c]
    }
}

/*
 * 解題思路：
 * 1. 將每個數字對應的字母用陣列 KEYS 儲存。
 * 2. 使用回溯 (backtracking) 遞迴生成所有組合：
 * - 對當前數字，逐一嘗試每個字母加入當前路徑。
 * - 遞迴到最後一個數字 → 將完整組合加入結果。
 * - 回溯到上一層繼續嘗試其他字母。
 * 3. 返回所有可能組合。
 */
