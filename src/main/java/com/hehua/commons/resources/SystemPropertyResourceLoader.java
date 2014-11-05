/**
 * 
 */
package com.hehua.commons.resources;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.FileSystemResourceLoader;
import org.springframework.core.io.Resource;

/**
 * 
 * @author zhouzhihua <zhihua@afanda.com>
 * @version 1.0 create at Jul 16, 2013 4:02:32 PM
 */
public class SystemPropertyResourceLoader extends FileSystemResourceLoader {

    private final String configKey;

    /**
     * @param configKey
     */
    public SystemPropertyResourceLoader(String configKey) {
        super();
        this.configKey = configKey;
    }

    @Override
    protected Resource getResourceByPath(String path) {
        return super.getResourceByPath(path);
    }

    @Override
    public Resource getResource(String location) {
        String configFile = System.getProperty(configKey);
        if (StringUtils.isBlank(configFile)) {
            return null;
        }
        return super.getResource(configFile);
    }

}
