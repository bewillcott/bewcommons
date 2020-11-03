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

import java.util.Objects;

/**
 * Strings class description.
 *
 * @author Bradley Willcott &lt;bw.opensource@yahoo.com&gt;
 * @since 1.0.6
 * @version 1.0.6
 */
public class Strings {

    /**
     * This class is not meant to be instantiated.
     */
    private Strings() {
    }

    /**
     * Checks that the specified string isn't <i>blank</i>.<p>
     * That is, the string isn't empty or contain only white space code-points.
     *
     * @param str The string to check for blankness.
     *
     * @return {@code str} if not <i>blank</i>.
     *
     * @throws NullPointerException if {@code str} is <i>null</i>.
     * @throws BlankStringException if {@code str} is <i>blank</i>.
     */
    public static String requireNonBlank(String str) {
        if (Objects.requireNonNull(str).isBlank())
        {
            throw new BlankStringException();
        }

        return str;
    }

    /**
     * Checks that the specified string isn't <i>blank</i>.<p>
     * That is, the string isn't empty or contain only white space code-points.
     *
     * @param str     The string to check for blankness.
     * @param message Detail message to be used in the event that a
     *                {@code BlankStringException} is thrown.
     *
     * @return {@code str} if not <i>blank</i>.
     *
     * @throws NullPointerException if {@code str} is <i>null</i>.
     * @throws BlankStringException if {@code str} is <i>blank</i>.
     */
    public static String requireNonBlank(String str, String message) {
        if (Objects.requireNonNull(str, message).isBlank())
        {
            throw new BlankStringException(message);
        }

        return str;
    }

    /**
     * Checks that the specified string isn't <i>empty</i>.<p>
     * That is, the string's {@code length} is greater than '{@code 0}'.
     *
     * @param str The string to check for emptiness.
     *
     * @return {@code str} if not <i>empty</i>.
     *
     * @throws NullPointerException if {@code str} is <i>null</i>.
     * @throws EmptyStringException if {@code str} is <i>empty</i>.
     */
    public static String requireNonEmpty(String str) {
        if (Objects.requireNonNull(str).isEmpty())
        {
            throw new EmptyStringException();
        }

        return str;
    }

    /**
     * Checks that the specified string isn't <i>empty</i>.<p>
     * That is, the string's {@code length} is '{@code 0}'.
     *
     * @param str     The string to check for emptiness.
     * @param message Detail message to be used in the event that an
     *                {@code EmptyStringException} is thrown.
     *
     * @return {@code str} if not <i>empty</i>.
     *
     * @throws NullPointerException if {@code str} is <i>null</i>.
     * @throws EmptyStringException if {@code str} is <i>empty</i>.
     */
    public static String requireNonEmpty(String str, String message) {
        if (Objects.requireNonNull(str, message).isEmpty())
        {
            throw new EmptyStringException(message);
        }

        return str;
    }
}
