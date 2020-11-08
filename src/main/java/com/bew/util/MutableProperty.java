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
import java.util.Objects;

/**
 *
 * This class contains an individual property, {key:value[comment]}.
 * <p>
 * Unlike the {@link Property} class, the {@code key} is a {@code String}, and
 * the {@code value} and {@code comment} members are mutable.
 * </p>
 * <p>
 * <b>Note:</b> It is acceptable for the {@code key} to contain a <u>Null</u>.
 * Though having more than one such {@code MutableProperty} would be
 * unadvisable.
 * </p>
 * <p>
 * <b>IMPORTANT:</b> {@link #compareTo(com.bew.util.MutableProperty) compareTo},
 * {@link #equals(java.lang.Object) equals} and {@link #hashCode} only use the
 * contents of the {@code key} member. So there is no testing for equality, etc.
 * on the contents of either the {@code value} or the {@code comment} members.
 *
 * @param <V> Object type for {@code value}.
 *
 * @author Bradley Willcott &lt;bw.opensource@yahoo.com&gt;
 *
 * @since 1.0
 * @version 1.0
 */
public class MutableProperty<V> implements Serializable, Comparable<MutableProperty<V>> {

    private static final long serialVersionUID = 3791415885940633406L;
    /**
     * Default is {@code null}.
     *
     * @since 1.0
     */
    public String comment = null;

    /**
     * The {@code key} can only be set through a constructor.
     *
     * @since 1.0
     */
    public final String key;

    /**
     * @since 1.0
     */
    public V value;

    /**
     *
     * @param key   Set key.
     * @param value Set initial value.
     *
     * @since 1.0
     */
    public MutableProperty(String key, V value) {
        this.key = key;
        this.value = value;
    }

    /**
     *
     * @param key     Set key.
     * @param value   Set initial value.
     * @param comment Set initial comment.
     *
     * @since 1.0
     */
    public MutableProperty(String key, V value, String comment) {
        this.key = key;
        this.value = value;
        this.comment = comment;
    }

    @Override
    public int compareTo(MutableProperty<V> other) throws NullPointerException {
        if (other == null)
        {
            throw new NullPointerException("Null parameter.");
        }

        if (this.key == null)
        {
            if (other.key == null)
            {
                return 0;
            } else
            {
                return -1;
            }
        } else
        {
            if (other.key == null)
            {
                return 1;
            } else
            {
                return this.key.compareTo(other.key);
            }
        }
    }

    @Override
    public boolean equals(Object other) {
        return false;
    }

    public boolean equals(String other) {
        if (this.key == null)
        {
            return other == null;
        } else if (other == null)
        {
            return false;
        } else
        {
            return this.key.equals(other);
        }
    }

    public boolean equals(MutableProperty<V> other) {
        if (other == null)
        {
            return false;
        } else if (this.key == null)
        {
            return other.key == null;
        } else
        {
            return this.key.equals(other.key);
        }

    }

    /**
     * Get a copy of this property that is NOT thread safe. Further, if either
     * the {@code value} or {@code comment} is changed, the readonly copy will
     * NOT reflect it and neither will it throw any exception.
     *
     * @return Read only copy.
     */
    public Property2<String, V> getReadOnly() {
        return new Property2<>(key, value, comment);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.key);
        return hash;
    }

    @Override
    public String toString() {
        return "MutableProperty { key=" + key + ", value=" + value
               + ", comment=" + comment + " }";
    }
}
