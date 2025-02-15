package pricticum_structures.sprint2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class StackMax {
    private final List<Integer> items;
    private int size = 0;

    public StackMax() {
        items = new ArrayList<>();
    }

    public void push(String item) {
        items.add(Integer.parseInt(item));
        size++;
    }

    public String pop() {
        if (size == 0) {
            return "error";
        }
        String answer = String.valueOf(items.get(size - 1));
        items.remove(size -1);
        size--;
        return answer;
    }

    public String getMax() {
        if (size == 0) {
            return "None";
        }
        return String.valueOf(items.stream()
                .max(Integer::compareTo)
                .get());
    }
}


public class StackOnArray {

    public static void main(String[] args) throws IOException {
        StackMax stack = new StackMax();
        StringBuilder buffer = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int n = Integer.parseInt(reader.readLine());
            for (int i = 0; i < n; i++) {
                StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
                chooseStackCommand(stack, tokenizer, buffer);
            }
        }
        System.out.println(buffer);
    }

    private static void chooseStackCommand(StackMax stack, StringTokenizer tokenizer, StringBuilder buffer) {
        switch (tokenizer.nextToken()) {
            case "push": {
                stack.push(tokenizer.nextToken());
                break;
            }
            case "pop": {
                String result = stack.pop();
                if (result.equals("error")) {
                    buffer.append(result).append("\n");
                }
                break;
            }
            case "get_max": {
                buffer.append(stack.getMax()).append("\n");
                break;
            }
        }
    }
}
