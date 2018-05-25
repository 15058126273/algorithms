package search;

import java.util.LinkedList;
import java.util.List;

/**
 * 无序链表
 *
 * @author yjy
 * 2018-05-25 22:34
 */
public class SequentialSearchST<K, V> implements SearchST<K, V> {

    private int size;
    private Node<K, V> first;

    @Override
    public int size() {
        return size;
    }

    @Override
    public void put(K key, V val) {
        if (first == null) {
            first = new Node<>(key, val);
            size++;
        } else {
            Node<K, V> v = first;
            for (;v != null; v = v.next) {
                if (v.key.equals(key)) {
                    v.val = val;
                    return;
                } else if (v.next == null) {
                    v.next = new Node<>(key, val);
                    size++;
                }
            }
        }
    }

    @Override
    public V get(K key) {
        Node<K, V> v = first;
        while (v != null) {
            if (v.key.equals(key)) {
                return v.val;
            }
            v = v.next;
        }
        return null;
    }

    @Override
    public void delete(K key) {
        if (first != null && key.equals(first.key)) {
            first = first.next;
        } else {
            Node<K, V> v = first;
            while (v != null && v.next != null) {
                if (key.equals(v.next.key)) {
                    v.next = v.next.next;
                    return;
                }
                v = v.next;
            }
        }
    }

    @Override
    public boolean contains(K key) {
        Node<K, V> v = first;
        while (v != null) {
            if (v.key.equals(key)) {
                return true;
            }
            v = v.next;
        }
        return false;
    }

    @Override
    public K min() {
        return null;
    }

    @Override
    public K max() {
        return null;
    }

    @Override
    public K floor() {
        return null;
    }

    @Override
    public K ceiling(K key) {
        return null;
    }

    @Override
    public int rank(K key) {
        return 0;
    }

    @Override
    public K select(int i) {
        return null;
    }

    @Override
    public void deleteMin() {

    }

    @Override
    public void deleteMax() {

    }

    @Override
    public int size(K key1, K key2) {
        return 0;
    }

    @Override
    public Iterable<K> keys() {
        List<K> it = new LinkedList<>();
        for (Node<K, V> v = first; v != null; v = v.next) {
            it.add(v.key);
        }
        return it;
    }

    @Override
    public Iterable<K> keys(K key1, K key2) {
        return null;
    }

    class Node<K, V> {

        private K key;
        private V val;
        private Node<K, V> next;

        public Node(K key, V val) {
            this(key, val, null);
        }

        public Node(K key, V val, Node<K, V> next) {
            this.key = key;
            this.val = val;
            this.next = next;
        }

    }

}
