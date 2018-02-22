import java.util.Random;

public class ArrayChange {
    ArrayChange(){

    }

    public static int[][] arrayRandomFiller(int a, int b, int minrand, int maxrand) {
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
        for (int i = 0; i < a; i++) {
            System.out.println();
            for (int j = 0; j < b; j++) {
                System.out.print(array[i][j] + " ");
            }
        }
        return array;
    }

    public static int[] arrayRandomFiller(int a, int minrand, int maxrand) {
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
        for (int i = 0; i < a; i++) {
            System.out.print(array[i] + " ");
        }
        return array;
    }

}
