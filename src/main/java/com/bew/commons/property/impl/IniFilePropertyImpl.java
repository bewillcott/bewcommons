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

import com.bew.commons.property.IniFileProperty;

/**
 * Class description.
 *
 * @param <V> Value type.
 *
 * @author <a href="mailto:bw.opensource@yahoo.com">Bradley Willcott</a>
 *
 * @since 1.0.20
 * @version 1.0.20
 */
public class IniFilePropertyImpl<V> extends ImmutableIniFilePropertyImpl<V>
        implements IniFileProperty<V> {

    public IniFilePropertyImpl(String key, V value, String comment) {
        super(key, value, comment);
    }

    public IniFilePropertyImpl(String key, V value) {
        this(key, value, null);
    }

    @Override
    public void comment(String comment) {
        this.comment = comment;
    }

    @Override
    public void value(V value) {
        this.value = value;
    }
}
