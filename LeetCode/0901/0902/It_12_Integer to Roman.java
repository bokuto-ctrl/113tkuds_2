class Solution {
    public String intToRoman(int num) {
        // 值對應的羅馬符號，從大到小排列
        int[] values = { 1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1 };
        String[] symbols = { "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I" };

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < values.length; i++) {
            while (num >= values[i]) {
                num -= values[i];
                sb.append(symbols[i]);
            }
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.intToRoman(3749)); // MMMDCCXLIX
        System.out.println(sol.intToRoman(58)); // LVIII
        System.out.println(sol.intToRoman(1994)); // MCMXCIV
    }
}
/*
 * 解題思路：
 * 1. 準備對應的整數值與羅馬符號陣列，從大到小排列。
 * 2. 從大到小依次減去對應的值，並把符號加到結果字串。
 * 3. 重複直到 num 減為 0，返回組合好的羅馬數字。
 */
