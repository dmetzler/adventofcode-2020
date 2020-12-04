/*
 * (C) Copyright 2020 Nuxeo (http://nuxeo.com/).
 * This is unpublished proprietary source code of Nuxeo SA. All rights reserved.
 * Notice of copyright on this source code does not indicate publication.
 *
 * Contributors:
 *     dmetzler
 */
package org.dmetzler.advent2020.day2;

import java.util.regex.Pattern;

public interface PasswordPolicy {

    public static final Pattern PATTERN = Pattern.compile("([0-9]*)-([0-9]*) (.)");

    String name();

    boolean isValid(Password password);

}
