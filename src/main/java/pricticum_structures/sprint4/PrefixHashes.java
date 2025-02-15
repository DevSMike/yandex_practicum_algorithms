package pricticum_structures.sprint4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class PrefixHashes {

    public static void main(String[] args) throws IOException {
        int a;
        int m;
        String str;
        int t;

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        a = Integer.parseInt(reader.readLine().strip());
        m = Integer.parseInt(reader.readLine().strip());
        str = reader.readLine().strip();
        t = Integer.parseInt(reader.readLine().strip());

        int n = str.length();
        long[] h = new long[n + 1];
        long[] p = new long[n + 1];
        p[0] = 1;
        h[0] = 0;
        for (int i = 0; i < n; i++) {
            int charInt = str.charAt(i);
            h[i + 1] = ((h[i] * a + charInt) % m);
            p[i + 1] = (p[i] * a) % m;
        }

        StringBuilder buffer = new StringBuilder();
        for (int i = 0; i < t; i++) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            int l = Integer.parseInt(tokenizer.nextToken());
            int r = Integer.parseInt(tokenizer.nextToken());
            long result = ((h[r] - ((h[l - 1] * p[r - l + 1]) % m) + m) % m);
            buffer.append(result).append("\n");
        }

        reader.close();
        System.out.println(buffer);
    }
}
