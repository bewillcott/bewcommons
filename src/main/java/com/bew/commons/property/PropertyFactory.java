/*
 * Copyright (C) 2020 <a href="mailto:bw.opensource@yahoo.com">Bradley Willcott</a>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.bew.commons.property;

import com.bew.commons.property.impl.*;

/**
 * This is a factory class for obtaining various Property implementations.
 *
 * @author <a href="mailto:bw.opensource@yahoo.com">Bradley Willcott</a>
 *
 * @since 1.0.20
 * @version 1.0.20
 */
public class PropertyFactory {

    /**
     * Obtain a new Property implementation instance.
     *
     * @param <K>   Key type.
     * @param <V>   Value type.
     * @param key   new key.
     * @param value new value.
     *
     * @return a new instance of an Property implementation.
     */
    public static <K, V> Property<K, V> create(K key, V value) {
        return new PropertyImpl<>(key, value);
    }

    /**
     * Obtain a new IniFileProperty implementation instance.
     *
     * @param <V>   Value type.
     * @param key   new key.
     * @param value new value.
     *
     * @return a new instance of an IniFileProperty implementation.
     */
    public static <V> IniFileProperty<V> create(String key, V value) {
        return new IniFilePropertyImpl<>(key, value);
    }

    /**
     * Obtain a new PropertyComment implementation instance.
     *
     * @param <K>     Key type.
     * @param <V>     Value type.
     * @param key     new key.
     * @param value   new value.
     * @param comment new comment.
     *
     * @return a new instance of an PropertyComment implementation.
     */
    public static <K, V> PropertyComment<K, V> create(K key, V value, String comment) {
        return new PropertyCommentImpl<>(key, value, comment);
    }

    /**
     * Obtain a new IniFileProperty implementation instance.
     *
     * @param <V>     Value type.
     * @param key     new key.
     * @param value   new value.
     * @param comment new comment.
     *
     * @return a new instance of an IniFileProperty implementation.
     */
    public static <V> IniFileProperty<V> create(String key, V value, String comment) {
        return new IniFilePropertyImpl<>(key, value, comment);
    }

    /**
     * Obtain a new ImmutableProperty implementation instance.
     *
     * @param <K>   Key type.
     * @param <V>   Value type.
     * @param key   new key.
     * @param value new value.
     *
     * @return a new instance of an ImmutableProperty implementation.
     */
    public static <K, V> ImmutableProperty<K, V> createImmutable(K key, V value) {
        return new ImmutablePropertyImpl<>(key, value);
    }

    /**
     * Obtain a new ImmutableIniFileProperty implementation instance.
     *
     * @param <V>   Value type.
     * @param key   new key.
     * @param value new value.
     *
     * @return a new instance of an ImmutableIniFileProperty implementation.
     */
    public static <V> ImmutableIniFileProperty<V> createImmutable(String key, V value) {
        return new ImmutableIniFilePropertyImpl<>(key, value);
    }

    /**
     * Obtain a new ImmutablePropertyComment implementation instance.
     *
     * @param <K>     Key type.
     * @param <V>     Value type.
     * @param key     new key.
     * @param value   new value.
     * @param comment new comment.
     *
     * @return a new instance of an ImmutablePropertyComment implementation.
     */
    public static <K, V> ImmutablePropertyComment<K, V> createImmutable(K key, V value, String comment) {
        return new ImmutablePropertyCommentImpl<>(key, value, comment);
    }

    /**
     * Obtain a new ImmutableIniFileProperty implementation instance.
     *
     * @param <V>     Value type.
     * @param key     new key.
     * @param value   new value.
     * @param comment new comment.
     *
     * @return a new instance of an ImmutableIniFileProperty implementation.
     */
    public static <V> ImmutableIniFileProperty<V> createImmutable(String key, V value, String comment) {
        return new ImmutableIniFilePropertyImpl<>(key, value, comment);
    }

    private PropertyFactory() {
    }
}
