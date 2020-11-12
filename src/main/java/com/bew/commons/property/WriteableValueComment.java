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

/**
 * This interface provides only property setters, and adds the {@code comment} attribute.
 *
 * @param <K> Key type.
 * @param <V> Value type.
 *
 * @author <a href="mailto:bw.opensource@yahoo.com">Bradley Willcott</a>
 *
 * @since 1.0.20
 * @version 1.0.20
 */
public interface WriteableValueComment<K, V> extends WriteableValue<K, V> {

    /**
     * Set property {@code comment}.
     *
     * @param comment to be set.
     */
    public void comment(String comment);

}
