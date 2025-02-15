package pricticum_structures.sprint8.final_tasks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/*
yandex contest: https://contest.yandex.ru/contest/26133/run-report/133356445/

Требовалось реализовать префиксное дерево и по нему найти: возможно ли разделить текст на слова из этого дерева.

-- ПРИНЦИП РАБОТЫ АЛГОРИТМА --
Считываем массив строк,  все считанные строки добавляем в префиксное дерево. Проверяем, можно ли разбить текст на эти слова.
Проверка проходит при помощи динамичесского программирования.
1. В массиве dp[] будет состояние, возможно ли получить префикс длины i.
2. Базовый случай для задачи dp[0] = строку длины ноль можно получить всегда.
3. Переход динамики dp[j] = true, если при переборе текста дошли до указателя на конец слова.
4. Порядок вычисления: вычисляем значение dp[j], на основе этого значения заходим во внутренний цикл по dp[i]. Если префикс
длины i возможно получить, то начинаем перебирать текст.
5. Ответ на задачу содержится в ячейке dp[text.length()].

Перебираем всех детей от корня каждый раз в цикле в поисках всевозможных префиксов деерева.

-- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
Такой подход является корректным, потому что оптимальным образом ищет ответ на задачку при помощи префиксного деерва.
Так мы не храним все комбинации целиком, а от общего начала продолжаем. При помощи указателя на конец слова (isEnd), понимаем
, где слово завершено.

-- ВРЕМЕННАЯ СЛОЖНОСТЬ --
L - суммарная длина слов во множестве.
N - размер текста.
K - длина самого длинного слова
Построение бора - O(L)
Проверка - можно ли представить текст словами из бора O(N * K).
=> O(N * K + L)

-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
O(N) - массив dp  (N - длина текста)
E - размерность алфавита
K - средняя длина строки
M - количество строк
Обхем памяти, занимаемый префиксным деревом: O(E * K * M)
= > O(N + E * K * M)
 */

public class PrefixTree {
    static class TreeNode {
        Map<Character, TreeNode> children = new HashMap<>();
        boolean isEnd;
    }

    static class Trie {
        TreeNode root = new TreeNode();

        public void insert(String data) {
            if (data == null || data.isEmpty()) {
                return;
            }
            TreeNode node = root;
            for (char c : data.toCharArray()) {
                if (!node.children.containsKey(c)) {
                    node.children.put(c, new TreeNode());
                }
                node = node.children.get(c);
            }
            node.isEnd = true;
        }

        private boolean canSplit(String text) {
            boolean[] dp = new boolean[text.length() + 1];
            dp[0] = true;

            for (int i = 0; i < text.length(); i++) {
                TreeNode node = root;
                if (dp[i]) {
                    for (int j = i; j <= text.length(); j++) {
                        if (node.isEnd) {
                            dp[j] = true;
                        }
                        if (j == text.length() || !node.children.containsKey(text.charAt(j))) {
                            break;
                        }
                        node = node.children.get(text.charAt(j));
                    }
                }
            }
            return dp[text.length()];
        }
    }

    public static void main(String[] args) throws IOException {
        Trie trie = new Trie();
        String text;
        int n;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            text = reader.readLine();
            n = Integer.parseInt(reader.readLine());

            for (int i = 0; i < n; i++) {
                trie.insert(reader.readLine());
            }
        }
        boolean result = trie.canSplit(text);
        if (result) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }
}
