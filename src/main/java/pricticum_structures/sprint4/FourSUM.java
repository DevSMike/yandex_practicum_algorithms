package pricticum_structures.sprint4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class FourSUM {

    public static void main(String[] args) throws IOException {

        int n;
        int s;
        List<Long> arr;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            n = Integer.parseInt(reader.readLine().strip());

            if (n == 0) {
                System.out.println(0);
                return;
            }

            s = Integer.parseInt(reader.readLine().strip());
            arr = Arrays.stream(reader.readLine().strip().split(" "))
                    .map(Long::parseLong)
                    .collect(Collectors.toList());
        }

        Collections.sort(arr);
        Set<String> set = new HashSet<>();

        for (int i = 0; i < arr.size(); i++) {
            for (int j = i + 1; j < arr.size(); j++) {
                int left = j + 1;
                int right = arr.size() - 1;
                while (left < right) {
                    long sum = arr.get(i) + arr.get(j) + arr.get(left) + arr.get(right);
                    if (sum > s) {
                        right--;
                    } else if (sum < s) {
                        left++;
                    } else {
                        set.add(arr.get(i) + " " + arr.get(j) + " " + arr.get(left) + " " + arr.get(right));
                        left++;
                        right--;
                    }
                }
            }
        }

        StringBuilder buffer = new StringBuilder();
        set.stream()
                .sorted((a, b) -> {
                    int i = 0;
                    StringTokenizer first = new StringTokenizer(a);
                    StringTokenizer second = new StringTokenizer(b);
                    int compare = 0;
                    while (i < a.length() && i < b.length()) {
                        int aDigit = Integer.parseInt(first.nextToken());
                        int bDigit = Integer.parseInt(second.nextToken());
                        compare = Integer.compare(aDigit, bDigit);
                        if (compare != 0) {
                            return compare;
                        }
                    }
                    return compare;
                })
                .forEach(x -> buffer.append(x).append("\n"));

        System.out.println(set.size() + "\n" + buffer);
    }

}
