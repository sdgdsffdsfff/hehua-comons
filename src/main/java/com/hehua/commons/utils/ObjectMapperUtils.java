package com.hehua.commons.utils;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.type.TypeFactory;
import org.codehaus.jackson.node.ArrayNode;
import org.codehaus.jackson.node.ObjectNode;

/**
 * JSON 与　Java对象映射工具
 * 
 * @author sam
 * 
 */
public class ObjectMapperUtils {

    private final static Log logger = LogFactory.getLog(ObjectMapperUtils.class);

    static ObjectMapper mapper = null;
    static {
        mapper = new ObjectMapper();
        mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public static <T> String toJSON(T obj) {
        try {
            String toJsonResult = mapper.writeValueAsString(obj);
            String filterJsonResult = JSONUtils.filterOutIllegalCharacter(toJsonResult);
            if (logger.isDebugEnabled()) {
                if (!StringUtils.equals(filterJsonResult, toJsonResult)) {
                    logger.debug("to json filter make diff, \nraw:" + toJsonResult + "\nfiltered:"
                            + filterJsonResult);
                }
            }
            return filterJsonResult;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T fromJSON(String json, Class<T> valueType) {
        try {
            return mapper.readValue(json, valueType);
        } catch (JsonParseException e) {
            try {
                logger.info("found invalid json to read:" + json);
                return mapper.readValue(JSONUtils.filterOutIllegalCharacter(json), valueType);
            } catch (Exception e1) {
                throw new RuntimeException(e1);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings({ "unchecked", "deprecation", "rawtypes" })
    public static <T> T fromJSON(String json, Class<? extends Collection> collectionType,
            Class<?> valueType) {
        if (json == null || json.length() == 0) {
            try {
                return (T) collectionType.newInstance();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        try {
            return (T) mapper
                    .readValue(json, TypeFactory.collectionType(collectionType, valueType));
        } catch (JsonParseException e) {
            try {
                logger.info("found invalid json to read:" + json);
                return (T) mapper.readValue(JSONUtils.filterOutIllegalCharacter(json),
                        TypeFactory.collectionType(collectionType, valueType));
            } catch (Exception e1) {
                throw new RuntimeException(e1);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings({ "unchecked", "deprecation", "rawtypes" })
    public static <T> T fromJSON(String json, Class<? extends Map> mapType, Class<?> keyType,
            Class<?> valueType) {
        if (json == null || json.length() == 0) {
            try {
                return (T) mapType.newInstance();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        try {
            return (T) mapper.readValue(json, TypeFactory.mapType(mapType, keyType, valueType));
        } catch (JsonParseException e) {
            try {
                logger.info("found invalid json to read:" + json);
                return (T) mapper.readValue(JSONUtils.filterOutIllegalCharacter(json),
                        TypeFactory.mapType(mapType, keyType, valueType));
            } catch (Exception e1) {
                throw new RuntimeException(e1);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static ObjectNode createObjectNode() {
        return mapper.createObjectNode();
    }

    public static ArrayNode createArrayNode() {
        return mapper.createArrayNode();
    }

    public static ObjectMapper getObjectMapper() {
        return mapper;
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        Set<String> sSet = new HashSet<String>();
        sSet.add("Hi,Hello");
        String json = toJSON(sSet);
        System.out.println(json);
        System.out.println(System.currentTimeMillis() - start);
        LinkedList<String> fromJSON = fromJSON(json, LinkedList.class, String.class);
        for (String string : fromJSON) {
            System.out.println(string);
        }
    }
}
