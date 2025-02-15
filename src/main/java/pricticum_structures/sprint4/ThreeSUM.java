package pricticum_structures.sprint4;

import java.util.Arrays;


/*
Асимптотика: O(N^2) по времени
O(1) по памяти
 */
public class ThreeSUM {

    public static void main(String[] args) {

        int[] arr = {6,6,4,4,0,8,10};
        int x = 16;
        Arrays.sort(arr);

        for (int i = 0; i < arr.length; i++) {
            int j = i +1;
            int k = arr.length- 1;
            while (j < k) {
                if (arr[i] + arr[j] + arr[k] > x) {
                    k--;
                } else if (arr[i] + arr[j] + arr[k] < x) {
                    j++;
                } else {
                    System.out.println(arr[i] + " " + arr[j] + " " + arr[k]);
                    j++;
                    k--;
                }
            }
        }
    }
}
