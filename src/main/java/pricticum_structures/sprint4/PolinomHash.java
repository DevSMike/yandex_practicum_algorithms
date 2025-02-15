package pricticum_structures.sprint4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//Берем полиномиальный хеш от строки по формуле, константы m и a задаются при вводе
public class PolinomHash {

    public static void main(String[] args) throws IOException {

        int a;
        int m;
        String hashStr;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            a = Integer.parseInt(reader.readLine().strip());
            m = Integer.parseInt(reader.readLine().strip());
            hashStr = reader.readLine().strip();
        }

        long aPowN = 1;
        long result = 0;
        for (int i = 0; i < hashStr.length(); i++) {
            result = (result + hashStr.charAt(hashStr.length() - 1 - i) * aPowN) % m;
            aPowN = (aPowN * a) % m;
        }

        System.out.println(result % m);
    }
}
