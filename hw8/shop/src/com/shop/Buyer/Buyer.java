package com.shop.Buyer;

import com.shop.Shelfs.Shelf;
import com.shop.bag.Bag;
import com.shop.position.Position;

public interface Buyer {

    void chooseBag();

    boolean buy(Shelf firstshelf,Shelf secondshelf);

    boolean pay(double sum);

    String seeBuyer();

    Order getOrderStatus();

    Bag getBag();
}
