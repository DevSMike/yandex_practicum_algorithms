package pricticum_structures.sprint4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Groups {

    public static void main(String[] args) throws IOException {

        int n;
        Map<String, Integer> values = new HashMap<>();
        StringBuilder buffer = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            n = Integer.parseInt(reader.readLine());
            for (int i = 0; i < n; i++) {
                String currentGroup = reader.readLine();
                values.put(currentGroup, values.getOrDefault(currentGroup, 0) + 1);
                if (values.get(currentGroup) == 1) {
                    buffer.append(currentGroup)
                            .append("\n");
                }
            }
          //  values.keySet().forEach(x->buffer.append(x).append("\n"));
            System.out.println(buffer);
        }
    }


}
