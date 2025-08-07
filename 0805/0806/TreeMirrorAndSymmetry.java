class TreeMirrorAndSymmetry {

    static class TreeNode {
        int val;
        TreeNode left, right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    // 判斷是否為對稱樹（自我鏡像）
    public static boolean isSymmetric(TreeNode root) {
        if (root == null)
            return true;
        return isMirror(root.left, root.right);
    }

    private static boolean isMirror(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null)
            return true;
        if (t1 == null || t2 == null)
            return false;
        return (t1.val == t2.val)
                && isMirror(t1.left, t2.right)
                && isMirror(t1.right, t2.left);
    }

    // 將樹轉成鏡像樹（原地反轉左右子樹）
    public static TreeNode mirror(TreeNode root) {
        if (root == null)
            return null;
        TreeNode leftMirror = mirror(root.left);
        TreeNode rightMirror = mirror(root.right);
        root.left = rightMirror;
        root.right = leftMirror;
        return root;
    }

    // 比較兩棵樹是否互為鏡像
    public static boolean areMirrors(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null)
            return true;
        if (t1 == null || t2 == null)
            return false;
        return (t1.val == t2.val)
                && areMirrors(t1.left, t2.right)
                && areMirrors(t1.right, t2.left);
    }

    // 檢查 t2 是否為 t1 的子樹
    public static boolean isSubtree(TreeNode t1, TreeNode t2) {
        if (t2 == null)
            return true; // 空樹是任何樹的子樹
        if (t1 == null)
            return false;
        if (isSameTree(t1, t2))
            return true;
        return isSubtree(t1.left, t2) || isSubtree(t1.right, t2);
    }

    private static boolean isSameTree(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null)
            return true;
        if (t1 == null || t2 == null)
            return false;
        if (t1.val != t2.val)
            return false;
        return isSameTree(t1.left, t2.left) && isSameTree(t1.right, t2.right);
    }

    // 測試
    public static void main(String[] args) {
        /*
         * 1
         * / \
         * 2 2
         * / \
         * 3 3
         */
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.right.right = new TreeNode(3);

        System.out.println("是否為對稱樹: " + isSymmetric(root)); // true

        // 建立另一棵樹作鏡像比較
        TreeNode mirrorTree = new TreeNode(1);
        mirrorTree.left = new TreeNode(2);
        mirrorTree.right = new TreeNode(2);
        mirrorTree.left.right = new TreeNode(3);
        mirrorTree.right.left = new TreeNode(3);

        System.out.println("兩樹是否互為鏡像: " + areMirrors(root, mirrorTree)); // true

        // 鏡像轉換
        mirror(root);
        System.out.println("鏡像轉換後，是否與 mirrorTree 相同: " + isSameTree(root, mirrorTree)); // true

        // 子樹判斷
        TreeNode subTree = new TreeNode(2);
        subTree.left = new TreeNode(3);

        System.out.println("subTree 是否為 mirrorTree 的子樹: " + isSubtree(mirrorTree, subTree)); // true
    }
}
