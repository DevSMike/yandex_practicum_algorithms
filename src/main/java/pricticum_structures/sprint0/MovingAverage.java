package pricticum_structures.sprint0;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/*
Вам дана статистика по числу запросов в секунду к вашему любимому рекомендательному сервису.

Измерения велись n секунд.

В секунду i поступает qi запросов.

Примените метод скользящего среднего с длиной окна k к этим данным и выведите результат.
 */
public class MovingAverage {
    public static void main(String[] args) throws IOException {
        List<Integer> arr;
        List<Double> resultArr = new ArrayList<>();
        int n;
        int k;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            n = Integer.parseInt(reader.readLine().strip());
            arr = Arrays.stream(reader.readLine().strip().split(" "))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
            k = Integer.parseInt(reader.readLine().strip());
        }

        double currentSum = getSumById(k, arr);
        resultArr.add(currentSum / k);

        for (int i = 0; i < n - k; i++) {
            currentSum -= arr.get(i);
            currentSum += arr.get(k + i);
            resultArr.add(currentSum / k);
        }

        resultArr.forEach(x -> System.out.print(x + " "));
    }

    public static int getSumById(int k, List<Integer> arr) {
        int sum = 0;
        for (int i = 0; i < k; i++) {
            sum += arr.get(i);
        }
        return sum;
    }
}
