/*
 * (C) Copyright 2020 Nuxeo (http://nuxeo.com/).
 * This is unpublished proprietary source code of Nuxeo SA. All rights reserved.
 * Notice of copyright on this source code does not indicate publication.
 *
 * Contributors:
 *     dmetzler
 */
package org.dmetzler.advent2020.day4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @since TODO
 */
public class Passport {

    enum Field {
        BIRTH_YEAR("byr", new YearValidator(1920, 2002)), //
        ISSUE_YEAR("iyr", new YearValidator(2010, 2020)), //
        EXPIRATION_YEAR("eyr", new YearValidator(2020, 2030)), //
        HEIGHT("hgt", new HeightValidator()), //
        HAIR_COLOR("hcl", new HairColorValidator()), //
        EYE_COLOR("ecl", new EyeColorValidator()), //
        PASSPORT_ID("pid", new PidValidator()), //
        COUNTRY_ID("cid", new YesValidator());

        private String code;

        private FieldValidator validator;

        private Field(String code, FieldValidator validator) {
            this.code = code;
            this.validator = validator;
        }

        public static Field from(String code) {

            for (Field field : Field.values()) {
                if (field.code.equals(code)) {
                    return field;
                }
            }
            throw new RuntimeException("Unknown field type");
        }

        public static EnumSet<Field> mandatoryFields() {
            return EnumSet.complementOf(EnumSet.of(Field.COUNTRY_ID));
        }
    }

    public interface FieldValidator {
        boolean validate(String value);
    }

    public static class YesValidator implements FieldValidator {

        @Override
        public boolean validate(String value) {
            return true;
        }

    }

    public static class YearValidator implements FieldValidator {

        private int start;

        private int end;

        public YearValidator(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public boolean validate(String value) {
            try {
                int year = Integer.parseInt(value);
                return year >= start && year <= end;
            } catch (NumberFormatException e) {
                return false;
            }
        }

    }

    public static class HeightValidator implements FieldValidator {

        private static final Pattern PATTERN = Pattern.compile("(\\d{1,3})([i|c])");

        @Override
        public boolean validate(String value) {
            Matcher matcher = PATTERN.matcher(value);
            if (matcher.find()) {
                String unit = matcher.group(2);
                int size = Integer.parseInt(matcher.group(1));

                if ("i".equals(unit)) {
                    return size >= 59 && size <= 76;
                } else {
                    return size >= 150 && size <= 193;
                }
            } else {
                return false;
            }
        }

    }

    public abstract static class AbstractRegexValidator implements FieldValidator {

        private String pattern;

        /**
         *
         */
        public AbstractRegexValidator(String pattern) {
            this.pattern = pattern;
        }

        @Override
        public boolean validate(String value) {
            return value.matches(pattern);
        }

    }

    public static class HairColorValidator extends AbstractRegexValidator {

        private static final String PATTERN = "#[a-f0-9]{6}";

        public HairColorValidator() {
            super(PATTERN);
        }

    }

    public static class PidValidator extends AbstractRegexValidator {

        private static final String PATTERN = "[0-9]{9}";

        public PidValidator() {
            super(PATTERN);
        }

    }

    public static class EyeColorValidator implements FieldValidator {

        private String[] eyeColors = new String[] { "amb", "blu", "brn", "gry", "grn", "hzl", "oth" };

        @Override
        public boolean validate(String value) {
            return Arrays.asList(eyeColors).contains(value);
        }

    }

    private Map<Field, String> fields = new HashMap<>();

    public static List<Passport> readFrom(URL url) {

        try (InputStream in = url.openStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(in, "UTF-8"))) {
            List<Passport> result = new ArrayList<>();
            Passport current = new Passport();
            String line = reader.readLine();
            while (line != null) {
                if ("".equals(line)) {
                    result.add(current);
                    current = new Passport();
                } else {
                    fillWithLine(current, line);
                }
                line = reader.readLine();
            }
            result.add(current);
            return result;
        } catch (IOException e) {
            return null;
        }

    }

    private static void fillWithLine(Passport passport, String line) {
        Collections.list(new StringTokenizer(line, " ")).stream().map(token -> (String) token).forEach(str -> {
            String[] fieldArray = str.split(":");
            passport.fields.put(Field.from(fieldArray[0]), fieldArray[1]);
        });
    }

    public String getField(Field field) {
        return fields.get(field);
    }

    public boolean isValid() {
        boolean valid = fields.keySet().containsAll(Field.mandatoryFields());
        for (Entry<Field, String> field : fields.entrySet()) {
            valid = valid && field.getKey().validator.validate(field.getValue());
        }
        return valid;
    }

}
