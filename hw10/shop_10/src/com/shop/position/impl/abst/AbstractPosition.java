package com.shop.position.impl.abst;

import com.shop.position.Position;

/**
 * Created by cube on 21.02.2018.
 */
public abstract class AbstractPosition implements Position {

    public double price;

    public String name;

    public AbstractPosition(double price, String name) {
        this.price = price;
        this.name = name;
    }

    @Override
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
