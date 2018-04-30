package com.shop.Buyer;

import com.shop.Shelfs.Shelf;
import com.shop.bag.Bag;

public interface Buyer {

    void chooseBag();

    boolean buy(Shelf firstshelf,Shelf secondshelf);

    boolean pay(double sum);

    String seeBuyer();

    public String getOrderList();

    Order getOrderStatus();

    Bag getBag();
}
