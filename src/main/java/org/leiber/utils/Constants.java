package org.leiber.utils;

/**
 * Utility class to handle constants in the package. <br>
 * <p>
 * This class contains constants that represent magic numbers.
 * and other values that are used in the project.
 * <p>
 * Created on 11/10/2024 at 7:24 p.m.
 *
 * @author Leiber Bertel
 */
public class Constants {

    private Constants() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated.");
    }

    /**
     * Provides the magic numbers used in the App. <p>
     * Created on 12/10/2024 at 12:03 a.m.
     *
     * @author Leiber Bertel
     */
    public static final class MagicNumber {
        public static final Integer ZERO = 0;
        public static final Integer ONE = 1;
        public static final Integer TWO = 2;
        public static final Integer THREE = 3;
        public static final Integer FOUR = 4;
        public static final Integer FIVE = 5;
        public static final Integer SIX = 6;
        public static final Integer SEVEN = 7;
        public static final Integer TEN = 10;

        private MagicNumber() {

        }
    }
}
