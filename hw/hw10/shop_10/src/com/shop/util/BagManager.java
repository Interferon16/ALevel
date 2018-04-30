package com.shop.util;

import com.shop.position.Position;
import com.shop.position.impl.abst.AbstractPosition;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class BagManager {

    public static void sortByPricen(Position[] positions, int realIndex) {
        Arrays.sort(positions, 0, realIndex, new Comparator<Position>() {
            @Override
            public int compare(Position o1, Position o2) {
                if (o1.getPrice() < o2.getPrice()) {
                    return -1;
                }
                if (o1.getPrice() > o2.getPrice()) {
                    return 1;
                }
                return 0;
            }
        });
    }

    public static void sortByPrice(ArrayList<AbstractPosition> positions) {
        positions.sort(new Comparator<AbstractPosition>() {
            @Override
            public int compare(AbstractPosition o1, AbstractPosition o2) {
                if (o1.getPrice() < o2.getPrice()) {
                    return -1;
                }
                if (o1.getPrice() > o2.getPrice()) {
                    return 1;
                }
                return 0;
            }
        });


    }
}
