import java.util.*;

public class M09_AVLValidate {
    static class TreeNode {
        int val;
        TreeNode left, right;

        TreeNode(int v) {
            val = v;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++)
            arr[i] = sc.nextInt();

        TreeNode root = buildTree(arr);

        if (!isBST(root, Long.MIN_VALUE, Long.MAX_VALUE)) {
            System.out.println("Invalid BST");
        } else if (!isAVL(root)) {
            System.out.println("Invalid AVL");
        } else {
            System.out.println("Valid");
        }
    }

    // 層序建樹
    public static TreeNode buildTree(int[] arr) {
        if (arr.length == 0 || arr[0] == -1)
            return null;

        TreeNode root = new TreeNode(arr[0]);
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        int i = 1;
        while (i < arr.length && !q.isEmpty()) {
            TreeNode node = q.poll();

            if (i < arr.length && arr[i] != -1) {
                node.left = new TreeNode(arr[i]);
                q.offer(node.left);
            }
            i++;

            if (i < arr.length && arr[i] != -1) {
                node.right = new TreeNode(arr[i]);
                q.offer(node.right);
            }
            i++;
        }
        return root;
    }

    // 檢查 BST（遞迴帶上下界）
    public static boolean isBST(TreeNode node, long min, long max) {
        if (node == null)
            return true;
        if (node.val <= min || node.val >= max)
            return false;
        return isBST(node.left, min, node.val) && isBST(node.right, node.val, max);
    }

    // 檢查 AVL（後序回傳高度）
    public static boolean isAVL(TreeNode node) {
        return checkHeight(node) != -1;
    }

    // 回傳高度，若不合法傳 -1
    public static int checkHeight(TreeNode node) {
        if (node == null)
            return 0;

        int lh = checkHeight(node.left);
        if (lh == -1)
            return -1;

        int rh = checkHeight(node.right);
        if (rh == -1)
            return -1;

        if (Math.abs(lh - rh) > 1)
            return -1;

        return Math.max(lh, rh) + 1;
    }
}

/*
 * Time Complexity: O(n)
 * 說明：
 * - 建樹 O(n)
 * - 檢查 BST O(n)
 * - 檢查 AVL O(n)
 * 每個節點最多訪問一次
 * Space Complexity: O(n) 遞迴堆疊 + 佇列建樹
 */
