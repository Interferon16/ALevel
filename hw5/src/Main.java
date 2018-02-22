import java.util.*;

public class Main {

    private static void arrayPrinter(int[] a) {
        int length=a.length;
        for (int i = 0; i < length; i++) {
            System.out.print(a[i] + " ");
        }
    }

    private static void arrayPrinter(int[][] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.println("");
            for (int j = 0; j < a[i].length; j++) {
                System.out.print(a[i][j] + " ");
            }
        }
    }


    public static void main(String[] args) {
        System.out.println("Демонстрация");
        System.out.println("Введите номер задания");
        System.out.println("1 - quickSort");
        System.out.println("2 - Лабиринт");

        Scanner scanner = new Scanner(System.in);
        switch (scanner.nextInt()) {
            case 1:
                int randomarray[]=ArrayGenerate.generateRandomArray(20,0,100);
                arrayPrinter(randomarray);
                System.out.println(" - Сгенерированный массив");
                QuickSort sort=new QuickSort(randomarray);
                arrayPrinter(randomarray);
                System.out.println(" - Массив после сортировки QuickSort");
                break;
            case 2:
                MazeRunner.run();
                System.out.println("Area under construction :(");
                break;
            default:
                break;

        }
    }
}
