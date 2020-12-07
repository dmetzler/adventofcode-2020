/*
 * (C) Copyright 2020 Nuxeo (http://nuxeo.com/).
 * This is unpublished proprietary source code of Nuxeo SA. All rights reserved.
 * Notice of copyright on this source code does not indicate publication.
 *
 * Contributors:
 *     dmetzler
 */
package org.dmetzler.advent2020.day3;

import java.util.Arrays;

public class Day3Puzzle {

    public static void main(String[] args) {
        ForestMap map = ForestMap.readFromFile(Day3Puzzle.class.getResource("/day3-input.txt"));
        Toboggan tob1 = new SimpleToboggan(3);
        System.out.println("Number of trees: " + tob1.slide(map));

        int result = 1;
        for(Integer i : Arrays.asList(new Integer[] {1,3,5,7}) ) {
            Toboggan tob = new SimpleToboggan(i);
            System.out.println("Number of trees: " + tob.slide(map));
            System.out.println(result);
            result = result * tob.slide(map);
        }

        Toboggan tob = new SimpleToboggan(1,2);
        System.out.println("Number of trees: " + tob.slide(map));
        result = result * tob.slide(map);

        System.out.println(result);
    }

}
