public class Bottle {

    public static int arraySorter(int[] m) {
        int count = 1;
        int iteration = 0;
        while (count > 0) {
            count = 0;
            for (int i = 0; i < m.length - 1; i++) {
                if (m[i] > m[i + 1]) {
                    int voop = m[i];
                    m[i] = m[i + 1];
                    m[i + 1] = voop;
                    count++;
                    iteration++;
                }
            }
        }
        return iteration;
    }

}

