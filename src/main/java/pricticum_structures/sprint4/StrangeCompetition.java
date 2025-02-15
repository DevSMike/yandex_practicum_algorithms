package pricticum_structures.sprint4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class StrangeCompetition {

    public static void main(String[] args) throws IOException {
        char[] str1;
        char[] str2;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            str1 = reader.readLine().strip().toCharArray();
            str2 = reader.readLine().strip().toCharArray();
        }

        if (str1.length != str2.length) {
            System.out.println("NO");
            return;
        }

        Map<Character, Integer> strMap1 = new HashMap<>();
        Map<Character, Integer> strMap2 = new HashMap<>();

        int i =0, j =0;
        boolean isEquals = false;
        while (i < str1.length && j < str2.length) {
            if (!strMap1.containsKey(str1[i])) {
                strMap1.put(str1[i], i);
            }
            if (!strMap2.containsKey(str2[i])) {
                strMap2.put(str2[j],j);
            }

            isEquals = strMap1.get(str1[i++]).equals(strMap2.get(str2[j++]));
            if (!isEquals) break;
        }

        if (isEquals) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }

    }
}
