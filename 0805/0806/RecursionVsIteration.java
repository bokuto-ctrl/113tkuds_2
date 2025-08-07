class RecursionVsIteration {

    // 1. 計算二項式係數 C(n,k)
    // 遞迴版本
    public static long binomialRecursive(int n, int k) {
        if (k == 0 || k == n)
            return 1;
        return binomialRecursive(n - 1, k - 1) + binomialRecursive(n - 1, k);
    }

    // 迭代版本（使用動態規劃）
    public static long binomialIterative(int n, int k) {
        long[][] C = new long[n + 1][k + 1];
        for (int i = 0; i <= n; i++) {
            int limit = Math.min(i, k);
            for (int j = 0; j <= limit; j++) {
                if (j == 0 || j == i)
                    C[i][j] = 1;
                else
                    C[i][j] = C[i - 1][j - 1] + C[i - 1][j];
            }
        }
        return C[n][k];
    }

    // 2. 尋找陣列中所有元素的乘積
    // 遞迴版本
    public static long productRecursive(int[] arr, int index) {
        if (index == arr.length)
            return 1;
        return arr[index] * productRecursive(arr, index + 1);
    }

    // 迭代版本
    public static long productIterative(int[] arr) {
        long product = 1;
        for (int v : arr)
            product *= v;
        return product;
    }

    // 3. 計算字串中元音字母的數量
    // 遞迴版本
    public static int countVowelsRecursive(String s, int index) {
        if (index == s.length())
            return 0;
        char c = Character.toLowerCase(s.charAt(index));
        int count = (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') ? 1 : 0;
        return count + countVowelsRecursive(s, index + 1);
    }

    // 迭代版本
    public static int countVowelsIterative(String s) {
        int count = 0;
        for (char c : s.toLowerCase().toCharArray()) {
            if ("aeiou".indexOf(c) != -1)
                count++;
        }
        return count;
    }

    // 4. 檢查括號是否配對正確 (只考慮 () [] {})
    // 遞迴版本 (用輔助方法)
    public static boolean checkBracketsRecursive(String s) {
        return checkBracketsHelper(s, 0, s.length() - 1);
    }

    private static boolean checkBracketsHelper(String s, int start, int end) {
        if (start > end)
            return true;
        if (s.charAt(start) == '(' && findMatching(s, start, end, ')') == start + 1)
            return checkBracketsHelper(s, start + 1, end - 1);
        if (s.charAt(start) == '[' && findMatching(s, start, end, ']') == start + 1)
            return checkBracketsHelper(s, start + 1, end - 1);
        if (s.charAt(start) == '{' && findMatching(s, start, end, '}') == start + 1)
            return checkBracketsHelper(s, start + 1, end - 1);

        int matchPos = findMatching(s, start, end, matchingBracket(s.charAt(start)));
        if (matchPos == -1)
            return false;

        // 檢查內層
        boolean inside = checkBracketsHelper(s, start + 1, matchPos - 1);
        // 檢查外層
        boolean outside = checkBracketsHelper(s, matchPos + 1, end);

        return inside && outside;
    }

    private static int findMatching(String s, int start, int end, char target) {
        int count = 0;
        for (int i = start; i <= end; i++) {
            if (s.charAt(i) == s.charAt(start))
                count++;
            else if (s.charAt(i) == target)
                count--;
            if (count == 0)
                return i;
        }
        return -1;
    }

    private static char matchingBracket(char c) {
        switch (c) {
            case '(':
                return ')';
            case '[':
                return ']';
            case '{':
                return '}';
            default:
                return ' ';
        }
    }

    // 迭代版本 (使用 stack)
    public static boolean checkBracketsIterative(String s) {
        java.util.Stack<Character> stack = new java.util.Stack<>();
        for (char c : s.toCharArray()) {
            if (c == '(' || c == '[' || c == '{')
                stack.push(c);
            else if (c == ')' || c == ']' || c == '}') {
                if (stack.isEmpty())
                    return false;
                char top = stack.pop();
                if (!matches(top, c))
                    return false;
            }
        }
        return stack.isEmpty();
    }

    private static boolean matches(char open, char close) {
        return (open == '(' && close == ')') ||
                (open == '[' && close == ']') ||
                (open == '{' && close == '}');
    }

    // 測試與效能比較
    public static void main(String[] args) {
        // 測試參數
        int n = 20, k = 10;
        int[] arr = { 1, 2, 3, 4, 5, 6 };
        String testStr = "Hello World! This is a test string with vowels and brackets () [] {}.";

        // 1. 二項式係數
        long start = System.nanoTime();
        long recBin = binomialRecursive(n, k);
        long end = System.nanoTime();
        System.out.printf("遞迴二項式係數 C(%d,%d) = %d, 耗時 %d ns\n", n, k, recBin, (end - start));

        start = System.nanoTime();
        long iterBin = binomialIterative(n, k);
        end = System.nanoTime();
        System.out.printf("迭代二項式係數 C(%d,%d) = %d, 耗時 %d ns\n", n, k, iterBin, (end - start));

        // 2. 陣列乘積
        start = System.nanoTime();
        long recProd = productRecursive(arr, 0);
        end = System.nanoTime();
        System.out.printf("遞迴陣列乘積 = %d, 耗時 %d ns\n", recProd, (end - start));

        start = System.nanoTime();
        long iterProd = productIterative(arr);
        end = System.nanoTime();
        System.out.printf("迭代陣列乘積 = %d, 耗時 %d ns\n", iterProd, (end - start));

        // 3. 計算字串元音數
        start = System.nanoTime();
        int recVowels = countVowelsRecursive(testStr, 0);
        end = System.nanoTime();
        System.out.printf("遞迴元音數 = %d, 耗時 %d ns\n", recVowels, (end - start));

        start = System.nanoTime();
        int iterVowels = countVowelsIterative(testStr);
        end = System.nanoTime();
        System.out.printf("迭代元音數 = %d, 耗時 %d ns\n", iterVowels, (end - start));

        // 4. 括號配對檢查
        String bracketsStr = "([]{}){}()[]";
        start = System.nanoTime();
        boolean recCheck = checkBracketsRecursive(bracketsStr);
        end = System.nanoTime();
        System.out.printf("遞迴括號配對 = %b, 耗時 %d ns\n", recCheck, (end - start));

        start = System.nanoTime();
        boolean iterCheck = checkBracketsIterative(bracketsStr);
        end = System.nanoTime();
        System.out.printf("迭代括號配對 = %b, 耗時 %d ns\n", iterCheck, (end - start));
    }
}
