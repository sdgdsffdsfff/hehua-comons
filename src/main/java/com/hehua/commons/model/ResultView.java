/**
 * 
 */
package com.hehua.commons.model;

import java.util.Collections;
import java.util.Map;

/**
 * @author zhihua
 *
 */
public class ResultView<T> {

    public static final ResultView<Map<String, String>> SUCCESS = new ResultView<Map<String, String>>(
            CommonMetaCode.Success, Collections.<String, String> emptyMap());

    public static final ResultView<Map<String, String>> ERROR = new ResultView<Map<String, String>>(
            CommonMetaCode.Error, null);

    private final Meta meta;

    private final T data;

    public ResultView(MetaCode metaCode) {
        this(metaCode, null);
    }

    public ResultView(MetaCode metaCode, T data) {
        super();
        this.meta = new Meta(metaCode);
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public Meta getMeta() {
        return meta;
    }

}
