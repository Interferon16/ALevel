package com.shop.Shelfs;

import com.shop.position.impl.abst.AbstractPosition;

public interface Shelf {

    AbstractPosition get();
    boolean put(AbstractPosition position);
    boolean put(AbstractPosition position, int quantity);
    boolean checkAvailable();
    void showShelf();
    int getFreeSpace();
    boolean zatavarka();


}
