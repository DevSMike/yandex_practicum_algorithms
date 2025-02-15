package pricticum_structures.sprint2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Monitoring {

    public static void main(String[] args) throws IOException {
        int[][] output;
        int n;
        int m;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            n = Integer.parseInt(reader.readLine());
            m = Integer.parseInt(reader.readLine());
            output = new int[m][n];
            for (int i = 0; i < n; i++) {
                List<Integer> line = readList(reader);
                for (int j = 0; j < m; j++) {
                    output[j][i] = line.get(j);
                }
            }
        }
        StringBuilder buffer = new StringBuilder();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                buffer.append(output[i][j]).append(" ");
            }
            buffer.append("\n");
        }
        System.out.println(buffer);
    }

    private static List<Integer> readList(BufferedReader reader) throws IOException {
        return Arrays.stream(reader.readLine().strip().split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }
}
