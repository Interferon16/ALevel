package com.shop.bag;

import com.shop.position.Position;
import com.shop.position.impl.abst.AbstractPosition;

/**
 * Created by cube on 21.02.2018.
 */
public interface Bag {
    int getNotUsedSize();

    void add(AbstractPosition position);

    boolean haveNext();

    Position next();

    void initIterator();

    void sortByPrice();

    Position get();


}
