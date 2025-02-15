package pricticum_structures.sprint8.final_tasks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/*
yandex contest: https://contest.yandex.ru/contest/26133/run-report/133431008/

Требовалось реализовать алгоритм распаковки строк; и поиска общего префикса для полученных строк.

-- ПРИНЦИП РАБОТЫ АЛГОРИТМА --
Считываем строки и производим их распаковку. Распаковка происходит при помощи стека, имитируя работу рекурсии.
Считываем текущий символ, если это число, то запоминаем число (если оно будет больше 10, то в int преобразуем в это число
(может содержать несколько символов). Если символ - открывающая скобка, то сохраняем текущую версию строки и число повторов
в стеке. После этого добавляем символы в результирующую строку. Как только встречаем закрывающую скобку, кэшируем текующее
состояние строки, получаем из стека прошлую версию строки, добавляем в прошлую версию строки закешированный результат
число раз, которое было в стеке. После этого мы получаем распакованную строку.

Потом ищем для массива строк общий префикс. Для каждой строки проверяем, содержится ли в ней префикс. Если нет, сужаем префикс.

-- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
Такой подход является корректным, потому что сначала собирается массив распакованных строк, а уже потом для них ищется
общий префикс.

-- ВРЕМЕННАЯ СЛОЖНОСТЬ --
N - размерность самой длинной строки. M - количество строк.
Считывание и распаковка происходит за O(M * N).
P - длина наименьшей строки. Поиск общего префикса происходит за O(M * P).
Суммарная сложность: O(M * N + M * P)

-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
O(N) - размернось самой длинной строки в методе unpack(), эту память занимает стек.
O(N * M) - массив строк
=> O(M*N + N)
 */

public class PackedPrefix {
    private static String unpack(String packed) {
        StringBuilder result = new StringBuilder();
        int count = 0;
        Stack<String> stack = new Stack<>();

        for (int i = 0; i < packed.length(); i++) {
            char curChar = packed.charAt(i);

            if (Character.isDigit(curChar)) {
                count = count * 10 + curChar - '0';
            } else if (curChar == '[') {
                stack.push(result.toString());
                stack.push(String.valueOf(count));
                result = new StringBuilder();
                count = 0;
            } else if (curChar == ']') {
                String tempStr = result.toString();
                int repeatTimes = Integer.parseInt(stack.pop());
                String prevStr = stack.pop();
                StringBuilder finalResult = new StringBuilder(prevStr);

                for (int j = 0; j < repeatTimes; j++) {
                    finalResult.append(tempStr);
                }
                result = finalResult;
            } else {
                result.append(curChar);
            }
        }

        return result.toString();
    }

    private static String commonPrefix(String[] strings) {
        String prefix = strings[0];

        for (int i = 1; i < strings.length; i++) {
            if (strings[i].length() < prefix.length()) {
                prefix = strings[i];
            }
        }

        int end = prefix.length();

        for (int i = 1; i < strings.length; i++) {
           for (int j =0; j < end; j++) {
               if (strings[i].charAt(j) != prefix.charAt(j)) {
                   end = j;
                   break;
               }
           }
        }
        return prefix.substring(0, end);
    }

    public static void main(String[] args) throws IOException {
        int n;
        String[] strings;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            n = Integer.parseInt(reader.readLine());
            strings = new String[n];

            for (int i = 0; i < n; i++) {
                strings[i] = unpack(reader.readLine());
            }
        }
        System.out.println(commonPrefix(strings));
    }
}

