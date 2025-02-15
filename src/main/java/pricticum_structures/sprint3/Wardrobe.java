package pricticum_structures.sprint3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Wardrobe {

    public static void main(String[] args) throws IOException {
        int n;
        List<Integer> arr;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            n = Integer.parseInt(reader.readLine());
            if (n == 0) {
                return;
            }
            arr = Arrays.stream(reader.readLine().strip().split(" "))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
        }

        StringBuilder buffer = new StringBuilder();
        int[] sortedArr = countingSort(arr, n);

        for (int j : sortedArr) {
            buffer.append(j).append(" ");
        }
        System.out.println(buffer);
    }

    private static int[] countingSort(List<Integer> arr, int n) {
        int[] countArr = new int[n + 2];
        for (int elem : arr) {
            countArr[elem]++;
        }

        int[] sortedArr = new int[arr.size()];
        int index = 0;

        for (int elem = 0; elem < countArr.length; elem++) {
            for (int amount = 0; amount < countArr[elem]; amount++) {
                sortedArr[index++] = elem;
            }
        }
        return sortedArr;
    }
}
