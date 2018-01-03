package common.exercise;

/**
 * @description: 最大堆
 * @author: yjy
 * @datetime: 2018/01/03 10:13
 */
public class MaxHeap {

    private int capacity; // 容量
    private int size; // 有效元素数量
    private int[] heap; // 堆

    public MaxHeap() {
        this(8, 0);
    }

    public MaxHeap(int[] arr) {
        this(arr.length, 0);
        for (int x : arr) {
            insert(x);
        }
    }

    public MaxHeap(int capacity) {
        this(capacity, 0);
    }

    public MaxHeap(int capacity, int size) {
        this.capacity = capacity;
        this.size = size;
        this.heap = new int[capacity];
    }

    /**
     * 插入元素
     * @param val 元素
     */
    public void insert(int val) {
        if (++this.size > this.capacity) {
            expansion(8);
        }
        int temp = (heap[size - 1] = val);
        for (int i = size - 1; i > 0;) {
            int p = i >> 1;
            if (heap[p] < temp) {
                heap[i] = heap[p];
                heap[p] = temp;
                i = p;
            } else {
                break;
            }
        }
    }

    /**
     * 移除指定元素
     * @param index 元素位置
     * @return 元素
     */
    public int remove(int index) {
        if (index >= this.size) {
            throw new IndexOutOfBoundsException("Out Of Size : " + index);
        }
        this.size--;
        int val = heap[index];
        int last = heap[size];
        heap[index] = last;
        for (int i = index, temp = heap[i]; i < size;) {
            int leftI, rightI, upI = i;
            if ((leftI = i << 1) < size && temp < heap[leftI]) {
                upI = leftI;
                temp = heap[upI];
            }
            if ((rightI = (i << 1) + 1) < size && temp < heap[rightI]) {
                upI = rightI;
                temp = heap[upI];
            }
            if (upI != i) {
                heap[upI] = heap[i];
                heap[i] = temp;
                i = upI;
            } else {
                break;
            }
        }
        return val;
    }

    /**
     * 扩容
     * @param count 扩容幅度
     */
    private void expansion(int count) {
        this.capacity += count;
        int[] newHeap = new int[this.capacity];
        System.arraycopy(heap, 0, newHeap, 0, size - 1);
        this.heap = newHeap;
    }

    /**
     * 打印当前堆
     */
    public void print() {
        for (int i = 0; i < size; i++) {
            System.out.print(heap[i]);
        }
        System.out.println("size:" +size);
    }

    /**
     * 检测当前是否为最大堆
     * @param i 检测起点, 整体检测 i=0
     * @return 是否
     */
    public boolean check(int i) {
        int left = (i << 1) + 1, right = left + 1;
        if (left >= size) {
            return true;
        } else if (right >= size) {
            return heap[left] <= heap[i] && check(left);
        } else {
            return heap[left] <= heap[i] && heap[right] <= heap[i] && check(left) && check(right);
        }
    }

}
