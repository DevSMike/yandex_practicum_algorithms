package pricticum_structures.sprint2.final_tasks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
yandex contest: https://contest.yandex.ru/contest/22781/run-report/122197831/

Задача состояла в том, чтобы распарсить строку польской нотации, произвести операции над числами,
которые в этой строке указаны.

--ПРИНЦИП РАБОТЫ АЛГОРИТМА--
 Создаем множество математических операций, проходимся по массиву строк: если строка число,
то записываем его в стек, а если операция (элемент массива входит во множество),
то парсим математическую операцию.
(Важно: деление выполняем с помощью Math.floor(), округляет результат до ближайшего
минимального целого числа).

--ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ--
 Когда встречается число, оно будет записано в стек. Когда будет встречена операция, то над двумя последними
 операндами будет проведена эта операция, а результат сохранится на вершину стека. Данный результат операции будет
 использован, как операнд в следующей операции.
 Деление выполняется с помощью Math.floor(), будет округлено до меньшего целого (является одним из требований задачи).
 Таким образом, строка корректно парсится, а результат корректно обрабатывается.

 --ВРЕМЕННАЯ СЛОЖНОСТЬ--
 * Математические операции всегда выполняем для двух операндов
(поиск операндов O(1) - два раза stack.pop())
 * Результат операции кладется на вершину стека (станет операндом для следующей операции)
 сложность сохранения результата - O(1).
 Итоговая сложность алгоритма - O(n), где N - это количество элементов в распаршенной строке (количество чисел +
 операций).

 --ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ--
 Создается стек размером O(M) - где M - это количество чисел в строке.
 */
public class Calculator {

    public static void main(String[] args) throws IOException {
        Stack<Integer> stack = new Stack<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            int n = tokenizer.countTokens();
            for (int i = 0; i < n; i++) {
                String letter = tokenizer.nextToken();
                if (isStringCanBeParsed(letter)) {
                    stack.push(Integer.parseInt(letter));
                } else {
                    calculate(stack, letter);
                }
            }
        }
        System.out.println(stack.pop());
    }

    private static boolean isStringCanBeParsed(String string) {
        if (string.charAt(0) == '-' && string.length() > 1) {
            return Character.isDigit(string.charAt(1));
        } else {
            return Character.isDigit(string.charAt(0));
        }
    }

    private static void calculate(Stack<Integer> stack, String operation) {
        int result = stack.pop();

        switch (operation) {
            case "+": {
                result += stack.pop();
                break;
            }
            case "-": {
                result = stack.pop() - result;
                break;
            }
            case "/": {
                double operand = stack.pop();
                result = (int) Math.floor(operand / result);
                break;
            }
            case "*": {
                result *= stack.pop();
                break;
            }
        }
        stack.push(result);
    }
}
