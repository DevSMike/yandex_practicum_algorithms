package pricticum_structures.sprint4.final_tasks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/*

yandex contest: https://contest.yandex.ru/contest/24414/run-report/125718818/

Стояла задача реализовать хеш таблицу, с метода get(), put(), delete(). Все методы должны выполняться
за O(1) по времени

-- ПРИНЦИП РАБОТЫ АЛГОРИТМА --
Создается хеш-таблица размеом bucketSize. BucketSize - ближайшее простое число БОЛЬШЕЕ, чем n. Так как
bucketSize - простое число, коллизий будет меньше.
Хеш-таблица собой представляет массив LinkedList'ов, в которых хранится Node. Node представляет собой
пару ключ и значение.
Если происходит коллизия, то вставляем в голову списка новую ноду.
При поиске элемента итерируемся по листу.

-- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
Такой подход выбора index (значение id по модулю bucketSize) гарантирует минимально-возможное число
коллизий. Даже если коллизия случается, элемент вставляется в голову списка за O(1). Так как хеш-функция
равномерная, то сложность поиска элемента также приблизительно O(1) => условия задачи выполняются.

-- ВРЕМЕННАЯ СЛОЖНОСТЬ --
* Вставка элемента в таблицу: O(1) - вставка в начало списка;
* Поиск элемента (get/delete/put-обновляем значение) - примерно за O(1). Приблизительно
O(1), если производим поиск элемента (итерируемся по списку или берем 1 элемент из ячейки
т.к. хеш-функция равномерная).

-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
Массив содержит bucketSize ячеек -> N;
Каждая ячейка содержит LinkedList размером случившихся коллизий -> M;
Итоговая пространственная сложность: O(N * M).
 */
class HashTable {

    static class Node {
        public int key;
        public int value;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private static int nearestPrime(int n) {
        int limit = n + 100;
        List<Integer> primes = new ArrayList<>();
        int[] lp = new int[limit + 1];
        for (int i = 2; i <= limit; i++) {
            if (lp[i] == 0) {
                lp[i] = i;
                primes.add(i);
                if (i > n) {
                    return i;
                }
            }

            for (int prime : primes) {
                int x = prime * i;
                if (prime > lp[i] || x > limit) break;
                lp[x] = prime;
            }
        }
        return primes.get(primes.size() - 1);
    }

    private int getIndexByKey(int key) {
        return (key % bucketsSize + bucketsSize) % bucketsSize;
    }

    private final LinkedList<Node>[] elements;
    private final int bucketsSize;

    public HashTable(int size) {
        //bucketsSize = nearestPrime(size);
        bucketsSize = size < 10 ? 1 : size / 10;
        elements = new LinkedList[bucketsSize];
        for (int i = 0; i < bucketsSize; i++) {
            elements[i] = new LinkedList<>();
        }
    }

    public void put(int key, int value) {
        int index = getIndexByKey(key);
        Node newNode = new Node(key, value);
        boolean updated = false;
        for (int i = 0; i < elements[index].size(); i++) {
            if (elements[index].get(i).key == key) {
                elements[index].get(i).value = value;
                updated = true;
                break;
            }
        }

        if (!updated) {
            elements[index].addFirst(newNode);
        }
    }

    public Integer get(int key) {
        int index = getIndexByKey(key);
        for (int i = 0; i < elements[index].size(); i++) {
            if (elements[index].get(i).key == key) {
                return elements[index].get(i).value;
            }
        }
        return null;
    }

    public Integer delete(int key) {
        int index = getIndexByKey(key);
        for (int i = 0; i < elements[index].size(); i++) {
            if (elements[index].get(i).key == key) {
                return elements[index].remove(i).value;
            }
        }
        return null;
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        HashTable hashTable;
        StringBuilder buffer = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int n = Integer.parseInt(reader.readLine().strip());
            hashTable = new HashTable(n);

            for (int i = 0; i < n; i++) {
                StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
                menu(buffer, tokenizer, hashTable);
            }
        }

        System.out.println(buffer);
    }

    private static void menu(StringBuilder buffer, StringTokenizer tokenizer, HashTable hashTable) {
        String command = tokenizer.nextToken();

        switch (command) {
            case "get": {
                Integer result = hashTable.get(Integer.parseInt(tokenizer.nextToken()));
                buffer.append(result == null ? "None" : result).append("\n");
                break;
            }
            case "put": {
                int key = Integer.parseInt(tokenizer.nextToken());
                int value = Integer.parseInt(tokenizer.nextToken());
                hashTable.put(key, value);
                break;
            }
            case "delete": {
                Integer result = hashTable.delete(Integer.parseInt(tokenizer.nextToken()));
                buffer.append(result == null ? "None" : result).append("\n");
                break;
            }
        }
    }
}
