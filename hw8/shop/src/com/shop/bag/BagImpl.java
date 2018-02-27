package com.shop.bag;

import com.shop.util.BagManager;
import com.shop.position.Position;
import com.shop.position.impl.abst.AbstractPosition;

/**
 * Created by cube on 21.02.2018.
 */
public class BagImpl implements Bag {
    public BagImpl(){
        SIZE = 10;
        positions = new Position[SIZE];
    }

    private int SIZE;

    private Position[] positions;

    private int realIndex = 0;

    private int iteratorIndex = 0;

    @Override
    public int getNotUsedSize() {
        return SIZE - realIndex;
    }

    public void add(AbstractPosition position) {
        positions[realIndex++] = position;
    }

    public boolean haveNext() {
        return iteratorIndex < realIndex;
    }

    public Position next() {
        return positions[iteratorIndex++];
    }

    public void initIterator() {
        iteratorIndex = 0;
    }

    public void sortByPrice(){
        BagManager.sortByPrice(this.positions,realIndex);
    }

    public Position get() {
        if (realIndex<0) {
            return null;
        }
        Position temp = positions[iteratorIndex];
        positions[iteratorIndex++] = null;
        return temp;
    }
}
