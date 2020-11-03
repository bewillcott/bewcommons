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
package com.bew.commons;

/**
 * Thrown to indicate that a method has been passed a parameter with an invalid
 * or inappropriate value.
 * <p>
 * <b>Changed:</b><br>
 * Version 1.1:<br>
 * Now extends {@code RuntimeException}, instead of {@code Exception}.
 *
 * @author Brad Willcott &lt;bw.opensource@yahoo.com&gt;
 * @since 1.0
 * @version 1.0.6
 */
public class InvalidParameterValueException extends RuntimeException {

    private static final long serialVersionUID = 947167888439966970L;

    /**
     * Constructs an instance of <code>InvalidParameterValueException</code>
     * without a detail message.
     */
    public InvalidParameterValueException() {
        super();
    }

    /**
     * Constructs an instance of <code>InvalidParameterValueException</code>
     * with the specified detail message.
     *
     * @param message The detail message.
     */
    public InvalidParameterValueException(String message) {
        super(message);
    }

    /**
     * Constructs a new exception with the specified cause and a detail message
     * of (cause==null ? null : cause.toString()) (which typically contains the
     * class and detail message of cause). This constructor is useful for
     * exceptions that are little more than wrappers for other throwables (for
     * example, PrivilegedActionException).
     *
     * @param cause The cause (which is saved for later retrieval by the
     *              Throwable.getCause() method). (A null value is permitted,
     *              and indicates that the cause is nonexistent or unknown.)
     */
    public InvalidParameterValueException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructs a new exception with the specified detail message and cause.
     * <p>
     * Note that the detail message associated with cause is not automatically
     * incorporated in this exception's detail message.
     *
     * @param message The detail message (which is saved for later retrieval by
     *                the Throwable.getMessage() method).
     * @param cause   The cause (which is saved for later retrieval by the
     *                Throwable.getCause() method). (A null value is permitted,
     *                and indicates that the cause is nonexistent or unknown.)
     */
    public InvalidParameterValueException(String message, Throwable cause) {
        super(message, cause);
    }
}
