/*
 * (C) Copyright 2020 Nuxeo (http://nuxeo.com/).
 * This is unpublished proprietary source code of Nuxeo SA. All rights reserved.
 * Notice of copyright on this source code does not indicate publication.
 *
 * Contributors:
 *     dmetzler
 */
package org.dmetzler.advent2020.day2;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.apache.commons.io.IOUtils;

public class Password {

    private static final Pattern PATTERN = Pattern.compile("(.*): (.*)");

    final String policyDefinition;

    final String passwordAsString;

    public Password(String policyDefinition, String password) {
        this.policyDefinition = policyDefinition;
        this.passwordAsString = password;
    }

    public static List<Password> readFromFile(URL url) {
        try (InputStream in = url.openStream()) {
            return IOUtils.readLines(in, Charset.defaultCharset())
                          .stream()
                          .map(Password::fromString)
                          .filter(Objects::nonNull)
                          .collect(Collectors.toList());
        } catch (IOException e) {
            return Collections.emptyList();
        }
    }

    public static Password fromString(String str) {

        Matcher matcher = PATTERN.matcher(str);
        if (matcher.find()) {
            String policy = matcher.group(1);
            String password = matcher.group(2);
            return new Password(policy, password);
        } else {
            return null;
        }
    }

    public boolean isValid(PasswordPolicy policy) {
        return policy.isValid(this);

    }

    @Override
    public int hashCode() {
        return Objects.hash(passwordAsString, policyDefinition);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Password other = (Password) obj;
        return Objects.equals(passwordAsString, other.passwordAsString)
                && Objects.equals(policyDefinition, other.policyDefinition);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Password [");
        if (policyDefinition != null) {
            builder.append("policyDefinition=");
            builder.append(policyDefinition);
            builder.append(", ");
        }
        if (passwordAsString != null) {
            builder.append("passwordAsString=");
            builder.append(passwordAsString);
        }
        builder.append("]");
        return builder.toString();
    }

    public String getPasswordAsString() {
        return passwordAsString;
    }

    /**
     * @return
     * @since TODO
     */
    public String getPolicyDefinition() {
        return policyDefinition;
    }

}
