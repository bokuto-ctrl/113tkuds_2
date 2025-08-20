import java.util.*;

public class M05_GCD_LCM_Recursive {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long a = sc.nextLong();
        long b = sc.nextLong();

        long g = gcd(a, b);
        long l = (a / g) * b; // 先除後乘避免溢位

        System.out.println("GCD: " + g);
        System.out.println("LCM: " + l);
    }

    // 遞迴版歐幾里得算法
    public static long gcd(long x, long y) {
        if (y == 0)
            return x;
        return gcd(y, x % y);
    }
}

/*
 * Time Complexity: O(log(min(a, b)))
 * 說明：歐幾里得算法每次遞迴至少使參數減小一半 → 遞迴深度 O(log min(a, b))
 * Space Complexity: O(log(min(a, b)))，因遞迴呼叫堆疊
 */
