package pricticum_structures.sprint0;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
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
public class TwoTokens {

    public static void main(String[] args) throws IOException {
        int n;
        List<Integer> arr;
        int k;
        boolean isFound = false;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            n = Integer.parseInt(reader.readLine().strip());
            arr = Arrays.stream(reader.readLine().strip().split(" "))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
            k = Integer.parseInt(reader.readLine().strip());
        }

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (arr.get(i) + arr.get(j) == k) {
                    System.out.println(arr.get(i) + " " + arr.get(j));
                    isFound = true;
                    return;
                }
            }
        }
        if (!isFound) {
            System.out.println("None");
        }
    }

}
