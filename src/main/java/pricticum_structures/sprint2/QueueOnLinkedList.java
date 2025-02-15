package pricticum_structures.sprint2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class MyListQueue {
    private static class Node {
        int data;
        Node next;
        Node prev;

        public Node(int data, Node next, Node prev) {
            this.data = data;
            this.next = next;
            this.prev = prev;
        }
    }

    private Node head;
    private Node tail;
    private int size;

    public MyListQueue() {
        this.size = 0;
    }

    public void put(int x) {
        if (size == 0) {
            head = new Node(x, null, null);
        } else if (size == 1) {
            tail = new Node(x, null, head);
            head.next = tail;
        } else {
            Node newTail = new Node(x, null, tail);
            tail.next = newTail;
            tail = newTail;
        }
        size++;
    }

    public String get() {
        if (size == 0) {
            return "error";
        }
        int data = head.data;
        head = head.next;
        size--;
        return Integer.toString(data);
    }

    public int getSize() {
        return size;
    }
}

public class QueueOnLinkedList {

    public static void main(String[] args) throws IOException {
        MyListQueue queue = new MyListQueue();
        StringBuilder buffer = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int n = Integer.parseInt(reader.readLine());
            for (int i = 0; i < n; i++) {
                StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
                chooseCommand(queue, buffer, tokenizer);
            }
        }
        System.out.println(buffer);
    }

    private static void chooseCommand(MyListQueue queue, StringBuilder buffer, StringTokenizer tokenizer) {
        switch (tokenizer.nextToken()) {
            case "put": {
                queue.put(Integer.parseInt(tokenizer.nextToken()));
                break;
            }
            case "get": {
                buffer.append(queue.get()).append("\n");
                break;
            }
            case "size": {
                buffer.append(queue.getSize()).append("\n");
                break;
            }
        }
    }

}
