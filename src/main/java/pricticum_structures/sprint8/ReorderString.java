package pricticum_structures.sprint8;

import java.util.Scanner;
import java.util.StringTokenizer;

public class ReorderString {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StringTokenizer tokenizer = new StringTokenizer(scanner.nextLine());
        String[] words = new String[tokenizer.countTokens()];
        StringBuilder buffer = new StringBuilder();
        for (int i = words.length - 1; i >=0; i--) {
            words[i] = tokenizer.nextToken();
        }

        for (String word : words) {
            if (buffer.length() > 0) {
                buffer.append(" ");
            }
            buffer.append(word);
        }
        System.out.println(buffer);
    }
}
