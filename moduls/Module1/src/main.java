import tasks.Task_2;
import tasks.Task_1.EntryExams;
import tasks.Task_4;
import tasks.Task_5;
import tasks.Task_3;

import java.io.IOException;
import java.util.Scanner;

public class main {

    public static void main(String[] args) throws IOException {
        System.out.println("Введите номер задания:");
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        switch (a) {
            case 1:
                EntryExams.start();
                break;
            case 2:
                Task_2.run();
                break;
            case 3:
                int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 1, 2, 3, 11, 12, 12, 12, 7, 7, 128};
                Task_3.run(array);
                break;
            case 4:
                Task_4.run("C:\\test.txt");
                break;
            case 5:
                Task_5.run();
                break;
            default:
                System.out.println("Таких задач нет");
                break;
        }

    }
}
