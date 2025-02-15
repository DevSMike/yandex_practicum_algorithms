package pricticum_structures.sprint7.final_tasks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


/*
yandex contest: https://contest.yandex.ru/contest/25597/run-report/132246361/

Требовалось реализовать алгоритм Левенштейна - поиска минимального количества операций для преобразования
одной строки в другую

-- ПРИНЦИП РАБОТЫ АЛГОРИТМА --
1. В массиве dp[] будет хранится количество операций, которое потребуется для преобразования строки длины n [0;n] в строку
от [0;m].
2. Базовый случай для задачи dp[0] = префикс большей строки.
3. Переход динамики dp[j] = min(dp[j- 1] +1, dp[j] + 1, prev + cost). cost = 1 || 0 в зависимости от надобности замены символа
4. Порядок вычисления: вычисляем значение dp[j], базируясь на предыдущих значениях prev (имитируем предыдщие значение изм матрицы)
или уже рассчитанные ранее значения в массиве dp.
5. Ответ на задачу содержится в ячейке dp[m].

-- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
Количество изменений будет рассчитываться в реальном времени, базируясь только на prev значении dp[j]. Для длины стороки
от 1 до n будет рассчитано количество изменений для строк длины m. Мы вседа храним как бы последнюю строку в матрице.
Поэтому ответ будет находится в последней ячейке массива dp[m].

-- ВРЕМЕННАЯ СЛОЖНОСТЬ --
Алгоритм совершит обход по всей матрице => Временная сложность = O(N * M)

-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
Алгоритм хранит в памяти только последнюю строку матрицы, в которой содержится длина наименьшей строки => O(M).
 */
public class Levenshtein {

    public static void main(String[] args) throws IOException {
        String str1;
        String str2;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            str1 = reader.readLine();
            str2 = reader.readLine();
        }
        int result = levenshtein(str1, str2);
        System.out.println(result);
    }

    private static int levenshtein(String str1, String str2) {
        int n = str1.length();
        int m = str2.length();

        if (n > m) {
            String temp = str1;
            str1 = str2;
            str2 = temp;
            n = str1.length();
            m = str2.length();
        }

        int[] dp = new int[m + 1];

        for (int j = 0; j <= m; j++) {
            dp[j] = j;
        }

        for (int i = 1; i <= n; i++) {
            int prev = dp[0];
            dp[0] = i; // пустая строка = префиксу str1

            for (int j = 1; j <= m; j++) {
                int temp = dp[j];
                int cost = str1.charAt(i - 1) == str2.charAt(j - 1) ? 0 : 1;
                dp[j] = Math.min(Math.min(dp[j - 1] + 1, dp[j] + 1), prev + cost);
                prev = temp;
            }
        }
        return dp[m];
    }
}
