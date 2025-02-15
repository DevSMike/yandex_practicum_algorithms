package pricticum_structures.sprint6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BFS {


    public static void main(String[] args) throws IOException {

        int n;
        int m;
        int[] colors;
        Map<Integer, List<Integer>> graph = new HashMap<>();
        int s;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {

            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            n = Integer.parseInt(tokenizer.nextToken());
            m = Integer.parseInt(tokenizer.nextToken());
            colors = new int[n + 1];

            for (int i = 0; i < m; i++) {
                StringTokenizer edge = new StringTokenizer(reader.readLine());
                int from = Integer.parseInt(edge.nextToken());
                int to = Integer.parseInt(edge.nextToken());

                List<Integer> fromVertices = graph.getOrDefault(from, new ArrayList<>());
                List<Integer> toVertices = graph.getOrDefault(to, new ArrayList<>());

                fromVertices.add(to);
                toVertices.add(from);

                graph.put(from, fromVertices);
                graph.put(to, toVertices);
            }
            s = Integer.parseInt(reader.readLine());
        }

        for (List<Integer> list : graph.values()) {
            Collections.sort(list);
        }

        StringBuilder buffer = new StringBuilder();
        bfs(s, colors, graph, buffer);
        System.out.println(buffer);
    }

    private static void bfs(int start, int[] colors, Map<Integer, List<Integer>> graph, StringBuilder buffer) {
        Queue<Integer> planned = new LinkedList<>();
        planned.add(start);
        colors[start] = 1;

        while (!planned.isEmpty()) {
            int current = planned.poll();
            buffer.append(current).append(" ");

            for (int w : graph.getOrDefault(current, new ArrayList<>())) {
                if (colors[w] == 0) {
                    colors[w] = 1;
                    planned.add(w);
                }
            }
            colors[current] = 2;
        }
    }
}
