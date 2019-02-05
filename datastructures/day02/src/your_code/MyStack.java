package your_code;
import ADTs.StackADT;

import java.util.LinkedList;

/**
 * An implementation of the Stack interface.
 */
public class MyStack implements StackADT<Integer> {

    private LinkedList<Integer> ll;
    private LinkedList<Integer> maxElem = new LinkedList<>();

    public MyStack() {
        ll = new LinkedList<>();
    }

    @Override
    public void push(Integer e) {
        ll.addFirst(e);
        if (maxElem.isEmpty()) {maxElem.addFirst(e);}
        else if (e >= maxElem.getFirst()) {
            maxElem.addFirst(e);
        }
    }

    @Override
    public Integer pop() {
        if (maxElem.getFirst() == ll.getFirst()) {
            maxElem.removeFirst();
        }
        Integer pop = ll.removeFirst();
        return pop;
    }

    @Override
    public boolean isEmpty() {
        return ll.isEmpty();
    }

    @Override
    public Integer peek() {
        return ll.getFirst();
    }

    public Integer maxElement() {
        return maxElem.getFirst();
    }
}
