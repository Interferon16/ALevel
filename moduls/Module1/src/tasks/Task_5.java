package tasks;

import java.util.Scanner;

public class Task_5 {

    public static void run() {
        int a = 0, c = 0, b = 0, d = 0;
        System.out.println("Введите начальное положение коня");
        Scanner scanner = new Scanner(System.in);
        scanner.useDelimiter("");
        a = checker(scanner, "");
        b = checker(scanner, b);
        System.out.println("Введите точку куда переместить коня");
        Scanner scanner1 = new Scanner(System.in);
        scanner1.useDelimiter("");
        c = checker(scanner1, "");
        d = checker(scanner1, d);

        if ((Math.abs(a - c) == 2 & Math.abs(b - d) == 1) | (Math.abs(a - c) == 1 & Math.abs(b - d) == 2)) {
            System.out.println("Ход возможен");
        } else
            System.out.println("Ход невозможен");//ход коня ограничен двумя шагами по одной оси и одни шагом по другой

        //System.out.println(a+" -start- "+b+" | "+c+" -finish- "+d); //тест проверок

    }
    private static int swap(String a){
        int temp;
        switch(a){
            case "a":temp=1;break;
            case "b":temp=2;break;
            case "c":temp=3;break;
            case "d":temp=4;break;
            case "e":temp=5;break;
            case "f":temp=6;break;
            case "g":temp=7;break;
            case "h":temp=8;break;
            case "A":temp=1;break;
            case "B":temp=2;break;
            case "C":temp=3;break;
            case "D":temp=4;break;
            case "E":temp=5;break;
            case "F":temp=6;break;
            case "G":temp=7;break;
            case "H":temp=8;break;
            default:temp=-1;
        }
        return temp;
    }


    private static int checker(Scanner scanner, String x) {
        int z = 0;
        if (scanner.hasNext(".")) {
            x = scanner.next();
        }
        if (swap(x) > 0) {
            z = swap(x);
        } else {
            System.out.println("Неверные данные");
            System.exit(0);
        }
        return z;
    }

    private static int checker(Scanner scanner, int x) {
        if (scanner.hasNextInt()) {
            x = scanner.nextInt();
        }
        if (x > 0 && x < 9) {
        } else {
            System.out.println("Неверные данные");
            System.exit(0);
        }
        return x;
    }

}
