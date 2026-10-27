package by.it.group551004.fedkovich.lesson10;

import java.util.Collection;
import java.util.Iterator;
import java.util.Deque;

public class MyArrayDeque<E> implements Deque<E> {
    private final static int INITIAL_CAPACITY = 2;
    private Object[] data = new Object[INITIAL_CAPACITY];
    private int cap = INITIAL_CAPACITY;
    private int len = 0;
    private int head = 0;
    private int tail = 0;

    private int headLeft() {
        if (len == 0)
            return head;

        if (head == 0) {
            head = cap - 1;
        } else {
            --head;
        }
        return head;
    }

    private int headRight() {
        if (len == 0)
            return head;

        ++head;
        if (head == cap) {
            head = 0;
        }
        return head;
    }

    private int tailLeft() {
        if (len == 0)
            return tail;

        if (tail == 0) {
            tail = cap - 1;
        } else {
            --tail;
        }
        return tail;
    }

    private int tailRight() {
        if (len == 0)
            return tail;

        ++tail;
        if (tail == cap) {
            tail = 0;
        }
        return tail;
    }

    private void ensureAdditionalLength(int newElements) {
        if (len + newElements <= cap)
            return;

        int newCap = cap;
        while (len + newElements > newCap) {
            newCap = newCap + (newCap >> 1);
        }

        Object[] newData = new Object[newCap];

        if (head < tail) {
            System.arraycopy(data, head, newData, 0, len);
            head = 0;
            tail = (len == 0) ? 0 : len - 1;
        } else if (tail < head) {
            int headToEnd = cap - head;
            int startToTail = tail + 1;

            System.arraycopy(data, head, newData, 0, headToEnd);
            System.arraycopy(data, 0, newData, headToEnd, startToTail);

            head = 0;
            tail = len - 1;
        } else {
            newData[0] = data[0];
        }

        cap = newCap;
        data = newData;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();

        res.append("[");
        if (len > 0) {
            int i = head;
            res.append(data[i]);

            while (i != tail) {
                i = (i + 1) % cap;
                res.append(", ").append(data[i]);
            }
        }
        res.append("]");

        return res.toString();
    }

    @Override
    public int size() {
        return len;
    }

    @Override
    public boolean add(E e) {
        addLast(e);
        return true;
    }

    @Override
    public void addFirst(E e) {
        ensureAdditionalLength(1);
        data[headLeft()] = e;
        ++len;
    }

    @Override
    public void addLast(E e) {
        ensureAdditionalLength(1);
        data[tailRight()] = e;
        ++len;
    }

    @Override
    public E element() {
        return getFirst();
    }

    @Override
    public E getFirst() {
        return (E) data[head];
    }

    @Override
    public E getLast() {
        return (E) data[tail];
    }

    @Override
    public E poll() {
        return pollFirst();
    }

    @Override
    public E pollFirst() {
        if (len == 0)
            return null;

        Object e = data[head];
        data[head] = null;
        headRight();
        --len;

        return (E) e;
    }

    @Override
    public E pollLast() {
        if (len == 0)
            return null;

        Object e = data[tail];
        data[tail] = null;
        tailLeft();
        --len;

        return (E) e;
    }

    // ================= OPTIONAL ==================
    @Override
    public boolean offerFirst(E e) {
        return false;
    }

    @Override
    public boolean offerLast(E e) {
        return false;
    }

    @Override
    public E removeFirst() {
        return null;
    }

    @Override
    public E removeLast() {
        return null;
    }

    @Override
    public E peekFirst() {
        return null;
    }

    @Override
    public E peekLast() {
        return null;
    }

    @Override
    public boolean removeFirstOccurrence(Object o) {
        return false;
    }

    @Override
    public boolean removeLastOccurrence(Object o) {
        return false;
    }

    @Override
    public boolean offer(E e) {
        return false;
    }

    @Override
    public E remove() {
        return null;
    }

    @Override
    public E peek() {
        return null;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return false;
    }

    @Override
    public void push(E e) {

    }

    @Override
    public E pop() {
        return null;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public Iterator<E> descendingIterator() {
        return null;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }
}
