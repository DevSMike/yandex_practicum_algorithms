package pricticum_structures.sprint1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* contest: https://contest.yandex.ru/contest/22450/run-report/119971256/ */

public class SleightOfHand {

    public static void main(String[] args) throws IOException {

        int k;
        int[] digits = new int[10];
        int counter = 0;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            k = Integer.parseInt(reader.readLine().strip());
            for (int i = 0; i < 4; i++) {
                char[] line = reader.readLine().strip().toCharArray();
                for (char elem : line) {
                    if (Character.isDigit(elem)) {
                        int index = elem - '0';
                        digits[index]++;
                    }
                }
            }
        }

        for (int digit : digits) {
            if (digit == 0) {
                continue;
            }
            if (digit <= k * 2) {
                counter++;
            }
        }

        System.out.println(counter);
    }
}
