package pricticum_structures.sprint5;

import java.util.Arrays;

public class HeapShiftUp {
    public static int siftUp(int[] heap, int idx) {
        // Your code
        // “ヽ(´▽｀)ノ”
        if (idx == 1) {
            return idx;
        }

        int parentIdx = idx / 2;

        if (heap[parentIdx] < heap[idx]) {
            int temp = heap[parentIdx];
            heap[parentIdx] = heap[idx];
            heap[idx] = temp;
            return siftUp(heap, parentIdx);
        } else {
            return idx;
        }
    }

    private static void test() {
        int[] sample = {-1, 12, 6, 8, 3, 15, 7};
        assert siftUp(sample, 5) == 1;
        System.out.println(siftUp(sample, 5));
    }

    public static void main(String[] args) {
        int[] sample = {-1, 12, 6, 8, 3, 15, 7};
        assert siftUp(sample, 5) == 1;
        System.out.println(siftUp(sample, 5));
        System.out.println(Arrays.toString(sample));
    }
}


