package com.shop;

import com.shop.TypeOfManagingTheShop.Automatic;
import com.shop.TypeOfManagingTheShop.Manual;
import java.util.Scanner;

/**
 * Created by cube on 21.02.2018.
 */
public class Main {

    public static void main(String[] args) {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Выберите тип управления магазином:");
            System.out.println("Внимание, в магазине действует акция \"За каждые три товара самый дешевый в подарок!\": \n");
            System.out.println("1: ручной");
            System.out.println("2: автоматический с помощью сущностей");
            System.out.println("3: Выход");
            switch (scanner.nextInt()) {
                case 1:
                    Manual.start();
                    break;
                case 2:
                    Automatic.start();
                    break;
                case 3:
                    System.exit(0);
                default:
                    System.exit(0);
            }
        }
    }
}
