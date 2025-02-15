package pricticum_structures.sprint5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;


// решение формулой Каталана
/*
 (2n)! / ( (n+1)! * n!)) --> количество бинарных деревьев
 */
public class DifferentBinaryTree {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        reader.close();
        System.out.println(countBinarySearchTrees(n));
    }


    private static BigInteger countBinarySearchTrees(int n) {
        BigInteger divider = factorial(n + 1).multiply(factorial(n));
        BigInteger numerator = factorial(2 * n);
        return numerator.divide(divider);
    }


    private static BigInteger factorial(int num) {
        BigInteger result = BigInteger.ONE;
        for (int i = 2; i <= num; i++) {
            result = result.multiply(BigInteger.valueOf(i));
        }
        return result;
    }

    @Deprecated
    //код генерации всех возможных последовательностей из n чисел
    private static void generate(int n, List<Integer> current, List<List<Integer>> list, boolean[] used) {
        if (current.size() == n) {
            list.add(new ArrayList<>(current));
            return;
        }

        for (int i = 1; i <= n; i++) {
            if (!used[i]) {
                used[i] = true;
                current.add(i);
                generate(n, current, list, used);
                current.remove(current.size() - 1);
                used[i] = false;
            }
        }
    }

}
