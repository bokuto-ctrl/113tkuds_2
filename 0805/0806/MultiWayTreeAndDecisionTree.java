import java.util.*;

// 多路樹節點定義（可有多個子節點）
class MultiTreeNode {
    String val;
    List<MultiTreeNode> children;

    MultiTreeNode(String val) {
        this.val = val;
        this.children = new ArrayList<>();
    }

    void addChild(MultiTreeNode child) {
        children.add(child);
    }

    int degree() {
        return children.size();
    }
}

public class MultiWayTreeAndDecisionTree {

    // 深度優先走訪（遞迴）
    public static void dfs(MultiTreeNode node) {
        if (node == null)
            return;
        System.out.print(node.val + " ");
        for (MultiTreeNode child : node.children) {
            dfs(child);
        }
    }

    // 廣度優先走訪（使用隊列）
    public static void bfs(MultiTreeNode root) {
        if (root == null)
            return;
        Queue<MultiTreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            MultiTreeNode curr = queue.poll();
            System.out.print(curr.val + " ");
            for (MultiTreeNode child : curr.children) {
                queue.offer(child);
            }
        }
    }

    // 計算多路樹高度
    public static int height(MultiTreeNode node) {
        if (node == null)
            return 0;
        int maxChildHeight = 0;
        for (MultiTreeNode child : node.children) {
            maxChildHeight = Math.max(maxChildHeight, height(child));
        }
        return maxChildHeight + 1;
    }

    // 印出每個節點的度數（子節點數）
    public static void printDegrees(MultiTreeNode node) {
        if (node == null)
            return;
        System.out.println("節點 " + node.val + " 的度數: " + node.degree());
        for (MultiTreeNode child : node.children) {
            printDegrees(child);
        }
    }

    // 簡單決策樹範例：猜數字遊戲
    // 節點val為問題或猜測，children是回答選項
    // 範例：判斷動物是狗還是貓的簡單決策樹
    public static MultiTreeNode buildSimpleDecisionTree() {
        MultiTreeNode root = new MultiTreeNode("有毛嗎？");

        MultiTreeNode yesNode = new MultiTreeNode("會叫嗎？");
        MultiTreeNode noNode = new MultiTreeNode("是魚嗎？");

        root.addChild(yesNode);
        root.addChild(noNode);

        MultiTreeNode dogNode = new MultiTreeNode("是狗");
        MultiTreeNode catNode = new MultiTreeNode("是貓");
        yesNode.addChild(dogNode);
        yesNode.addChild(catNode);

        MultiTreeNode fishNode = new MultiTreeNode("是魚");
        MultiTreeNode unknownNode = new MultiTreeNode("不確定");
        noNode.addChild(fishNode);
        noNode.addChild(unknownNode);

        return root;
    }

    public static void main(String[] args) {
        // 建立多路樹範例
        MultiTreeNode root = new MultiTreeNode("A");
        MultiTreeNode b = new MultiTreeNode("B");
        MultiTreeNode c = new MultiTreeNode("C");
        MultiTreeNode d = new MultiTreeNode("D");
        MultiTreeNode e = new MultiTreeNode("E");
        MultiTreeNode f = new MultiTreeNode("F");

        root.addChild(b);
        root.addChild(c);
        b.addChild(d);
        b.addChild(e);
        c.addChild(f);

        System.out.println("DFS 走訪:");
        dfs(root);
        System.out.println("\nBFS 走訪:");
        bfs(root);

        System.out.println("\n多路樹高度: " + height(root));
        System.out.println("各節點度數:");
        printDegrees(root);

        // 建立並測試簡單決策樹
        MultiTreeNode decisionRoot = buildSimpleDecisionTree();
        System.out.println("\n簡單決策樹 DFS 走訪:");
        dfs(decisionRoot);
    }
}
