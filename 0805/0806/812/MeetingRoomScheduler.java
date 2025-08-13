import java.util.*;

public class MeetingRoomScheduler {

    // 會議類別
    static class Meeting {
        int start, end;

        public Meeting(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    // Part 1: 計算最少需要多少會議室
    public static int minMeetingRooms(int[][] intervals) {
        if (intervals == null || intervals.length == 0)
            return 0;

        // 將會議轉成 Meeting 物件並排序（依開始時間）
        Meeting[] meetings = new Meeting[intervals.length];
        for (int i = 0; i < intervals.length; i++) {
            meetings[i] = new Meeting(intervals[i][0], intervals[i][1]);
        }
        Arrays.sort(meetings, (a, b) -> a.start - b.start);

        // Min Heap 追蹤每個會議室的結束時間
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        minHeap.offer(meetings[0].end);

        for (int i = 1; i < meetings.length; i++) {
            if (meetings[i].start >= minHeap.peek()) {
                minHeap.poll(); // 會議室可用
            }
            minHeap.offer(meetings[i].end);
        }

        return minHeap.size();
    }

    // Part 2: 在有限 N 個會議室下，安排會議使總時間最大 (貪心方法)
    public static int maxTotalMeetingTime(int[][] intervals, int N) {
        if (intervals == null || intervals.length == 0 || N == 0)
            return 0;

        // 先依結束時間排序（貪心選最早結束的會議）
        Arrays.sort(intervals, (a, b) -> a[1] - b[1]);

        PriorityQueue<Integer> roomEndTimes = new PriorityQueue<>(); // 追蹤每個會議室結束時間
        int totalTime = 0;

        for (int[] meeting : intervals) {
            int start = meeting[0], end = meeting[1];
            if (roomEndTimes.size() < N) {
                // 有空閒會議室
                roomEndTimes.offer(end);
                totalTime += (end - start);
            } else if (start >= roomEndTimes.peek()) {
                // 最早結束的會議室可使用
                roomEndTimes.poll();
                roomEndTimes.offer(end);
                totalTime += (end - start);
            }
            // 否則無法安排，跳過
        }

        return totalTime;
    }

    // 測試程式
    public static void main(String[] args) {
        int[][][] testIntervals = {
                { { 0, 30 }, { 5, 10 }, { 15, 20 } },
                { { 9, 10 }, { 4, 9 }, { 4, 17 } },
                { { 1, 5 }, { 8, 9 }, { 8, 9 } }
        };

        System.out.println("Part 1: 最少需要多少會議室");
        for (int[][] intervals : testIntervals) {
            System.out.println("會議：" + Arrays.deepToString(intervals)
                    + " → 需要 " + minMeetingRooms(intervals) + " 個會議室");
        }

        System.out.println("\nPart 2: 給定 N 個會議室，總會議時間最大");
        int[][] intervals2 = { { 1, 4 }, { 2, 3 }, { 4, 6 } };
        int N = 1;
        System.out.println("會議：" + Arrays.deepToString(intervals2)
                + ", N=" + N + " → 最大總時間 = " + maxTotalMeetingTime(intervals2, N));
    }
}
