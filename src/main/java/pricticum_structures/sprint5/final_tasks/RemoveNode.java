package pricticum_structures.sprint5.final_tasks;


/*
yandex contest: https://contest.yandex.ru/contest/24810/run-report/129530061/

Требовалось реализовать удаление узла из BST.

-- ПРИНЦИП РАБОТЫ АЛГОРИТМА --
Сначала узел проверяется на null - базовый случай рекурсии. Далее проверяется, если у узла всегда 1 ребенок (правый /
левый) - то узел просто заменяется этим ребенком. Если у узла двое детей, то мы ищем максимальный в левом поддереве,
заменяем его с узлом, рекурсивно обновляем левый узел.

-- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
Такой подход является корректным, потому что мы возвращаем обновленную ссылку на корень в конце, если дерево изменилось,
то мы об этом узнаем. Все узлы рекурсивно обновляются => сначала нужный узел для удаления найдется, потом все связанные с ним узлы
будут обновлены.

-- ВРЕМЕННАЯ СЛОЖНОСТЬ --
Итоговая временная сложность алгоритма рассчитывается таким образом:
N - число элементов в дереве
H - Высота дерева
- Поиск нужной ноды: O(log(N)) (если один ребенок)
- Поиск нужной ноды (если 2 ребенка, поиск ноды на замену): O(H)
=> O(H) + O(logN) ==> O(H) (H > log(N));

-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
Доп. структур не используется, память только на стек.
 - Если один ребенок: O(logN)
 - Если два ребенка: O(H)
 ==> O(H)
 */

// <template>
class Node {
    private int value;
    private Node left;
    private Node right;

    Node(Node left, Node right, int value) {
        this.left = left;
        this.right = right;
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
// <template>

public class RemoveNode {
    public static Node remove(Node root, int key) {
        if (root == null) {
            return null;
        }

        if (root.getValue() < key) {
            root.setRight(remove(root.getRight(), key));
        } else if (root.getValue() > key) {
            root.setLeft(remove(root.getLeft(), key));
        } else {
            if (root.getLeft() == null || root.getRight() == null) {
                root = root.getLeft() == null ? root.getRight() : root.getLeft();
            } else {
                Node leftMaxNode = getMax(root.getLeft());
                root.setValue(leftMaxNode.getValue());
                root.setLeft(remove(root.getLeft(), leftMaxNode.getValue()));
            }
        }

        return root;
    }

    private static Node getMax(Node cur) {
        while (cur != null && cur.getRight() != null) {
            cur = cur.getRight();
        }
        return cur;
    }

    private static void test() {
        Node node1 = new Node(null, null, 2);
        Node node2 = new Node(node1, null, 3);
        Node node3 = new Node(null, node2, 1);
        Node node4 = new Node(null, null, 6);
        Node node5 = new Node(node4, null, 8);
        Node node6 = new Node(node5, null, 10);
        Node node7 = new Node(node3, node6, 5);
        Node newHead = remove(node7, 10);
        assert newHead.getValue() == 5;
        assert newHead.getRight() == node5;
        assert newHead.getRight().getValue() == 8;
    }

    public static void main(String[] args) {
        Node node1 = new Node(null, null, 2);
        Node node2 = new Node(node1, null, 3);
        Node node3 = new Node(null, node2, 1);
        Node node4 = new Node(null, null, 6);
        Node node5 = new Node(node4, null, 8);
        Node node6 = new Node(node5, null, 10);
        Node node7 = new Node(node3, node6, 5);
        Node newHead = remove(node7, 10);
        System.out.println(newHead.getValue());
        System.out.println(newHead.getRight() == node5);
        System.out.println(newHead.getRight().getValue() == 8);
        assert newHead.getValue() == 5;
        assert newHead.getRight() == node5;
        assert newHead.getRight().getValue() == 8;
    }
}