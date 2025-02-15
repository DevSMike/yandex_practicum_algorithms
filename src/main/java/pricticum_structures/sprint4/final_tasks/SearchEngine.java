package pricticum_structures.sprint4.final_tasks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
yandex contest: https://contest.yandex.ru/contest/24414/run-report/125455198/

Требовалось реализовать поисковую систему с оптимальным поисковым индексом.

-- ПРИНЦИП РАБОТЫ АЛГОРИТМА --
Создается словарь: отображение слова в мапу<документ, количество повторений>. Потом для каждого запроса
создается отдельная мапа: отображение документа в количество повторений. В каждом запросе используются только
уникальные слова. Рассчитывается для каждого запроса, документы сортируются по релевантности (лимит 5).
 Сортировка по релевантности || по минимальному индексу в базе.

-- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
Такой подход позволяет создать оптимальный поисковый индекс. По слову нам доступна информация: в каких документах
встречается и по сколько раз в каждом. При таком подходе можно быстро построить отображение для каждого запроса.

-- ВРЕМЕННАЯ СЛОЖНОСТЬ --
Итоговая временная сложность алгоритма рассчитывается таким образом:
- Количество запросов -> M;
- Количество слов в запросе -> L;
- Количество документов -> N;
=> O(N * logN * M * L);

-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
Итоговая пространственная сложность алгоритма будет равна занимаемой памяти dictionary
* N - количество слов
* M - количество документов
=> O(N * M);
 */

public class SearchEngine {

    public static void main(String[] args) throws IOException {
        Map<String, Map<Integer, Integer>> dictionary = new HashMap<>();

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());

        for (int i = 0; i < n; i++) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            int size = tokenizer.countTokens();
            while (size-- > 0) {
                String key = tokenizer.nextToken();
                Map<Integer, Integer> valuesForKey = dictionary.getOrDefault(key, new HashMap<>());
                valuesForKey.put(i + 1, valuesForKey.getOrDefault(i + 1, 0) + 1);
                dictionary.put(key, valuesForKey);
            }
        }

        int m = Integer.parseInt(reader.readLine());
        StringBuilder buffer = new StringBuilder();
        for (int i = 0; i < m; i++) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            Set<String> uniqueWords = new HashSet<>();
            Map<Integer, Integer> countingMap = new HashMap<>();
            int size = tokenizer.countTokens();
            while (size-- > 0) {
                String word = tokenizer.nextToken();

                if (isWordNotUnique(uniqueWords, word)) {
                    continue;
                }

                for (Map.Entry<Integer, Integer> map : dictionary.getOrDefault(word, new HashMap<>()).entrySet()) {
                    countingMap.put(map.getKey(), countingMap.getOrDefault(map.getKey(), 0) + map.getValue());
                }
            }

            if (countingMap.size() == 0) {
                continue;
            }

            countingMap.entrySet().stream().sorted((a, b) -> {
                        int compare1 = -1 * Integer.compare(a.getValue(), b.getValue());
                        if (compare1 != 0) {
                            return compare1;
                        }
                        return Integer.compare(a.getKey(), b.getKey());
                    })
                    .limit(5)
                    .forEach(x -> buffer.append(x.getKey()).append(" "));
            buffer.append("\n");
        }

        System.out.println(buffer);
        reader.close();
    }

    private static boolean isWordNotUnique(Set<String> set, String word) {
        if (set.contains(word)) {
            return true;
        } else {
            set.add(word);
            return false;
        }
    }
}
