import java.util.*;

public class LC03_NoRepeat_TaipeiMetroTap {
    public static int longestUniqueFlow(String s) {
        Map<Character, Integer> map = new HashMap<>(); // 紀錄字元最後出現的位置
        int left = 0, maxLen = 0;

        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);

            // 如果字元 c 出現過，且位置 >= left，移動左指針
            if (map.containsKey(c) && map.get(c) >= left) {
                left = map.get(c) + 1;
            }

            map.put(c, right); // 更新 c 最後位置
            maxLen = Math.max(maxLen, right - left + 1); // 更新答案
        }

        return maxLen;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine().trim();
        sc.close();

        int res = longestUniqueFlow(s);
        System.out.println(res);
    }

    /*
     * 解題思路：
     * 1. 找字串中最長不重複子字串長度。
     * 2. 用滑動視窗 + Map 記錄每個字元最後出現位置。
     * 3. 遇重複字元就把左指針移到上次位置的下一個。
     * 4. 一次遍歷更新最大長度即可。
     */}
