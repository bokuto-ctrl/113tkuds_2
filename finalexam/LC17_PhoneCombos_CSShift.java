import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LC17_PhoneCombos_CSShift {
    static String[] mapping = {
            "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"
    };

    public static void backtrack(String digits, int index, StringBuilder sb, List<String> res) {
        if (index == digits.length()) {
            res.add(sb.toString());
            return;
        }
        int digit = digits.charAt(index) - '2';
        String letters = mapping[digit];
        for (char c : letters.toCharArray()) {
            sb.append(c);
            backtrack(digits, index + 1, sb, res);
            sb.deleteCharAt(sb.length() - 1); // 回溯
        }
    }

    public static List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();
        if (digits == null || digits.length() == 0)
            return res;
        backtrack(digits, 0, new StringBuilder(), res);
        return res;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String digits = sc.nextLine().trim();
        sc.close();

        List<String> res = letterCombinations(digits);
        for (String s : res) {
            System.out.println(s);
        }
    }
}
/*
 * 解題思路：
 * 1. 將陣列排序。
 * 2. 固定前兩個元素，剩餘部分用左右雙指針找和為 target 的四元組。
 * 3. 找到組合後跳過重複元素，避免重複輸出。
 * 4. 遍歷完成即可得到所有不重複四元組。
 * 5. 時間 O(n^3)，空間 O(1)（不計輸出）。
 */
