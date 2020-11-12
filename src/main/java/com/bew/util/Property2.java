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
 * @author <a href="mailto:bw.opensource@yahoo.com">Bradley Willcott</a>
 *
 * @since 1.0
 * @version 1.0
 *
 * @deprecated To be removed before next commit.
 */
@Deprecated
public class Property2<V> implements IMutableProperty<V>,
        Serializable, Comparable<Property2<V>> {

    private static final long serialVersionUID = 3791415885940633406L;
    /**
     * Default is {@code null}.
     *
     * @since 1.0
     */
    private String comment = null;

    /**
     * The {@code key} can only be set through a constructor.
     *
     * @since 1.0
     */
    private final String key;

    /**
     * @since 1.0
     */
    private V value;

    /**
     *
     * @param key   Set key.
     * @param value Set initial value.
     *
     * @since 1.0
     */
    public Property2(String key, V value) {
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
    public Property2(String key, V value, String comment) {
        this.key = key;
        this.value = value;
        this.comment = comment;
    }

    @Override
    public int compareTo(Property2<V> other) throws NullPointerException {
        int rtn = 0;

        if (!equals(Objects.requireNonNull(other)))
        {
            if (this.key == null)
            {
                if (other.key != null)
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

        return rtn;
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj
               || obj == null
                  && this.key == null
               || obj != null
                  && obj instanceof String strValue
                  && Objects.equals(this.key, strValue)
               || obj != null
                  && obj instanceof Property2<?> other
                  && checkClasses(this.value, other.value)
                  && Objects.equals(this.key, other.key);
    }

    @Override
    public String getComment() {
        return comment;
    }

    @Override
    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String getKey() {
        return key;
    }

    /**
     * Get a copy of this property that is NOT thread safe. Further, if either
     * the {@code value} or {@code comment} is changed, the read-only copy will
     * NOT reflect it and neither will it throw any exception.
     *
     * @return Read only copy.
     */
    public Property<String, V> getReadOnly() {
        return new Property<>(key, value, comment);
    }

    @Override
    public V getValue() {
        return value;
    }

    @Override
    public void setValue(V value) {
        this.value = value;
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

    /**
     * Called by {@link #equals(java.lang.Object) equals(Object obj)} to check
     * the runtime class of two objects.
     * <p>
     * This is a work-around the type erasure that happens at runtime, and also
     * to allow primitives be compared through auto-boxing.
     *
     * @param aObject Object a.
     * @param bObject Object b.
     *
     * @return {@code true} if they are of the same class type, {@code false} otherwise.
     */
    private boolean checkClasses(Object aObject, Object bObject) {
        return aObject.getClass() == bObject.getClass();
    }
}
