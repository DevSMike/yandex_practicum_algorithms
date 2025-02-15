package pricticum_structures.sprint7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//Задача о рюкзаке, решение за O(M) пространственную сложность
public class LeprechaunGold {

    public static void main(String[] args) throws IOException {
        int n;
        int m;
        int[] weight;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            n = Integer.parseInt(tokenizer.nextToken());
            m = Integer.parseInt(tokenizer.nextToken());
            weight = new int[n];
            StringTokenizer arr = new StringTokenizer(reader.readLine());

            for (int i = 0; i < n; i++) {
                weight[i] = Integer.parseInt(arr.nextToken());
            }
        }
        Arrays.sort(weight);
        int[] dp = new int[m + 1];

        for (int i = 1; i <= n; i++) {
            int[] cur = new int[m + 1];
            for (int j = 1; j <= m; j++) {
                if (weight[i - 1] > j) {
                    cur[j] = dp[j];
                } else {
                    cur[j] = Math.max(dp[j], dp[j - weight[i - 1]] + weight[i - 1]);
                }
            }
            dp = cur;
        }

        System.out.println(dp[m]);
    }
}
