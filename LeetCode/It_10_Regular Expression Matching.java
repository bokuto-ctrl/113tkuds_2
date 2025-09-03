class Solution {
    public boolean isMatch(String s, String p) {
        int m = s.length(), n = p.length();
        // dp[i][j] 表示 s[0..i-1] 與 p[0..j-1] 是否匹配
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true; // 空字串匹配空模式

        // 處理模式開頭可以匹配空字串的情況
        for (int j = 2; j <= n; j++) {
            if (p.charAt(j - 1) == '*') {
                dp[0][j] = dp[0][j - 2];
            }
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                char sc = s.charAt(i - 1);
                char pc = p.charAt(j - 1);

                if (pc == '.' || pc == sc) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (pc == '*') {
                    // p[j-2] 為 '*' 前面的元素
                    char prev = p.charAt(j - 2);
                    // '*' 表示 0 次
                    dp[i][j] = dp[i][j - 2];
                    // '*' 表示 1 次或多次
                    if (prev == '.' || prev == sc) {
                        dp[i][j] = dp[i][j] || dp[i - 1][j];
                    }
                }
            }
        }

        return dp[m][n];
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.isMatch("aa", "a")); // false
        System.out.println(sol.isMatch("aa", "a*")); // true
        System.out.println(sol.isMatch("ab", ".*")); // true
        System.out.println(sol.isMatch("aab", "c*a*b"));// true
        System.out.println(sol.isMatch("mississippi", "mis*is*p*.")); // false
    }
}
/*
 * 解題思路：
 * 1. 用 dp[i][j] 表示 s 前 i 個字符是否匹配 p 前 j 個字符。
 * 2. '.' 可匹配任意字符，'*' 表示前一字符出現 0 次或多次。
 * 3. 遍歷 s 和 p 更新 dp 表，最後返回 dp[m][n]。
 */
