import java.util.*;

class BinaryTreeBasicOperations {

    // 節點類別：一般樹節點，可有多子節點
    static class TreeNode {
        int val;
        List<TreeNode> children;

        public TreeNode(int val) {
            this.val = val;
            children = new ArrayList<>();
        }

        public void addChild(TreeNode child) {
            children.add(child);
        }
    }

    // 1. 計算節點總和與平均值
    static class SumCount {
        int sum = 0;
        int count = 0;
    }

    public static SumCount sumAndCount(TreeNode root) {
        SumCount sc = new SumCount();
        if (root == null)
            return sc;
        sc.sum += root.val;
        sc.count += 1;
        for (TreeNode c : root.children) {
            SumCount childSC = sumAndCount(c);
            sc.sum += childSC.sum;
            sc.count += childSC.count;
        }
        return sc;
    }

    // 2. 找最大值與最小值節點（回傳節點值）
    public static int findMax(TreeNode root) {
        if (root == null)
            return Integer.MIN_VALUE;
        int maxVal = root.val;
        for (TreeNode c : root.children) {
            maxVal = Math.max(maxVal, findMax(c));
        }
        return maxVal;
    }

    public static int findMin(TreeNode root) {
        if (root == null)
            return Integer.MAX_VALUE;
        int minVal = root.val;
        for (TreeNode c : root.children) {
            minVal = Math.min(minVal, findMin(c));
        }
        return minVal;
    }

    // 3. 計算樹的寬度 (每層節點數最大值) - 使用 BFS
    public static int treeWidth(TreeNode root) {
        if (root == null)
            return 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int maxWidth = 0;

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            maxWidth = Math.max(maxWidth, levelSize);
            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();
                for (TreeNode c : node.children) {
                    queue.offer(c);
                }
            }
        }
        return maxWidth;
    }

    // 4. 判斷是否為完全二元樹（最多2子節點）
    // 完全二元樹定義：除了最後一層外，所有層節點皆滿，最後一層節點靠左排列
    public static boolean isCompleteBinaryTree(TreeNode root) {
        if (root == null)
            return true;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean mustBeLeaf = false;

        while (!queue.isEmpty()) {
            TreeNode curr = queue.poll();

            // 子節點數不能超過2（確保是二元樹）
            if (curr.children.size() > 2)
                return false;

            // 左子
            TreeNode left = curr.children.size() > 0 ? curr.children.get(0) : null;
            // 右子
            TreeNode right = curr.children.size() > 1 ? curr.children.get(1) : null;

            if (mustBeLeaf && (left != null || right != null)) {
                // 之前已經遇到沒有兩個子節點的節點，後面不該有子節點
                return false;
            }

            if (left != null) {
                queue.offer(left);
            } else {
                // 若左子不存在，右子也應不存在才符合完全二元樹
                if (right != null)
                    return false;
                mustBeLeaf = true;
            }

            if (right != null) {
                queue.offer(right);
            } else {
                mustBeLeaf = true;
            }
        }

        return true;
    }

    // 主程式測試
    public static void main(String[] args) {
        // 建立一棵樹
        TreeNode root = new TreeNode(10);
        TreeNode c1 = new TreeNode(5);
        TreeNode c2 = new TreeNode(15);
        TreeNode c3 = new TreeNode(3);
        TreeNode c4 = new TreeNode(7);
        TreeNode c5 = new TreeNode(12);
        TreeNode c6 = new TreeNode(18);

        root.addChild(c1);
        root.addChild(c2);
        c1.addChild(c3);
        c1.addChild(c4);
        c2.addChild(c5);
        c2.addChild(c6);

        SumCount sc = sumAndCount(root);
        System.out.println("節點總和 = " + sc.sum);
        System.out.println("節點平均值 = " + (double) sc.sum / sc.count);
        System.out.println("最大節點值 = " + findMax(root));
        System.out.println("最小節點值 = " + findMin(root));
        System.out.println("樹的寬度 = " + treeWidth(root));
        System.out.println("是否為完全二元樹？ " + (isCompleteBinaryTree(root) ? "是" : "否"));

        // 建立一個非完全二元樹（故意讓c2多3個子節點）
        TreeNode c7 = new TreeNode(20);
        TreeNode c8 = new TreeNode(25);
        TreeNode c9 = new TreeNode(30);
        c2.children.add(c7);
        c2.children.add(c8);
        c2.children.add(c9);

        System.out.println("加入多餘子節點後，是否為完全二元樹？ " + (isCompleteBinaryTree(root) ? "是" : "否"));
    }
}
