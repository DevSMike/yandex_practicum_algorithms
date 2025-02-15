package pricticum_structures.sprint0;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

/*
Рита и Гоша играют в игру.
У Риты есть n фишек, на каждой из которых написано количество очков.
Фишки лежат на столе в порядке неубывания очков на них.
Сначала Гоша называет число k, затем Рита должна выбрать две фишки,
сумма очков на которых равна заданному числу.

Рите надоело искать фишки самой,
и она решила применить свои навыки программирования для решения этой задачи.
Помогите ей написать программу для поиска нужных фишек.
 */
public class TwoTokensOptimized {

    public static void main(String[] args) throws IOException {

        int n;
        List<Integer> arr;
        int k;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            n = Integer.parseInt(reader.readLine().strip());
            arr = Arrays.stream(reader.readLine().strip().split(" "))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
            k = Integer.parseInt(reader.readLine().strip());
        }

        solutionWithSet(arr, k);
        solutionWithSort(arr, k, n);
    }

    public static void solutionWithSet(List<Integer> arr, int k) {
        Set<Integer> set = new HashSet<>();
        for (int elem : arr) {
            int currentElem = k - elem;
            if (set.contains(currentElem)) {
                System.out.println(elem + " " + currentElem);
                return;
            } else {
                set.add(elem);
            }
        }
        System.out.println("None");
    }

    public static void solutionWithSort(List<Integer> arr, int k, int n) {
        Collections.sort(arr);
        int right = n - 1;
        int left = 0;

        while (left < right) {
            if (arr.get(left) + arr.get(right) > k) {
                --right;
            } else if (arr.get(left) + arr.get(right) < k) {
                ++left;
            } else {
                System.out.println(arr.get(left) + " " + arr.get(right));
                return;
            }
        }
        System.out.println("None");
    }
}
