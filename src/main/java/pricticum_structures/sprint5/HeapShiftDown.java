package pricticum_structures.sprint5;

import java.util.Arrays;

public class HeapShiftDown {

    public static int siftDown(int[] heap, int idx) {
        // Your code
        // “ヽ(´▽｀)ノ”
        int leftChild = 2 * idx;
        int rightChild = 2 * idx + 1;

        if (leftChild >= heap.length) {
            return idx;
        }

        int largestChild = leftChild;
        if (rightChild < heap.length && heap[rightChild] > heap[leftChild]) {
            largestChild = rightChild;
        }

        if (heap[largestChild] > heap[idx]) {
            int temp = heap[largestChild];
            heap[largestChild] = heap[idx];
            heap[idx] = temp;
            return siftDown(heap, largestChild);
        } else {
            return idx;
        }

    }

    private static void test() {
        int[] sample = {-1, 12, 1, 8, 3, 4, 7};
        assert siftDown(sample, 2) == 5;
    }

    public static void main(String[] args) {
        int[] sample = {-1, 12, 1, 8, 3, 4, 7};
        assert siftDown(sample, 2) == 5;

        System.out.println(siftDown(sample, 2));
        System.out.println(Arrays.toString(sample));
    }
}
