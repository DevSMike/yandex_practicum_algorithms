package pricticum_structures.sprint7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// поиск наибольшей возрастающей последовательности в массиве
public class Travel {

    public static void main(String[] args) throws IOException  {
        int n;
        int[] arr;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            n = Integer.parseInt(reader.readLine());
            arr = new int[n];
            StringTokenizer str = new StringTokenizer(reader.readLine());

            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(str.nextToken());
            }
        }

        int[] dp = new int[n];
        int[] prev = new int[n];

        for (int i =0; i < n; i++) {
            dp[i] = 1;
            prev[i] = -1;

            for (int j = 0; j < i; j++) {
                if (arr[j] < arr[i] && dp[j] + 1 > dp[i]) {
                    dp[i] = dp[j] + 1;
                    prev[i] = j;
                }
            }
        }

        int len = 0;
        int lastPosition = 0;

        for (int i =0; i < n; i++) {
            if (dp[i] > len) {
                len = dp[i];
                lastPosition = i;
            }
        }

        int[] result = new int[len];
        int idx = len - 1;
        while (lastPosition != -1) {
            result[idx--] = lastPosition + 1;
            lastPosition = prev[lastPosition];
        }

        StringBuilder buffer = new StringBuilder();
        buffer.append(len).append("\n");

        for (int val : result) {
            buffer.append(val).append(" ");
        }

        System.out.println(buffer);
    }
}
