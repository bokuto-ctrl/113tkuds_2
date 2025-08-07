import java.util.ArrayList;
import java.util.List;

class BSTRangeQuerySystem {

    static class TreeNode {
        int val;
        TreeNode left, right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    static class BST {
        TreeNode root;

        // 插入節點
        public void insert(int val) {
            root = insert(root, val);
        }

        private TreeNode insert(TreeNode node, int val) {
            if (node == null)
                return new TreeNode(val);
            if (val < node.val)
                node.left = insert(node.left, val);
            else
                node.right = insert(node.right, val);
            return node;
        }

        // 範圍查詢：回傳範圍內所有節點值（有序）
        public List<Integer> rangeQuery(int min, int max) {
            List<Integer> result = new ArrayList<>();
            rangeQuery(root, min, max, result);
            return result;
        }

        private void rangeQuery(TreeNode node, int min, int max, List<Integer> result) {
            if (node == null)
                return;
            if (node.val > min)
                rangeQuery(node.left, min, max, result);
            if (node.val >= min && node.val <= max)
                result.add(node.val);
            if (node.val < max)
                rangeQuery(node.right, min, max, result);
        }

        // 範圍計數：計算範圍內節點數量
        public int rangeCount(int min, int max) {
            return rangeCount(root, min, max);
        }

        private int rangeCount(TreeNode node, int min, int max) {
            if (node == null)
                return 0;
            if (node.val < min)
                return rangeCount(node.right, min, max);
            if (node.val > max)
                return rangeCount(node.left, min, max);
            return 1 + rangeCount(node.left, min, max) + rangeCount(node.right, min, max);
        }

        // 範圍總和：計算範圍內節點值總和
        public int rangeSum(int min, int max) {
            return rangeSum(root, min, max);
        }

        private int rangeSum(TreeNode node, int min, int max) {
            if (node == null)
                return 0;
            if (node.val < min)
                return rangeSum(node.right, min, max);
            if (node.val > max)
                return rangeSum(node.left, min, max);
            return node.val + rangeSum(node.left, min, max) + rangeSum(node.right, min, max);
        }

        // 最接近查詢：找出最接近給定值的節點值
        public int closestValue(int target) {
            return closestValue(root, target, root.val);
        }

        private int closestValue(TreeNode node, int target, int closest) {
            if (node == null)
                return closest;
            if (Math.abs(node.val - target) < Math.abs(closest - target)) {
                closest = node.val;
            }
            if (target < node.val) {
                return closestValue(node.left, target, closest);
            } else if (target > node.val) {
                return closestValue(node.right, target, closest);
            } else {
                return node.val;
            }
        }
    }

    public static void main(String[] args) {
        BST bst = new BST();
        int[] values = { 20, 9, 25, 5, 12, 11, 14 };

        for (int v : values) {
            bst.insert(v);
        }

        int min = 10, max = 22;
        System.out.println("範圍查詢 [" + min + ", " + max + "] 節點值: " + bst.rangeQuery(min, max));
        System.out.println("範圍內節點數量: " + bst.rangeCount(min, max));
        System.out.println("範圍內節點值總和: " + bst.rangeSum(min, max));

        int target = 13;
        System.out.println("最接近 " + target + " 的節點值: " + bst.closestValue(target));
    }
}
