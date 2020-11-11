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

import java.util.SortedSet;
import java.util.TreeSet;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 *
 * @author Bradley Willcott
 */
public class MutablePropertyTest {

    private static final String[] EXPECTED =
    {
        null, "Adam", "Fred", "Greg", "Julie", "Mary", "Peter", "Wayne"
    };

    private static final int KEY = 0;

    private static final String[][] PROPS =
    {
        {
            "Greg", "Doctors"
        },
        {
            "Mary", "Home"
        },
        {
            "Julie", "Holidays"
        },
        {
            "Wayne", "Driving"
        },
        {
            "Peter", "Work"
        },
        {
            "Adam", "Work"
        },
        {
            null, "I'm IT!"
        },
        {
            "Fred", "Home"
        }
    };

    private static final int VALUE = 1;

    /**
     * Test of compareTo method, of class MutableProperty.
     */
    @Test
    public void testCompareTo() {
        System.out.println("testCompareTo");
        final SortedSet<MutableProperty<String>> set = new TreeSet<>();
        final MembersState ms = new MembersState();
        String key = "test";
        String value = "data";
        String comment = "Hello there!";

        for (String[] prop : PROPS)
        {
            set.add(new MutableProperty<>(prop[KEY], prop[VALUE]));
        }

        assertEquals(EXPECTED.length, set.size());

        for (MutableProperty<String> mp : set)
        {
            assertTrue(() ->
            {
                if (EXPECTED[ms.index] == null)
                {
                    return mp.key == null;
                } else
                {
                    return EXPECTED[ms.index].equals(mp.key);
                }
            });

            ms.index++;
        }

        MutableProperty<String> mp = new MutableProperty<>(key, value);
        MutableProperty<String> mp1 = new MutableProperty<>(null, value);
        MutableProperty<String> mp2 = new MutableProperty<>(null, key, comment);
        MutableProperty<String> nullMP = null;

        assertThrows(NullPointerException.class, () ->
             {
                 mp.compareTo(nullMP);
             });

        assertDoesNotThrow(() ->
        {
            assertEquals(1, mp.compareTo(mp1));
            assertEquals(0, mp1.compareTo(mp2));
        });

    }

    /**
     * Test of equals method, of class MutableProperty.
     */
    @Test
    public void testEquals() {
        System.out.println("testEquals");
        String key = "test";
        String value = "data";
        String comment = "Hello there!";

        String nullString = null;
        MutableProperty<String> nullMP = null;
        Object nullObject = null;

        MutableProperty<String> mp = new MutableProperty<>(key, value);
        MutableProperty<String> mp1 = new MutableProperty<>(key, value, comment);
        MutableProperty<String> mp2 = new MutableProperty<>(value, key);
        MutableProperty<String> mp3 = new MutableProperty<>(null, key);
        MutableProperty<String> mp4 = new MutableProperty<>(null, key, comment);

        assertTrue(mp.equals(mp1));
        assertTrue(mp1.equals(mp));
        assertFalse(mp1.equals(mp2));

        assertTrue(mp.equals(key));
        assertFalse(mp.equals(value));
        assertFalse(mp.equals(Float.parseFloat("100.0F")));

        assertFalse(mp.equals(nullString));
        assertFalse(mp.equals(nullMP));
        assertFalse(mp.equals(nullObject));

        assertFalse(mp3.equals(mp));
        assertTrue(mp3.equals(mp4));
        assertFalse(mp3.equals(value));
        assertFalse(mp3.equals(nullMP));
        assertTrue(mp3.equals(nullString));
    }

    /**
     * Test of getReadOnly method, of class MutableProperty.
     */
    @Test
    public void testGetReadOnly() {
        System.out.println("testGetReadOnly");
        String key = "test";
        String value = "data";
        String comment = "Hello there!";

        MutableProperty<String> mp = new MutableProperty<>(key, value);
        MutableProperty<String> mp1 = new MutableProperty<>(key, value, comment);

        Property<String, String> p = mp.getReadOnly();
        Property<String, String> p1 = mp1.getReadOnly();

        assertTrue(key.equals(p.key));
        assertTrue(value.equals(p.value));
        assertFalse(comment.equals(p.comment));

        assertTrue(key.equals(p1.key));
        assertTrue(value.equals(p1.value));
        assertTrue(comment.equals(p1.comment));
    }

    /**
     * Test of hashCode method, of class MutableProperty.
     */
    @Test
    public void testHashCode() {
        System.out.println("testHashCode");
        String key = "test";
        String value = "data";
        String comment = "Hello there!";

        MutableProperty<String> mp = new MutableProperty<>(key, value);
        MutableProperty<String> mp1 = new MutableProperty<>(key, value, comment);
        MutableProperty<String> mp2 = new MutableProperty<>(value, key);

        assertEquals(mp.hashCode(), mp1.hashCode());
        assertNotEquals(mp.hashCode(), mp2.hashCode());
    }

    /**
     * Test of toString method, of class MutableProperty.
     */
    @Test
    public void testToString() {
        System.out.println("testToString");
        String key = "test";
        String value = "data";

        MutableProperty<String> mp = new MutableProperty<>(key, value);
        assertTrue(mp.toString().startsWith("MutableProperty"));
    }

    private class MembersState {

        int index = 0;
    }
}
