import java.util.*;

public class M10_RBPropertiesCheck {
    static class Node {
        int val;
        char color; // 'R' 或 'B'

        Node(int v, char c) {
            val = v;
            color = c;
        }
    }

    static boolean violationFound = false;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Node[] nodes = new Node[n];

        for (int i = 0; i < n; i++) {
            int val = sc.nextInt();
            String col = sc.next();
            char color = (val == -1) ? 'B' : col.charAt(0); // 空節點視為黑
            nodes[i] = new Node(val, color);
        }

        // 1. 根節點檢查
        if (n > 0 && nodes[0].color != 'B') {
            System.out.println("RootNotBlack");
            return;
        }

        // 2 & 3. 遞迴檢查紅紅與黑高度
        int bh = checkRB(nodes, 0);
        if (violationFound)
            return;
        if (bh == -1) {
            System.out.println("BlackHeightMismatch");
            return;
        }

        System.out.println("RB Valid");
    }

    // 回傳從節點到葉的黑高度，-1 表示黑高不一致
    public static int checkRB(Node[] nodes, int idx) {
        if (idx >= nodes.length || nodes[idx].val == -1)
            return 1; // NIL視為黑，高度1

        Node node = nodes[idx];
        int left = 2 * idx + 1;
        int right = 2 * idx + 2;

        // 紅紅違規
        if (node.color == 'R') {
            if ((left < nodes.length && nodes[left].color == 'R' && nodes[left].val != -1) ||
                    (right < nodes.length && nodes[right].color == 'R' && nodes[right].val != -1)) {
                System.out.println("RedRedViolation at index " + idx);
                violationFound = true;
                return -1;
            }
        }

        int lbh = checkRB(nodes, left);
        int rbh = checkRB(nodes, right);
        if (violationFound)
            return -1;

        if (lbh != rbh)
            return -1; // 黑高不一致

        return lbh + (node.color == 'B' ? 1 : 0);
    }
}
