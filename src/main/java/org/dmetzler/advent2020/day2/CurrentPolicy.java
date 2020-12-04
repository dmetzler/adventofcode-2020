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

public class CurrentPolicy implements PasswordPolicy {

    @Override
    public String name() {
        return "Official Toboggan Corporate Authentication System";
    }

    @Override
    public boolean isValid(Password password) {
        Matcher matcher = PATTERN.matcher(password.getPolicyDefinition());
        if (matcher.find()) {
            int index1 = Integer.parseInt(matcher.group(1));
            int index2 = Integer.parseInt(matcher.group(2));
            char letter = matcher.group(3).charAt(0);

            int count = 0;
            if (password.getPasswordAsString().charAt(index1 - 1) == letter) {
                count++;
            }
            if (password.getPasswordAsString().charAt(index2 - 1) == letter) {
                count++;
            }
            return count == 1;

        } else {
            return false;
        }
    }

}
