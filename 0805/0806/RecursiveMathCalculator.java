class RecursiveMathCalculator {

    // 計算組合數 C(n, k)
    public static int combination(int n, int k) {
        if (k == 0 || k == n)
            return 1;
        return combination(n - 1, k - 1) + combination(n - 1, k);
    }

    // 計算卡塔蘭數 Catalan(n)
    public static int catalan(int n) {
        if (n == 0)
            return 1;
        int res = 0;
        for (int i = 0; i < n; i++) {
            res += catalan(i) * catalan(n - 1 - i);
        }
        return res;
    }

    // 計算漢諾塔移動步數 hanoi(n)
    public static int hanoiMoves(int n) {
        if (n == 1)
            return 1;
        return 2 * hanoiMoves(n - 1) + 1;
    }

    // 判斷是否為回文數（例如 12321）
    public static boolean isPalindrome(int number) {
        return number == reverseNumber(number);
    }

    // 輔助方法：反轉一個數字
    private static int reverseNumber(int n) {
        int reversed = 0;
        while (n > 0) {
            reversed = reversed * 10 + (n % 10);
            n /= 10;
        }
        return reversed;
    }

    // 主程式測試
    public static void main(String[] args) {
        // 測試組合數 C(n, k)
        System.out.println("C(5, 2) = " + combination(5, 2)); // 10
        System.out.println("C(6, 3) = " + combination(6, 3)); // 20

        // 測試卡塔蘭數
        System.out.println("Catalan(4) = " + catalan(4)); // 14
        System.out.println("Catalan(5) = " + catalan(5)); // 42

        // 測試漢諾塔
        System.out.println("Hanoi moves for 3 disks = " + hanoiMoves(3)); // 7
        System.out.println("Hanoi moves for 4 disks = " + hanoiMoves(4)); // 15

        // 測試回文數
        System.out.println("Is 12321 a palindrome? " + isPalindrome(12321)); // true
        System.out.println("Is 45654 a palindrome? " + isPalindrome(45654)); // true
        System.out.println("Is 12345 a palindrome? " + isPalindrome(12345)); // false
    }
}
