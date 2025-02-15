package pricticum_structures.sprint8;

import java.util.Scanner;

public class CalculatePrefixFunction {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        int n = str.length();
        scanner.close();

        int[] pi = new int[n];
        pi[0] = 0;

        for (int i = 1; i < n; i++) {
            int k = pi[i-1];
            while (k > 0 && str.charAt(k) != str.charAt(i)) {
                k = pi[k-1];
            }

            if (str.charAt(k) == str.charAt(i)) {
                k++;
            }
            pi[i] = k;
        }

        StringBuilder builder = new StringBuilder();
        for (int elem : pi) {
            builder.append(elem).append(" ");
        }

        System.out.println(builder);
    }
}
/*
abracadabra

 */