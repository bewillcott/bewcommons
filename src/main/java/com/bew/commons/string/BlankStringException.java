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
package com.bew.commons.string;

/**
 * Thrown to indicate that a {@linkplain  java.lang.String#isBlank() String.isBlank}.
 *
 * @author Bradley Willcott &lt;bw.opensource@yahoo.com&gt;
 * @since 1.0.6
 * @version 1.0.6
 */
public class BlankStringException extends RuntimeException {

    private static final long serialVersionUID = 5342099602738442090L;

    /**
     * Constructs an instance of <code>BlankStringException</code>
     * without a detail message.
     */
    public BlankStringException() {
        super();
    }

    /**
     * Constructs an instance of <code>BlankStringException</code>
     * with the specified detail message.
     *
     * @param msg The detail message.
     */
    public BlankStringException(String msg) {
        super(msg);
    }
}
