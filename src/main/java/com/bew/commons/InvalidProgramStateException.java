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
 * This exception might be thrown to indicate that there was a class level
 * internal state that was incompatible with the requested methods operation.
 *
 * @author Brad Willcott
 * @since 1.0
 */
public class InvalidProgramStateException extends Exception {

    private static final long serialVersionUID = 5899909813938071202L;

    /**
     * Constructs an InvalidParameterValueException with no detail message.
     */
    public InvalidProgramStateException() {
        super();
    }

    /**
     * Constructs an InvalidParameterValueException with the specified detail
     * message.
     *
     * @param message the detail message.
     */
    public InvalidProgramStateException(String message) {
        super(message);
    }

    /**
     * Constructs a new exception with the specified cause and a detail message
     * of (cause==null ? null : cause.toString()) (which typically contains the
     * class and detail message of cause). This constructor is useful for
     * exceptions that are little more than wrappers for other throwables (for
     * example, PrivilegedActionException).
     *
     * @param cause the cause (which is saved for later retrieval by the
     *              Throwable.getCause() method). (A null value is permitted,
     *              and indicates that the cause is nonexistent or unknown.)
     */
    public InvalidProgramStateException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructs a new exception with the specified detail message and cause.
     *
     * Note that the detail message associated with cause is not automatically
     * incorporated in this exception's detail message.
     *
     * @param message the detail message (which is saved for later retrieval by
     *                the Throwable.getMessage() method).
     * @param cause   the cause (which is saved for later retrieval by the
     *                Throwable.getCause() method). (A null value is permitted,
     *                and indicates that the cause is nonexistent or unknown.)
     */
    public InvalidProgramStateException(String message, Throwable cause) {
        super(message, cause);
    }
}
