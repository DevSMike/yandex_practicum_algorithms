package pricticum_structures.sprint5;

public class NumericWays {

    public static int treeSolution(Node head) {
        // Your code
        // “ヽ(´▽｀)ノ”


        return numericWay(head, 0);
    }

    private static int numericWay(Node head, int currentSum) {
        if (head == null) {
            return 0;
        }

        currentSum = currentSum * 10 + head.value;

        if (head.right == null && head.left == null) {
            return currentSum;
        }

        return numericWay(head.right, currentSum) + numericWay(head.left, currentSum);
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
        Node node1 = new Node(2, null, null);
        Node node2 = new Node(1, null, null);
        Node node3 = new Node(3, node1, node2);
        Node node4 = new Node(2, null, null);
        Node node5 = new Node(1, node4, node3);
        assert treeSolution(node5) == 275;
    }

    public static void main(String[] args) {
        Node node1 = new Node(2, null, null);
        Node node2 = new Node(1, null, null);
        Node node3 = new Node(3, node1, node2);
        Node node4 = new Node(2, null, null);
        Node node5 = new Node(1, node4, node3);
        System.out.println(treeSolution(node5) == 275);
    }

}
