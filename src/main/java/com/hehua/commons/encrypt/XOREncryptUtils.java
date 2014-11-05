/**
 * 
 */
package com.hehua.commons.encrypt;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.io.IOUtils;

/**
 * 
 * @author zhouzhihua <zhihua@afanda.com>
 * @version 1.0 create at May 5, 2014 4:54:44 PM
 */
public class XOREncryptUtils {

    /**
     * @param inputFilename
     * @param outputFilename
     */
    public static void encrpty(String inputFilename, String outputFilename) {
        InputStream inputFileStream = null;

        OutputStream outputFileStream = null;

        try {
            inputFileStream = new FileInputStream(new File(inputFilename));

            outputFileStream = new BufferedOutputStream(new FileOutputStream(outputFilename));

            int c = 0;
            int b = inputFileStream.read();
            while (b != -1) {
                c++;
                if (c > 10) {
                    outputFileStream.write(b);
                } else {
                    outputFileStream.write(b ^ 13);
                }
                b = inputFileStream.read();
            }
            outputFileStream.flush();

        } catch (FileNotFoundException e) {
            throw new RuntimeException("inputFilename=" + inputFilename + ",outputFileName="
                    + outputFilename, e);
        } catch (IOException e) {
            throw new RuntimeException("inputFilename=" + inputFilename + ",outputFileName="
                    + outputFilename, e);
        } finally {
            IOUtils.closeQuietly(inputFileStream);
            IOUtils.closeQuietly(outputFileStream);
        }
    }

    public static void decrpty(String inputFilename, String outputFilename) {
        encrpty(inputFilename, outputFilename);
    }
}
