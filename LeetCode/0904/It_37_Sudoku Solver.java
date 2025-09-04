class Solution {
    public void solveSudoku(char[][] board) {
        solve(board);
    }

    private boolean solve(char[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.') {
                    for (char c = '1'; c <= '9'; c++) { // 嘗試 1-9
                        if (isValid(board, i, j, c)) {
                            board[i][j] = c;
                            if (solve(board))
                                return true;
                            board[i][j] = '.'; // 回溯
                        }
                    }
                    return false; // 無法填入任何數字
                }
            }
        }
        return true; // 所有格子填滿
    }

    private boolean isValid(char[][] board, int row, int col, char c) {
        for (int i = 0; i < 9; i++) {
            if (board[row][i] == c)
                return false; // 行檢查
            if (board[i][col] == c)
                return false; // 列檢查
            if (board[3 * (row / 3) + i / 3][3 * (col / 3) + i % 3] == c)
                return false; // 3x3 子格檢查
        }
        return true;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        char[][] board = {
                { '5', '3', '.', '.', '7', '.', '.', '.', '.' },
                { '6', '.', '.', '1', '9', '5', '.', '.', '.' },
                { '.', '9', '8', '.', '.', '.', '.', '6', '.' },
                { '8', '.', '.', '.', '6', '.', '.', '.', '3' },
                { '4', '.', '.', '8', '.', '3', '.', '.', '1' },
                { '7', '.', '.', '.', '2', '.', '.', '.', '6' },
                { '.', '6', '.', '.', '.', '.', '2', '8', '.' },
                { '.', '.', '.', '4', '1', '9', '.', '.', '5' },
                { '.', '.', '.', '.', '8', '.', '.', '7', '9' }
        };
        sol.solveSudoku(board);

        for (char[] row : board) {
            for (char c : row)
                System.out.print(c + " ");
            System.out.println();
        }
    }
}

/*
 * 解題思路：
 * 1. 遍歷每個空格 ('.')，嘗試填入 1~9。
 * 2. 每次填入前檢查行、列、3x3 子格是否有效。
 * 3. 若有效，遞迴下一格；若填不下去則回溯。
 * 4. 最後整個數獨被正確填滿即完成。
 * 時間複雜度：最壞情況 O(9^(n^2))，n=9
 * 空間複雜度：O(n^2) 遞迴棧
 */
