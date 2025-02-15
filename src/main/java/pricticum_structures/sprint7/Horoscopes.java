package pricticum_structures.sprint7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Horoscopes {
    public static void main(String[] args) throws IOException {

        int n;
        int m;
        int[] arr1;
        int[] arr2;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            n = Integer.parseInt(reader.readLine());
            arr1 = new int[n];
            readArr(reader, n, arr1);
            m = Integer.parseInt(reader.readLine());
            arr2 = new int[m];
            readArr(reader, m, arr2);
        }

        int[][] dp = new int[n+1][m+1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (arr1[i-1] == arr2[j-1]) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }

        int[] answer1 = new int[dp[n][m]];
        int[] answer2 = new int[dp[n][m]];
        int idx = dp[n][m] - 1;
        int i = n;
        int j = m;

        while (idx >= 0) {
            if (arr1[i-1] == arr2[j-1]) {
                answer1[idx] = i;
                answer2[idx] = j;
                idx--;
                i--;
                j--;
            } else if (dp[i][j] == dp[i-1][j]) {
                i--;
            } else {
                j--;
            }
        }

        StringBuilder buffer = new StringBuilder();

        for (int value : answer1) {
            buffer.append(value).append(" ");
        }
        buffer.append("\n");
        for (int value : answer2) {
            buffer.append(value).append(" ");

        }

        System.out.println(dp[n][m] + "\n" + buffer);
    }

    private static void readArr(BufferedReader reader, int size, int[] arr) throws IOException {
        StringTokenizer arrStr = new StringTokenizer(reader.readLine());
        for (int i = 0; i < size; i++) {
            arr[i] = Integer.parseInt(arrStr.nextToken());
        }
    }
}
