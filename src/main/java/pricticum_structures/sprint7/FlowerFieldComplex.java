package pricticum_structures.sprint7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class FlowerFieldComplex {

    private static final String UP = "U";
    private static final String RIGHT = "R";

    public static void main(String[] args) throws IOException {
        int n;
        int m;
        int[][] filed;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            n = Integer.parseInt(tokenizer.nextToken());
            m = Integer.parseInt(tokenizer.nextToken());
            filed = new int[n][m];

            for (int i = 0; i < n; i++) {
                String str = reader.readLine();
                for (int j = 0; j < m; j++) {
                    filed[i][j] = str.charAt(j) - '0';
                }
            }
        }

        int[][] dp = new int[n][m];
        StringBuilder buffer = new StringBuilder();

        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j < m; j++) {
                int fromDown = i + 1 < n ? dp[i + 1][j] : 0;
                int fromRight = j - 1 >= 0 ? dp[i][j - 1] : 0;
                dp[i][j] = Math.max(fromRight, fromDown) + filed[i][j];
            }
        }

        Stack<String> stack = new Stack<>();

        int col = m - 1;
        int row = 0;

        while (row != n - 1 || col != 0) {
            int fromUp = row + 1 < n ? dp[row + 1][col] : -1;
            int fromRight = col - 1 >= 0 ? dp[row][col - 1] : -1;
            if (fromUp > fromRight) {
                stack.push(UP);
                row++;
            } else {
                stack.push(RIGHT);
                col--;
            }
        }

        while (!stack.empty()) {
            buffer.append(stack.pop());
        }
        System.out.println(dp[0][m - 1]);
        System.out.println(buffer);
    }
}


