package pricticum_structures.sprint4;

import java.util.Random;

//брут-форс решение
public class BreakMe {
    public static String alphabet = "abcdefghijklmnopqrstuvwxyz";

    public static void main(String[] args) {
        int m = 123_987_123;
        int a = 1000;

        while (true) {
            String str1 = generateRandomStr();
            String str2 = generateRandomStr();
            long hashStr1 = getStrPolinomeHash(a, m, str1);
            long hashStr2 = getStrPolinomeHash(a, m, str2);
            if (hashStr1 == hashStr2 && !str1.equals(str2)) {
                System.out.println(str1 + "\n" + str2);
                break;
            }
        }
    }

    private static long getStrPolinomeHash(int a, int m, String str) {
        long power = 1;
        long result = 0;
        for (int i = 0; i < str.length(); i++) {
            result = (result + power * str.charAt(str.length() - 1 - i)) % m;
            power = (power * a) % m;
        }
        return result % m;
    }

    private static String generateRandomStr() {
        Random random = new Random();
        int len = random.nextInt(100);
        StringBuilder randomStr = new StringBuilder();
        int i = 0;
        while (i++ < len) {
            int charIdx = random.nextInt(alphabet.length() - 1);
            randomStr.append(alphabet.charAt(charIdx));
        }
        return randomStr.toString();
    }
}
