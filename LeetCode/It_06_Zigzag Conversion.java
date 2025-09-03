class Solution {
    public String convert(String s, int numRows) {
        if (numRows == 1 || s.length() <= numRows)
            return s;

        StringBuilder[] rows = new StringBuilder[numRows];
        for (int i = 0; i < numRows; i++) {
            rows[i] = new StringBuilder();
        }

        int curRow = 0;
        boolean goingDown = false; // 控制方向

        for (char c : s.toCharArray()) {
            rows[curRow].append(c);
            // 到達頂部或底部就改變方向
            if (curRow == 0 || curRow == numRows - 1)
                goingDown = !goingDown;
            curRow += goingDown ? 1 : -1;
        }

        StringBuilder result = new StringBuilder();
        for (StringBuilder row : rows) {
            result.append(row);
        }
        return result.toString();
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.convert("PAYPALISHIRING", 3)); // PAHNAPLSIIGYIR
        System.out.println(sol.convert("PAYPALISHIRING", 4)); // PINALSIGYAHRPI
        System.out.println(sol.convert("A", 1)); // A
    }
}
/*
 * 1.從第 0 列開始往下走，走到最後一列就往上，循環往返。每一個字元 c 加到當前的列 curRow。
 * 2.goingDown = true → 往下移動 curRow+1 goingDown = false → 往上移動 curRow-1 當到達最上面
 * (curRow == 0) 或最下面 (curRow == numRows-1) → 翻轉方向。
 * 3.用 StringBuilder[] rows 儲存每一列的字元。最後把所有列的內容拼接成結果字串。
 */
