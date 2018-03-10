package tasks;
/*
Не работает
 */


import java.util.Scanner;

public class Task_5 {

    public static void run(){
        System.out.println("Введите начальное положение коня");
        Scanner starposition = new Scanner(System.in);
        String spc = starposition.next();
        int spn = starposition.nextInt();
        System.out.println(spc+spn);

    }
}
