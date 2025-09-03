class Solution {
    public static String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0)
            return "";

        String prefix = strs[0]; // 假設第一個字串是公共前綴

        for (int i = 1; i < strs.length; i++) {
            // 逐步縮短 prefix，直到匹配 strs[i]
            while (strs[i].indexOf(prefix) != 0) {
                prefix = prefix.substring(0, prefix.length() - 1);
                if (prefix.isEmpty())
                    return "";
            }
        }

        return prefix;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String[] strs1 = { "flower", "flow", "flight" };
        String[] strs2 = { "dog", "racecar", "car" };

        System.out.println(sol.longestCommonPrefix(strs1)); // "fl"
        System.out.println(sol.longestCommonPrefix(strs2)); // ""
    }
}
/*
 * 解題思路：
 * 1. 假設第一個字串為公共前綴 prefix。
 * 2. 遍歷剩下的字串，逐步縮短 prefix，直到每個字串都以 prefix 開頭。
 * 3. 若 prefix 變空 → 返回 ""；遍歷完成 → 返回 prefix。
 */
