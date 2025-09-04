import java.util.*;

class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        backtrack(candidates, target, 0, new ArrayList<>(), res);
        return res;
    }

    private void backtrack(int[] candidates, int target, int start, List<Integer> path, List<List<Integer>> res) {
        if (target == 0) {
            res.add(new ArrayList<>(path));
            return;
        }
        if (target < 0)
            return;

        for (int i = start; i < candidates.length; i++) {
            path.add(candidates[i]);
            backtrack(candidates, target - candidates[i], i, path, res);
            path.remove(path.size() - 1);
        }
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] candidates1 = { 2, 3, 6, 7 };
        System.out.println(sol.combinationSum(candidates1, 7)); // [[2,2,3],[7]]

        int[] candidates2 = { 2, 3, 5 };
        System.out.println(sol.combinationSum(candidates2, 8)); // [[2,2,2,2],[2,3,3],[3,5]]

        int[] candidates3 = { 2 };
        System.out.println(sol.combinationSum(candidates3, 1)); // []
    }
}
/*
 * 解題思路：
 * 1. 使用回溯從 candidates 中選數字，使總和等於 target。
 * 2. path 保存當前組合，target 為剩餘目標：
 * - target == 0：加入結果。
 * - target < 0：剪枝。
 * - 否則從當前索引開始選數字，可重複使用。
 * 3. 遞迴結束後返回所有組合。
 * 時間複雜度 O(2^t)，空間複雜度 O(target / min(candidates))。
 */
