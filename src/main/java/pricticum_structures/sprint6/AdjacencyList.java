package pricticum_structures.sprint6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class AdjacencyList {

    public static void main(String[] args) throws IOException {

        int n;
        int m;
        Map<Integer, List<Integer>> vertices = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            n = Integer.parseInt(tokenizer.nextToken());
            m = Integer.parseInt(tokenizer.nextToken());

            for (int i = 0; i < m; i++) {
                StringTokenizer edge = new StringTokenizer(reader.readLine());
                Integer vertex = Integer.parseInt(edge.nextToken());
                Integer adjacency = Integer.parseInt(edge.nextToken());

                List<Integer> adjacencyForVertex = vertices.getOrDefault(vertex, new ArrayList<>());
                adjacencyForVertex.add(adjacency);
                vertices.put(vertex, adjacencyForVertex);
            }

            StringBuilder buffer = new StringBuilder();

            for (int i = 1; i <= n; i++) {
                if (!vertices.containsKey(i)) {
                    buffer.append("0").append("\n");
                    continue;
                }
                List<Integer> value = vertices.get(i);
                buffer.append(value.size()).append(" ");
                value.stream()
                        .sorted()
                        .forEach(x -> buffer.append(x).append(" "));
                buffer.append("\n");
            }
            System.out.print(buffer);
        }
    }
}
