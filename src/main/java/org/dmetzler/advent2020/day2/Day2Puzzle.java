/*
 * (C) Copyright 2020 Nuxeo (http://nuxeo.com/).
 * This is unpublished proprietary source code of Nuxeo SA. All rights reserved.
 * Notice of copyright on this source code does not indicate publication.
 *
 * Contributors:
 *     dmetzler
 */
package org.dmetzler.advent2020.day2;

public class Day2Puzzle {

    public static void main(String... args) {
        final PasswordPolicy[] policies = new PasswordPolicy[] { new OldPolicy(), new CurrentPolicy() };

        for (PasswordPolicy policy : policies) {

            System.out.print(String.format("%s: ", policy.name()));
            System.out.println(Password.readFromFile(Day2Puzzle.class.getResource("/day2-passwords-input.txt"))
                                       .stream()
                                       .filter(p -> p.isValid(policy))
                                       .count());
        }
    }
}
