package alibaba.ExtraOnlineTest;

public interface LruCache<K, V> {

    void setCapacity(int capacity);

    void put(K cacheKey, V cacheValue);

    V get(K cacheKey);
}
