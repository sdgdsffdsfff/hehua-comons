package com.hehua.commons.encrypt;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class XOREncryptOutputStream extends FilterOutputStream {

    private final int key;

    private final int limit;

    private int writes;

    /**
     * @param out
     * @param key
     */
    public XOREncryptOutputStream(OutputStream out, int key) {
        super(out);
        this.key = key;
        this.limit = 0;
    }

    /**
     * @param out
     * @param key
     * @param limit
     */
    public XOREncryptOutputStream(OutputStream out, int key, int limit) {
        super(out);
        this.key = key;
        this.limit = limit;
    }

    @Override
    public void write(int b) throws IOException {
        writes++;
        if (limit > 0 && writes > limit) {
            super.write(b);
        } else {
            super.write(b ^ key);
        }
    }

}
