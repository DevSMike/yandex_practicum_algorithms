package pricticum_structures.sprint7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Market {

    public static void main(String[] args) throws IOException {
        int n;
        int[] arr;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            n = Integer.parseInt(reader.readLine());
            arr = new int[n + 1];
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            int i = 0;
            int size = tokenizer.countTokens();

            while (size-- > 0) {
                arr[i++] = Integer.parseInt(tokenizer.nextToken());
            }
            arr[n] = -1;
        }

        boolean isBought = false;
        int maxProfit = 0;
        int buyIdx = 0;

        for (int i = 0; i < n; i++) {
            if (arr[i] < arr[i + 1])  {
                if (!isBought) {
                    isBought = true;
                    buyIdx = i;
                }
            } else {
                if (isBought) {
                    isBought = false;
                    maxProfit += arr[i] - arr[buyIdx];
                }
            }
        }

        System.out.println(maxProfit);
    }
}
