import java.util.PriorityQueue;

public class StockMaximizer {

    public static int maxProfit(int[] prices, int K) {
        if (prices == null || prices.length < 2 || K == 0)
            return 0;

        int n = prices.length;
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);

        // 找出所有可交易區間的利潤
        int i = 0;
        while (i < n - 1) {
            // 找最低點 (買入)
            while (i < n - 1 && prices[i + 1] <= prices[i])
                i++;
            if (i == n - 1)
                break;
            int buy = prices[i++];

            // 找最高點 (賣出)
            while (i < n && prices[i] >= prices[i - 1])
                i++;
            int sell = prices[i - 1];

            int profit = sell - buy;
            if (profit > 0)
                maxHeap.offer(profit);
        }

        // 選取最大的 K 個利潤
        int totalProfit = 0;
        for (int t = 0; t < K && !maxHeap.isEmpty(); t++) {
            totalProfit += maxHeap.poll();
        }

        return totalProfit;
    }

    public static void main(String[] args) {
        int[][] testPrices = {
                { 2, 4, 1 },
                { 3, 2, 6, 5, 0, 3 },
                { 1, 2, 3, 4, 5 }
        };
        int[] Ks = { 2, 2, 2 };

        for (int t = 0; t < testPrices.length; t++) {
            int profit = maxProfit(testPrices[t], Ks[t]);
            System.out.println("價格：" + java.util.Arrays.toString(testPrices[t]) + ", K=" + Ks[t]);
            System.out.println("最大利潤：" + profit);
            System.out.println("--------------------------");
        }
    }
}
