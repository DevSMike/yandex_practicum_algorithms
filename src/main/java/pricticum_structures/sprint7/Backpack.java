package pricticum_structures.sprint7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Backpack {

    public static void main(String[] args) throws IOException {
        int n;
        int m;
        int[] cost;
        int[] weight;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            n = Integer.parseInt(tokenizer.nextToken());
            m = Integer.parseInt(tokenizer.nextToken());

            cost = new int[n];
            weight = new int[n];

            for (int i = 0; i < n; i++) {
                StringTokenizer token = new StringTokenizer(reader.readLine());
                weight[i] = Integer.parseInt(token.nextToken());
                cost[i] = Integer.parseInt(token.nextToken());
            }
        }

        int[][] dp = new int[n + 1][m + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (weight[i - 1] > j) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - weight[i - 1]] + cost[i - 1]);
                }
            }
        }

        List<Integer> v = new ArrayList<>();
        printVertex(dp, v, n, m, weight);
        StringBuilder buffer = new StringBuilder();
        buffer.append(v.size()).append("\n");
        v.forEach(x->buffer.append(x).append(" "));

        System.out.println(buffer);

    }

    private static void printVertex(int[][] dp, List<Integer> v, int i, int w, int[] weight) {
        if (dp[i][w] == 0) {
            return;
        }
        if (dp[i][w] == dp[i - 1][w]) {
            printVertex(dp, v, i - 1, w, weight);
        } else {
            printVertex(dp, v, i - 1, w - weight[i - 1], weight);
            v.add(0, i);
        }
    }
}
