class Solution {
    public int reverse(int x) {
        int rev = 0;

        while (x != 0) {
            int pop = x % 10; // 取出最後一位數
            x /= 10; // 去掉最後一位

            // 檢查是否溢出
            if (rev > Integer.MAX_VALUE / 10 || (rev == Integer.MAX_VALUE / 10 && pop > 7))
                return 0;
            if (rev < Integer.MIN_VALUE / 10 || (rev == Integer.MIN_VALUE / 10 && pop < -8))
                return 0;

            rev = rev * 10 + pop; // 更新反轉後的數字
        }

        return rev;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.reverse(123)); // 321
        System.out.println(sol.reverse(-123)); // -321
        System.out.println(sol.reverse(120)); // 21
        System.out.println(sol.reverse(1534236469)); // 0 (溢出)
    }
}
/*
 * 1.使用 % 10 取出最後一位數 pop。使用 /= 10 去掉最後一位。
 * 2.在更新 rev = rev * 10 + pop 之前，檢查是否會超過 int 範圍。int 最大值：2147483647 → 最後一位不能超過
 * 7。int 最小值：-2147483648 → 最後一位不能小於 -8
 * 3.每次把 pop 放到 rev 的最後一位：rev = rev * 10 + pop。
 */
