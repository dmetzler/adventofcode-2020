/*
 * (C) Copyright 2020 Nuxeo (http://nuxeo.com/).
 * This is unpublished proprietary source code of Nuxeo SA. All rights reserved.
 * Notice of copyright on this source code does not indicate publication.
 *
 * Contributors:
 *     dmetzler
 */
package org.dmetzler.advent2020.day3;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

public class Day3Test {

    private ForestMap map;

    @Before
    public void doBefore() {
        map = ForestMap.readFromFile(getClass().getResource("/day3-sample-input.txt"));
    }

    @Test
    public void can_read_map() throws Exception {

        assertThat(map.get(0, 0)).isEqualTo(ForestMap.TYPE.OPEN);
        assertThat(map.get(1, 0)).isEqualTo(ForestMap.TYPE.OPEN);
        assertThat(map.get(2, 0)).isEqualTo(ForestMap.TYPE.TREE);
        assertThat(map.get(3, 0)).isEqualTo(ForestMap.TYPE.TREE);

        assertThat(map.get(0, 1)).isEqualTo(ForestMap.TYPE.TREE);
        assertThat(map.get(1, 1)).isEqualTo(ForestMap.TYPE.OPEN);

        assertThat(map.get(11, 1)).isEqualTo(ForestMap.TYPE.TREE);
        assertThat(map.get(11, 2)).isEqualTo(ForestMap.TYPE.OPEN);
    }

    @Test
    public void can_count_trees() throws Exception {
        Toboggan tob = new SimpleToboggan(3);
        assertThat(tob.slide(map)).isEqualTo(7);

    }

    @Test
    public void can_test_multiple_slopes() throws Exception {

        int result = 1;
        for(Integer i : Arrays.asList(new Integer[] {1,3,5,7}) ) {
            Toboggan tob = new SimpleToboggan(i);
            result = result * tob.slide(map);
        }

        Toboggan tob = new SimpleToboggan(1,2);
        result = result * tob.slide(map);

        System.out.println(result);

    }
}
