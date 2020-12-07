/*
 * (C) Copyright 2020 Nuxeo (http://nuxeo.com/).
 * This is unpublished proprietary source code of Nuxeo SA. All rights reserved.
 * Notice of copyright on this source code does not indicate publication.
 *
 * Contributors:
 *     dmetzler
 */
package org.dmetzler.advent2020.day3;

import org.dmetzler.advent2020.day3.ForestMap.TYPE;

public class SimpleToboggan implements Toboggan {

    private int x, y;

    private int slopex;

    private int slopey = 1;

    public SimpleToboggan(int slope) {
        this.slopex = slope;
        setPosition(0, 0);

    }

    public SimpleToboggan(int slopex, int slopey) {
        this.slopex = slopex;
        this.slopey = slopey;
        setPosition(0, 0);

    }

    private void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int slide(ForestMap map) {
        int cost = 0;


        for (int i = 0, j = 0; i < map.getSize(); i = i + slopey, j = j+ slopex) {
            setPosition(j, i);
            printPosition(map);
            cost += map.get(this.x, this.y).getCost();
        }
        return cost;
    }

    private void printPosition(ForestMap map) {
        System.out.print(String.format("%03d :", y+1));
        for (int i = 0; i < 40; i++) {

            if (i == x) {
                System.out.print(map.get(i, y) == TYPE.TREE ? "X" : "O");
            } else {
                System.out.print(map.get(i, y));
            }

        }
        System.out.println();
    }

}
