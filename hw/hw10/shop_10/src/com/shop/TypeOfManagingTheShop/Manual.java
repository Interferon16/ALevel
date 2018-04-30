package com.shop.TypeOfManagingTheShop;

import com.shop.Shelfs.Shelf;
import com.shop.bag.ATBPacket;
import com.shop.bag.Bag;
import com.shop.bag.BagImpl;
import com.shop.manager.ShopManager;
import com.shop.position.impl.Apple;

import java.util.Random;
import java.util.Scanner;

public class Manual {
    private static Shelf appleshelf;
    private static Shelf penshelf;

    public static void start() {
        Scanner scanner = new Scanner(System.in);
        Bag bag;
        appleshelf = new Shelf();
        penshelf = new Shelf();
        System.out.println("S chem poidem vasia?");
        System.out.println("1: ATB power");
        System.out.println("2: Standart edition Galia");
        switch (scanner.nextInt()) {
            case 1:
                bag = new ATBPacket();
                break;
            case 2:
                bag = new BagImpl();
                break;
            default:
                System.out.println("sho zirkaech? pognali s rukzacom");
                bag = new BagImpl();
        }
        while (true) {
            System.out.println("Shito delaem desy?");
            System.out.println("1: go za pokupkami person-san");
            System.out.println("2: go na cassu");
            System.out.println("3: Zatavarit polki");
            switch (scanner.nextInt()) {
                case 1:
                    doPokupki(bag);
                    break;
                case 2:
                    goNaCassu(bag);
                    break;
                case 3:
                    zatavarkaPolok(appleshelf, penshelf);
                    break;
                default:
                    System.out.println("dich. ti vtiraech mne dich");
            }
        }
    }

    private static void doPokupki(Bag bag) {
        Scanner scanner = new Scanner(System.in);
        while (bag.getNotUsedSize() != 0) {
            System.out.println("1:Pen");
            System.out.println("2:Apple");
            System.out.println("3:dumay");
            switch (scanner.nextInt()) {
                case 1:
                    if (penshelf.checkAvailable()) {
                        bag.add(penshelf.get());
                        break;
                    } else System.out.println("\n \n Karandashi zakonchilis \n \n");
                    break;
                case 2:
                    if (appleshelf.checkAvailable()) {
                        bag.add(appleshelf.get());
                        break;
                    } else System.out.println("\n \n Yablok net no vi dergites \n \n");
                    break;
                case 3:
                    return;
                default:
                    System.out.println("dich. ti vtiraech mne dich");
            }
        }
        System.out.println("Galia, I te sho grushick");
    }

    private static void goNaCassu(Bag bag) {
        ShopManager babaGala = new ShopManager();
        try {
            babaGala.sum(bag, "threeForTwo");
            System.out.println("oi mama, hera tac dorogo");
            System.exit(0);
        } catch (InterruptedException e) {
            System.out.println("SCORUYY!!BABA GALA ");
        }
    }

    private static void zatavarkaPolok(Shelf appleshelf, Shelf penshelf) {
        while (true) {
            Random rnd = new Random();
            Scanner scanner = new Scanner(System.in);
            System.out.println("Kakie polki budem zatavarivat?");
            System.out.println("1: Polku s karandashami");
            System.out.println("2: Polku s yablokami");
            System.out.println("3: Vse, ya zakonchil.");
            switch (scanner.nextInt()) {
                case 1:
                    for (int i = 0; i < 90; i++) {
                        double price = (double)(rnd.nextInt(100)+10) / 10;
                        penshelf.put(new Apple(price, "Pen"));
                    }
                    break;
                case 2:
                    for (int i = 0; i < 90; i++) {
                        double price = (double)(rnd.nextInt(100)+10) / 10;
                        appleshelf.put(new Apple(price, "Apple"));
                    }
                    break;
                case 3:
                    return;
                default:
                    System.out.println("dich. ti vtiraech mne dich");
            }
        }
    }
}
