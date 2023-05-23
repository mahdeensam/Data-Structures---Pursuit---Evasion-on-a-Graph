/*
Mahdeen Ahmed Khan Sameer
Project 7
Heap Class: To implement a priority queue using a heap data structure, allowing insertion, retrieval, and removal of elements with their priorities efficiently.
 */

import java.util.Comparator;
import java.util.NoSuchElementException;

public class Heap<T extends Comparable<T>> implements PriorityQueue<T> {
    private Node<T> root, last;
    private int size;
    private Comparator<T> comparator;

    public Heap(Comparator<T> comparator, boolean maxHeap) {
        if (comparator == null) {
            this.comparator = Comparator.naturalOrder();
        } else {
            this.comparator = maxHeap ? comparator.reversed() : comparator;
        }
    }

    public Heap(Comparator<T> comparator) {
        this(comparator, false);
    }

    public Heap(boolean maxHeap) {
        this(null, maxHeap);
    }

    public Heap() {
        this(Comparator.naturalOrder(), false);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T peek() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        return root.data;
    }

    @Override
    public void offer(T item) {
        Node<T> newNode = new Node<>(item);
        if (root == null) {
            root = last = newNode;
        } else {
            Node<T> parent = findParentNode(size + 1);
            if (parent.left == null) {
                parent.left = newNode;
            } else {
                parent.right = newNode;
            }
            newNode.parent = parent;
            last = newNode;
        }
        size++;
        bubbleUp(newNode);
    }

    @Override
    public T poll() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        T result = root.data;
        root.data = last.data;
        if (size == 1) {
            root = last = null;
        } else {
            Node<T> parent = findParentNode(size);
            if (parent.right != null) {
                last = parent.right;
                parent.right = null;
            } else {
                last = parent.left;
                parent.left = null;
            }
            bubbleDown(root);
        }
        size--;
        return result;
    }

    @Override
    public void updatePriority(T item) {
        updatePriority(root, item);
    }

    private Node<T> findParentNode(int idx) {
        // Convert the index to binary and remove the '1' at the beginning
        String binary = Integer.toBinaryString(idx);
        Node<T> node = root;
        for (int i = 1; i < binary.length() - 1; i++) {
            if (binary.charAt(i) == '0') {
                node = node.left;
            } else {
                node = node.right;
            }
        }
        return node;
    }

    private void updatePriority(Node<T> node, T item) {
        if (node == null) {
            return;
        }
        if (node.data.equals(item)) {
            node.data = item;
            bubbleUp(node);
            bubbleDown(node);
            return;
        }
        updatePriority(node.left, item);
        updatePriority(node.right, item);
    }

    private void bubbleUp(Node<T> curNode) {
        while (curNode.parent != null && comparator.compare(curNode.data, curNode.parent.data) > 0) {
            swap(curNode, curNode.parent);
            curNode = curNode.parent;
        }
    }

    private void bubbleDown(Node<T> curNode) {
        while (true) {
            Node<T> left = curNode.left;
            Node<T> right = curNode.right;
            Node<T> largest = curNode;

            if (left != null && comparator.compare(left.data, largest.data) > 0) {
                largest = left;
            }

            if (right != null && comparator.compare(right.data, largest.data) > 0) {
                largest = right;
            }

            if (largest != curNode) {
                swap(largest, curNode);
                curNode = largest;
            } else {
                break;
            }
        }
    }

    private void swap(Node<T> node1, Node<T> node2) {
        T temp = node1.data;
        node1.data = node2.data;
        node2.data = temp;
    }

    private class Node<T> {
        T data;
        Node<T> left, right, parent;

        Node(T data) {
            this.data = data;
        }
    }
}

