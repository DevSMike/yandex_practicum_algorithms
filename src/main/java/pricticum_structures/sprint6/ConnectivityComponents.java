package pricticum_structures.sprint6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class ConnectivityComponents {

    public static void main(String[] args) throws IOException {

        int n;
        int m;

        Map<Integer, List<Integer>> graph = new HashMap<>();
        int[] colors;

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
        }

        Map<Integer, List<Integer>> resultMap = new LinkedHashMap<>();
        StringBuilder buffer = new StringBuilder();
        countConnectivityComponentsMain(colors, graph, resultMap);

        buffer.append(resultMap.size()).append("\n");

        for (List<Integer> vertices : resultMap.values()) {
            Collections.sort(vertices);
            vertices.forEach(x -> buffer.append(x).append(" "));
            buffer.append("\n");
        }

        System.out.println(buffer);

    }

    private static void countConnectivityComponentsMain(int[] colors, Map<Integer, List<Integer>> graph,
                                                        Map<Integer, List<Integer>> result) {
        int counter = 1;
        for (int i = 1; i < colors.length; i++) {
            counter = getConnectivityComponents(i, counter, colors, graph, result);
        }
    }

    private static int getConnectivityComponents(int v, int count, int[] colors, Map<Integer, List<Integer>> graph,
                                                 Map<Integer, List<Integer>> result) {

        Stack<Integer> stack = new Stack<>();
        stack.push(v);

        while (!stack.isEmpty()) {

            int current = stack.pop();
            if (colors[current] == 0) {
                colors[current] = count;

                List<Integer> vertices = result.getOrDefault(count, new ArrayList<>());
                vertices.add(current);
                result.put(count, vertices);

                stack.push(v);

                for (int w : graph.getOrDefault(current, new ArrayList<>())) {
                    if (colors[w] == 0) {
                        stack.push(w);
                    }
                }
            }
        }


        return count + 1;
    }
}
