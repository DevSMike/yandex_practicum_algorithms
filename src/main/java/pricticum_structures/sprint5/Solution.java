package pricticum_structures.sprint5;

public class Solution {
    public static int treeSolution(Node head) {
        int max = head.value;
        max = findMaxInPath(head.left, max);
        max = findMaxInPath(head.right, max);
        return max;
    }

    private static int findMaxInPath(Node transferNode, int max) {
        while (transferNode != null) {
            max = Math.max(transferNode.value, max);

            if (isNodeExist(transferNode.left) && isNodeExist(transferNode.right)) {
                transferNode = transferNode.left.value > transferNode.right.value ? transferNode.left : transferNode.right;
            } else if (isNodeExist(transferNode.left)) {
                transferNode = transferNode.left;
            } else {
                transferNode = transferNode.right;
            }
        }
        return max;
    }

    private static boolean isNodeExist(Node node) {
        return node != null;
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
    }
    // <template>


    private static void test() {
        Node node1 = new Node(1);
        Node node2 = new Node(-5);
        Node node3 = new Node(3);
        node3.left = node1;
        node3.right = node2;
        Node node4 = new Node(2);
        node4.left = node3;
        assert treeSolution(node4) == 3;
    }

    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(-5);
        Node node3 = new Node(3);
        node3.left = node1;
        node3.right = node2;
        Node node4 = new Node(2);
        node4.left = node3;
        System.out.println(treeSolution(node4));
        // assert treeSolution(node4) == 3;
    }
}