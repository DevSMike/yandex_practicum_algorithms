package pricticum_structures.sprint3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/*
Находим бинарным поиском число, если его нет по возможности ближайшее к нему.
 */
public class TwoBikes {


    public static void main(String[] args) throws IOException {
        List<Integer> money;
        int daysCount;
        int bikePrice;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            daysCount = Integer.parseInt(reader.readLine());
            money = Arrays.stream(reader.readLine().strip().split(" "))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
            bikePrice = Integer.parseInt(reader.readLine());
        }

        StringBuilder result = new StringBuilder();
        result.append(indexBinarySearch(money, bikePrice))
                .append(" ")
                .append(indexBinarySearch(money, bikePrice * 2));
        System.out.println(result);
    }


    private static int indexBinarySearch(List<Integer> arr, int x) {
        int[] diffInfo = {1_000_000, -2};
        return binarySearchRecursive(arr, x, 0, arr.size() - 1, diffInfo, false);
    }

    private static int binarySearchRecursive(List<Integer> arr, int x, int left, int right, int[] difInfo, boolean quit) {
        if (quit) {
            if (difInfo[0] < 0 ) return -1;
            return difInfo[1] + 1;
        }
        int mid = (right + left) / 2;

        if (difInfo[0] >= Math.abs(arr.get(mid) - x) && arr.get(mid) >= x) {
            difInfo[1] = mid;
            difInfo[0] = arr.get(mid) - x;
        }

        boolean currentQuit = left == right;

        if (x > arr.get(mid)) {
            return binarySearchRecursive(arr, x, mid + 1, right, difInfo, currentQuit);
        } else {
            return binarySearchRecursive(arr, x, left, mid, difInfo, currentQuit);
        }
    }
}
