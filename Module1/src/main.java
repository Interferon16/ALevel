import tasks.Numbers;
import tasks.Task_1.EntryExams;
import tasks.Task_5;
import tasks.task_3;

import java.util.Scanner;

public class main {

    public static void main(String[] args) {
        System.out.println("Введите номер задания:");
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        switch (a) {
            case 2:
                Numbers.run();
                break;
            case 3:
                int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 1, 2, 3, 11, 12, 12, 12, 7, 7, 128};
                task_3.run(array);
                break;
            case 1:
                EntryExams.start();
                break;
        }

    }
}
