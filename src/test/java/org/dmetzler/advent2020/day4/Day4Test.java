/*
 * (C) Copyright 2020 Nuxeo (http://nuxeo.com/).
 * This is unpublished proprietary source code of Nuxeo SA. All rights reserved.
 * Notice of copyright on this source code does not indicate publication.
 *
 * Contributors:
 *     dmetzler
 */
package org.dmetzler.advent2020.day4;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.dmetzler.advent2020.day4.Passport.EyeColorValidator;
import org.dmetzler.advent2020.day4.Passport.Field;
import org.dmetzler.advent2020.day4.Passport.FieldValidator;
import org.dmetzler.advent2020.day4.Passport.HairColorValidator;
import org.dmetzler.advent2020.day4.Passport.HeightValidator;
import org.dmetzler.advent2020.day4.Passport.PidValidator;
import org.dmetzler.advent2020.day4.Passport.YearValidator;
import org.junit.Test;

public class Day4Test {

    List<Passport> passports = Passport.readFrom(this.getClass().getResource("/day4-sample-input.txt"));

    @Test
    public void can_read_passport_file() throws Exception {
        assertThat(passports).hasSize(4);
    }

    @Test
    public void can_read_passport() throws Exception {
        Passport passport = passports.get(0);
        assertThat(passport.getField(Field.BIRTH_YEAR)).isEqualTo("1937");
    }

    @Test
    public void can_test_passport_valid() throws Exception {

        assertThat(passports.get(0).isValid()).isTrue();
        assertThat(passports.get(1).isValid()).isFalse();
        assertThat(passports.get(2).isValid()).isTrue();
        assertThat(passports.get(3).isValid()).isFalse();
    }

    @Test
    public void can_validate_years() throws Exception {
        FieldValidator validator = new YearValidator(1920, 2002);
        assertThat(validator.validate("2002")).isTrue();
        assertThat(validator.validate("2003")).isFalse();
    }

    @Test
    public void can_validate_height() throws Exception {
        FieldValidator validator = new HeightValidator();

        assertThat(validator.validate("60in")).isTrue();
        assertThat(validator.validate("190cm")).isTrue();
        assertThat(validator.validate("190in")).isFalse();
        assertThat(validator.validate("190")).isFalse();
        assertThat(validator.validate("in")).isFalse();
        assertThat(validator.validate("cm")).isFalse();
    }

    @Test
    public void can_validate_hcl() throws Exception {
        FieldValidator validator = new HairColorValidator();

        assertThat(validator.validate("#123abc")).isTrue();
        assertThat(validator.validate("#123abz")).isFalse();
        assertThat(validator.validate("123abc")).isFalse();

    }

    @Test
    public void can_validate_ecl() throws Exception {
        FieldValidator validator = new EyeColorValidator();

        assertThat(validator.validate("brn")).isTrue();
        assertThat(validator.validate("wat")).isFalse();

    }

    @Test
    public void can_validate_pid() throws Exception {
        FieldValidator validator = new PidValidator();

        assertThat(validator.validate("000000001")).isTrue();
        assertThat(validator.validate("0123456789")).isFalse();

    }

    @Test
    public void can_validate_passport_fileds() throws Exception {
        List<Passport> validPassports = Passport.readFrom(this.getClass().getResource("/day4-valid-passport.txt"));
        List<Passport> invalidPassports = Passport.readFrom(this.getClass().getResource("/day4-invalid-passport.txt"));

        assertThat(validPassports.stream().allMatch(Passport::isValid)).isTrue();
        assertThat(invalidPassports.stream().noneMatch(Passport::isValid)).isTrue();
    }

}
