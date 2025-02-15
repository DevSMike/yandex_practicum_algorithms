package pricticum_structures.sprint6.final_tasks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


/*
yandex contest: https://contest.yandex.ru/contest/25070/run-report/131411689/

Требовалось реализовать алгоритм поиска максимального остовного дерева

-- ПРИНЦИП РАБОТЫ АЛГОРИТМА --
Создается множество еще непосещенных вершин (это все вершины графа изначально), множество посещенных вершин (изначально пусто).
Также кучу, которая содержит ребра графа, которые мы будем рассматривать. Изнчаально мы выполянем функцию addVertex(): удаляем ее
из множества непосещенных вершин; добавляем во множество посещенных; добавляем в кучу все ее ребра, конечная вершина которых
еще находится во множестве непосещенных вершин. Пока множество непосещеннных вершин не пустое и куча с ребрами не пустая,
мы получаем ребро с максимальным весом из кучи, удаляем его. Если конец этого ребра еще не посещен, то мы добавляем вершину в
наше MST, а также выполянем функцию addVertex() для конца этого ребра.

-- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
Такой подход к решению является корректным, потому что во время выполнения функции findMst() будут рассмотрены всевозможные
ребра максимального веса из одной вершины в другую => будет собрано MST => будет найден вес этого MST.

-- ВРЕМЕННАЯ СЛОЖНОСТЬ --
Пусть n - кол-во вершин; m - кол-во ребер
Функция findMst() включает в себя цикл while , который работает за O(n), на каждой итерации выполняется
addVertex() за O(log(m)) и heap.popMax() за O(log(m))  = O(n * (O(log(m) + O(log(m))
==> O(n * log(m));

-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
 Пусть n - кол-во вершин; m - кол-во ребер
 Граф хранится в виде списка смежности : O(n + m)
 Доп множества посещенных && непосещенных вершин : O(n) + O(n)
 Куча с ребрами: O(m)

 ==> пространственная сложность O(n + m);
 */

public class ExpensiveNetwork {
    static final String WRONG_ANSWER = "Oops! I did it again";

    static class Edge implements Comparable<Edge> {
        int start;
        int end;
        int weight;

        public Edge(int start, int end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return -1 * Integer.compare(this.weight, o.weight);
        }
    }

    public static void main(String[] args) throws IOException {
        int n;
        int m;
        int startVertex = 1;
        List<Edge>[] graph;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            n = Integer.parseInt(tokenizer.nextToken());
            m = Integer.parseInt(tokenizer.nextToken());
            graph = new ArrayList[n + 1];
            fillGraph(graph, n);

            for (int i = 0; i < m; i++) {
                StringTokenizer edge = new StringTokenizer(reader.readLine());
                int start = Integer.parseInt(edge.nextToken());
                int end = Integer.parseInt(edge.nextToken());
                int weight = Integer.parseInt(edge.nextToken());

                List<Edge> startEdges = graph[start];
                startEdges.add(new Edge(start, end, weight));
                List<Edge> endEdges = graph[end];
                endEdges.add(new Edge(end, start, weight));

                graph[start] = startEdges;
                graph[end] = endEdges;
            }
        }

        List<Edge> mst = new ArrayList<>();
        int result = findMst(graph, mst, startVertex);

        if (result == -1) {
            System.out.print(WRONG_ANSWER);
        } else {
            System.out.print(result);
        }

    }

    private static void fillGraph(List<Edge>[] graph, int n) {
        while (n > 0) {
            graph[n--] = new ArrayList<>();
        }
    }

    private static int findMst(List<Edge>[] graph, List<Edge> mst, int startVertex) {
        boolean[] visited = new boolean[graph.length];
        int notVisitedCounter = graph.length - 1;
        PriorityQueue<Edge> edges = new PriorityQueue<>();

        addVertex(startVertex, edges, visited, graph[startVertex]);
        notVisitedCounter--;

        while (notVisitedCounter > 0 && !edges.isEmpty()) {
            Edge maxEdge = edges.poll();
            if (!visited[maxEdge.end]) {
                mst.add(maxEdge);
                addVertex(maxEdge.end, edges, visited, graph[maxEdge.end]);
                notVisitedCounter--;
            }
        }

        if (notVisitedCounter > 0) {
            return -1;
        }

        return findMstWeight(mst);
    }

    private static int findMstWeight(List<Edge> mst) {
        int maxWeight = 0;
        for (Edge edge : mst) {
            maxWeight += edge.weight;
        }

        return maxWeight;
    }

    private static void addVertex(int v, PriorityQueue<Edge> edges, boolean[] visited, List<Edge> vertexEdges) {
        visited[v] = true;
        for (Edge edge : vertexEdges) {
            if (!visited[edge.end]) {
                edges.add(edge);
            }
        }
    }

}
