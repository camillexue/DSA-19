public class MyArrayList {
    private Cow[] elems;
    private int size;

    // TODO: Runtime: O(1)
    public MyArrayList() {
        this(10);
    }

    // TODO: Runtime: O(1)
    public MyArrayList(int capacity) {
        elems = new Cow[capacity];
        size = 0;
    }

    // TODO: Runtime: O(N) O(1)* on average
    public void add(Cow c) {
        elems[size] = c;
        size++;

        if (size == elems.length)
            increaseSize();

    }

    // TODO: Runtime: O(1)
    public int size() {
        return size;
    }

    // TODO: Runtime: O(1)
    public Cow get(int index) {
        if (index < size) {
            return elems[index];
        }
        else {
            throw new IndexOutOfBoundsException("Index out of bounds.");
        }
    }

    // TODO: Runtime: O(N)
    public Cow remove(int index) {
        if(index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds.");
        }

        Cow removedCow = get(index);
        while (index < size - 1) {
            elems[index] = elems[index+1];
            index++;
        }

        size--;
        if (size < .25*elems.length && size >1)
            decreaseSize();
        return removedCow;
    }

    // TODO: Runtime: O(N)
    public void add(int index, Cow c) {
        if(index > size)
            throw new IndexOutOfBoundsException("Index out of bounds.");

        if (size == elems.length)
            increaseSize();

        for(int i = index; i < size; i++) {
            elems[i+1] = elems[i];
        }
        elems[index] = c;
        size++;

    }

    // Runtime: O(N)
    public void decreaseSize() {
        Cow[] newElems = new Cow[elems.length / 2];
        System.arraycopy(elems, 0, newElems, 0, elems.length / 2);
        elems = newElems;
    }

    // Runtime: O(N)
    public void increaseSize() {
        Cow[] newElems = new Cow[elems.length * 2];
        System.arraycopy(elems, 0, newElems, 0, size);
        elems = newElems;
    }
}