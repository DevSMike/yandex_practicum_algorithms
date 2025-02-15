package pricticum_structures.sprint6.final_tasks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


/*
yandex contest: https://contest.yandex.ru/contest/25070/run-report/131414357/

Требовалось реализовать поиск всех остравов

-- ПРИНЦИП РАБОТЫ АЛГОРИТМА --
Осуществляется обход матрицы, если элемент равен началу острова (#) и еще не был посещен, то мы увеличиваем счетчик остравов,
запускаем поиск в глубину от текущих координат. Результат этого поиска - длина острова. Сравниваем ее с максимальной,
при необходимости обновляем.

-- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
Такой подход к решению явяляется корректным, потому что будут просмотрены все точки начала островов. Если часть острова была
посещена ранее, то алгоримт ее пропустит => количество остравов считается корректно. Всевозможные перемещения заданы в
массиве DIRECTIONS, т.е. мы сможем посетить только те точки, которые прпоисаны в условии. Если координата выходит за границы
матрицы, то она не будет рассмотрена => длина острова также рассчитывается корректно.

-- ВРЕМЕННАЯ СЛОЖНОСТЬ --
n = rows, m - columns;
dfs() запускается только для уникальных клеток, если клетка обработалась, то второй раз она не будет обрабатываться.
dfs() в худшем случае (вся матрица - остров) отработает 1 раз за O(n * m) и больше не выполнится.
Цикл с проходом по матрице работает за O(n * m).
Так как dfs() выполняется только для непосещенных клеток, т.е. каждая клетка обрабатывается только 1 раз за весь алгоритм,
== > итоговая временная сложность будет O(n * m).

-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
n = rows, m - columns;
Мы храним матрицу - эо O(n * m) памяти
В dfs() используется доп память в виде стека. В худшем случае стек будет хранить всю матрицу - O(n * m), но в таком случае
это будет выполнено на одной итерации, а не каждой итерации цикла
== > итоговая пространственная сложность O(n * m).
 */

public class WaterWorld {
    private static final int[] DIRECTIONS = {-1, 0, 1, 0, -1};
    private static final Character SHARP = '#';
    private static final Character DOT = '.';

    public static void main(String[] args) throws IOException {
        int rows;
        int cols;
        char[][] matrix;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            rows = Integer.parseInt(tokenizer.nextToken());
            cols = Integer.parseInt(tokenizer.nextToken());

            matrix = new char[rows][cols];

            for (int i = 0; i < rows; i++) {
                String row = reader.readLine();
                for (int j = 0; j < cols; j++) {
                    matrix[i][j] = row.charAt(j);
                }
            }
        }

        int maxLen = 0;
        int count = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] == SHARP) {
                    count++;
                    int size = dfs(i, j, matrix, rows, cols);
                    maxLen = Math.max(maxLen, size);
                }
            }
        }

        System.out.print(count + " " + maxLen);
    }

    private static int dfs(int x, int y, char[][] matrix, int rows, int cols) {
        Stack<int[]> stack = new Stack<>();
        stack.push(new int[]{x, y});
        matrix[x][y] = DOT;
        int size = 0;

        while (!stack.empty()) {
            int[] current = stack.pop();
            size++;

            for (int i = 0; i < DIRECTIONS.length - 1; i++) {
                int newX = current[0] + DIRECTIONS[i];
                int newY = current[1] + DIRECTIONS[i + 1];
                if (newX >= 0 && newX < rows && newY >= 0 && newY < cols && matrix[newX][newY] == SHARP) {
                    matrix[newX][newY] = DOT;
                    stack.push(new int[]{newX, newY});
                }
            }
        }
        return size;
    }
}
