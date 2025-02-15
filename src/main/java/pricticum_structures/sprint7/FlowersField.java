package pricticum_structures.sprint7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class FlowersField {

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
        dp[n - 1][0] = filed[n - 1][0];

        for (int j = 1; j < m; j++) {
            dp[n - 1][j] = dp[n - 1][j - 1] + filed[n - 1][j];
        }

        for (int i = n - 2; i >= 0; i--) {
            dp[i][0] = dp[i + 1][0] + filed[i][0];
        }


        for (int i = n - 2; i >= 0; i--) {
            for (int j = 1; j < m; j++) {
                dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]) + filed[i][j];
            }
        }

        System.out.println(dp[0][m - 1]);
    }
}
