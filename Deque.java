/* *****************************************************************************
 *  Name: Bao Khanh Dinh
 *  Date: 21/03/2020
 *  Description: Doubled-ended Queue
 **************************************************************************** */

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private Node first;
    private Node last;
    private int size;

    private class Node {
        Item data;
        Node next;
        Node prev;
        Node(final Item data) {
            this.data = data;
        }
    }

    // construct an empty deque
    public Deque() {
        this.size = 0;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return this.size == 0;
    }

    // return the number of items on the deque
    public int size() {
        return this.size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("added first item must be not null");
        }
        final Node oldFirst = this.first;
        final Node newFirst = new Node(item);
        if (oldFirst != null) {
            oldFirst.prev = newFirst;
            newFirst.next = oldFirst;
        }
        this.first = newFirst;
        this.size++;

        if (this.size == 1) {
            this.last = this.first; // init last
        }
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("added Last item must be not null");
        }
        final Node newNode = new Node(item);
        final Node oldLast = this.last;
        if (oldLast != null) {
            newNode.prev = oldLast;
            oldLast.next = newNode;
        }
        this.last = newNode;

        this.size++;

        if (this.size == 1) {
           this.first = this.last; // init first
        }
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (this.size == 0) {
            throw new NoSuchElementException();
        }
        final Node removingNode = this.first;
        final Node newFirst = this.first.next;
        this.first = newFirst;
        if (newFirst != null) {
           newFirst.prev = null;
        }
        this.size--;

        if (this.size == 0) {
            this.last = null;
        }

        return removingNode.data;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (this.size == 0) {
            throw new NoSuchElementException();
        }
        final Node removingNode = this.last;
        final Node newLast = this.last.prev;

        if (newLast != null) {
            newLast.next = null;
        }

        this.last = newLast;
        this.size--;

        if (this.size == 0) {
            this.first = null;
        }
        return removingNode.data;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new DequeIterator<Item>();
    }

    private class DequeIterator<Item> implements Iterator<Item> {
        Node current;

        DequeIterator() {
            current = first;
        }

        public boolean hasNext() {
            return current != null;
        }

        public Item next() {
            if (!this.hasNext()) {
               throw new NoSuchElementException();
            }
            final Node old = this.current;
            this.current = old.next;
            return (Item) old.data;
        }

        public void remove() {
            // do nothing
            throw new UnsupportedOperationException();
        }
    }


    // unit testing (required)
    public static void main(String[] args) {
        final Deque<Integer> stringDeque = new Deque<>();
        stringDeque.addLast(1);
        stringDeque.removeFirst();
        stringDeque.addFirst(3);
        stringDeque.removeLast();
        Iterator<Integer> iterator = stringDeque.iterator();
        int i = 1;
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
            i++;
        }
        System.out.println(i);
    }

}