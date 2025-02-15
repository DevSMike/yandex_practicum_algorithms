package pricticum_structures.sprint3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class PartSort {
    public static void main(String[] args) throws IOException {
        int n;
        List<Integer> arr;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            n = Integer.parseInt(reader.readLine());
            arr = Arrays.stream(reader.readLine().strip().split(" "))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
        }

        int[] minRight = new int[n];
        minRight[n - 1] = arr.get(n - 1);
        int[] maxLeft = new int[n];
        maxLeft[0] = arr.get(0);

        for (int i = n - 2; i >= 0; i--) {
            minRight[i] = Math.min(arr.get(i), minRight[i + 1]);
        }

        for (int i = 1; i < n; i++) {
            maxLeft[i] = Math.max(arr.get(i), maxLeft[i - 1]);
        }

        int blockCounter = 0;

        for (int i = 0; i < n -1; i++) {
            if (maxLeft[i] <= minRight[i+1]) {
                blockCounter++;
            }
        }

        blockCounter++;
        System.out.println(blockCounter);
    }
}
