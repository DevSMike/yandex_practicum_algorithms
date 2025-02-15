package pricticum_structures.sprint3;

import java.io.IOException;
import java.util.Scanner;

public class GenBuckets {

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        generateBucketsRecursion(n, 0, 0, "");
    }

    private static void generateBucketsRecursion(int max, int open, int close, String sequence) {
        if (2 * max == sequence.length()) {
            System.out.println(sequence);
            return;
        }

        if (open < max) {
            generateBucketsRecursion(max, open + 1, close, sequence + "(");
        }

        if (close < open) {
            generateBucketsRecursion(max, open, close + 1, sequence + ")");
        }
    }
}
