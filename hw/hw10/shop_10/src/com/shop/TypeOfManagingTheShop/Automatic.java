package com.shop.TypeOfManagingTheShop;

import com.shop.Buyer.Buyer;
import com.shop.Buyer.StandartBuyer;
import com.shop.Shelfs.Shelf;
import com.shop.bag.Bag;
import com.shop.manager.ShopManager;
import com.shop.position.Position;
import com.shop.position.impl.Apple;
import com.shop.position.impl.Pen;
import com.shop.position.impl.abst.AbstractPosition;
import com.shop.util.PeopleManager;

import java.util.Random;
import java.util.Scanner;

public class Automatic {

    private static Shelf appleshelf = new Shelf<Apple>();
    private static Shelf penshelf = new Shelf<Pen>();
    private static String orderlist;

    public static void start() {
        zavozProduktov(appleshelf, penshelf);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите количество покупателей");
        int cycle = scanner.nextInt();
        for (int i = 0; i < cycle; i++) {
            if (!penshelf.checkAvailable() || !appleshelf.checkAvailable()) {
                zavozProduktov(appleshelf, penshelf);
                System.out.println("################ Завоз продуктов #################");
            }
            doPokupki();
        }
        System.out.println("MAP списка покупок:");
        System.out.println(orderlist);
    }

    private static void doPokupki() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        double sum = 0;
        PeopleManager generate = new PeopleManager();
        Buyer pokupatel = new StandartBuyer(generate.nextName(), generate.nextAge(), generate.nextMoney());
        pokupatel.chooseBag();
        System.out.println("Начальный статус покупателя - " + pokupatel.seeBuyer());
        pokupatel.buy(appleshelf, penshelf);
        ShopManager babaGala = new ShopManager();
        try {
            sum = babaGala.sum(pokupatel.getBag(), "threeForTwo");
        } catch (InterruptedException e) {
            System.out.println("SCORUYY!!BABA GALA ");
        }
        if (pokupatel.pay(sum)) {
            System.out.println("Покупатель разплатился.");
            System.out.println("Финальный статус покупателя - " + pokupatel.seeBuyer() + "\n");
        } else {
            System.out.println("У покупателя не хватило денег, товар возвращен на полки");
            System.out.println("Финальный статус покупателя - " + pokupatel.seeBuyer() + "\n");
            refund(pokupatel, appleshelf, penshelf);
        }
        orderlist=pokupatel.getOrderList();
    }

    private static void zavozProduktov(Shelf appleshelf, Shelf penshelf) {
        Random rnd = new Random();
        for (int i = 0; i < 90; i++) {
            double price = (double) (rnd.nextInt(100) + 10) / 10;
            appleshelf.put(new Apple(price, "Apple"));
            price = (double) (rnd.nextInt(200) + 20) / 10;
            penshelf.put(new Pen(price, "Pen"));
        }
    }

    private static void refund(Buyer pokupatel, Shelf appleshelf, Shelf penshelf) {
        Bag bag = pokupatel.getBag();
        bag.initIterator();
        while (bag.haveNext()) {
            Position position = bag.get();
            if (position instanceof Apple) {
                AbstractPosition pos = (AbstractPosition) position;
                appleshelf.put(pos);
            }
            if (position instanceof Pen) {
                AbstractPosition pos = (AbstractPosition) position;
                penshelf.put(pos);
            }
        }
    }
}
