/*
1. Игра “угадай число”. Пользователь вводит число от 1 до 10. Программа сама
выбирает число и выводит угадал пользователь или нет(почитать про генерацию
псевдослучайных чисел в java)
 */

import java.util.Scanner;
import java.util.Random;

public class hw2_1 {


    public static void main(String[] args) {
        System.out.println("Введите число от 1 до 10");
        Scanner number = new Scanner(System.in);
        int i = number.nextInt();
        if (i < 1 || i > 10) {
            System.out.println("Вы ввели не верное число из диапазона 1-10");
            System.exit(1);
        }
        Random rnd = new Random();
        int a = rnd.nextInt(10) + 1;

        if (i == a) {
            System.out.println("Поздравля в угадали число " + a + " !!!");
        } else System.out.println("Загаданное число было " + a + " и вы его не угадали :(");
    }
}
