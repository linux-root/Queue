import edu.princeton.cs.algs4.StdRandom;
import java.util.Iterator;
import java.util.NoSuchElementException;

/* *****************************************************************************
 *  Name: Bao Khanh Dinh
 *  Date:
 *  Description:
 **************************************************************************** */
public class RandomizedQueue<Item> implements Iterable<Item> {
   private Node first;
   private int size;

   private class Node {
       Item data;
       Node next;

       Node(Item item) {
           this.data = item;
       }

       Node(final Node source) {
           if (source == null) return;
           this.data = source.data;
           Node sourcePointer = source.next;
           Node thisPointer = this;
           while (sourcePointer != null) {
               thisPointer.next = new Node(sourcePointer.data);
               sourcePointer = sourcePointer.next;
               thisPointer = thisPointer.next;
           }
       }
   }

    // construct an empty randomized queue
    public RandomizedQueue() {
       this.size = 0;
    }

    private Node cloneFirst() {
       if (this.first == null) return null;
       return new Node(this.first);
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return this.size == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return this.size;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("item cannot be null");
        }
        final Node newNode = new Node(item);
        newNode.next = this.first;
        this.first = newNode;
        this.size++;
    }

    // remove and return a random item
    public Item dequeue() {
        if (this.isEmpty()) {
            throw new NoSuchElementException();
        }

        int random = StdRandom.uniform(0, this.size);
        if (random == 0) {
            final Node removedNode = this.first;
            this.first = this.first.next;
            this.size--;
            return removedNode.data;
        }

        int counter = 0;
        Node beforeRemovedNode = this.first;

        while (counter < random - 1) {
            beforeRemovedNode = beforeRemovedNode.next;
            counter++;
        }

        final Node removedNode = beforeRemovedNode.next;
        beforeRemovedNode.next = removedNode.next;
        this.size--;
        return removedNode.data;
    }

    // return a random item (but do not remove it)
    public Item sample() {
       if (this.isEmpty()) {
           throw new NoSuchElementException();
       }
        int random = StdRandom.uniform(0, this.size);
        Node sample = this.first;
        int counter = 0;
        while (counter != random && sample != null) {
            sample = sample.next;
             counter++;
        }
        return sample.data;
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomizedIterator<>();
    }

    private class RandomizedIterator<Item> implements Iterator<Item> {
       Node firstCopy;
       int size;

        RandomizedIterator() {
            this.firstCopy = cloneFirst();
            this.size = size();
        }

        public boolean hasNext() {
            return this.size > 0;
        }

        public Item next() {
            if (!this.hasNext()) {
                throw new NoSuchElementException();
            }
            // pop out;
            int random = StdRandom.uniform(0, this.size);

            if (random == 0) {
                Node removedNode = this.firstCopy;
                this.firstCopy = this.firstCopy.next;
                this.size--;
                return (Item) removedNode.data;
            }

            Node beforeRemovedNode = this.firstCopy;
            int count = 0;
            while (count < random - 1) {
               beforeRemovedNode = beforeRemovedNode.next;
               count++;
            }
            final Node removedNode = beforeRemovedNode.next;
            beforeRemovedNode.next = removedNode.next;
            this.size--;
            return (Item) removedNode.data;
        }

        public void remove() {
            // do nothing
            throw new UnsupportedOperationException();
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<String> randomizedQueue = new RandomizedQueue<>();
        randomizedQueue.enqueue("Dinh");
        randomizedQueue.enqueue("Bao");
        randomizedQueue.enqueue("Khanh");
        randomizedQueue.enqueue("Thu");
        randomizedQueue.enqueue("Huong");

        for (String s : randomizedQueue) {
            System.out.println(s);
        }
    }

}