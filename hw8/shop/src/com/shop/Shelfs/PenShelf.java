package com.shop.Shelfs;


import com.shop.position.impl.Pen;
import com.shop.position.impl.abst.AbstractPosition;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class PenShelf implements Shelf {

    public PenShelf() {
        SHELFSIZE = 100;
        count = 0;
        shelf = new Pen[SHELFSIZE];
    }

    private int SHELFSIZE;
    private int count;
    private String name = "Pen";
    private AbstractPosition[] shelf;

    public boolean put(AbstractPosition position) {
        if (count < SHELFSIZE - 1) {
            shelf[count++] = position;
            return true;
        } else {
            return false;
        }
    }

    public boolean put(AbstractPosition position, int quantity) {
        if (count + quantity < SHELFSIZE - 1) {
            for (int i = 0; i < quantity; i++) {
                shelf[count++] = position;
            }
            return true;
        } else {
            return false;
        }
    }

    public AbstractPosition get() {
        if (count < 1) {
            return null;
        }
        AbstractPosition temp = shelf[--count];
        shelf[count] = null;
        return temp;
    }

    public boolean checkAvailable() {
        return count > 0;
    }

    @Override
    public void showShelf() {
        for (int i = 0; i < count; i++) {
            System.out.println(shelf[i]);
        }
    }

    public int getFreeSpace() {
        return SHELFSIZE - 1 -count;
    }

    public boolean zatavarka() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the adding QUANTITY of "+name);
        int quantity = scanner.nextInt();
        if (quantity > this.getFreeSpace() || quantity == 0) {
            System.out.println("Sorry but you adding too much or zero, free space on shelf is - " + this.getFreeSpace());
            return false;
        }
        double price = 0.0d;
        while(price <= 0){
            Scanner scanner2 = new Scanner(System.in);
            System.out.println("Enter the PRICE of adding products");
            price = scanner2.nextDouble();
            if(price<=0){
                System.out.println("Price cannot be zero or less, enter another PRICE");
            }
            this.put(new Pen(price,name),quantity);
        }
        return true;
    }
}

