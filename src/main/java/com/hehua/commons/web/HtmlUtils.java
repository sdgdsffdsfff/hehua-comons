/**
 * 
 */
package com.hehua.commons.web;

import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author zhihua
 *
 */
public class HtmlUtils {

    private static final Log logger = LogFactory.getLog(HtmlUtils.class);

    static String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>"; //定义script的正则表达式{或<script>]*?>[\\s\\S]*?<\\/script> }

    static String regEx_option = "<[\\s]*?option[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?option[\\s]*?>"; //定义option的正则表达式{或<script>]*?>[\\s\\S]*?<\\/script> }

    static String regEx_select = "<[\\s]*?select[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?select[\\s]*?>"; //定义select的正则表达式{或<script>]*?>[\\s\\S]*?<\\/script> }

    static String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>"; //定义style的正则表达式{或<style>]*?>[\\s\\S]*?<\\/style> }

    static String regEx_html_exp = "<![^>].*?\\s*?-\\s*?-\\s*?>";// "<[\\s]*?\\![^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?[\\s]*?>"; //定义style的正则表达式{或<style>]*?>[\\s\\S]*?<\\/style> }

    static String regEx_html = "<[^>]+>"; //定义HTML标签的正则表达式    

    static Pattern p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);

    static Pattern p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);

    static Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);

    static Pattern p_option = Pattern.compile(regEx_option, Pattern.CASE_INSENSITIVE);

    static Pattern p_select = Pattern.compile(regEx_select, Pattern.CASE_INSENSITIVE);

    static Pattern p_html_exp = Pattern.compile(regEx_html_exp, Pattern.CASE_INSENSITIVE);

    public static String html2Text(String html) {
        html = org.apache.commons.lang3.StringEscapeUtils.unescapeHtml4(html);
        try {
            html = p_option.matcher(html).replaceAll(""); //过滤select标签
            html = p_select.matcher(html).replaceAll(""); //过滤option标签
            html = p_script.matcher(html).replaceAll(""); //过滤script标签
            html = p_style.matcher(html).replaceAll(""); //过滤style标签  
            html = p_html_exp.matcher(html).replaceAll(""); //过滤注释
            html = p_html.matcher(html).replaceAll(""); //过滤html标签   
        } catch (Exception e) {
            logger.error("html2Text(String)", e); //$NON-NLS-1$
        }
        return html;//返回文本字符串    
    }
}
