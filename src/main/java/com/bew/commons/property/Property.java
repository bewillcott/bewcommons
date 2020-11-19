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

import java.io.Serializable;
import java.util.Objects;

/**
 * This is the parent class for all of the classes in this package.
 * <p>
 * This class is an immutable class that contains all the attributes/fields
 * and the methods to read them.
 * <dl>
 * <dt>The fields:</dt>
 * <dd>
 * <ul>
 * <li>K key: is {@code protected} and {@code final}.</li>
 * <li>V value: is {@code protected}</li>
 * <li>String comment: is {@code protected}</li>
 * </ul>
 * </dd>
 * </dl>
 * The {@code key} field is immutable, and therefore
 * can only be set by one of the constructors of this class.
 * <p>
 * It is possible for a sub-class to be a mutator class, having methods that
 * can modify either/or both of the fields: {@code value} and {@code comment}.
 *
 * @param <K> key type.
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
public class Property<K extends Comparable<K>, V> implements
        Serializable,
        Comparable<Property<K, V>> {

    /**
     * @serial serial
     */
    private static final long serialVersionUID = -3045318643922146363L;

    /**
     * The property's comment;
     */
    protected String comment;

    /**
     * the property's key.
     */
    protected final K key;

    /**
     * The property's value.
     */
    protected V value;

    /**
     * Create a new instance of {@code Property} as a copy of an existing instance
     * of {@code Property}, or one of its sub-classes.
     *
     * @param <T>      the type of the class being copied.
     * @param property The instance to copy.
     */
    public <T extends Property<K, V>> Property(T property) {
        this(property.key, property.value, property.comment);
    }

    /**
     * Create a new instance of {@code Property} with a {@code null} comment.
     *
     * @param key   The key.
     * @param value The value.
     */
    public Property(K key, V value) {
        this(key, value, null);
    }

    /**
     * Create a new instance of {@code Property}.
     *
     * @param key     The key.
     * @param value   The value.
     * @param comment The comment.
     */
    public Property(K key, V value, String comment) {
        this.key = key;
        this.value = value;
        this.comment = comment;
    }

    /**
     * Get the property: {@code comment}.
     *
     * @return {@code comment} contents.
     */
    public String comment() {
        return comment;
    }

    @Override
    public int compareTo(Property<K, V> other) {
        int rtn = 0;

        if (!equals(Objects.requireNonNull(other)))
        {
            if (this.getClass() != other.getClass())
            {
                throw new ClassCastException("Must be the same sub-class of Property<K, V>.");
            }

            if (this.key == null)
            {
                if (other.key != null)
                {
                    rtn = -1;
                }
            } else
            {
                if (other.key == null)
                {
                    rtn = 1;
                } else
                {
                    rtn = this.key.compareTo(other.key);
                }
            }
        }

        return rtn;
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj
               || obj != null
                  && obj instanceof Property<?, ?> other
                  && this.getClass() == other.getClass()
                  && this.value.getClass() == other.value.getClass()
                  && this.key.equals(other.key);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.key);
    }

    /**
     * Get the property: {@code key}.
     *
     * @return {@code key} contents.
     */
    public K key() {
        return key;
    }

    /**
     * Get the property: {@code value}.
     *
     * @return {@code value} contents.
     */
    public V value() {
        return value;
    }

}
