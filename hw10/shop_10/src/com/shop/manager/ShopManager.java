package com.shop.manager;

import com.shop.bag.Bag;
import com.shop.position.Position;

import java.text.DecimalFormat;

/**
 * Created by cube on 21.02.2018.
 */
public class ShopManager {
    DecimalFormat formatmoney = new DecimalFormat("0.00");

    public double sum(Bag bag) throws InterruptedException {
        double sum = 0;
        bag.initIterator();
        while (bag.haveNext()) {
            System.out.println("PICK!!");
            sum += bag.next().getPrice();
            Thread.sleep(10);
        }
        System.out.println("Ваша сумма составляет " + sum);
        return sum;
    }

    public double sum(Bag bag, String action) throws InterruptedException {
        double sum;
        switch (action) {
            case "threeForTwo":
                sum = threeForTwo(bag);
                break;
            default:
                sum = sum(bag);
                break;
        }
        return sum;
    }

    private double threeForTwo(Bag bag) throws InterruptedException {
        double sum = 0;
        bag.initIterator();
        int count = 0, freePossitions = 0;
        while (bag.haveNext()) {
            Position a = bag.next();
            System.out.println("PICK!! - " + a.getName() + " " + a.getPrice());
            sum += a.getPrice();
            Thread.sleep(10);
            count++;
        }
        bag.sortByPrice();
        freePossitions = count / 3;
        count = 0;
        bag.initIterator();
        while (freePossitions > count) {
            sum -= bag.next().getPrice();
            count++;
        }
        System.out.println("Ваша сумма составляет " + formatmoney.format(sum) + " по акции Вам предоставленны " + freePossitions + " товаров бесплатно.");
        return sum;
    }
}
