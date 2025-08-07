import java.util.*;

class BSTKthElement {

    static class TreeNode {
        int val;
        TreeNode left, right;
        int count; // 子樹節點數，包括自己

        TreeNode(int val) {
            this.val = val;
            this.count = 1;
        }
    }

    static class BST {
        private TreeNode root;

        // 更新節點 count
        private void updateCount(TreeNode node) {
            node.count = 1 + size(node.left) + size(node.right);
        }

        private int size(TreeNode node) {
            return node == null ? 0 : node.count;
        }

        // 插入節點（維護 count）
        public void insert(int val) {
            root = insert(root, val);
        }

        private TreeNode insert(TreeNode node, int val) {
            if (node == null)
                return new TreeNode(val);
            if (val < node.val) {
                node.left = insert(node.left, val);
            } else {
                node.right = insert(node.right, val);
            }
            updateCount(node);
            return node;
        }

        // 刪除節點（維護 count）
        public void delete(int val) {
            root = delete(root, val);
        }

        private TreeNode delete(TreeNode node, int val) {
            if (node == null)
                return null;
            if (val < node.val) {
                node.left = delete(node.left, val);
            } else if (val > node.val) {
                node.right = delete(node.right, val);
            } else {
                // 找到節點
                if (node.left == null)
                    return node.right;
                else if (node.right == null)
                    return node.left;
                else {
                    // 找右子樹最小值替代
                    TreeNode successor = min(node.right);
                    node.val = successor.val;
                    node.right = delete(node.right, successor.val);
                }
            }
            updateCount(node);
            return node;
        }

        private TreeNode min(TreeNode node) {
            while (node.left != null)
                node = node.left;
            return node;
        }

        // 1. 找第 k 小元素
        public int kthSmallest(int k) {
            if (k <= 0 || k > size(root))
                throw new IllegalArgumentException("k out of range");
            return kthSmallest(root, k);
        }

        private int kthSmallest(TreeNode node, int k) {
            int leftCount = size(node.left);
            if (k == leftCount + 1)
                return node.val;
            else if (k <= leftCount)
                return kthSmallest(node.left, k);
            else
                return kthSmallest(node.right, k - leftCount - 1);
        }

        // 2. 找第 k 大元素 (用第 size-k+1 小元素找)
        public int kthLargest(int k) {
            int n = size(root);
            if (k <= 0 || k > n)
                throw new IllegalArgumentException("k out of range");
            return kthSmallest(root, n - k + 1);
        }

        // 3. 找出第 k 小到第 j 小之間所有元素 (含端點)
        public List<Integer> rangeKth(int k, int j) {
            if (k > j)
                throw new IllegalArgumentException("k should <= j");
            List<Integer> result = new ArrayList<>();
            rangeKth(root, new int[] { k, j }, result, new int[] { 0 });
            return result;
        }

        // 用中序遞迴並記錄目前排名
        private void rangeKth(TreeNode node, int[] range, List<Integer> result, int[] count) {
            if (node == null)
                return;
            rangeKth(node.left, range, result, count);
            count[0]++;
            if (count[0] >= range[0] && count[0] <= range[1]) {
                result.add(node.val);
            }
            if (count[0] > range[1])
                return;
            rangeKth(node.right, range, result, count);
        }
    }

    public static void main(String[] args) {
        BST bst = new BST();
        int[] values = { 20, 8, 22, 4, 12, 10, 14 };
        for (int v : values)
            bst.insert(v);

        System.out.println("第 3 小元素: " + bst.kthSmallest(3)); // 10
        System.out.println("第 2 大元素: " + bst.kthLargest(2)); // 20

        System.out.println("第 2 小到第 5 小元素: " + bst.rangeKth(2, 5)); // [8, 10, 12, 14]

        bst.insert(9);
        System.out.println("插入 9 後第 4 小元素: " + bst.kthSmallest(4)); // 9

        bst.delete(10);
        System.out.println("刪除 10 後第 4 小元素: " + bst.kthSmallest(4)); // 12
    }
}
