/*
 * (C) Copyright 2020 Nuxeo (http://nuxeo.com/).
 * This is unpublished proprietary source code of Nuxeo SA. All rights reserved.
 * Notice of copyright on this source code does not indicate publication.
 *
 * Contributors:
 *     dmetzler
 */
package org.dmetzler.advent2020.day5;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;

public class Day5Test {

    @Test
    public void can_read_input() throws Exception {
        List<BoardingPass> boardingpasses = BoardingPass.readFromFile(getClass().getResource("/day5-sample-input.txt"));
        assertThat(boardingpasses).hasSize(3);
    }

    @Test
    public void can_get_boarding_pass_info() throws Exception {
        BoardingPass pass;

        pass = new BoardingPass("BFFFBBFRRR");
        assertThat(pass.getRow()).isEqualTo(70);
        assertThat(pass.getColumn()).isEqualTo(7);
        assertThat(pass.getId()).isEqualTo(567);

        pass = new BoardingPass("FFFBBBFRRR");
        assertThat(pass.getRow()).isEqualTo(14);
        assertThat(pass.getColumn()).isEqualTo(7);
        assertThat(pass.getId()).isEqualTo(119);

        pass = new BoardingPass("BBFFBBFRLL");
        assertThat(pass.getRow()).isEqualTo(102);
        assertThat(pass.getColumn()).isEqualTo(4);
        assertThat(pass.getId()).isEqualTo(820);

    }
}
