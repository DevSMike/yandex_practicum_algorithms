package pricticum_structures.sprint5;


// значение ноды должно укладываться в диапазон, тогда дерево - бинарное дерево поиска
public class SolutionBinaryTree {
    public static boolean treeSolution(Node head) {
        // Your code
        // “ヽ(´▽｀)ノ”
        return isBinarySearchTree(head, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }


    private static boolean isBinarySearchTree(Node node, int min, int max) {

        if (node == null) {
            return true;
        }

        if (node.value < min || node.value > max) {
            return false;
        }

        return isBinarySearchTree(node.right, node.value + 1, max)
                && isBinarySearchTree(node.left, min, node.value - 1);
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
        Node node2 = new Node(4, null, null);
        Node node3 = new Node(3, node1, node2);
        Node node4 = new Node(8, null, null);
        Node node5 = new Node(5, node3, node4);
        assert treeSolution(node5);
        node2.value = 5;
        assert !treeSolution(node5);
    }


    public static void main(String[] args) {
        Node node1 = new Node(1, null, null);
        Node node2 = new Node(4, null, null);
        Node node3 = new Node(3, node1, node2);
        Node node4 = new Node(8, null, null);
        Node node5 = new Node(5, node3, node4);
        System.out.println(treeSolution(node5));
        node2.value = 5;
        System.out.println(treeSolution(node5));
    }
}