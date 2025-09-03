import java.util.HashMap;
import java.util.Map;

class Solution {
    public static int romanToInt(String s) {
        Map<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);

        int result = 0;
        int n = s.length();

        for (int i = 0; i < n; i++) {
            int value = map.get(s.charAt(i));

            // 如果下一個數字比當前大，採用減法
            if (i + 1 < n && value < map.get(s.charAt(i + 1))) {
                result -= value;
            } else {
                result += value;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.romanToInt("III")); // 3
        System.out.println(sol.romanToInt("LVIII")); // 58
        System.out.println(sol.romanToInt("MCMXCIV")); // 1994
    }
}
/*
 * 解題思路：
 * 1. 建立羅馬符號與數值對應表。
 * 2. 遍歷字串，每次取當前符號的值。
 * 3. 如果下一個符號比當前大 → 採用減法；否則 → 加法。
 * 4. 累加結果，遍歷完成後返回整數。
 */
