import java.util.*;

public class BSTConversionAndBalance {

    static class TreeNode {
        int val;
        TreeNode left, right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    // 1. 將BST轉換成排序的雙向鏈結串列（中序遍歷，節點左指向前驅，右指向後繼）
    // 返回雙向鏈結串列的頭節點
    static TreeNode prev = null;
    static TreeNode head = null;

    public static TreeNode bstToDoublyList(TreeNode root) {
        prev = null;
        head = null;
        convertBSTToDoublyList(root);
        return head;
    }

    private static void convertBSTToDoublyList(TreeNode node) {
        if (node == null)
            return;
        convertBSTToDoublyList(node.left);

        if (prev == null) {
            head = node; // 最左邊節點為頭
        } else {
            prev.right = node;
            node.left = prev;
        }
        prev = node;

        convertBSTToDoublyList(node.right);
    }

    // 2. 將排序陣列轉換為平衡的BST（中間點為根，遞迴）
    public static TreeNode sortedArrayToBST(int[] nums) {
        return sortedArrayToBSTHelper(nums, 0, nums.length - 1);
    }

    private static TreeNode sortedArrayToBSTHelper(int[] nums, int start, int end) {
        if (start > end)
            return null;
        int mid = start + (end - start) / 2;
        TreeNode node = new TreeNode(nums[mid]);
        node.left = sortedArrayToBSTHelper(nums, start, mid - 1);
        node.right = sortedArrayToBSTHelper(nums, mid + 1, end);
        return node;
    }

    // 3. 檢查BST是否平衡，並計算平衡因子(左子樹高度 - 右子樹高度)
    // 平衡定義：任一節點的平衡因子 ∈ [-1, 1]
    static class BalanceStatusWithHeight {
        boolean balanced;
        int height;

        BalanceStatusWithHeight(boolean balanced, int height) {
            this.balanced = balanced;
            this.height = height;
        }
    }

    public static boolean isBalanced(TreeNode root) {
        return checkBalance(root).balanced;
    }

    private static BalanceStatusWithHeight checkBalance(TreeNode node) {
        if (node == null)
            return new BalanceStatusWithHeight(true, 0);

        BalanceStatusWithHeight left = checkBalance(node.left);
        BalanceStatusWithHeight right = checkBalance(node.right);

        boolean balanced = left.balanced && right.balanced &&
                Math.abs(left.height - right.height) <= 1;
        int height = Math.max(left.height, right.height) + 1;

        return new BalanceStatusWithHeight(balanced, height);
    }

    // 計算並列印每個節點的平衡因子（節點值: 平衡因子）
    public static void printBalanceFactors(TreeNode root) {
        printBalanceFactorsHelper(root);
    }

    private static int printBalanceFactorsHelper(TreeNode node) {
        if (node == null)
            return 0;
        int leftHeight = printBalanceFactorsHelper(node.left);
        int rightHeight = printBalanceFactorsHelper(node.right);
        int balanceFactor = leftHeight - rightHeight;
        System.out.println("節點 " + node.val + " 的平衡因子: " + balanceFactor);
        return Math.max(leftHeight, rightHeight) + 1;
    }

    // 4. 將BST中每個節點的值改為所有大於等於該節點值的總和（逆中序遍歷）
    static int sum = 0;

    public static void bstToGreaterSumTree(TreeNode root) {
        sum = 0;
        reverseInOrder(root);
    }

    private static void reverseInOrder(TreeNode node) {
        if (node == null)
            return;
        reverseInOrder(node.right);
        sum += node.val;
        node.val = sum;
        reverseInOrder(node.left);
    }

    // 輔助函式：中序遍歷列印BST
    public static void inorderPrint(TreeNode root) {
        if (root == null)
            return;
        inorderPrint(root.left);
        System.out.print(root.val + " ");
        inorderPrint(root.right);
    }

    // 測試
    public static void main(String[] args) {
        /*
         * 5
         * / \
         * 3 8
         * / \ \
         * 2 4 10
         */
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.right = new TreeNode(8);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(4);
        root.right.right = new TreeNode(10);

        System.out.println("中序遍歷原BST:");
        inorderPrint(root);
        System.out.println();

        // 1. BST -> 雙向鏈結串列
        TreeNode head = bstToDoublyList(root);
        System.out.print("雙向鏈結串列 (從頭開始向右): ");
        TreeNode cur = head;
        while (cur != null) {
            System.out.print(cur.val + " ");
            cur = cur.right;
        }
        System.out.println();

        // 2. 排序陣列轉平衡BST
        int[] sortedArr = { 1, 2, 3, 4, 5, 6, 7 };
        TreeNode balancedBST = sortedArrayToBST(sortedArr);
        System.out.print("平衡BST中序遍歷: ");
        inorderPrint(balancedBST);
        System.out.println();

        // 3. 是否平衡及平衡因子
        System.out.println("原BST是否平衡? " + isBalanced(root));
        System.out.println("節點平衡因子:");
        printBalanceFactors(root);

        // 4. BST轉換為Greater Sum Tree
        bstToGreaterSumTree(root);
        System.out.print("Greater Sum Tree中序遍歷: ");
        inorderPrint(root);
        System.out.println();
    }
}
