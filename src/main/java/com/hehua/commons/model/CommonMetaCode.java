/**
 * 
 */
package com.hehua.commons.model;

/**
 * @author zhihua
 *
 */
public enum CommonMetaCode implements MetaCode {

    Success(0, "成功"),

    BadReqeuest(400, "请求错误"),

    Unauthorized(401, "未登录"),

    NotFound(404, "请求不存在"),

    Forbidden(405, "禁止访问"),

    AuthenticationExpired(451, "授权过期"),

    Error(500, "内部错误")

    ;

    private final int code;

    private final String message;

    /**
     * @param code
     * @param message
     */
    private CommonMetaCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
