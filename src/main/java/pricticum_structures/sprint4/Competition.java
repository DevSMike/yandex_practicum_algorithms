package pricticum_structures.sprint4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

//Алгоритм префиксных сумм на мапах
public class Competition {

    public static void main(String[] args) throws IOException {
        int n;
        List<Integer> arr;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            n = Integer.parseInt(reader.readLine().strip());
            if (n == 0) {
                System.out.println("0");
                return;
            }
            arr = Arrays.stream(reader.readLine().strip().split(" "))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
        }

        Map<Integer, Integer> prefixMap = new HashMap<>();
        prefixMap.put(0, -1);

        int currentSum = 0;
        int maxLen = 0;
        for (int i = 0; i < n; i++) {
            currentSum += arr.get(i) == 0 ? -1 : arr.get(i);
            if (prefixMap.containsKey(currentSum)) {
                int prevIdx = prefixMap.get(currentSum);
                maxLen = Math.max(maxLen, i - prevIdx);
            } else {
                prefixMap.put(currentSum, i);
            }
        }

        System.out.println(maxLen);
    }

}
