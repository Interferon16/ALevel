/*
2. В переменной n хранится натуральное двузначное число. Создайте программу,
вычисляющую и выводящую на экран сумму цифр числа n (n – вводит
пользователь).
 */


import java.util.Scanner;

public class hw2_2 {
    public static void main(String[] args) {

        System.out.println("Введите двухзначное число \"n\"");
        Scanner number = new Scanner(System.in);
        int n = number.nextInt();
        if (n < 10 || n > 99) {
            System.out.println("Вы ввели не двухзначное число");
        }else {
            int b = n / 10 + n % 10;
            System.out.println("Сумма цифр числа " + n + " равна " + b);
        }
    }
}
