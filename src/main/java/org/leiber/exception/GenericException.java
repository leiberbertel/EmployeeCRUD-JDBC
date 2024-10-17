package org.leiber.exception;

/**
 * Generic exception.
 * <p>
 * Created on 09/10/2024 at 11:20 p.m.
 *
 * @author Leiber Bertel
 */
public class GenericException extends RuntimeException {
    public GenericException(String errorMsg) {
        super("Technical error. Please contact the administrator.\n" + errorMsg);
    }
}
