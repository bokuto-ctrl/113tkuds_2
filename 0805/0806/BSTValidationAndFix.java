import java.util.*;

public class BSTValidationAndFix {

    static class TreeNode {
        int val;
        TreeNode left, right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    // 1. 驗證是否為有效BST（利用範圍限制）
    public static boolean isValidBST(TreeNode root) {
        return isValidBST(root, null, null);
    }

    private static boolean isValidBST(TreeNode node, Integer low, Integer high) {
        if (node == null)
            return true;
        if ((low != null && node.val <= low) || (high != null && node.val >= high))
            return false;
        return isValidBST(node.left, low, node.val) && isValidBST(node.right, node.val, high);
    }

    // 2. 找出不符合BST規則的節點（即在中序遍歷中違反排序的節點）
    // 返回違反的節點列表（通常是兩個節點）
    public static List<TreeNode> findInvalidNodes(TreeNode root) {
        List<TreeNode> invalids = new ArrayList<>();
        TreeNode[] prev = new TreeNode[1]; // 追蹤前一個節點
        findInvalidNodes(root, prev, invalids);
        return invalids;
    }

    private static void findInvalidNodes(TreeNode node, TreeNode[] prev, List<TreeNode> invalids) {
        if (node == null)
            return;

        findInvalidNodes(node.left, prev, invalids);

        if (prev[0] != null && prev[0].val > node.val) {
            // 發現遞增違反
            if (invalids.isEmpty()) {
                invalids.add(prev[0]); // 第一個錯誤節點
            }
            invalids.add(node); // 第二個錯誤節點（可能重複加）
        }
        prev[0] = node;

        findInvalidNodes(node.right, prev, invalids);
    }

    // 3. 修復兩個節點位置錯誤的BST（交換錯誤節點值）
    public static void recoverBST(TreeNode root) {
        TreeNode[] nodes = new TreeNode[2];
        TreeNode[] prev = new TreeNode[1];
        recoverBSTHelper(root, prev, nodes);
        if (nodes[0] != null && nodes[1] != null) {
            // 交換值修復
            int temp = nodes[0].val;
            nodes[0].val = nodes[1].val;
            nodes[1].val = temp;
        }
    }

    private static void recoverBSTHelper(TreeNode node, TreeNode[] prev, TreeNode[] nodes) {
        if (node == null)
            return;

        recoverBSTHelper(node.left, prev, nodes);

        if (prev[0] != null && prev[0].val > node.val) {
            if (nodes[0] == null)
                nodes[0] = prev[0];
            nodes[1] = node;
        }
        prev[0] = node;

        recoverBSTHelper(node.right, prev, nodes);
    }

    // 4. 計算最少需要移除多少節點才能變成有效BST
    // 解法：求樹中最大BST子樹大小，節點數減去該大小即為答案
    public static int minRemovalsForBST(TreeNode root) {
        int totalNodes = countNodes(root);
        int maxBSTSize = largestBSTSubtree(root).maxBSTSize;
        return totalNodes - maxBSTSize;
    }

    // 用類別包裝子樹資訊
    static class BSTInfo {
        boolean isBST;
        int minVal, maxVal;
        int maxBSTSize;
        int size;

        BSTInfo(boolean isBST, int minVal, int maxVal, int maxBSTSize, int size) {
            this.isBST = isBST;
            this.minVal = minVal;
            this.maxVal = maxVal;
            this.maxBSTSize = maxBSTSize;
            this.size = size;
        }
    }

    // 計算子樹最大BST大小
    private static BSTInfo largestBSTSubtree(TreeNode node) {
        if (node == null) {
            return new BSTInfo(true, Integer.MAX_VALUE, Integer.MIN_VALUE, 0, 0);
        }

        BSTInfo left = largestBSTSubtree(node.left);
        BSTInfo right = largestBSTSubtree(node.right);

        int size = left.size + right.size + 1;

        if (left.isBST && right.isBST && node.val > left.maxVal && node.val < right.minVal) {
            int maxBSTSize = size;
            int minVal = Math.min(left.minVal, node.val);
            int maxVal = Math.max(right.maxVal, node.val);
            return new BSTInfo(true, minVal, maxVal, maxBSTSize, size);
        } else {
            int maxBSTSize = Math.max(left.maxBSTSize, right.maxBSTSize);
            return new BSTInfo(false, 0, 0, maxBSTSize, size);
        }
    }

    private static int countNodes(TreeNode node) {
        if (node == null)
            return 0;
        return 1 + countNodes(node.left) + countNodes(node.right);
    }

    // 測試範例
    public static void main(String[] args) {
        /*
         * 3
         * / \
         * 1 4
         * /
         * 2
         * 這棵樹不是有效BST，因為2在4左子但比3小
         */
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(1);
        root.right = new TreeNode(4);
        root.right.left = new TreeNode(2);

        System.out.println("是否有效BST？ " + isValidBST(root)); // false

        List<TreeNode> invalidNodes = findInvalidNodes(root);
        System.out.print("不符合BST規則的節點: ");
        for (TreeNode n : invalidNodes) {
            System.out.print(n.val + " ");
        }
        System.out.println();

        // 修復 BST（交換錯誤節點值）
        recoverBST(root);
        System.out.println("修復後是否有效BST？ " + isValidBST(root)); // true

        // 加入一個錯誤節點，使樹更亂
        root.right.left.val = 5; // 讓節點值錯誤
        System.out.println("修改後是否有效BST？ " + isValidBST(root)); // false

        System.out.println("需要移除節點數使成為BST: " + minRemovalsForBST(root));
    }
}
