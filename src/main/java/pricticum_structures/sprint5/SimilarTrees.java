package pricticum_structures.sprint5;

public class SimilarTrees {

    public static boolean treeSolution(Node head1, Node head2) {
        // Your code
        // “ヽ(´▽｀)ノ”
        StringBuilder bufferForHead1 = new StringBuilder();
        StringBuilder bufferForHead2 = new StringBuilder();

        printLMR(head1, bufferForHead1);
        printLMR(head2, bufferForHead2);

        return bufferForHead1.toString().contentEquals(bufferForHead2);
    }

    private static void printLMR(Node head, StringBuilder buffer) {
        if (head.left != null) {
            printLMR(head.left, buffer);
        }

        buffer.append(head.value);

        if (head.right != null) {
            printLMR(head.right, buffer);
        }
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
        Node node1 = new Node(1, null, null);
        Node node2 = new Node(2, null, null);
        Node node3 = new Node(3, node1, node2);
        Node node4 = new Node(1, null, null);
        Node node5 = new Node(2, null, null);
        Node node6 = new Node(3, node4, node5);
        assert treeSolution(node3, node6);
    }

    public static void main(String[] args) {

//        Node node2 = new Node(3, null, null);
//        Node node1 = new Node(1, node2, null);
//        Node node3 = new Node(0, node1, null);
//        Node node4 = new Node(3, null, null);
//        Node node5 = new Node(1, node4, null);
//        Node node6 = new Node(0, null, node5);
        Node node1 = new Node(1, null, null);
        Node node2 = new Node(2, null, null);
        Node node3 = new Node(3, node1, node2);
        Node node4 = new Node(1, null, null);
        Node node5 = new Node(2, null, null);
        Node node6 = new Node(3, node4, node5);
        System.out.println(treeSolution(node3, node6));
    }
}
