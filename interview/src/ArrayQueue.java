/**
 * non thread safe queue
 */
public class ArrayQueue {
    private Object[] arr;
    private int beginIndex = 0;
    private int endIndex = -1;
    private int capacity;

    public ArrayQueue() {
        arr = new Object[16];
        capacity = 16;
    }

    public ArrayQueue(int initalCapacity) {
        arr = new Object[initalCapacity];
        capacity = initalCapacity;
    }

    public void push(Object data) {
        if (endIndex >= capacity * 2 / 3) {
            expandCapacity();
        }
        if (beginIndex > capacity / 3) {
            reduceCapacity();
        }
        arr[++endIndex] = data;
    }

    private void expandCapacity() {
        int newCapacity = capacity * 2;
        Object[] newArr = new Object[newCapacity];
        System.arraycopy(arr, 0, newArr, 0, capacity);
        capacity = newCapacity;
        arr = newArr;
    }

    private void reduceCapacity() {
        int newCapacity = capacity - beginIndex;
        Object[] newArr = new Object[newCapacity];
        System.arraycopy(arr, beginIndex, newArr, 0, newCapacity);
        capacity = newCapacity;
        arr = newArr;
    }

    public Object pop() {
        if (beginIndex > endIndex) {
            return null;
        }
        Object object = arr[beginIndex];
        arr[beginIndex++] = null;
        return object;
    }

    public Object peek() {
        return arr[beginIndex];
    }

    public boolean empty() {
        return beginIndex > endIndex;
    }

    /**
     * simple test
     *
     * @param args
     */
    public static void main(String[] args) {
        ArrayQueue arrayQueue = new ArrayQueue();
        arrayQueue.push(1);
        System.out.println(arrayQueue.peek());
        System.out.println(arrayQueue.pop());
        System.out.println(arrayQueue.empty());
        arrayQueue.push(2);
        arrayQueue.push(3);
        System.out.println(arrayQueue.peek());
        System.out.println(arrayQueue.pop());
        System.out.println(arrayQueue.empty());
    }
}
