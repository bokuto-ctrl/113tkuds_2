import java.util.Scanner;

class TicTacToeBoard {

    private static char[][] board = new char[3][3];
    private static char currentPlayer = 'X';

    // 初始化棋盤
    public static void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';
            }
        }
    }

    // 列印棋盤
    public static void printBoard() {
        System.out.println("  0   1   2");
        for (int i = 0; i < 3; i++) {
            System.out.print(i);
            for (int j = 0; j < 3; j++) {
                System.out.print(" " + board[i][j]);
                if (j < 2)
                    System.out.print(" |");
            }
            System.out.println();
            if (i < 2)
                System.out.println(" ---+---+---");
        }
    }

    // 嘗試放置棋子
    public static boolean placeMark(int row, int col) {
        if (row < 0 || row >= 3 || col < 0 || col >= 3) {
            System.out.println("⚠️ 無效座標，請重新輸入！");
            return false;
        }
        if (board[row][col] != ' ') {
            System.out.println("⚠️ 該位置已被佔用！");
            return false;
        }
        board[row][col] = currentPlayer;
        return true;
    }

    // 檢查勝利條件
    public static boolean checkWin() {
        // 行與列
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == currentPlayer &&
                    board[i][1] == currentPlayer &&
                    board[i][2] == currentPlayer)
                return true;

            if (board[0][i] == currentPlayer &&
                    board[1][i] == currentPlayer &&
                    board[2][i] == currentPlayer)
                return true;
        }

        // 對角線
        if (board[0][0] == currentPlayer &&
                board[1][1] == currentPlayer &&
                board[2][2] == currentPlayer)
            return true;

        if (board[0][2] == currentPlayer &&
                board[1][1] == currentPlayer &&
                board[2][0] == currentPlayer)
            return true;

        return false;
    }

    // 檢查平手
    public static boolean isBoardFull() {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (board[i][j] == ' ')
                    return false;
        return true;
    }

    // 主程式
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        initializeBoard();

        System.out.println("🎮 井字遊戲開始！");
        printBoard();

        while (true) {
            System.out.println("玩家 " + currentPlayer + " 的回合。請輸入列與行（格式：row col）：");
            int row = scanner.nextInt();
            int col = scanner.nextInt();

            if (!placeMark(row, col)) {
                continue;
            }

            printBoard();

            if (checkWin()) {
                System.out.println("🎉 玩家 " + currentPlayer + " 勝利！");
                break;
            }

            if (isBoardFull()) {
                System.out.println("🤝 遊戲平手！");
                break;
            }

            // 換玩家
            currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
        }
    }
}
