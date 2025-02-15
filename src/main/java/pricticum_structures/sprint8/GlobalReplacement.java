package pricticum_structures.sprint8;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
эффективный поиск шаблона и замена на новый тест этого шаблона
 */
public class GlobalReplacement {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String text = scanner.nextLine();
        String s = scanner.nextLine();

        String t = scanner.nextLine();
        scanner.close();

        List<Integer> startIndexes = new ArrayList<>();
        String patternText = s + "#" + text;

        int[] pi = new int[s.length()];
        int piPrev = 0;

        for (int i = 1; i < patternText.length(); i++) {
            int k = piPrev;

            while (k > 0 && patternText.charAt(k) != patternText.charAt(i)) {
                k = pi[k - 1];
            }

            if (patternText.charAt(k) == patternText.charAt(i)) {
                k++;
            }

            if (i < s.length()) {
                pi[i] = k;
            }

            piPrev = k;

            if (k == s.length()) {
                startIndexes.add(i - 2 * s.length());
            }
        }

        StringBuilder newText = new StringBuilder();
        int idx = 0;
        int textIndex = 0;

        while (textIndex < text.length()) {
            if (idx < startIndexes.size() && textIndex == startIndexes.get(idx)) {
                newText.append(t);
                textIndex += s.length();
                idx++;
            } else {
                newText.append(text.charAt(textIndex));
                textIndex++;
            }
        }

        System.out.println(newText);
    }
}
