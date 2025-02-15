package pricticum_structures.sprint7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Schedule {

    static class TimeStamp implements Comparable<TimeStamp> {
        double start;
        double finish;

        public TimeStamp(double start, double finish) {
            this.start = start;
            this.finish = finish;
        }

        @Override
        public int compareTo(TimeStamp o) {
            int finishCompare = Double.compare(this.finish, o.finish);
            if (finishCompare == 0) {
                return Double.compare(this.start, o.start);
            }
            return finishCompare;
        }
    }

    public static void main(String[] args) throws IOException {
        int n;
        TimeStamp[] arr;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            n = Integer.parseInt(reader.readLine());
            arr = new TimeStamp[n];
            for (int i = 0; i < n; i++) {
                StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
                double start = Double.parseDouble(tokenizer.nextToken());
                double finish = Double.parseDouble(tokenizer.nextToken());
                arr[i] = new TimeStamp(start, finish);
            }
        }

        // DecimalFormat format = new DecimalFormat("#");
        Arrays.sort(arr);
        List<TimeStamp> result = new ArrayList<>();

        result.add(arr[0]);
        int count = 1;
        int j = 0;

        for (int i = 1; i < arr.length; i++) {
            if (arr[i].start >= arr[j].finish) {
                count++;
                j = i;
                result.add(arr[i]);
            }
        }

        StringBuilder buffer = new StringBuilder();
        buffer.append(count).append("\n");
        result.forEach(x -> buffer
                .append(x.start)
                .append(" ")
                .append(x.finish)
                .append("\n"));

        System.out.println(buffer);
    }
}
