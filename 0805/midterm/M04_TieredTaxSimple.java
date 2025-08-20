import java.util.*;

public class M04_TieredTaxSimple {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        long totalTax = 0; // 用來算平均
        for (int i = 0; i < n; i++) {
            long income = sc.nextLong();
            long tax = calcTax(income);
            System.out.println("Tax: " + tax);
            totalTax += tax;
        }

        long avg = totalTax / n; // 取整數
        System.out.println("Average: " + avg);
    }

    // 計算稅額（逐段累加）
    public static long calcTax(long income) {
        long tax = 0;
        if (income > 1_000_000) {
            tax += (income - 1_000_000) * 30 / 100;
            income = 1_000_000;
        }
        if (income > 500_000) {
            tax += (income - 500_000) * 20 / 100;
            income = 500_000;
        }
        if (income > 120_000) {
            tax += (income - 120_000) * 12 / 100;
            income = 120_000;
        }
        if (income > 0) {
            tax += income * 5 / 100;
        }
        return tax;
    }
}

/*
 * Time Complexity: O(n)
 * 說明：每筆收入稅額計算為 O(1)，共 n 筆 → O(n)
 * Space Complexity: O(1)，僅用常數變數
 */
