/*
 * (C) Copyright 2020 Nuxeo (http://nuxeo.com/).
 * This is unpublished proprietary source code of Nuxeo SA. All rights reserved.
 * Notice of copyright on this source code does not indicate publication.
 *
 * Contributors:
 *     dmetzler
 */
package org.dmetzler.advent2020.day5;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @since TODO
 */
public class Day5Puzzle {

    public static void main(String... args) {
        List<BoardingPass> passes = BoardingPass.readFromFile(Day5Puzzle.class.getResource("/day5-input.txt"));
        System.out.println(passes.stream().map(BoardingPass::getId).max(Integer::compare).get());
        List<Integer> ids = passes.stream().map(BoardingPass::getId).sorted(Integer::compare).collect(Collectors.toList());
        for (int i = 0; i < passes.size() - 1; i++) {
            if (ids.get(i+1)- ids.get(i) > 1) {
                System.out.println(ids.get(i) + 1);
            }
        }

    }
}
