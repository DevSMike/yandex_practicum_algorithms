package pricticum_structures.sprint5;

// Проверяем, что дерево сбалансированное, поиском длин двух поддеревьев
public class BalancedTree  {
    public static boolean treeSolution(Node head) {
        // Your code
        // “ヽ(´▽｀)ノ”
        return isBalanced(head) > 0;
    }

    private static int isBalanced(Node root) {

        if (root == null) {
            return 0;
        }

        int leftHeight = isBalanced(root.left);
        if (leftHeight == -1) {
            return -1;
        }

        int rightHeight = isBalanced(root.right);
        if (rightHeight == -1) {
            return -1;
        }

        if (Math.abs(leftHeight - rightHeight) > 1) {
            return -1;
        } else {
            return Math.max(rightHeight, leftHeight) + 1;
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
    }
    // <template>

    private static void test() {
        Node node1 = new Node(1);
        Node node2 = new Node(-5);
        Node node3 = new Node(3);
        node3.left = node1;
        node3.right = node2;
        Node node4 = new Node(10);
        Node node5 = new Node(2);
        node5.left = node3;
        node5.right = node4;
        assert treeSolution(node5);
    }

    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(-5);
        Node node3 = new Node(3);
        node3.left = node1;
        node3.right = node2;
        Node node4 = new Node(10);
        Node node5 = new Node(2);
        node5.left = node3;
        node5.right = node4;
        System.out.println(treeSolution(node5));
        assert treeSolution(node5);
    }
}