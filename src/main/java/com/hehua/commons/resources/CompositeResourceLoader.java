/**
 * 
 */
package com.hehua.commons.resources;

import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.ClassUtils;

/**
 * 
 * @author zhouzhihua <zhihua@afanda.com>
 * @version 1.0 create at Jul 16, 2013 4:01:13 PM
 */
public class CompositeResourceLoader implements ResourceLoader {

    private final ResourceLoader[] resourceLoaders;

    /**
     * @param resourceLocators
     */
    public CompositeResourceLoader(ResourceLoader[] resourceLoaders) {
        super();
        this.resourceLoaders = resourceLoaders;
    }

    @Override
    public Resource getResource(String location) {
        for (ResourceLoader resourceLoader : resourceLoaders) {
            Resource resource = resourceLoader.getResource(location);
            if (resource != null && resource.exists()) {
                return resource;
            }
        }
        return null;
    }

    @Override
    public ClassLoader getClassLoader() {
        return ClassUtils.getDefaultClassLoader();
    }

}
