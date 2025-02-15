package pricticum_structures.sprint7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class JumpingOnStair {

    static final int MOD = 1000000007;
    public static void main(String[] args) throws IOException {

        int n;
        int k;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            n = Integer.parseInt(tokenizer.nextToken());
            k = Integer.parseInt(tokenizer.nextToken());
        }
        long[] dp = new long[n];
        dp[0] = 1;

        for (int i = 1; i < n; i++) {
            int j = 1;
            while (j <= k) {
                if (i - j >= 0 && i - j < n) {
                    dp[i] = ((dp[i] % MOD) + (dp[i-j] % MOD)) % MOD;
                } else {
                    break;
                }
                j++;
            }
        }

        System.out.println(dp[n - 1]);
    }
}
