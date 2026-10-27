package by.it.group551004.fedkovich.lesson10;

import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Queue;

public class MyPriorityQueue<E extends Comparable<? super E>> implements Queue<E> {
    private final int INITIAL_CAPACITY = 2;
    private Object data[] = new Object[INITIAL_CAPACITY];
    private int cap = INITIAL_CAPACITY;
    private int len = 0;

    private void ensureAdditionalCapacity(int newElements) {
        if (len + newElements <= cap)
            return;

        int newCap = cap;
        while (len + newElements > newCap) {
            newCap = newCap + (newCap >>> 1);
        }

        Object[] newData = new Object[newCap];
        System.arraycopy(data, 0, newData, 0, len);

        data = newData;
        cap = newCap;
    }

    private void siftUp(int i) {
        E e = (E) data[i];

        while (i > 0) {
            int parent = (i - 1) >>> 1;
            if (e.compareTo((E) data[parent]) >= 0)
                break;

            data[i] = data[parent];
            i = parent;
        }
        data[i] = e;
    }

    private void siftDown(int i) {
        E e = (E) data[i];
        int half = len >>> 1;

        while (i < half) {
            int child = (i << 1) + 1;
            E c = (E) data[child];
            int right = child + 1;

            if (right < len && c.compareTo((E) data[right]) > 0) {
                child = right;
                c = (E) data[right];
            }

            if (e.compareTo(c) <= 0) {
                break;
            }

            data[i] = c;
            i = child;
        }
        data[i] = e;
    }

    private void heapify() {
        for (int i = (len >>> 1) - 1; i >= 0; --i)
            siftDown(i);
    }

    private E remove(int i) {
        if (i < 0 || i >= len)
            throw new IndexOutOfBoundsException();

        E e = (E) data[i];

        --len;
        data[i] = data[len];
        data[len] = null;
        siftDown(i);

        return e;
    }

    public String toString() {
        StringBuilder res = new StringBuilder();

        res.append("[");
        if (len > 0) {
            res.append(data[0]);
            for (int i = 1; i < len; ++i) {
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
    public void clear() {
        for (int i = 0; i < len; ++i) {
            data[i] = null;
        }
        len = 0;
    }

    @Override
    public boolean add(E e) {
        ensureAdditionalCapacity(1);

        data[len++] = e;
        siftUp(len - 1);

        return true;
    }

    @Override
    public E remove() {
        return remove(0);
    }

    @Override
    public boolean contains(Object o) {
        for (int i = 0; i < len; ++i) {
            if (data[i].equals(o))
                return true;
        }

        return false;
    }

    @Override
    public boolean offer(E e) {
        return add(e);
    }

    @Override
    public E poll() {
        return remove();
    }

    @Override
    public E peek() {
        if (len == 0)
            return null;

        return (E) data[0];
    }

    @Override
    public E element() {
        if (len == 0)
            throw new NoSuchElementException();

        return (E) data[0];
    }

    @Override
    public boolean isEmpty() {
        return len == 0;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object o : c) {
            boolean found = false;

            for (Object e : data) {
                if (o.equals(e)) {
                    found = true;
                }
            }

            if (!found) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        int oldLen = len;
        for (E e : c) {
            add(e);
        }

        return len != oldLen;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        if (c.isEmpty())
            return false;

        int newLen = 0;

        for (int i = 0; i < len; ++i) {
            if (!c.contains(data[i])) {
                data[newLen++] = data[i];
            }
        }

        if (newLen != len) {
            for (int i = newLen; i < cap; ++i) {
                data[i] = null;
            }

            len = newLen;
            heapify();
            return true;
        }

        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        if (c.isEmpty() && len > 0) {
            clear();
            return true;
        }

        int newLen = 0;

        for (int i = 0; i < len; ++i) {
            if (c.contains(data[i])) {
                data[newLen++] = data[i];
            }
        }

        if (newLen != len) {
            for (int i = newLen; i < cap; ++i) {
                data[i] = null;
            }

            len = newLen;
            heapify();
            return true;
        }

        return false;
    }

    // ================= OPTIONAL ==================
    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }
}
