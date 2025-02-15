package pricticum_structures.sprint6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class AdjacencyMatrix {

    public static void main(String[] args) throws IOException {
        int n;
        int m;
        int[][] vertices;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            n = Integer.parseInt(tokenizer.nextToken());
            m = Integer.parseInt(tokenizer.nextToken());
            vertices = new int[n][n];

            for (int i = 0; i < m; i++) {
                StringTokenizer edge = new StringTokenizer(reader.readLine());
                int from = Integer.parseInt(edge.nextToken());
                int to = Integer.parseInt(edge.nextToken());
                vertices[from - 1][to - 1] = 1;
            }
        }
        StringBuilder buffer = new StringBuilder();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                buffer.append(vertices[i][j]).append(" ");
            }
            buffer.append("\n");
        }

        System.out.print(buffer);

    }
}
