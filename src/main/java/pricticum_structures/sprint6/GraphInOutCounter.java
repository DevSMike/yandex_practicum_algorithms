package pricticum_structures.sprint6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Counter {

    private int entry;
    private int exit;

    public Counter(int startTime) {
        this.entry = startTime;
    }

    public void setEntry(int entry) {
        this.entry = entry;
    }

    public void setExit(int exit) {
        this.exit = exit;
    }

    public String getInfo() {
        return entry + " " + exit;
    }
}

public class GraphInOutCounter {


    public static void main(String[] args) throws IOException {
        int n;
        int m;

        Map<Integer, List<Integer>> graph = new HashMap<>();
        int[] colors; // 0 / 1 /2
        Counter[] inOutCounters;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            n = Integer.parseInt(tokenizer.nextToken());
            m = Integer.parseInt(tokenizer.nextToken());
            colors = new int[n + 1];
            inOutCounters = new Counter[n + 1];

            for (int i = 0; i < m; i++) {
                StringTokenizer edge = new StringTokenizer(reader.readLine());
                int from = Integer.parseInt(edge.nextToken());
                int to = Integer.parseInt(edge.nextToken());
                List<Integer> adj = graph.getOrDefault(from, new ArrayList<>());
                adj.add(to);
                graph.put(from, adj);

                if (!graph.containsKey(to)) {
                    graph.put(to, new ArrayList<>());
                }
            }
        }
        int start = 1;
        int time = 0;

        if (!graph.containsKey(start)) {
            graph.put(start, new ArrayList<>());
        }

        for (List<Integer> adj : graph.values()) {
            adj.sort((a, b) -> Integer.compare(a, b) * -1);
        }

        DFS(start, graph, time, colors, inOutCounters);
        StringBuilder buffer = new StringBuilder();
        for (int i = 1; i < inOutCounters.length; i++) {

            buffer.append(inOutCounters[i].getInfo()).append("\n");
        }

        System.out.println(buffer);
    }

    private static void DFS(int start, Map<Integer, List<Integer>> graph, int time, int[] colors, Counter[] counters) {
        Stack<Integer> stack = new Stack<>();
        stack.push(start);

        while (!stack.isEmpty()) {
            int v = stack.pop();

            if (colors[v] == 0) {
                counters[v] = new Counter(time);
                time += 1;
                colors[v] = 1;
                stack.push(v);

                for (int w : graph.get(v)) {
                    if (colors[w] == 0) {
                        stack.push(w);
                    }
                }
            } else if (colors[v] == 1) {
                colors[v] = 2;
                counters[v].setExit(time);
                time += 1;
            }
        }
    }
}
