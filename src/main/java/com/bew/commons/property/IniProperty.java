/*
 * This file is part of the BEW Commons Library (aka: BEWCommons).
 *
 * Copyright (C) 2020 Bradley Willcott
 *
 * BEWCommons is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * BEWCommons is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.bew.commons.property;

/**
 * This is a more specialized sub-class of {@code Property}, in
 * that it simplifies working with <i>ini</i> files.
 * <p>
 * Primarily it sets up the {@code <K>} type of
 * {@code Property<K extends Comparable<K>, V>} as {@link String}, thus
 * leaving only the {@code <V>} type for the {@code value}, to track.
 *
 * @param <V> value type.
 *
 * @author <a href="mailto:bw.opensource@yahoo.com">Bradley Willcott</a>
 *
 * @since 1.0.21
 * @version 1.0.21
 *
 * @deprecated Moved out to project: BEWProperty.
 */
@Deprecated
public class IniProperty<V> extends Property<String, V> {

    /**
     * @serial serial
     */
    private static final long serialVersionUID = -1375307249416791466L;

    /**
     * Create a new instance of {@code IniProperty} as a copy of an existing
     * instance of {@code Property}, or one of its sub-classes.
     *
     * @param <T>      the type of the class being copied.
     * @param property The instance to copy.
     */
    public <T extends Property<String, V>> IniProperty(T property) {
        super(property.key, property.value, property.comment);
    }

    /**
     * Create a new instance of {@code IniProperty} with a {@code null} comment.
     *
     * @param key   The key.
     * @param value The value.
     */
    public IniProperty(String key, V value) {
        super(key, value);
    }

    /**
     * Create a new instance of {@code IniProperty}.
     *
     * @param key     The key.
     * @param value   The value.
     * @param comment The comment.
     */
    public IniProperty(String key, V value, String comment) {
        super(key, value, comment);
    }
}
