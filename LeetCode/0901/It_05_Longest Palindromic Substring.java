class Solution {
    public String longestPalindrome(String s) {
        if (s == null || s.length() < 1)
            return "";

        int start = 0, end = 0;

        for (int i = 0; i < s.length(); i++) {
            // 以 s[i] 為中心，奇數長度回文
            int len1 = expandAroundCenter(s, i, i);
            // 以 s[i] 和 s[i+1] 為中心，偶數長度回文
            int len2 = expandAroundCenter(s, i, i + 1);
            int len = Math.max(len1, len2);

            if (len > end - start) {
                start = i - (len - 1) / 2; // 計算起始位置
                end = i + len / 2; // 計算結束位置
            }
        }

        return s.substring(start, end + 1);
    }

    // 從中心向外擴展，回傳回文長度
    private int expandAroundCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return right - left - 1; // 長度計算
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.longestPalindrome("babad")); // "bab" 或 "aba"
        System.out.println(sol.longestPalindrome("cbbd")); // "bb"
    }
}
/*
 * 初始化：用 start 和 end 記錄最長回文的範圍。
 * 奇數情況：以 s[i] 為中心。
 * 偶數情況：以 s[i] 和 s[i+1] 為中心。
 * 由中心往外展開，直到遇到不同字元或超出範圍。
 * 回傳回文長度。
 * 如果新的回文比目前長，更新 start 與 end。
 * 回傳 s.substring(start, end+1)。
 */
