/**
 * 
 */
package com.hehua.commons.exception;

import com.hehua.commons.model.MetaCode;

/**
 * 业务级别异常，所有业务的结果
 * 
 * @author zhouzhihua <zhihua@afanda.com>
 * @version 1.0 create at Aug 21, 2012 3:25:07 PM
 */
public class BusinessException extends Exception {

    private static final long serialVersionUID = 8685359675371536445L;

    private final MetaCode code;

    /**
     * @param code
     * @param message
     */
    public BusinessException(MetaCode code, String message) {
        super(message);
        this.code = code;
    }

    /**
     * @param code
     */
    public BusinessException(MetaCode code) {
        super();
        this.code = code;
    }

    /**
     * @param message
     * @param cause
     */
    public BusinessException(MetaCode code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public MetaCode getCode() {
        return code;
    }

}
