import java.util.*;

public class LC28_StrStr_NoticeSearch {
    public static int strStr(String haystack, String needle) {
        int n = haystack.length();
        int m = needle.length();
        if (m == 0)
            return 0;
        if (m > n)
            return -1;

        for (int i = 0; i <= n - m; i++) {
            int j = 0;
            while (j < m && haystack.charAt(i + j) == needle.charAt(j)) {
                j++;
            }
            if (j == m)
                return i;
        }
        return -1;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String haystack = sc.nextLine();
        String needle = sc.nextLine();
        sc.close();

        int idx = strStr(haystack, needle);
        System.out.println(idx);
    }
}
/*
 * 解題思路：
 * 1. 暴力法：逐個起點比較 haystack 與 needle。
 * 2. 若找到匹配，回傳起始索引；否則回 -1。
 * 3. 若字串較長或需高效，可改用 KMP 演算法避免重複比對。
 * 4. 時間 O(n*m)（暴力），空間 O(1)。
 */
