package pricticum_structures.sprint4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

// уникальная подстрока
public class Substring {
    public static void main(String[] args) throws IOException {
        String str;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            str = reader.readLine();
        }

        Map<Character, Integer> map = new HashMap<>();

        int maxLen = 0;
        int left = 0;
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            map.put(ch, map.getOrDefault(ch, 0) + 1);
            while (map.get(ch) > 1) {
                map.put(str.charAt(left), map.get(str.charAt(left)) - 1);
                if (map.get(str.charAt(left)) == 0) {
                    map.remove(str.charAt(left));
                }
                left++;
            }
            maxLen = Math.max(maxLen, i - left + 1);
        }
        System.out.println(maxLen);
    }
}
