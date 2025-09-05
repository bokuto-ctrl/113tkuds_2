import java.util.*;

public class LC04_Median_QuakeFeeds {
    public static double findMedian(double[] A, double[] B) {
        // 確保 A 是較短的陣列
        if (A.length > B.length) {
            return findMedian(B, A);
        }
        int n = A.length, m = B.length;
        int totalLeft = (n + m + 1) / 2;

        int left = 0, right = n; // A 的二分區間
        while (left <= right) {
            int i = (left + right) / 2; // A 左半部分元素個數
            int j = totalLeft - i; // B 左半部分元素個數

            double Aleft = (i == 0) ? Double.NEGATIVE_INFINITY : A[i - 1];
            double Aright = (i == n) ? Double.POSITIVE_INFINITY : A[i];
            double Bleft = (j == 0) ? Double.NEGATIVE_INFINITY : B[j - 1];
            double Bright = (j == m) ? Double.POSITIVE_INFINITY : B[j];

            if (Aleft <= Bright && Bleft <= Aright) {
                // 找到正確切割
                if ((n + m) % 2 == 1) {
                    return Math.max(Aleft, Bleft);
                } else {
                    return (Math.max(Aleft, Bleft) + Math.min(Aright, Bright)) / 2.0;
                }
            } else if (Aleft > Bright) {
                right = i - 1; // 往左縮
            } else {
                left = i + 1; // 往右擴
            }
        }
        throw new IllegalArgumentException("Invalid input arrays");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), m = sc.nextInt();
        double[] A = new double[n];
        double[] B = new double[m];
        for (int i = 0; i < n; i++)
            A[i] = sc.nextDouble();
        for (int j = 0; j < m; j++)
            B[j] = sc.nextDouble();
        sc.close();

        double ans = findMedian(A, B);
        System.out.printf("%.1f\n", ans);
    }
}
/*
 * 解題思路：
 * 1. 找兩個已排序陣列的聯合集中位數。
 * 2. 對較短陣列做二分切割，確保左右元素數量平衡。
 * 3. 根據左右最大/最小值判斷是否找到中位數。
 * 4. 時間 O(log min(n,m))，空間 O(1)。
 */
