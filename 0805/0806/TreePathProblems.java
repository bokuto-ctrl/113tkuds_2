import java.util.*;

public class TreePathProblems {

    static class TreeNode {
        int val;
        TreeNode left, right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    // 1. 找出從根節點到所有葉節點的路徑
    public static List<List<Integer>> allRootToLeafPaths(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        dfsPaths(root, path, res);
        return res;
    }

    private static void dfsPaths(TreeNode node, List<Integer> path, List<List<Integer>> res) {
        if (node == null)
            return;
        path.add(node.val);
        if (node.left == null && node.right == null) {
            // 到達葉節點，複製路徑加入結果
            res.add(new ArrayList<>(path));
        } else {
            dfsPaths(node.left, path, res);
            dfsPaths(node.right, path, res);
        }
        path.remove(path.size() - 1); // 回溯
    }

    // 2. 判斷樹中是否存在和為目標值的根到葉路徑
    public static boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null)
            return false;
        if (root.left == null && root.right == null)
            return root.val == targetSum;
        return hasPathSum(root.left, targetSum - root.val) || hasPathSum(root.right, targetSum - root.val);
    }

    // 3. 找出樹中和最大的根到葉路徑（回傳該路徑和）
    public static int maxRootToLeafSum(TreeNode root) {
        if (root == null)
            return Integer.MIN_VALUE; // 空樹回傳極小值
        if (root.left == null && root.right == null)
            return root.val;
        int leftMax = maxRootToLeafSum(root.left);
        int rightMax = maxRootToLeafSum(root.right);
        return root.val + Math.max(leftMax, rightMax);
    }

    // 4. 計算樹中任意兩節點間的最大路徑和（樹的直徑問題，節點值可為正負）
    // 路徑不一定從根開始，也不一定到葉節點
    static int maxPathSumResult = Integer.MIN_VALUE;

    public static int maxPathSum(TreeNode root) {
        maxPathSumResult = Integer.MIN_VALUE;
        maxGain(root);
        return maxPathSumResult;
    }

    // 返回該節點向下最大貢獻值（可不包含負值）
    private static int maxGain(TreeNode node) {
        if (node == null)
            return 0;
        int leftGain = Math.max(maxGain(node.left), 0);
        int rightGain = Math.max(maxGain(node.right), 0);

        int priceNewPath = node.val + leftGain + rightGain;
        maxPathSumResult = Math.max(maxPathSumResult, priceNewPath);

        return node.val + Math.max(leftGain, rightGain);
    }

    // 測試
    public static void main(String[] args) {
        /*
         * 1
         * / \
         * 2 3
         * / \
         * 4 5
         */

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        System.out.println("所有根到葉路徑:");
        List<List<Integer>> paths = allRootToLeafPaths(root);
        for (List<Integer> p : paths) {
            System.out.println(p);
        }

        int targetSum = 7;
        System.out.println("是否有和為 " + targetSum + " 的根到葉路徑？ " + hasPathSum(root, targetSum));

        System.out.println("根到葉最大和: " + maxRootToLeafSum(root));

        /*
         * 加入負值節點測試最大路徑和
         * -10
         * / \
         * 9 20
         * / \
         * 15 7
         */
        TreeNode root2 = new TreeNode(-10);
        root2.left = new TreeNode(9);
        root2.right = new TreeNode(20);
        root2.right.left = new TreeNode(15);
        root2.right.right = new TreeNode(7);

        System.out.println("任意兩節點最大路徑和: " + maxPathSum(root2));
    }
}
