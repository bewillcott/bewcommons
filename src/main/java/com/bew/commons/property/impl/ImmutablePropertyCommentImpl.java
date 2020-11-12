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
package com.bew.commons.property.impl;

import com.bew.commons.property.ImmutablePropertyComment;

/**
 * Class description.
 *
 * @param <K> Key type.
 * @param <V> Value type.
 *
 * @author <a href="mailto:bw.opensource@yahoo.com">Bradley Willcott</a>
 *
 * @since 1.0.20
 * @version 1.0.20
 */
public class ImmutablePropertyCommentImpl<K, V> extends ImmutablePropertyImpl<K, V>
        implements ImmutablePropertyComment<K, V> {

    public ImmutablePropertyCommentImpl(K key, V value, String comment) {
        super(key, value);
        this.comment = comment;
    }

    @Override
    public String comment() {
        return comment;
    }
}
