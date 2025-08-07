import java.util.*;

public class TreeReconstruction {

    static class TreeNode {
        int val;
        TreeNode left, right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    // 根據前序與中序重建二元樹
    public static TreeNode buildTreePreIn(int[] preorder, int[] inorder) {
        Map<Integer, Integer> inorderIndexMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++)
            inorderIndexMap.put(inorder[i], i);
        return buildPreInHelper(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1, inorderIndexMap);
    }

    private static TreeNode buildPreInHelper(int[] preorder, int preStart, int preEnd,
            int[] inorder, int inStart, int inEnd,
            Map<Integer, Integer> inorderIndexMap) {
        if (preStart > preEnd || inStart > inEnd)
            return null;

        int rootVal = preorder[preStart];
        TreeNode root = new TreeNode(rootVal);

        int inRootIndex = inorderIndexMap.get(rootVal);
        int leftTreeSize = inRootIndex - inStart;

        root.left = buildPreInHelper(preorder, preStart + 1, preStart + leftTreeSize,
                inorder, inStart, inRootIndex - 1, inorderIndexMap);

        root.right = buildPreInHelper(preorder, preStart + leftTreeSize + 1, preEnd,
                inorder, inRootIndex + 1, inEnd, inorderIndexMap);

        return root;
    }

    // 根據後序與中序重建二元樹
    public static TreeNode buildTreePostIn(int[] postorder, int[] inorder) {
        Map<Integer, Integer> inorderIndexMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++)
            inorderIndexMap.put(inorder[i], i);
        return buildPostInHelper(postorder, 0, postorder.length - 1, inorder, 0, inorder.length - 1, inorderIndexMap);
    }

    private static TreeNode buildPostInHelper(int[] postorder, int postStart, int postEnd,
            int[] inorder, int inStart, int inEnd,
            Map<Integer, Integer> inorderIndexMap) {
        if (postStart > postEnd || inStart > inEnd)
            return null;

        int rootVal = postorder[postEnd];
        TreeNode root = new TreeNode(rootVal);

        int inRootIndex = inorderIndexMap.get(rootVal);
        int leftTreeSize = inRootIndex - inStart;

        root.left = buildPostInHelper(postorder, postStart, postStart + leftTreeSize - 1,
                inorder, inStart, inRootIndex - 1, inorderIndexMap);

        root.right = buildPostInHelper(postorder, postStart + leftTreeSize, postEnd - 1,
                inorder, inRootIndex + 1, inEnd, inorderIndexMap);

        return root;
    }

    // 根據層序重建完全二元樹（陣列表示法）
    public static TreeNode buildCompleteTreeLevelOrder(int[] levelOrder) {
        if (levelOrder.length == 0)
            return null;
        TreeNode[] nodes = new TreeNode[levelOrder.length];
        for (int i = 0; i < levelOrder.length; i++) {
            if (levelOrder[i] != Integer.MIN_VALUE) { // 以 Integer.MIN_VALUE 代表空節點
                nodes[i] = new TreeNode(levelOrder[i]);
            }
        }
        for (int i = 0; i < levelOrder.length; i++) {
            if (nodes[i] != null) {
                int leftIdx = 2 * i + 1;
                int rightIdx = 2 * i + 2;
                if (leftIdx < levelOrder.length)
                    nodes[i].left = nodes[leftIdx];
                if (rightIdx < levelOrder.length)
                    nodes[i].right = nodes[rightIdx];
            }
        }
        return nodes[0];
    }

    // 驗證兩棵樹是否相同（結構和值都一樣）
    public static boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null)
            return true;
        if (p == null || q == null)
            return false;
        if (p.val != q.val)
            return false;
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    // 中序遍歷取得節點列表（用來比對）
    public static List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        inorderHelper(root, res);
        return res;
    }

    private static void inorderHelper(TreeNode node, List<Integer> res) {
        if (node == null)
            return;
        inorderHelper(node.left, res);
        res.add(node.val);
        inorderHelper(node.right, res);
    }

    // 測試
    public static void main(String[] args) {
        int[] preorder = { 3, 9, 20, 15, 7 };
        int[] inorder = { 9, 3, 15, 20, 7 };
        int[] postorder = { 9, 15, 7, 20, 3 };
        int[] levelOrder = { 3, 9, 20, Integer.MIN_VALUE, Integer.MIN_VALUE, 15, 7 };

        TreeNode treeFromPreIn = buildTreePreIn(preorder, inorder);
        TreeNode treeFromPostIn = buildTreePostIn(postorder, inorder);
        TreeNode treeFromLevelOrder = buildCompleteTreeLevelOrder(levelOrder);

        System.out.println("前中序重建後中序遍歷: " + inorderTraversal(treeFromPreIn));
        System.out.println("後中序重建後中序遍歷: " + inorderTraversal(treeFromPostIn));
        System.out.println("層序重建後中序遍歷: " + inorderTraversal(treeFromLevelOrder));

        // 驗證前中序與後中序重建結果相同
        System.out.println("前中序與後中序重建是否相同: " + isSameTree(treeFromPreIn, treeFromPostIn));
    }
}
