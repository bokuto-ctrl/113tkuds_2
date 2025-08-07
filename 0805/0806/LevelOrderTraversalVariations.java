import java.util.*;

public class LevelOrderTraversalVariations {

    static class TreeNode {
        int val;
        TreeNode left, right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    // 1. 將每一層節點分別存在不同 List 中
    public static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null)
            return res;

        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        while (!q.isEmpty()) {
            int size = q.size();
            List<Integer> level = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode cur = q.poll();
                level.add(cur.val);
                if (cur.left != null)
                    q.offer(cur.left);
                if (cur.right != null)
                    q.offer(cur.right);
            }
            res.add(level);
        }
        return res;
    }

    // 2. 之字形層序走訪（奇數層 L->R，偶數層 R->L）
    public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null)
            return res;

        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        boolean leftToRight = true;

        while (!q.isEmpty()) {
            int size = q.size();
            LinkedList<Integer> level = new LinkedList<>();
            for (int i = 0; i < size; i++) {
                TreeNode cur = q.poll();
                if (leftToRight)
                    level.addLast(cur.val);
                else
                    level.addFirst(cur.val);

                if (cur.left != null)
                    q.offer(cur.left);
                if (cur.right != null)
                    q.offer(cur.right);
            }
            res.add(level);
            leftToRight = !leftToRight;
        }
        return res;
    }

    // 3. 只列印每一層的最後一個節點值
    public static List<Integer> lastNodeEachLevel(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null)
            return res;

        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        while (!q.isEmpty()) {
            int size = q.size();
            Integer lastVal = null;
            for (int i = 0; i < size; i++) {
                TreeNode cur = q.poll();
                lastVal = cur.val;
                if (cur.left != null)
                    q.offer(cur.left);
                if (cur.right != null)
                    q.offer(cur.right);
            }
            res.add(lastVal);
        }
        return res;
    }

    // 4. 垂直層序走訪（水平位置分組）
    public static List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null)
            return res;

        // key: 水平位置, value: 該位置節點值列表
        TreeMap<Integer, List<Integer>> columnTable = new TreeMap<>();
        Queue<Pair> queue = new LinkedList<>();
        queue.offer(new Pair(root, 0));

        while (!queue.isEmpty()) {
            Pair p = queue.poll();
            TreeNode node = p.node;
            int col = p.col;

            columnTable.computeIfAbsent(col, k -> new ArrayList<>()).add(node.val);

            if (node.left != null)
                queue.offer(new Pair(node.left, col - 1));
            if (node.right != null)
                queue.offer(new Pair(node.right, col + 1));
        }

        for (List<Integer> colList : columnTable.values()) {
            res.add(colList);
        }
        return res;
    }

    // 輔助類別：節點+水平位置
    static class Pair {
        TreeNode node;
        int col;

        Pair(TreeNode node, int col) {
            this.node = node;
            this.col = col;
        }
    }

    // 測試
    public static void main(String[] args) {
        /*
         * 3
         * / \
         * 9 20
         * / \
         * 15 7
         */
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);

        System.out.println("每層節點列表: " + levelOrder(root));
        System.out.println("之字形層序: " + zigzagLevelOrder(root));
        System.out.println("每層最後一個節點: " + lastNodeEachLevel(root));
        System.out.println("垂直層序走訪: " + verticalOrder(root));
    }
}
