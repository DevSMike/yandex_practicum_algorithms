package pricticum_structures.sprint6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class TopologicalSort {

    public static void main(String[] args) throws IOException {
        int n;
        int m;
        int[] colors;
        Map<Integer, List<Integer>> graph = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            n = Integer.parseInt(tokenizer.nextToken());
            m = Integer.parseInt(tokenizer.nextToken());
            colors = new int[n + 1];

            for (int i = 0; i < m; i++) {
                StringTokenizer edge = new StringTokenizer(reader.readLine());
                int from = Integer.parseInt(edge.nextToken());
                int to = Integer.parseInt(edge.nextToken());

                List<Integer> adj = graph.getOrDefault(from, new ArrayList<>());
                adj.add(to);
                graph.put(from, adj);

           }
        }

        Stack<Integer> order = new Stack<>();
        StringBuilder buffer = new StringBuilder();

        topSortMain(colors, graph, order);
        while (!order.isEmpty()) {
            buffer.append(order.pop()).append(" ");
        }
        System.out.println(buffer);
    }

    private static void topSortMain(int[] colors, Map<Integer, List<Integer>> graph, Stack<Integer> order) {
        for (int i = 1; i < colors.length; i++) {
            if (colors[i] == 0) {
                topSort(i, colors, graph, order);
            }
        }
    }

    private static void topSort(int v, int[] colors, Map<Integer, List<Integer>> graph, Stack<Integer> order) {
        colors[v] = 1;
        for (int w : graph.getOrDefault(v, new ArrayList<>())) {
            if (colors[w] == 0) {
                topSort(w, colors, graph, order);
            }
        }

        colors[v] = 2;
        order.add(v);
    }
}
