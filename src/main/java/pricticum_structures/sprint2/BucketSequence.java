package pricticum_structures.sprint2;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BucketSequence {

    static class Stack {

        private final List<Character> elements;
        private int size;

        public Stack() {
            this.elements = new ArrayList<>();
            this.size = 0;
        }

        public void push(Character elem) {
            elements.add(elem);
            size++;
        }

        public Character pop() {
            if (size == 0) {
                return '#';
            }
            Character elem = elements.get(size - 1);
            elements.remove(size - 1);
            size--;
            return elem;
        }
    }

    public static void main(String[] args) throws IOException {
        Map<Character, Character> correctSequence = Map.of(
                '{', '}',
                '[', ']',
                '(', ')',
                '#', '-');
        Stack stack = new Stack();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        char[] sequence = reader.readLine().strip().toCharArray();
        reader.close();

        int i = 0;
        String result = "True";
        while (i < sequence.length) {
            while (i < sequence.length && correctSequence.containsKey(sequence[i])) {
                stack.push(sequence[i]);
                i++;
            }
            while (i < sequence.length && correctSequence.containsValue(sequence[i])) {
                if (correctSequence.get(stack.pop()) != sequence[i]) {
                    result = "False";
                    i = sequence.length;
                    break;
                }
                i++;
            }
        }

        if (stack.size > 0) {
            result = "False";
        }

        System.out.println(result);
    }
}
