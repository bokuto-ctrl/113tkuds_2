class MatrixCalculator {

    // 矩陣加法：A + B
    public static int[][] add(int[][] A, int[][] B) {
        int rows = A.length;
        int cols = A[0].length;
        int[][] result = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[i][j] = A[i][j] + B[i][j];
            }
        }
        return result;
    }

    // 矩陣乘法：A * B
    public static int[][] multiply(int[][] A, int[][] B) {
        int rowsA = A.length;
        int colsA = A[0].length;
        int colsB = B[0].length;

        int[][] result = new int[rowsA][colsB];

        for (int i = 0; i < rowsA; i++) {
            for (int j = 0; j < colsB; j++) {
                for (int k = 0; k < colsA; k++) {
                    result[i][j] += A[i][k] * B[k][j];
                }
            }
        }
        return result;
    }

    // 矩陣轉置
    public static int[][] transpose(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[][] transposed = new int[cols][rows];

        for (int i = 0; i < cols; i++) {
            for (int j = 0; j < rows; j++) {
                transposed[i][j] = matrix[j][i];
            }
        }
        return transposed;
    }

    // 找最大值
    public static int findMax(int[][] matrix) {
        int max = matrix[0][0];
        for (int[] row : matrix) {
            for (int val : row) {
                if (val > max)
                    max = val;
            }
        }
        return max;
    }

    // 找最小值
    public static int findMin(int[][] matrix) {
        int min = matrix[0][0];
        for (int[] row : matrix) {
            for (int val : row) {
                if (val < min)
                    min = val;
            }
        }
        return min;
    }

    // 列印矩陣
    public static void printMatrix(String label, int[][] matrix) {
        System.out.println(label);
        for (int[] row : matrix) {
            for (int val : row) {
                System.out.printf("%4d", val);
            }
            System.out.println();
        }
    }

    // 主程式：測試資料
    public static void main(String[] args) {
        int[][] matrixA = {
                { 1, 2, 3 },
                { 4, 5, 6 }
        };

        int[][] matrixB = {
                { 7, 8, 9 },
                { 10, 11, 12 }
        };

        int[][] matrixC = {
                { 1, 2 },
                { 3, 4 },
                { 5, 6 }
        };

        // 測試加法
        int[][] sum = add(matrixA, matrixB);
        printMatrix("矩陣 A + B =", sum);

        // 測試乘法 A * C
        int[][] product = multiply(matrixA, matrixC);
        printMatrix("矩陣 A * C =", product);

        // 測試轉置
        int[][] transposeA = transpose(matrixA);
        printMatrix("矩陣 A 的轉置 =", transposeA);

        // 測試最大最小值
        int max = findMax(matrixA);
        int min = findMin(matrixA);
        System.out.println("矩陣 A 最大值：" + max);
        System.out.println("矩陣 A 最小值：" + min);
    }
}
