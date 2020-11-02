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

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.junit.jupiter.api.Test;

import static com.bew.commons.reflect.Reflect.getPrivateAttribute;
import static com.bew.commons.reflect.Reflect.getPrivateMethod;
import static com.bew.commons.reflect.Reflect.instantiatePrivateClass;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;

/**
 *
 * @author Bradley Willcott &lt;bw.opensource@yahoo.com&gt;
 */
public class ReflectTest {

    public ReflectTest() {
    }

    /**
     * Test of instantiatePrivateClass method, of class Reflect.
     */
    @Test
    public void testInstantiatePrivateClass() {
        System.out.println("* ReflectTest - testInstantiatePrivateClass()");

        System.out.println("Using TestCase class:");
        TestCase tc = instantiatePrivateClass(TestCase.class, "Brad", 23);
        assertNotNull(tc);
        System.out.println("Params: " + tc);

        tc = instantiatePrivateClass(TestCase.class, "Brad 2", 23L);
        assertNotNull(tc);
        System.out.println("Params: " + tc);

        tc = instantiatePrivateClass(TestCase.class, "Brad 3", true);
        assertNotNull(tc);
        System.out.println("Params: " + tc);

        System.out.println("Using Person class:");
        Person person = instantiatePrivateClass(Person.class, "Jane Doe");
        assertNotNull(person);
        System.out.println(person);
    }

    /**
     * Test of instantiatePrivateClass method, of class Reflect.
     * Asserting hat 'null' returned, as no constructor has
     * a float parameter.
     */
    @Test
    public void testInstantiatePrivateClass_Bad() {
        System.out.println("* ReflectTest - testInstantiatePrivateClass_Bad()");
        TestCase tc = instantiatePrivateClass(TestCase.class, "Not there", 0.23);
        assertNull(tc);
        System.out.println("Params: " + tc);
    }

    /**
     * Test of instantiatePrivateClass method, of class Reflect,
     * to access default constructor.
     */
    @Test
    public void testInstantiatePrivateClass_Default() {
        System.out.println("* ReflectTest - testInstantiatePrivateClass_Default()");

        System.out.println("Using TestCase class:");
        TestCase tc = instantiatePrivateClass(TestCase.class);
        assertNotNull(tc);
        System.out.println("Default: " + tc);

        System.out.println("Using Person class:");
        Person person = instantiatePrivateClass(Person.class);
        assertNotNull(person);
        System.out.println(person);

    }

    /**
     * Testing of testgetPrivateMethod method, of class Reflect.
     */
    @Test
    public void testgetPrivateMethod() {
        System.out.println("* ReflectTest - testgetPrivateMethod()");

        System.out.println("Using Person class:");

        try
        {
            Person person = instantiatePrivateClass(Person.class);
            assertNotNull(person);

            Method setName = getPrivateMethod(person, "setName", "text");
            assertNotNull(setName);

            Method getName = getPrivateMethod(person, "getName");
            assertNotNull(getName);

            setName.invoke(person, "Peter Adams");

            System.out.println("name = " + getName.invoke(person));
            System.out.println(person);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex)
        {
            fail(ex.getMessage());
        }
    }

    /**
     * Testing of getPrivateAttribute method, of class Reflect.
     */
    @Test
    public void testgetPrivateAttribute() {
        System.out.println("* ReflectTest - testgetPrivateAttribute()");

        System.out.println("Using Person class:");
        Person person = instantiatePrivateClass(Person.class);
        assertNotNull(person);
        Field name = getPrivateAttribute(person, "name");
        assertNotNull(name);

        try
        {
            name.set(person, "Fred Smith");
            System.out.println("name = " + name.get(person));
        } catch (IllegalArgumentException | IllegalAccessException ex)
        {
            fail(ex.getMessage());
        }

        System.out.println(person);
    }
}
