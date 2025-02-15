package pricticum_structures.sprint5.final_tasks;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;


/*
yandex contest: https://contest.yandex.ru/contest/24810/run-report/129610044/

Требовалось реализовать кучу, с ее помощью реализовать пиромидальную сортировку списка учеников.

-- ПРИНЦИП РАБОТЫ АЛГОРИТМА --
Создается куча с первым фиктивным элементом (чтобы формулы для left/right узлов и родительского узла
при просеивании кучи работали корректно), далее мы считываем студентов (класс Student имплементирует Comparable, чтобы
куча сортировалась по признакам, описанным в задаче). После того как студенты были добавлены из массива в кучу (после
добавления элемента происходит просеивание вверх, тк элемент добавляется в конец, потом находит свое место), происходит
извлечение из кучи в новый массив(с просеиванием вниз, тк. мы извлекаем всегда первый элемент из кучи, заменяем его последним, потом
пересобираем кучу). Новый массив получится отсортированным по призанам, описанным в задании.

-- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
Такой подход корректный, потому что на вершине кучи будет всегда элемент, обладающий наиболее высоким приоритетом.
Приоритет задается методом compareTo. Результирующий массив будет отсортирован по убыванию приоритета.

-- ВРЕМЕННАЯ СЛОЖНОСТЬ --
Итоговая временная сложность алгоритма рассчитывается таким образом:
N - количество студентов
- Вставка в кучу : O(log(N))
- Снятие с вершины кучи : O(log(N))
- Все это повторяется N раз --> O(N * log(N)) + O(N * log(N)) = O(N * log(N));

-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
N - количество студентов
- O(N) - куча
- O(N) - массив студентов
- O(N) - отсортированный массив студентов
=> O(3*N) = O(N).
 */
class Student implements Comparable<Student> {

    private final String name;
    private final long taskCount;
    private final long fine;

    public Student(String name, long taskCount, long fine) {
        this.name = name;
        this.taskCount = taskCount;
        this.fine = fine;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public int compareTo(Student o) {
        int taskResult = Long.compare(this.taskCount, o.taskCount);
        if (taskResult != 0) {
            return taskResult;
        }

        int fineResult = Long.compare(this.fine, o.fine);
        if (fineResult != 0) {
            return -1 * fineResult;
        }

        return -1 * this.name.compareTo(o.name);
    }
}


class Heap {

    private final List<Student> heap;

    public Heap() {
        heap = new ArrayList<>();
        heap.add(new Student(null, -1L, -1L));
    }

    public List<Student> heapSort(List<Student> students) {
        for (Student student : students) {
            addHeap(student);
        }

        List<Student> sortedStudents = new ArrayList<>();
        while (heap.size() > 1) {
            Student max = popMax();
            sortedStudents.add(max);
        }

        return sortedStudents;
    }

    private void addHeap(Student student) {
        heap.add(student);
        shiftUp(heap.size() - 1);
    }

    private Student popMax() {
        Student result = heap.get(1);
        heap.set(1, heap.get(heap.size() - 1));
        heap.remove(heap.size() - 1);
        shiftDown(1);
        return result;
    }

    private void shiftUp(int index) {
        if (index <= 1) {
            return;
        }

        int parentIndex = index / 2;
        if (heap.get(parentIndex).compareTo(heap.get(index)) < 0) {
            Collections.swap(heap, parentIndex, index);
            shiftUp(parentIndex);
        }
    }

    private void shiftDown(int index) {
        int leftIndex = index * 2;
        int rightIndex = index * 2 + 1;

        if (leftIndex >= heap.size()) {
            return;
        }

        int largestIndex = leftIndex;
        if (rightIndex < heap.size() && heap.get(rightIndex).compareTo(heap.get(leftIndex)) > 0) {
            largestIndex = rightIndex;
        }

        if (heap.get(largestIndex).compareTo(heap.get(index)) > 0) {
            Collections.swap(heap, largestIndex, index);
            shiftDown(largestIndex);
        }
    }


}

public class HeapSort {

    public static void main(String[] args) throws IOException {
        Heap heap = new Heap();
        StringBuilder buffer = new StringBuilder();
        List<Student> students = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int n = Integer.parseInt(reader.readLine());
            for (int i = 0; i < n; i++) {
                StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
                Student student = new Student(tokenizer.nextToken(), Long.parseLong(tokenizer.nextToken()), Long.parseLong(tokenizer.nextToken()));
                students.add(student);
            }
        }
        heap.heapSort(students).forEach(x -> buffer.append(x.getName()).append("\n"));
        System.out.println(buffer);
    }

}
