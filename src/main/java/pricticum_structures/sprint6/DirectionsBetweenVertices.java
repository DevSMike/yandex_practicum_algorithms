package pricticum_structures.sprint6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class DirectionsBetweenVertices {
    public static void main(String[] args) throws IOException {

        int n;
        int m;
        Map<Integer, List<Integer>> graph = new HashMap<>();
        int[] color;
        int[] distances;

        int s;
        int e;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            n = Integer.parseInt(tokenizer.nextToken());
            m = Integer.parseInt(tokenizer.nextToken());

            color = new int[n + 1];
            distances = new int[n + 1];

            for (int i = 0; i < m; i++) {
                StringTokenizer edge = new StringTokenizer(reader.readLine());
                int from = Integer.parseInt(edge.nextToken());
                int to = Integer.parseInt(edge.nextToken());

                List<Integer> fromV = graph.getOrDefault(from, new ArrayList<>());
                List<Integer> toV = graph.getOrDefault(to, new ArrayList<>());

                fromV.add(to);
                toV.add(from);

                graph.put(from, fromV);
                graph.put(to, toV);
            }
            StringTokenizer path = new StringTokenizer(reader.readLine());
            s = Integer.parseInt(path.nextToken());
            e = Integer.parseInt(path.nextToken());
        }

        if (s == e) {
            System.out.print(0);
            return;
        }

        if (BFS(s, e, color, distances, graph)) {
            System.out.print(distances[e]);
        } else {
            System.out.print(-1);
        }


    }

    private static boolean BFS(int s, int e, int[] color, int[] dist, Map<Integer, List<Integer>> graph) {

        Queue<Integer> planned = new LinkedList<>();
        planned.add(s);
        color[s] = 1;

        while (!planned.isEmpty()) {
            int u = planned.poll();

            for (int w : graph.getOrDefault(u, new ArrayList<>())) {
                if (color[w] == 0) {
                    dist[w] = dist[u] + 1;
                    color[w] = 1;
                    planned.add(w);
                }
                if (w == e) {
                    return true;
                }
            }

            color[u] = 2;
        }
        return false;
    }
}
