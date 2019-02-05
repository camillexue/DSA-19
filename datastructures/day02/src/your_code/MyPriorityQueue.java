package your_code;

import java.util.LinkedList;

/**
 * An implementation of a priority Queue
 */
public class MyPriorityQueue {

    private LinkedList<Integer> ll;
    private LinkedList<Integer> maxElem = new LinkedList<>();

    public MyPriorityQueue() {
        ll = new LinkedList<>();
    }

    public void enqueue(int item) {
        int i = 0;
        if(!ll.isEmpty()) {
            while (item < ll.get(i) && i < ll.size()) {
                i++;
            }
        }
        ll.add(i, item);
    }

    /**
     * Return and remove the largest item on the queue.
     */
    public int dequeueMax() {
        return ll.removeFirst();
    }

}