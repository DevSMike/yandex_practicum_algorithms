package pricticum_structures.sprint3.final_tasks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.util.Collections.swap;


/*

yandex contest: https://contest.yandex.ru/contest/23815/run-report/124007777/

Стояла задача реализовать сортировку за O(n logn) без использования доп. памяти

-- ПРИНЦИП РАБОТЫ АЛГОРИТМА --
Есть два указателя: from (начало интервала) и to (конец интервала). Будем рекурсивно запускать quickSort
для интервала до pivot и после pivot. Для этого будем искать непосредственно элемент, до/после которого
будем производить сортировку. Для этого реализуем метод partition(), который берет pivot (элемент с сере-
дины интервала), мы двигаем левый указатель до тех пор, пока элемент слева меньше pivot, аналогично с
правым указателем, когда элемент справа больше pivot.  Когда условие двух циклов перестает выполнятся,
будем производить swap (right, left). Вернем элемент, для которого не будет найдена пара в swap.
По этому элементу делим массив на части, сортируем рекурсивно эти части.


-- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
Сортировка элементов выполняется с помощью Comparator. Таким образом, элементы сортируются корректно.
Требование по экономии дополнительной памяти также выполняется. На каждом шаге рекурсии будет использовано
O(N) памяти.

-- ВРЕМЕННАЯ СЛОЖНОСТЬ --
Временная сложность алгоритма:
в среднем/лучшем случае: O(n*log(N)), где n - количество элементов, log(n)  глубина рекурсии.
в худшем случае: по идее тоже O(n * log(n)), потому что pivot - средний элемент отрезка, помогает
избежать худшего случая, когда массив отсортирован.

-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
Функция на каждом шаге рекурсии работает с чатями массива, которые в данный момент сортируются.
Потребление памяти - O(N), где N - элементы массива * O(1) - константы на указатели (left/ right)
--> ИТОГОВАЯ пространственная сложность : O(N).
 */
class Student {
    private final int tasks;
    private final int fine;
    private final String login;

    public Student(String login, int tasks, int fine) {
        this.login = login;
        this.tasks = tasks;
        this.fine = fine;
    }

    public String getLogin() {
        return login;
    }

    public int getFine() {
        return fine;
    }

    public int getTasks() {
        return tasks;
    }
}

public class InPlaceQuickSort {
    private static final Comparator<Student> comparator = (a, b) -> {
        int madeTasks = Integer.compare(a.getTasks(), b.getTasks());
        if (madeTasks != 0) {
            return -1 * madeTasks; //sort desc
        }
        int fine = Integer.compare(a.getFine(), b.getFine());
        if (fine != 0) {
            return fine; //sort desc
        }
        return a.getLogin().compareTo(b.getLogin());
    };

    public static void main(String[] args) throws IOException {
        int n;
        List<Student> data = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            n = Integer.parseInt(reader.readLine());
            for (int i = 0; i < n; i++) {
                StringTokenizer tokenizer = new StringTokenizer(reader.readLine().strip());
                Student student = new Student(tokenizer.nextToken(), Integer.parseInt(tokenizer.nextToken()),
                        Integer.parseInt(tokenizer.nextToken()));
                data.add(student);
            }
        }
        quickSort(data, 0, data.size() - 1);
        StringBuilder buffer = new StringBuilder();
        data.forEach(x -> buffer.append(x.getLogin()).append("\n"));
        System.out.println(buffer);
    }


    private static int partition(List<Student> arr, int left, int right) {
        Student pivot = arr.get((left + right) / 2);
        while (left <= right) {
            while (comparator.compare(arr.get(left), pivot) < 0) {
                left++;
            }
            while (comparator.compare(arr.get(right), pivot) > 0) {
                right--;
            }
            if (left <= right) {
                swap(arr, left, right);
                left++;
                right--;
            }
        }

        return left;
    }

    private static void quickSort(List<Student> arr, int from, int to) {
        if (from < to) {
            int divideIdx = partition(arr, from, to);
            quickSort(arr, from, divideIdx - 1);
            quickSort(arr, divideIdx, to);
        }
    }
}
