package search;

/**
 * 搜索符号表接口
 *
 * @author yjy
 * 2018-05-25 22:35
 */
public interface SearchST<K, V> {

    int size();

    void put(K key, V val);

    V get(K key);

    void delete(K key);

    boolean contains(K key);

    default boolean isEmpty() {
        return size() == 0;
    }

    K min();

    K max();

    K floor();

    K ceiling(K key);

    int rank(K key);

    K select(int i);

    void deleteMin();

    void deleteMax();

    int size(K key1, K key2);

    Iterable<K> keys();

    Iterable<K> keys(K key1, K key2);

}
