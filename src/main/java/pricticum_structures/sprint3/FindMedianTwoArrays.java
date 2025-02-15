package pricticum_structures.sprint3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FindMedianTwoArrays {

    public static void main(String[] args) throws IOException {
        int m;
        int n;
        List<Integer> arrM;
        List<Integer> arrN;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            n = Integer.parseInt(reader.readLine());
            m = Integer.parseInt(reader.readLine());
            arrN = readList(reader);
            arrM = readList(reader);
        }

        double k = Math.ceil((n+m)/ 2.0);
        double result = findK((int) k, n, arrN, m, arrM);
        System.out.println(result);

    }

    // n - про большие
    // m - маленькие
    private static double findK(int k, int n, List<Integer> arrN, int m, List<Integer> arrM) {
        if (m > n) {
            return findK(k, m, arrM, n, arrN);
        }

        if (arrM.isEmpty()) {
            return arrN.get(k - 1);
        }

        if (k == 1) {
            return Math.max(arrM.get(arrM.size() - m), arrN.get(arrN.size() - n));
        }

        int i = Math.min(n, k / 2);
        int j = Math.min(m, k / 2);

        if (arrN.get(i - 1) > arrM.get(j - 1)) {
            return findK(k - j, n, arrN, m - j, arrM);
        } else {
            return findK(k - i, n - i, arrN, m, arrM);
        }
    }


    private static List<Integer> readList(BufferedReader reader) throws IOException {
        return Arrays.stream(reader.readLine().strip().split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }
}
