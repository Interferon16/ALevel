package com.shop.Shelfs;

import com.shop.position.Position;
import com.shop.position.impl.Apple;
import com.shop.position.impl.abst.AbstractPosition;

import java.util.ArrayList;
import java.util.Arrays;
import java.lang.reflect.*;
import java.util.Scanner;

public class Shelf<T extends AbstractPosition> {


    public Shelf() {
        SHELFSIZE = 10;
        shelf = new ArrayList(SHELFSIZE);
    }

    private int SHELFSIZE;
    private String name = "Apple";
    private ArrayList<T> shelf;


    public boolean put(T position) {
        this.shelf.add(position);
        return true;
    }

    public T get() {
        T temp = this.shelf.get(0);
        this.shelf.remove(0);
        return temp;
    }

    public boolean checkAvailable() {
        return !this.shelf.isEmpty();
    }

    public void showShelf() {
    }


}
