import java.util.*;

public class M06_PalindromeClean {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();

        // 清洗：只保留英文字母，轉小寫
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (Character.isLetter(c)) {
                sb.append(Character.toLowerCase(c));
            }
        }
        String cleaned = sb.toString();

        // 雙指標檢查回文
        boolean isPalindrome = checkPalindrome(cleaned);

        System.out.println(isPalindrome ? "Yes" : "No");
    }

    // 雙指標檢查
    public static boolean checkPalindrome(String s) {
        int left = 0, right = s.length() - 1;
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}
