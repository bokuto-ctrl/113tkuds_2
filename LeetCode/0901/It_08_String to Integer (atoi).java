class Solution {
    public int myAtoi(String s) {
        if (s == null || s.length() == 0)
            return 0;

        int i = 0, n = s.length();
        // 1. 忽略前導空格
        while (i < n && s.charAt(i) == ' ')
            i++;

        // 2. 處理符號
        int sign = 1;
        if (i < n) {
            if (s.charAt(i) == '+') {
                sign = 1;
                i++;
            } else if (s.charAt(i) == '-') {
                sign = -1;
                i++;
            }
        }

        // 3. 轉換數字
        int result = 0;
        while (i < n) {
            char c = s.charAt(i);
            if (c < '0' || c > '9')
                break;

            int digit = c - '0';

            // 4. 溢出檢查
            if (result > (Integer.MAX_VALUE - digit) / 10) {
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }

            result = result * 10 + digit;
            i++;
        }

        return result * sign;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.myAtoi("42")); // 42
        System.out.println(sol.myAtoi("   -042")); // -42
        System.out.println(sol.myAtoi("1337c0d3")); // 1337
        System.out.println(sol.myAtoi("0-1")); // 0
        System.out.println(sol.myAtoi("words and 987"));// 0
        System.out.println(sol.myAtoi("-91283472332")); // -2147483648 (溢出)
    }
}
/*
 * 1.初始化索引 i = 0，長度 n = s.length()
 * 2.忽略前導空格：while (i < n && s.charAt(i) == ' ') i++;
 * 3.處理符號
 * 4.逐位累加數字：
 * 5.返回結果：return result * sign;
 */
