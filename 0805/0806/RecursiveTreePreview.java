import java.util.ArrayList;
import java.util.List;

class RecursiveTreePreview {

    // -------- 1. 模擬檔案系統的資料夾/檔案結構 --------
    static class FileSystemNode {
        String name;
        boolean isFile;
        List<FileSystemNode> children; // 資料夾才有

        public FileSystemNode(String name, boolean isFile) {
            this.name = name;
            this.isFile = isFile;
            if (!isFile)
                this.children = new ArrayList<>();
        }

        public void addChild(FileSystemNode child) {
            if (this.isFile)
                throw new UnsupportedOperationException("File cannot have children.");
            this.children.add(child);
        }
    }

    public static int countFiles(FileSystemNode node) {
        if (node.isFile)
            return 1;
        int count = 0;
        for (FileSystemNode child : node.children) {
            count += countFiles(child);
        }
        return count;
    }

    // -------- 2. 多層選單結構 --------
    static class MenuItem {
        String name;
        List<MenuItem> subItems;

        public MenuItem(String name) {
            this.name = name;
            this.subItems = new ArrayList<>();
        }

        public void addSubItem(MenuItem item) {
            subItems.add(item);
        }
    }

    public static void printMenu(MenuItem menu, int level) {
        String indent = "  ".repeat(level);
        System.out.println(indent + "- " + menu.name);
        for (MenuItem sub : menu.subItems) {
            printMenu(sub, level + 1);
        }
    }

    // -------- 3. 巢狀陣列展平 --------
    public static List<Object> flattenList(List<?> nestedList) {
        List<Object> flatList = new ArrayList<>();
        for (Object elem : nestedList) {
            if (elem instanceof List<?>) {
                flatList.addAll(flattenList((List<?>) elem));
            } else {
                flatList.add(elem);
            }
        }
        return flatList;
    }

    // -------- 4. 巢狀清單最大深度 --------
    public static int maxDepth(List<?> nestedList) {
        int max = 1;
        for (Object elem : nestedList) {
            if (elem instanceof List<?>) {
                int depth = 1 + maxDepth((List<?>) elem);
                if (depth > max)
                    max = depth;
            }
        }
        return max;
    }

    // -------- 主程式測試 --------
    public static void main(String[] args) {
        // 1. 檔案系統測試
        FileSystemNode root = new FileSystemNode("root", false);
        FileSystemNode folder1 = new FileSystemNode("folder1", false);
        FileSystemNode folder2 = new FileSystemNode("folder2", false);
        FileSystemNode file1 = new FileSystemNode("file1.txt", true);
        FileSystemNode file2 = new FileSystemNode("file2.txt", true);
        FileSystemNode file3 = new FileSystemNode("file3.txt", true);

        folder1.addChild(file1);
        folder1.addChild(file2);
        folder2.addChild(file3);
        root.addChild(folder1);
        root.addChild(folder2);

        System.out.println("Total files in root: " + countFiles(root)); // 3

        // 2. 多層選單測試
        MenuItem mainMenu = new MenuItem("主選單");
        MenuItem fileMenu = new MenuItem("檔案");
        MenuItem editMenu = new MenuItem("編輯");
        MenuItem openItem = new MenuItem("開啟");
        MenuItem saveItem = new MenuItem("儲存");
        fileMenu.addSubItem(openItem);
        fileMenu.addSubItem(saveItem);
        mainMenu.addSubItem(fileMenu);
        mainMenu.addSubItem(editMenu);

        System.out.println("\n多層選單結構：");
        printMenu(mainMenu, 0);

        // 3. 巢狀陣列展平測試
        List<Object> nestedList = new ArrayList<>();
        nestedList.add(1);
        nestedList.add(List.of(2, 3));
        nestedList.add(List.of(List.of(4, 5), 6));
        System.out.println("\n展平巢狀陣列：" + flattenList(nestedList));

        // 4. 巢狀清單最大深度測試
        System.out.println("巢狀清單最大深度：" + maxDepth(nestedList)); // 3
    }
}
