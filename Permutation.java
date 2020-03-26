/* *****************************************************************************
 *  Name: Huong
 *  Date: 21
 *  Description: Yeu
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;

public class Permutation {
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        final RandomizedQueue<String> randomizedQueue = new RandomizedQueue<>();
        while (!StdIn.isEmpty()) {
            randomizedQueue.enqueue(StdIn.readString());
        }
        int i = 0;

        for (String s : randomizedQueue) {
            if (i == k) break;
            System.out.println(s);
            i++;
        }
    }
}
