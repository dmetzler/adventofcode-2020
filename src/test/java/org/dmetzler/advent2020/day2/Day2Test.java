/*
 * (C) Copyright 2020 Nuxeo (http://nuxeo.com/).
 * This is unpublished proprietary source code of Nuxeo SA. All rights reserved.
 * Notice of copyright on this source code does not indicate publication.
 *
 * Contributors:
 *     dmetzler
 */
package org.dmetzler.advent2020.day2;

import static org.assertj.core.api.Assertions.assertThat;

import java.net.URL;
import java.util.List;

import org.junit.Test;

public class Day2Test {

    private URL sampleInputURL = getClass().getResource("/day2-sample-input.txt");

    @Test
    public void can_read_password_and_policy() throws Exception {
        List<Password> passwords = Password.readFromFile(sampleInputURL);
        assertThat(passwords).isNotEmpty();
        assertThat(passwords).hasSize(5);

        Password sample = passwords.get(0);
        assertThat(sample).isEqualTo(new Password("1-3 a", "abcde"));

    }

    @Test
    public void can_detect_corrupt_password_old_policy() throws Exception {
        List<Password> passwords = Password.readFromFile(sampleInputURL);

        PasswordPolicy policy = new OldPolicy();
        assertThat(passwords.get(0).isValid(policy)).isTrue();
        assertThat(passwords.get(1).isValid(policy)).isFalse();
        assertThat(passwords.get(2).isValid(policy)).isTrue();
        assertThat(passwords.get(3).isValid(policy)).isFalse();
    }

    @Test
    public void can_detect_corrupt_password_new_policy() throws Exception {
        List<Password> passwords = Password.readFromFile(sampleInputURL);

        PasswordPolicy policy = new CurrentPolicy();
        assertThat(passwords.get(0).isValid(policy)).isTrue();
        assertThat(passwords.get(1).isValid(policy)).isFalse();
        assertThat(passwords.get(2).isValid(policy)).isFalse();
    }

}
