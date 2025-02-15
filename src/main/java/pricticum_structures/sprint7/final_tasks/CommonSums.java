package pricticum_structures.sprint7.final_tasks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;



/*
yandex contest: https://contest.yandex.ru/contest/25597/run-report/132096696/

Требовалось реализовать алгоритм поиска возможности деления суммы на две части, чтобы сумма в них была одинакова.

-- ПРИНЦИП РАБОТЫ АЛГОРИТМА --
1. В массиве dp[] будут храниться подсуммы [0, sum/2]. dp[i] - можем ли мы получить данную подсумму
2. Базовый случай для задачи dp[0] = true, потому что подсумму 0 мы всегда можем получить
3. Переход динамики dp[j] = dp[j] || dp[j-point]. Для каждого очка из массива проверим, реально ли получить подсумму
от [sum/2; point].
4. Порядок вычисления: вычисляем очере дное значение dp[j] опираясь на значение dp[j] ИЛИ dp[j-point], т.е. опираемся
на ранее посчитанные значения - динамическое программирование назад.
5. Ответ на задачу содержится в ячейке dp[sum/2], так как нам нужно вычислить, реально ли разбить сумму очков на 2 части так,
чтобы сумма в этих частях была одинакова.

-- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
Такой подход к решению является корректным, потому что будут проверены все подзадачи нахождения полусуммы. В итоге будет
массив boolean, где dp[sum/2] будет ответом на задачу.

-- ВРЕМЕННАЯ СЛОЖНОСТЬ --
N - размерность массива. M - полусумма массива. Временная сложность алгоритма: O (N * M)

-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
N - размерность массива. M - полусумма массива. Пространственная сложность алгоритма: O (N + M)
 */
public class CommonSums {
    public static void main(String[] args) throws IOException {
        int n;
        int[] points;
        int sum = 0;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            n = Integer.parseInt(reader.readLine());
            points = new int[n];
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            for (int i = 0; i < n; i++) {
                points[i] = Integer.parseInt(tokenizer.nextToken());
                sum += points[i];
            }
        }

        if (sum % 2 != 0) {
            System.out.println("False");
            return;
        }

        int target = sum / 2;
        boolean[] dp = new boolean[target + 1];
        dp[0] = true;
        for (int point : points) {
            for (int j = target; j >= point; j--) {
                dp[j] = dp[j] || dp[j - point];
            }
        }

        if (dp[target]) {
            System.out.println("True");
        } else {
            System.out.println("False");
        }
    }
}
