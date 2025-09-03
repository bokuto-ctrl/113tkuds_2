class Solution {
    public int strStr(String haystack, String needle) {
        int n = haystack.length(), m = needle.length();
        if (m == 0)
            return 0; // 空字串直接返回 0

        for (int i = 0; i <= n - m; i++) {
            if (haystack.substring(i, i + m).equals(needle)) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();

        String haystack1 = "sadbutsad", needle1 = "sad";
        System.out.println(sol.strStr(haystack1, needle1)); // 0

        String haystack2 = "leetcode", needle2 = "leeto";
        System.out.println(sol.strStr(haystack2, needle2)); // -1
    }
}

/*
 * 解題思路：
 * 1. 特殊情況：若 needle 是空字串，直接回傳 0。
 * 2. 遍歷 haystack 的每個起始索引 i (0 到 n-m)：
 * - 用 substring 取長度為 needle.length 的子字串
 * - 比對是否等於 needle
 * 3. 若找到匹配，返回索引 i；若遍歷完都沒找到，返回 -1
 * 時間複雜度 O((n-m+1) * m)，空間複雜度 O(1) (不額外使用資料結構)
 */
