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

/**
 * Provides a way of protecting the members from accidental modification.
 *
 * @param <V> Type for {@code value}.
 *
 * @author <a href="mailto:bw.opensource@yahoo.com">Bradley Willcott</a>
 *
 * @since 1.0
 * @version 1.0
 *
 * @see MutableProperty
 *
 * @deprecated To be removed before next commit.
 */
@Deprecated
public interface IImmutableProperty<V> {

    /**
     * Get property {@code key}.
     *
     * @return {@code key} contents.
     */
    public String getKey();

    /**
     * Get the property {@code value}.
     *
     * @return {@code value} contents.
     */
    public V getValue();

    /**
     * Get property {@code comment}.
     *
     * @return {@code comment} contents.
     */
    public String getComment();
}
