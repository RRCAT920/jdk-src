/*
 * Copyright (c) 2014, 2019, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */

package jdk.incubator.jpackage.internal;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * EnumeratedBundlerParams<T>
 *
 * Contains key-value pairs (elements) where keys are "displayable"
 * keys which the IDE can display/choose and values are "identifier" values
 * which can be stored in parameters' map.
 *
 * For instance the Mac has a predefined set of categories which can be applied
 * to LSApplicationCategoryType which is required for the mac app store.
 *
 * The following example illustrates a simple usage of
 *     the MAC_CATEGORY parameter:
 *
 * <pre>{@code
 *     Set<String> keys = MAC_CATEGORY.getDisplayableKeys();
 *
 *     String key = getLastValue(keys); // get last value for example
 *
 *     String value = MAC_CATEGORY.getValueForDisplayableKey(key);
 *     params.put(MAC_CATEGORY.getID(), value);
 * }</pre>
 *
 */
class EnumeratedBundlerParam<T> extends BundlerParamInfo<T> {
    // Not sure if this is the correct order, my idea is that from IDE
    // perspective the string to display to the user is the key and then the
    // value is some type of object (although probably a String in most cases)
    private final Map<String, T> elements;
    private final boolean strict;

    EnumeratedBundlerParam(String id, Class<T> valueType,
            Function<Map<String, ? super Object>, T> defaultValueFunction,
            BiFunction<String, Map<String, ? super Object>, T> stringConverter,
            Map<String, T> elements, boolean strict) {
        this.id = id;
        this.valueType = valueType;
        this.defaultValueFunction = defaultValueFunction;
        this.stringConverter = stringConverter;
        this.elements = elements;
        this.strict = strict;
    }

    boolean isInPossibleValues(T value) {
        return elements.values().contains(value);
    }

    // Having the displayable values as the keys seems a bit wacky
    Set<String> getDisplayableKeys() {
        return Collections.unmodifiableSet(elements.keySet());
    }

    // mapping from a "displayable" key to an "identifier" value.
    T getValueForDisplayableKey(String displayableKey) {
        return elements.get(displayableKey);
    }

    boolean isStrict() {
        return strict;
    }

    boolean isLoose() {
        return !isStrict();
    }

}
