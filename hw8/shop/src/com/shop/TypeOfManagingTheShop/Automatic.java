package com.shop.TypeOfManagingTheShop;

import com.shop.Buyer.Buyer;
import com.shop.Buyer.StandartBuyer;
import com.shop.Shelfs.AppleShelf;
import com.shop.Shelfs.PenShelf;
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

    private static Shelf appleshelf=new AppleShelf();
    private static Shelf penshelf=new PenShelf();

    public static void start() {
        Random rnd = new Random();
        for (int i = 0; i < 90; i++) {
            double price = (double)(rnd.nextInt(100)+10) / 10;
            appleshelf.put(new Apple(price, "Apple"));
            price = (double)(rnd.nextInt(200)+20) / 10;
            penshelf.put(new Pen(price, "Pen"));
        }
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите количество покупателей");
        int cycle = scanner.nextInt();
        for (int i = 0; i < cycle; i++) {
            doPokupki();
        }
    }

    private static void doPokupki() {
        double sum=0;
        PeopleManager generate = new PeopleManager();
        Buyer pokupatel = new StandartBuyer(generate.nextName(),generate.nextAge(),generate.nextMoney());
        pokupatel.chooseBag();
        System.out.println("Начальный статус покупателя - "+pokupatel.seeBuyer());
        pokupatel.buy(appleshelf,penshelf);
        ShopManager babaGala = new ShopManager();
        try {
        sum=babaGala.sum(pokupatel.getBag(),"threeForTwo");
        } catch (InterruptedException e) {
            System.out.println("SCORUYY!!BABA GALA ");
        }
        if(pokupatel.pay(sum)){
            System.out.println("Покупатель разплатился.");
            System.out.println("Финальный статус покупателя - "+pokupatel.seeBuyer()+"\n");
        }else{
            System.out.println("У покупателя не хватило денег, товар возвращен на полки");
            System.out.println("Финальный статус покупателя - "+pokupatel.seeBuyer()+"\n");
            refund(pokupatel, appleshelf, penshelf);
        }
    }

    private static void refund(Buyer pokupatel,Shelf appleshelf, Shelf penshelf){
        Bag bag = pokupatel.getBag();
        bag.initIterator();
        while(bag.haveNext()){
            Position position = bag.get();
            if (position instanceof Apple){
                AbstractPosition pos = (AbstractPosition) position;
                appleshelf.put(pos);
            }
            if (position instanceof Pen){
                AbstractPosition pos = (AbstractPosition) position;
                penshelf.put(pos);
            }
        }
    }
}
