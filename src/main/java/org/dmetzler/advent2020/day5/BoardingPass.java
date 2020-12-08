/*
 * (C) Copyright 2020 Nuxeo (http://nuxeo.com/).
 * This is unpublished proprietary source code of Nuxeo SA. All rights reserved.
 * Notice of copyright on this source code does not indicate publication.
 *
 * Contributors:
 *     dmetzler
 */
package org.dmetzler.advent2020.day5;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.io.IOUtils;

public class BoardingPass {

    private String pass;

    /**
     * @param string
     */
    public BoardingPass(String pass) {
        this.pass = pass.replace("F", "0").replace("B", "1").replace("L", "0").replace("R", "1");
    }

    public static List<BoardingPass> readFromFile(URL resource) {
        try {
            return IOUtils.readLines(resource.openStream(), Charset.defaultCharset())
                          .stream()
                          .map(BoardingPass::readFromString)
                          .collect(Collectors.toList());
        } catch (IOException e) {
            return null;
        }
    }

    public static BoardingPass readFromString(String pass) {
        return new BoardingPass(pass);
    }

    public int getRow() {
        return Integer.parseInt(pass.substring(0, 7), 2);
    }

    public int getColumn() {
        return Integer.parseInt(pass.substring(7, 10), 2);
    }

    public int getId() {
        return Integer.parseInt(pass, 2);
    }

}
