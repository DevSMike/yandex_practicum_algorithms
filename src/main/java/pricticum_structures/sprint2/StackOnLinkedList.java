package pricticum_structures.sprint2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class StackNode {
    int idx;
    int data;
    StackNode next;

    public StackNode(int idx, int data, StackNode next) {
        this.idx = idx;
        this.data = data;
        this.next = next;
    }
}

class StackMaxEffective {

    private StackNode head;
    private int size = 0;
    private int indexCounter = 0;

    public void push(String elem) {
        int data = Integer.parseInt(elem);
        if (head == null) {
            head = new StackNode(indexCounter++, data, null);
        } else if (data > head.data) {
            head = new StackNode(indexCounter++, data, head);
        } else {
            head.next = new StackNode(indexCounter++, data, head.next);
        }
        size++;
    }


    public String pop() {
        if (size == 0) {
            return "error";
        }
        int result;
        if (head.next == null) {
            result = head.data;
            head = null;
        } else if (head.idx > head.next.idx) {
            result = head.data;
            head = head.next;
        } else {
            result = head.next.data;
            head.next = head.next.next;
        }
        size--;
        return String.valueOf(result);
    }

    public String getMax() {
        if (size == 0) {
            return "None";
        }
        return String.valueOf(head.data);
    }

    public String getTopElem() {
        if (size == 0) {
            return "error";
        }
        int data;
        if (head.next == null) {
           data = head.data;
        } else if (head.idx > head.next.idx) {
            data = head.data;
        } else {
            data = head.next.data;
        }
        return String.valueOf(data);
    }

    public void printStack() {
        StackNode head = this.head;
        StringBuilder buffer = new StringBuilder();
        for (int i = 0; i < size; i++) {
            buffer.append(head.data).append(" ");
            head = head.next;
        }
        System.out.println(buffer);
    }


}

public class StackOnLinkedList {

    public static void main(String[] args) throws IOException  {
        StackMaxEffective stack = new StackMaxEffective();

        StringBuilder buffer = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int n = Integer.parseInt(reader.readLine());
            for (int i = 0; i < n; i++) {
                StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
                chooseStackCommand(stack, tokenizer, buffer);
            }
        }
        System.out.println(buffer);
    }

    private static void chooseStackCommand(StackMaxEffective stack, StringTokenizer tokenizer, StringBuilder buffer) {
        switch (tokenizer.nextToken()) {
            case "push": {
                stack.push(tokenizer.nextToken());
                break;
            }
            case "pop": {
                String result = stack.pop();
                if (result.equals("error")) {
                    buffer.append(result).append("\n");
                }
                break;
            }
            case "top" :{
                buffer.append(stack.getTopElem()).append("\n");
                break;
            }
            case "get_max": {
                buffer.append(stack.getMax()).append("\n");
                break;
            }
        }
    }
}
