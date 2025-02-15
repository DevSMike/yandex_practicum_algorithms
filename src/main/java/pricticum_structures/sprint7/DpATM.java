package pricticum_structures.sprint7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class DpATM {

    public static void main(String[] args) throws IOException {
        int x;
        int k;
        int[] arr;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            x = Integer.parseInt(reader.readLine());
            k = Integer.parseInt(reader.readLine());
            arr = new int[k];
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            for (int i =0; i < k; i++) {
                arr[i] = Integer.parseInt(tokenizer.nextToken());
            }
        }

        int[] dp = new int[x + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for (int i = 1; i <= x; i++) {
            for (int money : arr) {
                if (i >= money && dp[i - money] != Integer.MAX_VALUE) {
                    dp[i] = Math.min(dp[i], dp[i- money] + 1);
                }
            }
        }

        System.out.println(dp[x] == Integer.MAX_VALUE ? -1 : dp[x]);
    }
}
