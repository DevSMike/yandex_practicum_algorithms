package pricticum_structures.sprint4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

// определить какие строки являются анограммами
public class AnagramGrouping {

    public static void main(String[] args) throws IOException {
        List<String> arr = new ArrayList<>();
        int n;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            n = Integer.parseInt(reader.readLine());
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            for (int i = 0; i < n; i++) {
                char[] str = tokenizer.nextToken().toCharArray();
                Arrays.sort(str);
                arr.add(new String(str));
            }
        }

        Map<String, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < arr.size(); i++) {
            List<Integer> idx = map.getOrDefault(arr.get(i), new ArrayList<>());
            idx.add(i);
            map.put(arr.get(i), idx);
        }

        StringBuilder buffer = new StringBuilder();
        map.values().stream()
                .sorted(Comparator.comparingInt(a -> a.get(0)))
                .map(x->x.stream().sorted().collect(Collectors.toList()))
                .forEach(x -> {
                    for (int value : x) {
                        buffer.append(value).append(" ");
                    }
                    buffer.append("\n");
                });
        System.out.println(buffer);

    }
}
