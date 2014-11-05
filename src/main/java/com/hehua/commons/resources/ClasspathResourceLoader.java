/**
 * 
 */
package com.hehua.commons.resources;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.util.ResourceUtils;

/**
 * 
 * @author zhouzhihua <zhihua@afanda.com>
 * @version 1.0 create at Jul 16, 2013 4:03:48 PM
 */
public class ClasspathResourceLoader extends DefaultResourceLoader {

    @Override
    public Resource getResource(String location) {
        if (!StringUtils.startsWith(location, ResourceUtils.CLASSPATH_URL_PREFIX)) {
            location = ResourceUtils.CLASSPATH_URL_PREFIX + location;
        }
        return super.getResource(location);
    }

}
