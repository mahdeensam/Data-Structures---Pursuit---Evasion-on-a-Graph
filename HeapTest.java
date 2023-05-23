/*
Mahdeen Ahmed Khan Sameer
Project 7
HeapTest Class: To test Heap class
 */


import java.util.Comparator;
import java.util.*;


public class HeapTest {

    public static void main(String[] args) {
        testSize();
        testPeek();
        testOffer();
        testPoll();
        testUpdatePriority();
        testComparator();
    }

    private static void testSize() {
        Heap<Integer> heap = new Heap<>();
        assert heap.size() == 0 : "Heap size should initially be 0";

        heap.offer(1);
        assert heap.size() == 1 : "Heap size should be 1 after one offer";

        heap.offer(2);
        assert heap.size() == 2 : "Heap size should be 2 after two offers";

        heap.poll();
        assert heap.size() == 1 : "Heap size should be 1 after one poll";

        System.out.println("testSize passed");
    }

    private static void testPeek() {
        Heap<Integer> heap = new Heap<>();
        boolean exceptionThrown = false;
        try {
            heap.peek();
        } catch (Exception e) {
            exceptionThrown = true;
        }
        assert exceptionThrown : "Peek should throw an exception on an empty heap";

        heap.offer(1);
        assert heap.peek() == 1 : "Peek should return the first item offered to the heap";

        heap.offer(2);
        assert heap.peek() == 2 : "Peek should return the highest priority item";

        System.out.println("testPeek passed");
    }

    private static void testOffer() {
        Heap<Integer> heap = new Heap<>();
        heap.offer(1);
        assert heap.peek() == 1 : "Heap should contain the offered item";

        heap.offer(2);
        assert heap.peek() == 2 : "Heap should reorder items based on priority";

        System.out.println("testOffer passed");
    }

    private static void testPoll() {
        Heap<Integer> heap = new Heap<>();
        boolean exceptionThrown = false;
        try {
            heap.poll();
        } catch (Exception e) {
            exceptionThrown = true;
        }
        assert exceptionThrown : "Poll should throw an exception on an empty heap";

        heap.offer(1);
        assert heap.poll() == 1 : "Poll should return the first item offered to the heap";
        assert heap.size() == 0 : "Heap should be empty after polling the only item";

        heap.offer(2);
        heap.offer(3);
        assert heap.poll() == 3 : "Poll should return the highest priority item";

        System.out.println("testPoll passed");
    }

    private static void testUpdatePriority() {
        Heap<Integer> heap = new Heap<>();
        heap.offer(1);
        heap.offer(2);
        heap.updatePriority(1);
        assert heap.peek() == 2 : "After updating the priority, the item should be reordered in the heap";

        System.out.println("testUpdatePriority passed");
    }

    private static void testComparator() {
        Heap<Integer> heap = new Heap<>(Comparator.<Integer>reverseOrder());
        heap.offer(1);
        heap.offer(2);
        assert heap.peek() == 1 : "Heap should use the provided comparator";
    
        System.out.println("testComparator passed");
    }
}    