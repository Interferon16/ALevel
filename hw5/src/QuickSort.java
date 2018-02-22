public class QuickSort {

    QuickSort(int[] array) {
        this.array = array;
        int last = array.length - 1;
        int first = 0;
        recursSort(array, first, last);
    }

    private int array[];

    private void swap(int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    private void recursSort(int[] array, int first, int last) {
        int anchor = first;
        int i = first, j = last;
        if (last > first) {
            for (; i < j; ) {
                for (; array[anchor] < array[j]; ) {
                    j--;
                }
                if (anchor <= j) {
                    swap(anchor, j);
                    anchor = j;
                    j--;
                }
                for (; array[anchor] > array[i]; ) {
                    i++;
                }
                if (i <= anchor) {
                    swap(i, anchor);
                    anchor = i;
                    i++;
                }
            }
            if (j > first) recursSort(array, first, j);
            if (i < last) recursSort(array, i, last);
        }
    }
}
