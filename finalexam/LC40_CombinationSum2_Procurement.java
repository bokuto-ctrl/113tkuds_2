import java.util.*;

public class LC40_CombinationSum2_Procurement {
    public static void dfs(int[] nums, int start, int target, List<Integer> path, List<List<Integer>> res) {
        if (target == 0) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = start; i < nums.length; i++) {
            if (nums[i] > target)
                break;
            if (i > start && nums[i] == nums[i - 1])
                continue; // 同層跳重複
            path.add(nums[i]);
            dfs(nums, i + 1, target - nums[i], path, res); // i+1 不能重複
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
 * II 版（每個價格只能用一次）：
 * 1. 對候選價格排序。
 * 2. 回溯時下一層傳 i+1，並跳過同層重複值避免重複組合。
 * 3. 其他步驟同 I 版。
 * 
 * 時間複雜度：指數級，排序 O(n log n)。
 * 空間複雜度：遞歸深度 O(n)。
 */