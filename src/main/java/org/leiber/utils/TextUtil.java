package org.leiber.utils;

/**
 * Utility class for text validations <br>
 * Created on 11/10/2024 at 9:34 p.m.
 *
 * @author Leiber Bertel
 */
public class TextUtil {

    private TextUtil() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated.");
    }

    /**
     * Validates if a text is null or empty <br>
     * Created on 12/10/2024 at 12:00 a.m. <br>
     *
     * @author Leiber Bertel
     */
    public static boolean isEmptyNull(String text) {
        return text == null || text.trim().isEmpty() || text.isEmpty();
    }
}
