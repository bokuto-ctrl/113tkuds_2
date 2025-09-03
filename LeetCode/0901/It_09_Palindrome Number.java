class Solution {
    public boolean isPalindrome(int x) {
        // 負數或末位為 0 且不為 0 的數一定不是回文
        if (x < 0 || (x % 10 == 0 && x != 0))
            return false;

        int reverted = 0;
        while (x > reverted) {
            reverted = reverted * 10 + x % 10;
            x /= 10;
        }

        // x 可能比 reverted 少一位（奇數長度）
        return x == reverted || x == reverted / 10;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.isPalindrome(121)); // true
        System.out.println(sol.isPalindrome(-121)); // false
        System.out.println(sol.isPalindrome(10)); // false
        System.out.println(sol.isPalindrome(12321));// true
    }
}
/*
 * 解題思路：
 * 1. 題目要求判斷整數是否為回文。
 * 2. 負數或末位為 0（非 0）一定不是回文，直接返回 false。
 * 3. 將數字反轉一半（reverted），逐位取末位累加。
 * 4. 遍歷結束後，若 x 等於 reverted 或 x 等於 reverted/10（奇數長度），則為回文。
 * 5. 時間複雜度 O(log n)，空間複雜度 O(1)。
 */
