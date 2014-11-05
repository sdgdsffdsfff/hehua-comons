/**
 * 
 */
package com.hehua.commons.model;

/**
 * @author zhihua
 *
 */
public class MetaCodeImpl implements MetaCode {

    private int code;

    private String message;

    /**
     * @param code
     * @param message
     */
    public MetaCodeImpl(int code, String message) {
        super();
        this.code = code;
        this.message = message;
    }

    @Override
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
