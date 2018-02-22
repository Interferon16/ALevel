import java.util.Random;

public class ArrayGenerate {

    public static int[][] generateRandomArray(int a, int b, int minrand, int maxrand) {
        int array[][] = new int[a][b];
        if (minrand > maxrand) {
            int voop = minrand;
            minrand = maxrand;
            maxrand = voop;
        }
        for (int i = 0; i < a; i++) {
            for (int j = 0; j < b; j++) {
                Random rnd = new Random();
                array[i][j] = rnd.nextInt(maxrand + 1 - minrand) + minrand;
            }
        }
        return array;
    }

    public static int[] generateRandomArray(int a, int minrand, int maxrand) {
        int array[] = new int[a];
        if (minrand > maxrand) {
            int voop = minrand;
            minrand = maxrand;
            maxrand = voop;
        }
        for (int i = 0; i < a; i++) {
            Random rnd = new Random();
            array[i] = rnd.nextInt(maxrand + 1 - minrand) + minrand;
        }
        return array;
    }

}
