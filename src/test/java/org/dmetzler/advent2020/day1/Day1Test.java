/*
 * (C) Copyright 2020 Nuxeo (http://nuxeo.com/).
 * This is unpublished proprietary source code of Nuxeo SA. All rights reserved.
 * Notice of copyright on this source code does not indicate publication.
 *
 * Contributors:
 *     dmetzler
 */
package org.dmetzler.advent2020.day1;

import static org.assertj.core.api.Assertions.assertThat;

import java.net.URL;
import java.util.List;

import org.junit.Test;

/**
 * @since TODO
 */
public class Day1Test {

    @Test
    public void can_read_input() throws Exception {
        URL url = getClass().getResource("/sample-test.txt");
        List<Integer> expenses  = ExpenseReportReader.readExpenses(url.openStream());
        assertThat(expenses).hasSize(6);
    }

    @Test
    public void can_solve_puzze() throws Exception {
        Day1Puzzle puzzle = new Day1Puzzle(getClass().getResource("/sample-test.txt"),2020);

        assertThat(puzzle.solve(2)).isEqualTo(514579);
        assertThat(puzzle.solve(3)).isEqualTo(241861950);

    }

}
