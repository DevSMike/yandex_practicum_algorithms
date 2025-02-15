package pricticum_structures.sprint1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/* contest run: https://contest.yandex.ru/contest/22450/run-report/120363198/ */

public class NearestZero {

    public static void main(String[] args) throws IOException {
        int n;
        List<Integer> arr;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            n = Integer.parseInt(reader.readLine().strip());
            arr = Arrays.stream(reader.readLine().strip().split(" "))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
        }

        StringBuilder result = new StringBuilder();
        int minLen = n + 1;
        int[] resultArr = new int[n];

        for (int i = 0; i < n; i++) {
            if (arr.get(i) != 0) {
                resultArr[i] = ++minLen;
            } else {
                minLen = 0;
            }
        }

        for (int i = n - 1; i >= 0; i--) {
            if (arr.get(i) != 0) {
                resultArr[i] = Math.min(resultArr[i], ++minLen);
            } else {
                minLen = 0;
            }
        }

        for (int i = 0; i < n; i++) {
            result.append(resultArr[i]).append(" ");
        }

        System.out.println(result);
    }
}



