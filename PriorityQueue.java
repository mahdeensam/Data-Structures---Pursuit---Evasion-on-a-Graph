public interface PriorityQueue<T> {
    public void offer(T item);
    public int size();
    public T peek();
    public T poll();
    public void updatePriority(T item);
}