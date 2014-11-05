/**
 * 
 */
package com.hehua.commons.encrypt;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 
 * @author zhouzhihua <zhihua@afanda.com>
 * @version 1.0 create at May 5, 2014 4:08:55 PM
 */
public class XORDecryptInputStream extends FilterInputStream {

    private final int key;

    /**
     * @param in
     * @param key
     */
    public XORDecryptInputStream(InputStream in, int key) {
        super(in);
        this.key = key;
    }

    @Override
    public int read() throws IOException {
        return super.read() ^ key;
    }

}
