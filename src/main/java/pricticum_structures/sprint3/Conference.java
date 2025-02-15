package pricticum_structures.sprint3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Collections.swap;

public class Conference {

    public static void main(String[] args) throws IOException {
        int n;
        List<Integer> idList;
        int k;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            n = Integer.parseInt(reader.readLine().strip());
            idList = Arrays.stream(reader.readLine().strip().split(" "))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
            k = Integer.parseInt(reader.readLine().strip());
        }

        //O(1) memory
        int[] countArr = new int[10_001];
        boolean[] isUnique = new boolean[10_001];
        for (int i = 0; i < idList.size(); i++) {
            int elem = idList.get(i);
            countArr[elem]++;
            if (!isUnique[elem]) {
                isUnique[elem] = true;
            } else {
                idList.remove(i--);
            }
        }

        Comparator<Integer> comparator = (a, b) -> {
            int idCount = Integer.compare(countArr[a], countArr[b]);
            if (idCount != 0) {
                return -idCount;
            }
            return Integer.compare(a, b);
        };

        quickSort(idList, 0, idList.size() - 1, comparator);
        StringBuilder buffer = new StringBuilder();
        idList.stream()
                .limit(k)
                .forEach(x -> buffer.append(x).append(" "));
        System.out.println(buffer);
    }

    private static void quickSort(List<Integer> arr, int from, int to, Comparator<Integer> comparator) {
        if (from < to) {
            int divide = partition(arr, from, to, comparator);
            quickSort(arr, from, divide - 1, comparator);
            quickSort(arr, divide, to, comparator);
        }
    }

    private static int partition(List<Integer> arr, int left, int right, Comparator<Integer> comparator) {
        int pivot = arr.get((left + right) / 2);
        while (left <= right) {
            while (comparator.compare(arr.get(left), pivot) < 0) {
                left++;
            }

            while (comparator.compare(arr.get(right), pivot) > 0) {
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
