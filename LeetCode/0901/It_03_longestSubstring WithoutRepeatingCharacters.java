class Solution {
    public static int longthOfLongestSubstring(String s) {
        Set<Character> set = new HashSet<>();
        int left = 0, maxLen = 0;

        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);

            // 如果發現重複，就從左邊移除，直到沒有重複
            while (set.contains(c)) {
                set.remove(s.charAt(left));
                left++;
            }

            set.add(c);
            maxLen = Math.max(maxLen, right - left + 1);
        }
        return maxLen;
    }

    public static void main(String[] args) {
        System.out.println(longthOfLongestSubstring("abcabcbb")); // 3
        System.out.println(longthOfLongestSubstring("bbbbb")); // 1
        System.out.println(longthOfLongestSubstring("pwwkew")); // 3
    }
}
/*
 * 解題思路：
 * 使用滑動視窗（left, right）遍歷字串，HashSet 存目前子串的字符。
 * 1. right 向右擴展，遇到重複字符就從 left 開始移除，直到沒有重複。
 * 2. 每次更新最大長度 maxLen = right - left + 1。
 * 3. 遍歷結束後回傳 maxLen。
 * 時間複雜度 O(n)，空間複雜度 O(min(n, 字元集大小))。
 */
