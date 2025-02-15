package pricticum_structures.sprint3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Collections.swap;

public class Cookies {

    public static void main(String[] args) throws IOException {

        int n;
        int m;
        List<Integer> happinessK;
        List<Integer> cookiesSize;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            n = Integer.parseInt(reader.readLine().strip());
            happinessK = readList(reader);
            m = Integer.parseInt(reader.readLine().strip());
            cookiesSize = readList(reader);
        }

        quickSort(happinessK, 0, n-1);
        quickSort(cookiesSize, 0, m - 1);

        int counter = 0;
        int i = 0;
        int j = 0;
        while (i < happinessK.size() && j < cookiesSize.size()) {
            if (happinessK.get(i) <= cookiesSize.get(j)) {
                counter++;
                i++;
            }
            j++;
        }

        System.out.println(counter);
    }

    private static void quickSort(List<Integer> arr, int from, int to) {
        if (from < to) {
            int divideIdx = partition(arr, from, to);
            quickSort(arr, from, divideIdx - 1);
            quickSort(arr, divideIdx, to);
        }
    }

    private static int partition(List<Integer> arr, int left, int right) {
        int pivot = arr.get((left + right) / 2);

        while (left <= right) {

            while (arr.get(left) < pivot) {
                left++;
            }

            while (arr.get(right) > pivot) {
                right--;
            }

            if (left <= right) {
                swap(arr, left, right);
                left++;
                right--;
            }
        }

        return left;
    }

    private static List<Integer> readList(BufferedReader reader) throws IOException {
        return Arrays.stream(reader.readLine().strip().split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }
}
