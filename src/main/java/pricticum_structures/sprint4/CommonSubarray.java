package pricticum_structures.sprint4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class CommonSubarray {

    private static final long P = 345L;
    private static final long M = 5608713984039443L;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        List<Integer> arr1 = readList(reader, n);
        int m = Integer.parseInt(reader.readLine());
        List<Integer> arr2 = readList(reader, m);
        reader.close();
        if (n > m) {
            System.out.println(maxLenSubArray(arr1, arr2, m));
        } else {
            System.out.println(maxLenSubArray(arr2, arr1, n));
        }
    }

    private static int maxLenSubArray(List<Integer> maxArr, List<Integer> minArr, int len) {
        int left = 0, right = len;
        int maxLen = 0;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (isSubArrayWithLenExists(maxArr, minArr, mid)) {
                maxLen = Math.max(maxLen, mid);
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return maxLen;
    }

    private static Set<Long> hashArray(List<Integer> arr, int len) {
        long hash = 0;
        long pPow = 1;
        Set<Long> hashes = new HashSet<>();

        for (int i = 0; i < len; i++) {
            hash = (hash * P + arr.get(i)) % M;
            pPow = (pPow * P) % M;
        }

        hashes.add(hash);

        for (int i = len; i < arr.size(); i++) {
            hash = (hash * P + arr.get(i) - arr.get(i - len) * pPow % M + M) % M; //
            hashes.add(hash);
        }

        return hashes;
    }

    private static boolean isSubArrayWithLenExists(List<Integer> maxArr, List<Integer> minArr, int len) {
        Set<Long> min = hashArray(minArr, len);
        Set<Long> max = hashArray(maxArr, len);

        return min.stream().anyMatch(max::contains);
   }

    private static List<Integer> readList(BufferedReader reader, int size) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        List<Integer> array = new ArrayList<>();
        while (size-- > 0) {
            array.add(Integer.parseInt(tokenizer.nextToken()));
        }
        return array;
    }
}


