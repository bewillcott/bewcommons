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
package com.bew.commons.reflect;

/**
 * TestCase class description.
 *
 * @author Bradley Willcott &lt;bw.opensource@yahoo.com&gt;
 */
public class TestCase {

    private String name = "";
    private int number = 0;
    private long lNumber = 0;
    private boolean toggle;

    /**
     * This constructor is private to prevent external instantiation
     * of this class.
     */
    private TestCase() {
    }

    private TestCase(String name, int number) {
        this.name = name;
        this.number = number;
    }

    private TestCase(String name, long lnumber) {
        this.name = name;
        this.lNumber = lnumber;
    }

    private TestCase(String name, boolean toggle) {
        this.name = name;
        this.toggle = toggle;
    }

    @Override
    public String toString() {
        return "TestCase{\n"
               + "\tname = " + name + "\n"
               + "\tnumber = " + number + ",\n"
               + "\tlNumber = " + lNumber + "\n"
               + "\ttoggle = " + toggle + "\n"
               + "}";
    }
}
