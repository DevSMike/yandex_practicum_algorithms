package pricticum_structures.sprint3;

import java.util.Arrays;

public class MergeSort {
}

class Solution {
    public static int[] merge(int[] arr, int left, int mid, int right) {
        int[] leftArr = Arrays.copyOfRange(arr, left, mid);
        int[] rightArr = Arrays.copyOfRange(arr, mid, right);

        int l = 0;
        int r = 0;
        int k = left;

        while (l < leftArr.length && r < rightArr.length) {
            if (leftArr[l] <= rightArr[r]) {
                arr[k] = leftArr[l];
                l++;
            } else {
                arr[k] = rightArr[r];
                r++;
            }
            k++;
        }

        while (l < leftArr.length) {
            arr[k] = leftArr[l];
            l++;
            k++;
        }

        while (r < rightArr.length) {
            arr[k] = rightArr[r];
            k++;
            r++;
        }
        return arr;
    }

    public static void merge_sort(int[] arr, int left, int right) {
        if (right - left > 1) {
            int mid = (left + right) / 2;
            merge_sort(arr, left, mid);
            merge_sort(arr, mid, right);
            merge(arr, left, mid, right);
        }
    }

    public static void main(String[] args) {
        int[] a = {1, 4, 9, 2, 10, 11};
        int[] b = merge(a, 0, 3, 6);
        int[] expected = {1, 2, 4, 9, 10, 11};
        assert Arrays.equals(b, expected);
        int[] c = {1, 4, 2, 10, 1, 2};
        merge_sort(c, 0, 6);
        int[] expected2 = {1, 1, 2, 2, 4, 10};
        assert Arrays.equals(c, expected2);
    }
}