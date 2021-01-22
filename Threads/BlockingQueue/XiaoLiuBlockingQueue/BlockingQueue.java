package BlockingQueue.XiaoLiuBlockingQueue;

public interface BlockingQueue<T> {
    void put(T element) throws InterruptedException;

    T take() throws InterruptedException;
}
