public class Stack {
    private Node top;

    public Stack() {
        this.top = null;
    }

    public void push(char data) {
        Node newNode = new Node(data);
        newNode.next = top;
        top = newNode;
    }

    public char pop() {
        if (isEmpty()) throw new RuntimeException("Stack is empty");
        char data = top.data;
        top = top.next;
        return data;
    }

    public char peek() {
        if (isEmpty()) throw new RuntimeException("Stack is empty");
        return top.data;
    }

    public boolean isEmpty() {
        return top == null;
    }
}
