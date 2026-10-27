package by.it.group551004.fedkovich.lesson10;

import java.util.*;

public class MyLinkedList<E> implements Deque<E> {
    private Node head = null;
    private Node tail = null;
    private int len = 0;

    private class Node {
        E data;
        Node next;
        Node prev;

        Node(E data, Node prev, Node next) {
            this.data = data;
            this.prev = prev;
            this.next = next;
        }
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        Node node = head;

        res.append("[");
        if (node != null) {
            res.append(node.data);
            node = node.next;

            while (node != null) {
                res.append(", ").append(node.data);
                node = node.next;
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
        Node node = new Node(e, null, head);
        if (head == null) {
            head = node;
            tail = node;
        } else {
            head.prev = node;
            head = node;
        }

        ++len;
    }

    @Override
    public void addLast(E e) {
        Node node = new Node(e, tail, null);
        if (tail == null) {
            head = node;
            tail = node;
        } else {
            tail.next = node;
            tail = node;
        }
        ++len;
    }

    public E remove(int index) {
        if (index >= len)
            throw new IndexOutOfBoundsException();

        Node node = head;
        for (int i = 0; i < index; ++i) {
            node = node.next;
        }

        E e = node.data;

        Node prev = node.prev;
        if (prev != null) {
            prev.next = node.next;
        }

        Node next = node.next;
        if (next != null) {
            next.prev = node.prev;
        }

        --len;
        return e;
    }

    @Override
    public boolean remove(Object o) {
        Node node = head;

        while (node != null && node.data != o) {
            node = node.next;
        }

        if (node == null) {
            return false;
        }

        Node prev = node.prev;
        if (prev != null) {
            prev.next = node.next;
        }

        Node next = node.next;
        if (next != null) {
            next.prev = node.prev;
        }

        --len;
        return true;
    }

    @Override
    public E element() {
        return getFirst();
    }

    @Override
    public E getFirst() {
        if (head == null)
            return null;

        return head.data;
    }

    @Override
    public E getLast() {
        if (tail == null)
            return null;

        return tail.data;
    }

    @Override
    public E poll() {
        return pollFirst();
    }

    @Override
    public E pollFirst() {
        if (head == null)
            return null;

        E e = head.data;
        head = head.next;

        if (head == null) {
            tail = null;
        } else {
            head.prev = null;
        }

        --len;
        return e;
    }

    @Override
    public E pollLast() {
        if (tail == null)
            return null;

        E e = tail.data;
        tail = tail.prev;

        if (tail == null) {
            head = null;
        } else {
            tail.next = null;
        }

        --len;
        return e;
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

    @Override
    public void push(E e) {

    }

    @Override
    public E pop() {
        return null;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public boolean isEmpty() {
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

    @Override
    public Iterator<E> descendingIterator() {
        return null;
    }
}
