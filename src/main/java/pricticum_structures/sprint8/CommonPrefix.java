package pricticum_structures.sprint8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CommonPrefix {

    public static void main(String[] args) throws IOException {
        int n;
        String[] strings;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            n = Integer.parseInt(reader.readLine());
            strings = new String[n];
            for (int i =0; i < n; i++) {
                strings[i] = reader.readLine();
            }
        }


        String prefix = strings[0];

        for (int i = 1; i < strings.length; i++) {
            if (strings[i].length() < prefix.length()) {
                prefix = strings[i];
            }
        }

        int end = prefix.length();

        for (int i = 1; i < strings.length; i++) {
            for (int j = 0; j < end; j++) {
                if (strings[i].charAt(j) != prefix.charAt(j)) {
                    end = j;
                    break;
                }
            }
        }
        System.out.println(prefix.substring(0, end).length());
    }
}