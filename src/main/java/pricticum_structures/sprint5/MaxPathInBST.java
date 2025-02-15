package pricticum_structures.sprint5;

import java.lang.Math;

public class MaxPathInBST {
    public static int treeSolution(Node head) {
        // Your code
        // “ヽ(´▽｀)ノ”
        Value value = new Value();
        value.value = Integer.MIN_VALUE;
        findMaxWay(head, value);
        return value.value;
    }

    private static int findMaxWay(Node head, Value value) {

        if (head == null) {
            return 0;
        }

        int l = findMaxWay(head.left, value);
        int r = findMaxWay(head.right, value);

        int maxSinge = Math.max(head.value, Math.max(l, r) + head.value);
        int maxRoot = Math.max(maxSinge, head.value + l + r);

        value.value = Math.max(value.value, maxRoot);

        return maxSinge;
    }

    private static class Value {
        int value;
    }

    // <template>
    private static class Node {
        int value;
        Node left;
        Node right;

        Node(int value) {
            this.value = value;
            this.left = null;
            this.right = null;
        }

        Node(int value, Node left, Node right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }
    // <template>


    private static void test() {
        Node node1 = new Node(5, null, null);
        Node node2 = new Node(1, null, null);
        Node node3 = new Node(-3, node2, node1);
        Node node4 = new Node(2, null, null);
        Node node5 = new Node(2, node4, node3);
        assert treeSolution(node5) == 6;
    }


    public static void main(String[] args) {
        Node node1 = new Node(5, null, null);
        Node node2 = new Node(1, null, null);
        Node node3 = new Node(-3, node2, node1);
        Node node4 = new Node(2, null, null);
        Node node5 = new Node(2, node4, node3);

        System.out.println(treeSolution(node5) == 6);
    }
}
