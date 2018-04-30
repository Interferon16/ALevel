package task1;


import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class NewArrayList<E> implements List {
    private Object[] array;
    private int size = 0;

    public NewArrayList() {
        this(10);
    }

    public NewArrayList(int start_length) {
        this.array = new Object[start_length];
    }

    public int addElement(E element) {
        growArray();
        array[size++] = element;
        return size-1;
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

    @Override
    public Object set(int index, Object element) {
        return null;
    }

    @Override
    public void add(int index, Object element) {

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

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator listIterator() {
        return null;
    }

    @Override
    public ListIterator listIterator(int index) {
        return null;
    }

    @Override
    public List subList(int fromIndex, int toIndex) {
        return null;
    }

    private void growArray() {
        if (size > array.length * 0.6) {
            int new_size = (int) (array.length * 1.5);
            Object[] new_array = new Object[new_size];
            System.arraycopy(array, 0, new_array, 0, array.length);
            array = new_array;
        }
    }


    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public boolean add(Object o) {
        return false;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean addAll(Collection c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection c) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public boolean retainAll(Collection c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection c) {
        return false;
    }

    @Override
    public boolean containsAll(Collection c) {
        return false;
    }

    @Override
    public Object[] toArray(Object[] a) {
        return new Object[0];
    }

    public int length() {
        return array.length;
    }
}
