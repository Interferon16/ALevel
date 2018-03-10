package tasks;

import java.util.Random;
import java.util.Scanner;

public class Numbers {

    public static void run() {
        System.out.println("Введите максимальное целое число для угадываний");
        Scanner max_number = new Scanner(System.in);
        int n = max_number.nextInt();
        System.out.println("Введите количество попыток");
        Scanner num_of_try = new Scanner(System.in);
        int k = num_of_try.nextInt();
        Random rnd = new Random();
        int secret_number = rnd.nextInt(n + 1);
        int try_checker = k+1;
        for (int i = 0; i <= k; i++) {
            try_checker--;
            if(try_checker<1){
                System.out.println("У вас закончились попытки, досвидания");
                System.exit(0);
            }
            System.out.println("Введите целое угадываемое число");
            Scanner scanner = new Scanner(System.in);
            int number = scanner.nextInt();
            if(number>secret_number){
                System.out.println("Ваше число больше загаданного, у вас осталось " + try_checker +" попыток");
            }
            if(number<secret_number){
                System.out.println("Ваше число меньше загаданного, у вас осталось " + try_checker +" попыток");
            }
            if(number==secret_number){
                System.out.println("Поздравляю вы угада число - "+secret_number+", у вас оставалось " + try_checker +" попыток");
                break;
            }


        }
    }
}