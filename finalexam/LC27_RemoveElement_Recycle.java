import java.util.*;

public class LC27_RemoveElement_Recycle {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int val = sc.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++)
            nums[i] = sc.nextInt();
        sc.close();

        int write = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] != val) {
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
 * 2. 遍歷陣列，遇到不等於 val 的元素就寫入 write，並移動 write。
 * 3. 遍歷完成後 write 即為新長度，前段為保留元素。
 * 4. 時間 O(n)，空間 O(1)。
 */
