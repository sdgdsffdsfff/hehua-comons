/**
 * 
 */
package com.hehua.commons.exception;

/**
 * 内部异常
 * 
 * @author zhouzhihua <zhihua@afanda.com>
 * @version 1.0 create at Aug 21, 2012 3:36:45 PM
 */
public class InternalException extends RuntimeException {

    public static final String INTERNAL_EXCEPTION_MESSAGE_KEY = "internal_exception";

    private static final long serialVersionUID = -7774614868598125805L;

    private final String messageKey;

    /**
     * @param messageKey
     * @param message
     * @param cause
     */
    public InternalException(String messageKey, String message, Throwable cause) {
        super(message, cause);
        this.messageKey = messageKey;
    }

    /**
     * @param message
     * @param cause
     */
    public InternalException(String message, Throwable cause) {
        super(message, cause);
        this.messageKey = INTERNAL_EXCEPTION_MESSAGE_KEY;
    }

    public String getMessageKey() {
        return messageKey;
    }

}
