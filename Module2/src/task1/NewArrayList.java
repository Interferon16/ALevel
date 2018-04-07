package task1;


public class NewArrayList<E> {
    private int length;
    private Object[] array;
    private int size = 0;

    public NewArrayList() {
        this(10);
    }

    public NewArrayList(int length) {
        this.array = new Object[length];
        this.length = length;
    }

    public int addElement(E element) {
        growArray();
        array[size++] = element;
        return size;
    }

    public void add(E element, int index) {
        growArray();
        if (size <= index) {
            addElement(element);
        } else {
            for (int i = size; i >= index; i--) {
                array[size] = array[size - 1];
            }
            array[index] = element;
            size++;
        }
    }

    public E get(int index) {
        return (E) array[index];
    }


    public E remove(int index) {
        E result = (E) array[index];
        array[index] = null;
        for (int i = index; i < size; i++) {
            array[index] = array[index + 1];
        }
        array[size - 1] = null;
        return result;
    }

    private void growArray() {
        if (size > array.length * 0.6) {
            int new_size = (int) (array.length * 1.5);
            Object[] new_array = new Object[new_size];
            System.arraycopy(array, 0, new_array, 0, array.length);
            array = new_array;
            this.length = array.length;
        }
    }


    public int size() {
        return size;
    }

    public int length() {
        return length;
    }
}
