/*
 * (C) Copyright 2020 Nuxeo (http://nuxeo.com/).
 * This is unpublished proprietary source code of Nuxeo SA. All rights reserved.
 * Notice of copyright on this source code does not indicate publication.
 *
 * Contributors:
 *     dmetzler
 */
package org.dmetzler.advent2020.day2;

import java.util.regex.Matcher;

/**
 * @since TODO
 */
public class OldPolicy implements PasswordPolicy {

    @Override
    public String name() {
        return "sled rental place down the street";
    }

    @Override
    public boolean isValid(Password password) {

        Matcher matcher = PATTERN.matcher(password.getPolicyDefinition());
        if (matcher.find()) {
            int minOccurence = Integer.parseInt(matcher.group(1));
            int maxOccurence = Integer.parseInt(matcher.group(2));
            char letter = matcher.group(3).charAt(0);
            long count = password.getPasswordAsString().chars().filter(c -> c == letter).count();
            return count >= minOccurence && count <= maxOccurence;

        } else {
            return false;
        }

    }

}
