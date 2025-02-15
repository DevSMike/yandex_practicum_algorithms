package pricticum_structures.sprint4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Node {

    int key;
    int x1;
    int y1;

    public Node(int key, int x1, int y1) {
        this.key = key;
        this.x1 = x1;
        this.y1 = y1;
    }
}

//todo: доделать задачу
public class NearestStop {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Map<Node, Integer> map = new HashMap<>();
        int n = Integer.parseInt(reader.readLine());

        for (int i = 0; i < n; i++) {
            StringTokenizer x1y1 = new StringTokenizer(reader.readLine());
            map.put(new Node(i+1, Integer.parseInt(x1y1.nextToken()), Integer.parseInt(x1y1.nextToken())), 0);
        }

        int m = Integer.parseInt(reader.readLine());
        for (int i = 0; i < m; i++) {
            StringTokenizer x2y2 = new StringTokenizer(reader.readLine().strip());
            int x2 = Integer.parseInt(x2y2.nextToken());
            int y2 = Integer.parseInt(x2y2.nextToken());
            for (Node key : map.keySet()) {
                int dist = getDistSquared(key.x1, key.y1, x2, y2);
                if (dist < 20) {
                    map.put(key, map.get(key) + 1);
                }
            }
        }
        reader.close();

        System.out.println(map);
        System.out.println(map.entrySet().stream().max(Comparator
                .comparingInt((Map.Entry<Node, Integer> a) -> a.getValue())
                .thenComparingInt(a -> -1 * a.getKey().key))
                .get()
                .getKey()
                .key);

    }

    private static int getDistSquared(int x1, int y1, int x2, int y2) {
        return (int)Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
    }
}
