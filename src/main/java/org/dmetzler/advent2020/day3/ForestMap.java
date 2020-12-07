/*
  * (C) Copyright 2020 Nuxeo (http://nuxeo.com/).
 * This is unpublished proprietary source code of Nuxeo SA. All rights reserved.
 * Notice of copyright on this source code does not indicate publication.
 *
 * Contributors:
 *     dmetzler
 */
package org.dmetzler.advent2020.day3;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;

public class ForestMap {

    public enum TYPE {
        OPEN('.', 0), TREE('#', 1);

        private char code;

        private int cost;

        TYPE(char code, int cost) {
            this.code = code;
            this.cost = cost;
        }

        public static TYPE valueOf(char code) {

            for (TYPE type : TYPE.values()) {
                if (code == type.code) {
                    return type;
                }
            }
            throw new RuntimeException("Unknown terrain code");
        }

        public int getCost() {
            return cost;
        }

        @Override
        public String toString() {
            return "" + code;
        }

    }

    List<String> map = new ArrayList<>();

    public static ForestMap readFromFile(URL url) {
        try (InputStream in = url.openStream()) {

            ForestMap map = new ForestMap();

            IOUtils.readLines(in, Charset.defaultCharset()).forEach(r -> map.addRow(r));

            return map;
        } catch (IOException e) {
            return null;
        }

    }

    private void addRow(String r) {
        map.add(r);
    }

    public TYPE get(int i, int j) {
        String row = map.get(j);
        return TYPE.valueOf(row.charAt(i % row.length()));

    }

    /**
     * @return
     * @since TODO
     */
    public int getSize() {
        return map.size();
    }

}
