package pricticum_structures.sprint5;

public class TreeAnagram {

    public static boolean treeSolution(Node head) {
        StringBuilder bufferForLMR = new StringBuilder();
        StringBuilder bufferForRML = new StringBuilder();
        if (head.left != null) {
            printLMR(head.left, bufferForLMR);
        }
        if (head.right != null) {
            printRML(head.right, bufferForRML);
        }
        return bufferForRML.toString().contentEquals(bufferForLMR);
    }


    private static void printLMR(Node root, StringBuilder buffer) {
        if (root.left != null) {
            printLMR(root.left, buffer);
        }
        buffer.append(root.value);
        if (root.right != null) {
            printLMR(root.right, buffer);
        }
    }

    private static void printRML(Node root, StringBuilder buffer) {
        if (root.right != null) {
            printRML(root.right, buffer);
        }
        buffer.append(root.value);
        if (root.left!= null) {
            printRML(root.left, buffer);
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
        Node node1 = new Node(3,  null,  null);
        Node node2 = new Node(4,  null,  null);
        Node node3 = new Node(4,  null,  null);
        Node node4 = new Node(3,  null,  null);
        Node node5 = new Node(2, node1, node2);
        Node node6 = new Node(2, node3, node4);
        Node node7 = new Node(1, node5, node6);
        assert treeSolution(node7);
    }

    public static void main(String[] args) {
        Node node1 = new Node(3,  null,  null);
        Node node2 = new Node(4,  null,  null);
        Node node3 = new Node(4,  null,  null);
        Node node4 = new Node(3,  null,  null);
        Node node5 = new Node(2, node1, node2);
        Node node6 = new Node(2, node3, node4);
        Node node7 = new Node(1, node5, node6);
       System.out.println(treeSolution(node7));
    }
}
