import java.util.*;

class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(candidates); // 排序方便去重
        backtrack(candidates, target, 0, new ArrayList<>(), res);
        return res;
    }

    private void backtrack(int[] candidates, int target, int start, List<Integer> path, List<List<Integer>> res) {
        if (target == 0) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            if (candidates[i] > target)
                break; // 剪枝
            if (i > start && candidates[i] == candidates[i - 1])
                continue; // 去重
            path.add(candidates[i]);
            backtrack(candidates, target - candidates[i], i + 1, path, res); // i+1 避免重複使用
            path.remove(path.size() - 1);
        }
    }

    public static void main(String[] args) {
        Solution sol = new Solution();

        int[] candidates1 = { 10, 1, 2, 7, 6, 1, 5 };
        int target1 = 8;
        System.out.println(sol.combinationSum2(candidates1, target1));
        // Output: [[1,1,6],[1,2,5],[1,7],[2,6]]

        int[] candidates2 = { 2, 5, 2, 1, 2 };
        int target2 = 5;
        System.out.println(sol.combinationSum2(candidates2, target2));
        // Output: [[1,2,2],[5]]
    }
}

/*
 * 解題思路：
 * 1. 對 candidates 先排序，方便去重。
 * 2. 使用回溯：
 * - path 保存當前組合，target 為剩餘目標。
 * - target == 0：加入結果。
 * - target < 0：剪枝。
 * - 遍歷 candidates，從當前索引開始，下一層遞迴 i+1，避免重複使用。
 * - 若當前數字與前一個相同且 i>start，跳過，避免重複組合。
 * 時間複雜度 O(2^n)，空間複雜度 O(target)。
 */