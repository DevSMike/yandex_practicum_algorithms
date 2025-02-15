package pricticum_structures.sprint7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class TargetComboATM {

    public static void main(String[] args) throws IOException {
        int x;
        int k;
        int[] arr;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            x = Integer.parseInt(reader.readLine());
            k = Integer.parseInt(reader.readLine());
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            arr = new int[k];

            for (int i = 0; i < k; i++) {
                arr[i] = Integer.parseInt(tokenizer.nextToken());
            }
        }


        int[] dp = new int[x+1];
        dp[0] = 1;

        for (int money : arr) {
            for (int i = money; i <= x; i++) {
                dp[i] += dp[i-money];
            }
        }

        System.out.println(dp[x]);
    }
}
