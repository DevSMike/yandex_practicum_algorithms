package pricticum_structures.sprint6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class MaxDistanceInGraph {

    public static void main(String[] args) throws IOException {

        int n;
        int m;
        int[] colors;
        int[] distance;
        Map<Integer, List<Integer>> graph = new HashMap<>();
        int s;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {

            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            n = Integer.parseInt(tokenizer.nextToken());
            m = Integer.parseInt(tokenizer.nextToken());
            colors = new int[n + 1];
            distance = new int[n + 1];

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

        bfsWithDistance(s, colors, distance, graph);
        System.out.println(Arrays.stream(distance).max().getAsInt());
    }

    private static void bfsWithDistance(int s, int[] color, int[] distance, Map<Integer, List<Integer>> graph) {
        Queue<Integer> planned = new LinkedList<>();
        planned.add(s);
        color[s] = 1;

        while (!planned.isEmpty()) {
            int u = planned.poll();

            for (int v : graph.getOrDefault(u, new ArrayList<>())) {
                if (color[v] == 0) {
                    distance[v] = distance[u] + 1;
                    color[v] = 1;
                    planned.add(v);
                }
            }
            color[u] = 2;
        }
    }


}
