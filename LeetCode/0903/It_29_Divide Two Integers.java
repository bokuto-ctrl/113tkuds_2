class Solution {
    public int divide(int dividend, int divisor) {
        // 特殊情況：溢出
        if (dividend == Integer.MIN_VALUE && divisor == -1)
            return Integer.MAX_VALUE;
        if (dividend == Integer.MIN_VALUE && divisor == 1)
            return Integer.MIN_VALUE;

        // 判斷符號
        boolean negative = (dividend < 0) ^ (divisor < 0);

        // 轉為 long 避免溢出，並取絕對值
        long dvd = Math.abs((long) dividend);
        long dvs = Math.abs((long) divisor);

        int result = 0;
        while (dvd >= dvs) {
            long temp = dvs, multiple = 1;
            // 找最大的 temp * 2 <= dvd
            while (dvd >= (temp << 1)) {
                temp <<= 1;
                multiple <<= 1;
            }
            dvd -= temp;
            result += multiple;
        }

        return negative ? -result : result;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.divide(10, 3)); // 3
        System.out.println(sol.divide(7, -3)); // -2
        System.out.println(sol.divide(-2147483648, -1)); // 2147483647 (溢出)
    }
}

/*
 * 解題思路：
 * 1. 先處理溢出情況：當 dividend = MIN_VALUE 且 divisor = -1 時，返回 MAX_VALUE。
 * 2. 判斷結果符號：如果 dividend 與 divisor 異號，結果為負。
 * 3. 將 dividend 與 divisor 轉為 long 並取絕對值，避免溢出。
 * 4. 用減法 + 位運算：
 * - 每次找最大的 temp = divisor * 2^k <= dividend
 * - 用 dvd -= temp，累加結果 result += 2^k
 * 5. 返回帶符號的結果。
 * 時間複雜度 O(log n)，空間複雜度 O(1)
 */