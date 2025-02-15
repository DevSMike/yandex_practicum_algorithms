package pricticum_structures.sprint3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Collections.swap;

public class PerimeterOfTriangle {

    public static void main(String[] args) throws IOException {
        int n;
        List<Integer> arr;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            n = Integer.parseInt(reader.readLine());
            arr = Arrays.stream(reader.readLine().strip().split(" "))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
        }

        quickSort(arr, 0, arr.size() - 1);
        int perimeter = 0;

        for (int i = 0; i < n - 2; i++) {
            if (arr.get(i) < arr.get(i + 1) + arr.get(i + 2)) {
                perimeter = arr.get(i) + arr.get(i + 1) + arr.get(i + 2);
                break;
            }
        }

        System.out.println(perimeter);
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
            while (arr.get(left) > pivot) {
                left++;
            }

            while (arr.get(right) < pivot) {
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
}
