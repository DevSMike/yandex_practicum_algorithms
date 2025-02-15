package pricticum_structures.sprint3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class HouseBuying {

    public static void main(String[] args) throws IOException {

        int n;
        int k;
        List<Integer> prices;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            n = Integer.parseInt(tokenizer.nextToken());
            k = Integer.parseInt(tokenizer.nextToken());
            prices = Arrays.stream(reader.readLine().strip().split(" "))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
        }

        List<Integer> sortedPrices = mergeSort(prices);
        int counter = 0;

        for (int price: sortedPrices) {
            if (k >= price) {
                counter++;
                k -= price;
            }
        }
        System.out.println(counter);
    }


    private static List<Integer> mergeSort(List<Integer> arr) {
        if (arr.size() == 1) {
            return arr;
        }

        List<Integer> leftArr = mergeSort(new ArrayList<>(arr.subList(0, arr.size() / 2)));
        List<Integer> rightArr = mergeSort(new ArrayList<>(arr.subList(arr.size() / 2, arr.size())));
        List<Integer> sortedArr = new ArrayList<>(arr.size());

        int left = 0;
        int right = 0;
       // int k = 0;

        while (left < leftArr.size() && right < rightArr.size()) {
            if (leftArr.get(left) <= rightArr.get(right)) {
                sortedArr.add(leftArr.get(left++));
            } else {
                sortedArr.add(rightArr.get(right++));
            }
        }

        while (left < leftArr.size()) {
            sortedArr.add(leftArr.get(left++));
        }

        while (right < rightArr.size()) {
            sortedArr.add(rightArr.get(right++));
        }
        return sortedArr;
    }
}
