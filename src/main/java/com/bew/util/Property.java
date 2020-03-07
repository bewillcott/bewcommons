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
package com.bew.util;

import java.io.Serializable;

/**
 * This class contains an individual property, {key:value} pair. It also has
 * provision for a comment.
 *
 * @param <K> Object type for {@code key}.
 * @param <V> Object type for {@code value}.
 *
 *
 * @author Brad Willcott
 * @since 1.0
 * @version 1.0
 */
public class Property<K, V> implements Serializable {

    private static final long serialVersionUID = -3214267512383191249L;

    public final K key;
    public final V value;
    public final String comment;

    /**
     * The members of this class can only be set through a constructor.
     *
     * @param key   Set key
     * @param value Set value
     */
    public Property(K key, V value) {
        this.key = key;
        this.value = value;
        comment = null;
    }

    /**
     * The members of this class can only be set through a constructor.
     *
     * @param comment Set comment.
     * @param key     Set key.
     * @param value   Set value.
     */
    public Property(K key, V value, String comment) {
        this.key = key;
        this.value = value;
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "Property {" + "key=" + key + ", value=" + value
               + ", comment=" + comment + " }";
    }
}
