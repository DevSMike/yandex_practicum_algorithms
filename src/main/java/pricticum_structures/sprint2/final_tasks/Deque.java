package pricticum_structures.sprint2.final_tasks;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


/* yandex contest: https://contest.yandex.ru/contest/22781/run-report/122192671/

Задача состояла в том, чтобы реализовать Дек, в котором сложность каждой операции O(1).
Поскольку обращение к статическому массиву из всех структур данных самое быстрое,
а максимальный размер всегда задан --> используем кольцевой буфер на статическом массиве.

--ПРИНЦИП РАБОИТЫ АЛГОРИТМА--
Два указателя head и tail. head увеличивается "в голову" массива
(от середины влево). tail увеличивается "в хвост" массива (от середины вправо). Для того чтобы не было
выхода за границы массива увеличиваем указатели по модулю maxSize (tail = (tail + 1) % maxSize).
Если происходит выход за пределы массива с левой стороны (уход в отрицательный индекс), указателю
присваивается maxSize - 1, предыдущее состояние указателя.

--ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ--
НИ один из указателей не выйдет за границы массива (увеличиваются по модулю maxSize). Указатель head уменьшаем в
сторону начала списка, он всегда указывает на первый элемент. Указатель tail увеличиваем в сторону конца списка. Он всегда
указывает на последний элемент. Хвост указывает на последний элемент (они с head сначала указывают на один элемент),
поэтому при вставке сначала увеличиваем tail, потом вставляем (чтобы он всегда указывал на последний элемент, а не на
пустую ячейку).
Благодаря такому подходу можно удалять с конца ничего не вставляя в конец (так как tail всегда указывает на последний
элемент). Таким образом, все операции дека выполняются корректно.

--ВРЕМЕННАЯ СЛОЖНОСТЬ--
 * вставка в голову (двигаем индекс head ближе к голове, если переполняется, то в конец массива) - O(1)
 * вставка в хвост (происходит аналогично, но хвост переводим на начало очереди) - O(1)
 * функции pop_front / pop_back (получаем элемент по индексу) - O(1)

-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ--
 * O(N) - где N - это максимальный размер массива maxSize;
 */
public class Deque {

    int tail;
    int head;
    int[] values;
    int size;
    int maxSize;


    public Deque(int maxSize) {
        this.maxSize = maxSize;
        tail = maxSize / 2;
        head = maxSize / 2;
        values = new int[maxSize];
        size = 0;
    }

    private int checkNegativePointer(int pointer) {
        if (pointer < 0) {
            pointer = maxSize - 1;
        }
        return pointer;
    }

    public void pushFront(int value) {
        head = checkNegativePointer(head);
        values[head--] = value;
        size++;
    }

    public int popFront() {
        head = (head + 1) % maxSize;
        int result = values[head];
        size--;
        return result;
    }

    public int popBack() {
        tail = checkNegativePointer(tail);
        int result = values[tail--];
        size--;
        return result;
    }

    public void pushBack(int value) {
        tail = (tail + 1) % maxSize;
        values[tail] = value;
        size++;
    }

    public boolean isPop() {
        return size != 0;
    }

    public boolean isPush() {
        return size < maxSize;
    }

    public static void main(String[] args) throws IOException {
        StringBuilder buffer = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int maxCommands = Integer.parseInt(reader.readLine());
            int maxSize = Integer.parseInt(reader.readLine());
            Deque deque = new Deque(maxSize);

            for (int i = 0; i < maxCommands; i++) {
                StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
                chooseCommand(deque, tokenizer, buffer);
            }
        }

        System.out.println(buffer);
    }

    private static void chooseCommand(Deque deque, StringTokenizer tokenizer, StringBuilder buffer) {
        switch (tokenizer.nextToken()) {
            case "push_front": {
                if (deque.isPush()) {
                    deque.pushFront(Integer.parseInt(tokenizer.nextToken()));
                } else {
                    buffer.append("error").append("\n");
                }
                break;
            }
            case "push_back": {
                if (deque.isPush()) {
                    deque.pushBack(Integer.parseInt(tokenizer.nextToken()));
                } else {
                    buffer.append("error").append("\n");
                }
                break;
            }
            case "pop_back": {
                if (deque.isPop()) {
                    buffer.append(deque.popBack());
                } else {
                    buffer.append("error");
                }
                buffer.append("\n");
                break;
            }
            case "pop_front": {
                if (deque.isPop()) {
                    buffer.append(deque.popFront());
                } else {
                    buffer.append("error");
                }
                buffer.append("\n");
                break;
            }
        }
    }
}
