package pricticum_structures.sprint7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class TheGoldRush {

    static class GoldenHeap implements Comparable<GoldenHeap> {
        int costPerKilo;
        int kilo;

        public GoldenHeap(int cost, int kilo) {
            this.costPerKilo = cost;
            this.kilo = kilo;
        }

        @Override
        public int compareTo(GoldenHeap o) {
            return -1 * Integer.compare(this.costPerKilo, o.costPerKilo);
        }
    }


    public static void main(String[] args) throws IOException {

        long m; //грузоподъемность
        int n; // кол во куч песка
        GoldenHeap[] arr;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            m = Long.parseLong(reader.readLine());
            n = Integer.parseInt(reader.readLine());
            arr = new GoldenHeap[n];

            for (int i = 0; i < n; i++) {
                StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
                int c = Integer.parseInt(tokenizer.nextToken());
                int kilo = Integer.parseInt(tokenizer.nextToken());
                arr[i] = new GoldenHeap(c, kilo);
            }
        }

        Arrays.sort(arr);

        long profit = 0;

        for (int i = 0; i < n; i++) {
            if (m <= 0) {
                break;
            }

            while (arr[i].kilo > 0) {
                profit += arr[i].costPerKilo;
                arr[i].kilo--;
                m--;
                if (m <= 0) {
                    break;
                }
            }
        }

        System.out.println(profit);
    }
}
