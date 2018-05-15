package common.sort;

import java.util.Arrays;

/**
 * 最大优先队列
 *
 * @author yjy
 * @date 2018-05-15 10:48
 */
public class MaxPriority<T extends Comparable<T>> {

    public static void main(String[] args) {
        // just do it...
        MaxPriority<Double> priorityQueue = new MaxPriority<>(100);
        for (int i = 0; i++ < 100;) {
            priorityQueue.insert(Math.random());
        }
        for (int i = 1; !priorityQueue.isEmpty(); i++) {
            System.out.println(i + ":" + priorityQueue.delMax());
        }
    }

    public MaxPriority(T[] ts) {
        this(ts.length);
        for (T t : ts) {
            insert(t);
        }
    }

    public MaxPriority(int capacity) {
        this.size = 0;
        this.capacity = capacity;
        this.elements = new Comparable[capacity + 1];
    }

    private int capacity; // 容量
    private int size; // 当前大小
    private Comparable[] elements; // 元素

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public String toString() {
        return Arrays.toString(elements);
    }

    public T max() {
        return (T) elements[1];
    }

    public T delMax() {
        T max = (T) elements[1];
        swap(1, size);
        elements[size--] = null;
        down(1);
        if (size < (capacity >> 2)) {
            resize(capacity >> 1);
        }
        return max;
    }

    public void insert(T t) {
        if (++size > capacity) {
            resize(capacity << 1);
        }
        elements[size] = t;
        up(size);
    }

    private void up(int i) {
        while (i > 1 && less(i >> 1, i)) {
            swap(i, i >> 1);
            i >>= 1;
        }
    }

    private void down(int i) {
        while (i <= (size >> 1)) {
            int j = i << 1;
            if (j + 1 <= size && less(j, j + 1)) j++;
            if (less(i, j)) {
                swap(i, j);
                i = j;
            } else {
                break;
            }
        }
    }

    private void resize(int newCapacity) {
        Comparable[] newElements = new Comparable[newCapacity + 1];
        System.arraycopy(elements, 1, newElements, 1, size);
        elements = newElements;
        capacity = elements.length - 1;
    }

    private boolean less(int i, int j) {
        return elements[i].compareTo(elements[j]) < 0;
    }

    private void swap(int i, int j) {
        T temp = (T) elements[i];
        elements[i] = elements[j];
        elements[j] = temp;
    }

}
