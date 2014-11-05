/**
 * 
 */
package com.hehua.commons.security.web.whitelist;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

/**
 * @author zhihua
 *
 */
public class StyleCleaner {

    /**
     * 
     */
    public StyleCleaner() {
        super();
        init();
    }

    public interface StyleProperty {

        public String name();

        public boolean isSafe(String value);
    }

    public class EnumValueStyleProperty implements StyleProperty {

        private String name;

        private Set<String> values;

        /**
         * @param name
         * @param values
         */
        public EnumValueStyleProperty(String name, Set<String> values) {
            super();
            this.name = name;
            this.values = values;
        }

        @Override
        public String name() {
            return name;
        }

        @Override
        public boolean isSafe(String value) {
            return values.contains(value);
        }

    }

    public class PatternValueStyleProperty implements StyleProperty {

        private String name;

        private List<Pattern> valuePatterns;

        /**
         * @param name
         * @param valuePatterns
         */
        public PatternValueStyleProperty(String name, List<Pattern> valuePatterns) {
            super();
            this.name = name;
            this.valuePatterns = valuePatterns;
        }

        @Override
        public String name() {
            return name;
        }

        @Override
        public boolean isSafe(String value) {
            for (Pattern valuePattern : valuePatterns) {
                Matcher matcher = valuePattern.matcher(value);
                if (matcher.matches()) {
                    return true;
                }
            }
            return false;
        }

    }

    private Map<String, StyleProperty> propertiesMap = new HashMap<>();

    private void init() {

        // 只接收
        // text-decoration
        // text-indent
        // text-align
        // list-style-type
        // color
        // background-color

        addEnumValueProperty("text-decoration",
                Sets.newHashSet("none", "line-through", "underline", "overline"));

        addEnumValueProperty("text-align", Sets.newHashSet("left", "right", "center", "justify"));

        addPatternValueProperty("text-indent", Lists.newArrayList(Pattern.compile("^[\\d]+em$"),
                Pattern.compile("^[\\d]+px$"), Pattern.compile("^[\\d]+%$"),
                Pattern.compile("^[\\d]+$")));

        addEnumValueProperty("list-style-type", Sets.newHashSet("none", "disc", "circle", "square",
                "decimal", "decimal-leading-zero", "lower-roman", "upper-roman", "lower-alpha",
                "upper-alpha"));

        addPatternValueProperty("font-size", Lists.newArrayList(Pattern.compile("^[\\d]+em$"),
                Pattern.compile("^[\\d]+px$"), Pattern.compile("^[\\d]+$")));

        addPatternValueProperty(
                "color",
                Lists.newArrayList(
                        Pattern.compile("^(red|white|gray|silver|black|maroon|red|purple|fuchsia|green|lime|olive|yellow|navy|blue|teal|aqua|orange)$"), //
                        Pattern.compile("^#[0-9a-f]{3,6}$"), //
                        Pattern.compile("^rgb\\(\\s*[\\d]{1,3}\\s*,\\s*[\\d]{1,3}\\s*,\\s*[\\d]{1,3}\\s*\\)$")));

        addPatternValueProperty(
                "background-color",
                Lists.newArrayList(
                        Pattern.compile("^(red|white|gray|silver|black|maroon|red|purple|fuchsia|green|lime|olive|yellow|navy|blue|teal|aqua|orange)$"), //
                        Pattern.compile("^#[0-9a-f]{3,6}$"), //
                        Pattern.compile("^rgb\\(\\s*[\\d]{1,3}\\s*,\\s*[\\d]{1,3}\\s*,\\s*[\\d]{1,3}\\s*\\)$")));

    }

    private void addPatternValueProperty(String name, List<Pattern> patterns) {
        addProperty(new PatternValueStyleProperty(name, patterns));
    }

    private void addEnumValueProperty(String name, Set<String> values) {
        addProperty(new EnumValueStyleProperty(name, values));
    }

    private void addProperty(StyleProperty property) {
        this.propertiesMap.put(property.name(), property);
    }

    public String clean(String dirty) {

        dirty = StringUtils.trimToEmpty(dirty);

        if (dirty.isEmpty() || dirty.length() > 1000) {
            return StringUtils.EMPTY;
        }

        String[] splits = dirty.split(";");

        List<String> safes = new ArrayList<>(splits.length);

        for (String split : splits) {
            split = StringUtils.trimToEmpty(split);

            if (isSafeProperty(split)) {
                safes.add(split);
            }
        }

        if (safes.isEmpty()) {
            return StringUtils.EMPTY;
        }
        return StringUtils.join(safes, "; ") + ";";

    }

    public boolean isSafeProperty(String e) {

        e = StringUtils.trimToEmpty(e);

        if (StringUtils.isBlank(e)) {
            return false;
        }

        String name = null;
        String value = null;
        String[] splits = e.split(":", 2);
        if (splits.length > 0) {
            name = splits[0];
        }
        if (splits.length > 1) {
            value = splits[1];
        }

        return isSafeProperty(name, value);
    }

    public boolean isSafeProperty(String name, String value) {

        if (StringUtils.isBlank(name)) {
            return false;
        }

        StyleProperty styleProperty = propertiesMap.get(name);
        if (styleProperty == null) {
            return false;
        }

        value = StringUtils.trimToEmpty(value);

        return styleProperty.isSafe(value);
    }

    public static void main(String[] args) {
        StyleCleaner styleCleaner = new StyleCleaner();

        System.out
                .println(styleCleaner
                        .clean("text-decoration: line-through; color: red1; color: rgb(227, 108, 9); background-color: rgb(235, 241, 221);align:left;"));;
    }
}
