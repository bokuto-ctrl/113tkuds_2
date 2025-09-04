import java.util.HashSet;

class Solution {
    public boolean isValidSudoku(char[][] board) {
        HashSet<String> seen = new HashSet<>();

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char c = board[i][j];
                if (c != '.') {
                    String row = c + " in row " + i;
                    String col = c + " in col " + j;
                    String box = c + " in box " + (i / 3) + "-" + (j / 3);

                    if (seen.contains(row) || seen.contains(col) || seen.contains(box)) {
                        return false;
                    }

                    seen.add(row);
                    seen.add(col);
                    seen.add(box);
                }
            }
        }

        return true;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        char[][] board1 = {
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
        System.out.println(sol.isValidSudoku(board1)); // true
    }
}

/*
 * 解題思路：
 * 1. 用 HashSet 記錄每個數字在行、列、子格中的出現。
 * 2. 每次遇到數字，檢查其行、列、子格是否已存在。
 * 3. 若重複 → false，否則加入 HashSet。
 * 時間複雜度 O(9*9) = O(1)，空間複雜度 O(9*9) = O(1)。
 */