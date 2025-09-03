
import java.util.*;

class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> res = new ArrayList<>();
        if (s == null || s.length() == 0 || words == null || words.length == 0)
            return res;

        int wordLen = words[0].length();
        int wordCount = words.length;
        int totalLen = wordLen * wordCount;

        // 建立 words 的頻率表
        Map<String, Integer> wordMap = new HashMap<>();
        for (String word : words) {
            wordMap.put(word, wordMap.getOrDefault(word, 0) + 1);
        }

        // 遍歷每一個可能的起點
        for (int i = 0; i <= s.length() - totalLen; i++) {
            Map<String, Integer> seen = new HashMap<>();
            int j = 0;
            while (j < wordCount) {
                String sub = s.substring(i + j * wordLen, i + (j + 1) * wordLen);
                if (!wordMap.containsKey(sub))
                    break;

                seen.put(sub, seen.getOrDefault(sub, 0) + 1);
                if (seen.get(sub) > wordMap.get(sub))
                    break;

                j++;
            }
            if (j == wordCount)
                res.add(i);
        }

        return res;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String s1 = "barfoothefoobarman";
        String[] words1 = { "foo", "bar" };
        System.out.println(sol.findSubstring(s1, words1)); // [0, 9]

        String s2 = "wordgoodgoodgoodbestword";
        String[] words2 = { "word", "good", "best", "word" };
        System.out.println(sol.findSubstring(s2, words2)); // []

        String s3 = "barfoofoobarthefoobarman";
        String[] words3 = { "bar", "foo", "the" };
        System.out.println(sol.findSubstring(s3, words3)); // [6, 9, 12]
    }
}

/*
 * 解題思路：
 * 1. 計算每個 word 的長度 wordLen，word 的數量 wordCount，以及總長 totalLen = wordLen *
 * wordCount。
 * 2. 用 HashMap 建立 words 的頻率表 wordMap。
 * 3. 遍歷 s 中每個可能的起點 i（最多到 s.length() - totalLen）。
 * 4. 對於每個起點：
 * - 逐個切出長度為 wordLen 的子串 sub
 * - 記錄 seen 次數，如果 sub 不在 wordMap 或出現次數超過，跳出
 * - 如果全部 words 都匹配，將 i 加入結果
 * 時間複雜度：O(n * m * k)，n = s.length(), m = words.length, k = words[i].length()
 * 空間複雜度：O(m)
 */