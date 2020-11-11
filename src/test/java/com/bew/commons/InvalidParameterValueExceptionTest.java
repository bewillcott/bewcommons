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

import org.junit.jupiter.api.Test;

/**
 *
 * @author <a href="mailto:bw.opensource@yahoo.com">Bradley Willcott</a>
 */
public class InvalidParameterValueExceptionTest {

    @Test
    public void testConstructors() {
        System.out.println("testConstructors");
        InvalidParameterValueException ex = new InvalidParameterValueException();
        ex = new InvalidParameterValueException("Hello");
        ex = new InvalidParameterValueException(ex);
        ex = new InvalidParameterValueException("Hello again", ex);
    }

}
