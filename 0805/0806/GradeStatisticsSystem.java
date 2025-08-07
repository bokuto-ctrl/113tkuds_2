class GradeStatisticsSystem {

    public static char getGrade(int score) {
        if (score >= 90)
            return 'A';
        else if (score >= 80)
            return 'B';
        else if (score >= 70)
            return 'C';
        else if (score >= 60)
            return 'D';
        else
            return 'F';
    }

    public static void main(String[] args) {
        int[] scores = { 85, 92, 78, 96, 87, 73, 89, 94, 82, 90 };
        int total = 0;
        int max = scores[0];
        int min = scores[0];

        for (int score : scores) {
            total += score;
            if (score > max)
                max = score;
            if (score < min)
                min = score;
        }

        double average = total / (double) scores.length;

        int countA = 0, countB = 0, countC = 0, countD = 0, countF = 0;
        for (int score : scores) {
            char grade = getGrade(score);
            switch (grade) {
                case 'A':
                    countA++;
                    break;
                case 'B':
                    countB++;
                    break;
                case 'C':
                    countC++;
                    break;
                case 'D':
                    countD++;
                    break;
                case 'F':
                    countF++;
                    break;
            }
        }

        int aboveAverage = 0;
        for (int score : scores) {
            if (score > average) {
                aboveAverage++;
            }
        }

        System.out.println("=== 成績報表 ===");
        System.out.print("成績列表：");
        for (int score : scores) {
            System.out.print(score + " ");
        }
        System.out.println();
        System.out.printf("平均分數：%.2f\n", average);
        System.out.println("最高分數：" + max);
        System.out.println("最低分數：" + min);
        System.out.println("等第統計：");
        System.out.println("  A (90↑)：" + countA + " 人");
        System.out.println("  B (80↑)：" + countB + " 人");
        System.out.println("  C (70↑)：" + countC + " 人");
        System.out.println("  D (60↑)：" + countD + " 人");
        System.out.println("  F (<60)：" + countF + " 人");
        System.out.println("高於平均分的人數：" + aboveAverage + " 人");
    }
}
