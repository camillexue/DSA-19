package your_code;

public class MyLinkedList {

    private Node head;
    private Node tail;
    private int size;

    private class Node {
        Chicken val;
        Node prev;
        Node next;

        private Node(Chicken d, Node prev, Node next) {
            this.val = d;
            this.prev = prev;
            this.next = next;
        }

        private Node(Chicken d) {
            this.val = d;
            prev = null;
            next = null;
        }
    }

    public MyLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void add(Chicken c) {
        addLast(c);
    }

    public Chicken pop() {
        return removeLast();
    }

    public void addLast(Chicken c) {
        Node newChicken = new Node(c, tail, null);
        if(size == 0) {
            head = newChicken;
            tail = newChicken;
        }
        else {
            tail.next = newChicken;
            tail = newChicken;
        }
        size++;
    }

    public void addFirst(Chicken c) {
        Node newChicken = new Node(c, null, head);
        if(size == 0) {
            head = newChicken;
            tail = newChicken;
        }
        else {
            head.prev = newChicken;
            newChicken.next = head;
            head = newChicken;
        }
        size++;
    }

    public Chicken get(int index) {
        Node curr = head;
        if (index > size - 1) {
            throw new IndexOutOfBoundsException("Index out of bounds.");
        }
        else {
            for (int i = 0; i < index; i++) {
                curr = curr.next;
            }

            return curr.val;
        }
    }

    public Chicken remove(int index) {
        Node curr = head;
        if (index > size - 1) {
            throw new IndexOutOfBoundsException("Index out of bounds.");
        }
        else {
            if(index == 0) {return removeFirst();}
            if(index == size-1) {return removeLast();}

            for (int i = 0; i < index; i++) {
                curr = curr.next;
            }
            curr.prev.next = curr.next;
            curr.next.prev = curr.prev;

            size--;
        }
        return curr.val;
    }

    public Chicken removeFirst() {
        if(size == 0) {
            return null;
        }
        else {
            Node oldHead = head;
            if (head.next != null) {
                head = head.next;
                head.prev = null;
            }
            else {
                head = null;
                tail = null;
            }
            size--;

            return oldHead.val;
        }
    }

    public Chicken removeLast() {
        if(size == 0) {
            return null;
        }
        else {
            Node oldTail = tail;
            if (tail.prev != null) {
                tail = tail.prev;
                tail.next = null;
            }
            else {
                head = null;
                tail = null;
            }
            size--;

            return oldTail.val;
        }
    }
}