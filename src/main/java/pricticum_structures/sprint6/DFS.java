package pricticum_structures.sprint6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class DFS {

    public static void main(String[] args) throws IOException {
        int n;
        int m; // edges
        int s; //start
        Map<Integer, List<Integer>> graph = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            n = Integer.parseInt(tokenizer.nextToken());
            m = Integer.parseInt(tokenizer.nextToken());
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

        if (!graph.containsKey(s)) {
            graph.put(s, new ArrayList<>());
        }

        for (List<Integer> vertices : graph.values()) {
            vertices.sort((a,b) -> Integer.compare(a,b) * -1);
        }

        int[] color = new int[n + 1];
        StringBuilder buffer = new StringBuilder();

        Dfs(s, graph, buffer, color);

        System.out.println(buffer);
    }

    private static void Dfs(int start, Map<Integer, List<Integer>> graph, StringBuilder buffer, int[] color) {
        Stack<Integer> stack = new Stack<>();
        stack.push(start);

        while (!stack.isEmpty()) {
            int v = stack.pop();

            if (color[v] == 0) {
                color[v] = 1;
                stack.push(v);
                buffer.append(v).append(" ");

                for (int w : graph.get(v)) {
                    if (color[w] == 0) {
                        stack.push(w);
                    }
                }
            }

        }
    }
}
