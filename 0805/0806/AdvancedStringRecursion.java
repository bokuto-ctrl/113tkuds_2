import java.util.HashSet;
import java.util.Set;

class AdvancedStringRecursion {

    // 1️⃣ 遞迴產生字串的所有排列組合
    public static void permutations(String str) {
        permutationsHelper("", str);
    }

    private static void permutationsHelper(String prefix, String remaining) {
        if (remaining.length() == 0) {
            System.out.println(prefix);
            return;
        }

        for (int i = 0; i < remaining.length(); i++) {
            permutationsHelper(
                    prefix + remaining.charAt(i),
                    remaining.substring(0, i) + remaining.substring(i + 1));
        }
    }

    // 2️⃣ 遞迴實作字串匹配演算法 (naive)
    // 判斷 pattern 是否出現在 text 中，回傳第一次出現的起始索引，找不到回傳 -1
    public static int recursiveStringMatch(String text, String pattern) {
        return matchHelper(text, pattern, 0);
    }

    private static int matchHelper(String text, String pattern, int start) {
        if (start > text.length() - pattern.length())
            return -1;
        if (text.startsWith(pattern, start))
            return start;
        return matchHelper(text, pattern, start + 1);
    }

    // 3️⃣ 遞迴移除字串中重複字符，保留第一次出現的順序
    public static String removeDuplicates(String str) {
        return removeDuplicatesHelper(str, new HashSet<Character>(), 0, "");
    }

    private static String removeDuplicatesHelper(String str, Set<Character> seen, int index, String result) {
        if (index == str.length())
            return result;
        char c = str.charAt(index);
        if (!seen.contains(c)) {
            seen.add(c);
            result += c;
        }
        return removeDuplicatesHelper(str, seen, index + 1, result);
    }

    // 4️⃣ 遞迴計算字串的所有子字串組合（連續字串）
    public static void substrings(String str) {
        substringsHelper(str, 0, 1);
    }

    private static void substringsHelper(String str, int start, int end) {
        if (start == str.length())
            return;
        if (end > str.length()) {
            substringsHelper(str, start + 1, start + 2);
            return;
        }
        System.out.println(str.substring(start, end));
        substringsHelper(str, start, end + 1);
    }

    // 主程式測試
    public static void main(String[] args) {
        System.out.println("1. 字串所有排列組合:");
        permutations("abc");

        System.out.println("\n2. 字串匹配演算法:");
        String text = "hello world";
        String pattern = "world";
        System.out.println("pattern '" + pattern + "' 在 text 中第一次出現索引: " + recursiveStringMatch(text, pattern));

        System.out.println("\n3. 移除重複字元:");
        String input = "programming";
        System.out.println("原字串: " + input);
        System.out.println("移除重複後: " + removeDuplicates(input));

        System.out.println("\n4. 字串所有子字串:");
        substrings("abc");
    }
}
