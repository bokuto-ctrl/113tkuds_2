class Solution {
    public String countAndSay(int n) {
        String result = "1"; // 基本情況
        for (int i = 2; i <= n; i++) {
            StringBuilder sb = new StringBuilder();
            int count = 1;
            for (int j = 1; j < result.length(); j++) {
                if (result.charAt(j) == result.charAt(j - 1)) {
                    count++;
                } else {
                    sb.append(count).append(result.charAt(j - 1));
                    count = 1;
                }
            }
            sb.append(count).append(result.charAt(result.length() - 1)); // 處理最後一組
            result = sb.toString();
        }
        return result;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.countAndSay(1)); // 1211
        System.out.println(sol.countAndSay(4)); // 1
    }
}

/*
 * 解題思路：
 * 1. 從基本情況 "1" 開始迭代生成序列。
 * 2. 每次對前一個字串進行 **運行長度編碼(RLE)**：
 * - 遍歷字串，統計連續相同字元的數量 count。
 * - 遇到不同字元時，將 count + char 加入結果。
 * 3. 迭代 n-1 次即可得到第 n 項。
 * 時間複雜度 O(n * m)，m 為生成的字串長度
 * 空間複雜度 O(m)
 */