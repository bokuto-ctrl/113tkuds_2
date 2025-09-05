import java.util.*;

public class LC26_RemoveDuplicates_Scores {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++)
            nums[i] = sc.nextInt();
        sc.close();

        if (n == 0) {
            System.out.println(0);
            return;
        }

        int write = 1;
        for (int i = 1; i < n; i++) {
            if (nums[i] != nums[write - 1]) {
                nums[write] = nums[i];
                write++;
            }
        }

        System.out.println(write);
        for (int i = 0; i < write; i++) {
            System.out.print(nums[i]);
            if (i != write - 1)
                System.out.print(" ");
        }
    }
}
/*
 * 解題思路：
 * 1. 使用 write 指針指向可寫位置。
 * 2. 遍歷已排序陣列，若當前值與前一個保留值不同，寫入 write 位置並移動 write。
 * 3. 遍歷完成後 write 即為新長度，前段為去重結果。
 * 4. 時間 O(n)，空間 O(1)。
 */
