import java.util.*;

public class LC39_CombinationSum_PPE {
    public static void dfs(int[] nums, int start, int target, List<Integer> path, List<List<Integer>> res) {
        if (target == 0) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = start; i < nums.length; i++) {
            if (nums[i] > target)
                break; // 剪枝
            path.add(nums[i]);
            dfs(nums, i, target - nums[i], path, res); // i 可重複
            path.remove(path.size() - 1);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), target = sc.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++)
            nums[i] = sc.nextInt();
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        dfs(nums, 0, target, new ArrayList<>(), res);
        for (List<Integer> lst : res) {
            for (int x : lst)
                System.out.print(x + " ");
            System.out.println();
        }
    }
}
/*
 * 解題思路：
 * I 版（同一價格可重複使用）：
 * 1. 對候選價格排序。
 * 2. 使用回溯，每層嘗試從當前索引開始選數字。
 * 3. 若剩餘 target = 0，收錄組合；< 0 回溯。
 * 4. 下一層仍傳當前索引，允許重複使用同一價格。
 */
