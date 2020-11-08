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
 * Property2 record description.
 *
 * @param <K> Object type for {@code key}.
 * @param <V> Object type for {@code value}.
 *
 * @author <a href="mailto:bw.opensource@yahoo.com">Bradley Willcott</a>
 *
 * @since 1.0.6
 * @version 1.0.6
 */
public record Property2<K, V>(K key, V value, String comment)
        implements Serializable {

    private static final long serialVersionUID = 1604618868046L;

    public Property2(K key, V value) {
        this(key, value, null);
    }

    public static void main(String[] args) {
        Property2<String, String> prop = new Property2<>("Fred", "Smith");
        System.out.println("prop: " + prop);
        prop = new Property2<>("Mary", "Smith", "Had a little lamby...");
        System.out.println("prop: " + prop);
    }
}
