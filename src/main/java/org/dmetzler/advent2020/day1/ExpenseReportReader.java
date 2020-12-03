/*
 * (C) Copyright 2020 Nuxeo (http://nuxeo.com/).
 * This is unpublished proprietary source code of Nuxeo SA. All rights reserved.
 * Notice of copyright on this source code does not indicate publication.
 *
 * Contributors:
 *     dmetzler
 */
package org.dmetzler.advent2020.day1;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.io.IOUtils;

public class ExpenseReportReader {

    public static List<Integer> readExpenses(InputStream in) {
        try {
            return IOUtils.readLines(in, Charset.defaultCharset())
                          .stream()
                          .map(Integer::parseInt)
                          .collect(Collectors.toList());
        } catch (IOException e) {
            return Collections.emptyList();
        }
    }

}
