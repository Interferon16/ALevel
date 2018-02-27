package com.shop.Buyer;

import com.shop.Shelfs.AppleShelf;
import com.shop.Shelfs.Shelf;
import com.shop.bag.ATBPacket;
import com.shop.bag.Bag;
import com.shop.bag.BagImpl;
import com.shop.position.Position;

import java.text.DecimalFormat;
import java.util.Random;

public class StandartBuyer implements Buyer {

    public StandartBuyer(String name, int age, double money) {
        this.money = money;
        this.name = name;
        this.age = age;
    }

    DecimalFormat formatmoney = new DecimalFormat("0.00");
    private double money;
    private String name;
    private int age;

    private Order orderstatus = Order.OPEN;
    Random rnd = new Random();

    private Bag bag;

    public void chooseBag() {
        switch (rnd.nextInt(2)) {
            case 0:
                bag = new BagImpl();
                break;
            case 1:
                bag = new ATBPacket();
                break;
            default:
                bag = new BagImpl();
                break;
        }
    }

    public boolean buy(Shelf firstshelf, Shelf secondshelf) {
        int quantity = rnd.nextInt(6)+5;
        for (int i = 0; i < quantity; i++) {
            if(bag.getNotUsedSize()==0){
                break;
            }
            if (firstshelf.checkAvailable()) {
                firstshelf.get();
                bag.add(firstshelf.get());
            }
            if (secondshelf.checkAvailable()) {
                secondshelf.get();
                bag.add(secondshelf.get());
            }
        }return true;
    }

    public boolean pay(double sum) {
        if(sum>money){
            return false;
        }
        orderstatus = Order.PAID;
        money-=sum;
        return true;
    }



    public String seeBuyer() {
        return " | Имя: " + name + " | Возраст " + age + " | Количестов денег: " + formatmoney.format(money)+" | Статус заказа:"+getOrderStatus();
    }

    public Order getOrderStatus() {
        return orderstatus;
    }

    public Bag getBag() {
        return bag;
    }
}
