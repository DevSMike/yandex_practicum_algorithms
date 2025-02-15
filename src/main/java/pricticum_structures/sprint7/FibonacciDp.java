package pricticum_structures.sprint7;

import java.util.Scanner;

public class FibonacciDp {

    private static final long MOD = 1000000007;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        long[] dp = new long[n+1];
        dp[0] = 1;
        dp[1] = 1;

        for (int i = 2; i <= n; i++) {
            dp[i] = (dp[i-1] % MOD) + (dp[i-2] % MOD);
        }

        System.out.println(dp[n] % MOD);
    }
}
