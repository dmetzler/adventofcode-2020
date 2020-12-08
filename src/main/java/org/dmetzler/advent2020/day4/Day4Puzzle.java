/*
 * (C) Copyright 2020 Nuxeo (http://nuxeo.com/).
 * This is unpublished proprietary source code of Nuxeo SA. All rights reserved.
 * Notice of copyright on this source code does not indicate publication.
 *
 * Contributors:
 *     dmetzler
 */
package org.dmetzler.advent2020.day4;

public class Day4Puzzle {

    public static void main(String[] args) {
        System.out.println(Passport.readFrom(Day4Puzzle.class.getResource("/day4-input.txt"))
                                   .stream()
                                   .filter(Passport::isValid)
                                   .count());
    }
}
