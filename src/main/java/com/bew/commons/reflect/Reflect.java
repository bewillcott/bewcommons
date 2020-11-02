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

import java.lang.reflect.*;
import java.util.Objects;

/**
 * Reflect class description:
 * <p>
 * The primary purpose of this class, is to assist in Unit Testing classes
 * with hidden members.</p>
 * <p>
 * By using reflection, testing can be done without
 * requiring the changing of the public interface of the class.
 * <p>
 * In addition, reflection allows the testing of the internal code to help
 * maintain required end results.
 * </p>
 *
 * @author Bradley Willcott &lt;bw.opensource@yahoo.com&gt;
 */
public final class Reflect {

    /**
     * Get access to a private attribute/field of a class instance.
     * <p>
     * Example:<br>
     * The follow classes are in separate files: {@code Person.java} and {@code Main.java}.<br>
     * <br><hr>
     * <pre><code>
     * public class Person {
     *     private String name;
     *
     *     private Person() {
     *         name = "not set";
     *     }
     *
     *     public String toString() {
     *         return "Person { name = " + name + " }";
     *     }
     * }
     *
     * public class Main {
     *     public static void main() {
     *         Person person = Reflect.instantiatePrivateClass(Person.class);
     *         Field name = Reflect.getPrivateAttribute(person, "name");
     *         name.set(person, "Fred Smith");
     *         System.out.println("name = " + name.get(person));
     *         System.out.println(person);
     *     }
     * }</code></pre>
     * <hr>
     * <pre><code>
     * name = Fred Smith
     * Person { name = Fred Smith }
     * </code></pre>
     *
     * @param instance The instantiated Class object.
     * @param name     The name of the class attribute to get.
     *
     * @return The Field object representing the required attribute, or <i>null</i> if error.
     */
    public static Field getPrivateAttribute(Object instance, String name) {
        Field field = null;

        // instance and name must NOT be null.
        if (instance == null || name == null || name.isBlank())
        {
            return null;
        }

        try
        {

            field = instance.getClass().getDeclaredField(name);
            field.setAccessible(true);
        } catch (NoSuchFieldException | SecurityException ex)
        {
            System.err.println(ex.getMessage());
        }

        return field;
    }

    /**
     * Get access to a private method of a class instance.
     * <p>
     * Example:<br>
     * The follow classes are in separate files: {@code Person.java} and {@code Main.java}.<br>
     * <br><hr>
     * <pre><code>
     * public class Person {
     *     private String name;
     *
     *     private Person() {
     *         name = "not set";
     *     }
     *
     *     private String getName() {
     *         return name;
     *     }
     *
     *     private void setName(String name) {
     *         this.name = name;
     *     }
     *
     *     public String toString() {
     *         return "Person { name = " + name + " }";
     *     }
     * }
     *
     * public class Main {
     *     public static void main() {
     *         Person person = Reflect.instantiatePrivateClass(Person.class);
     *         Method setName = Reflect.getPrivateMethod(person, "setName", "text");
     *         Method getName = Reflect.getPrivateMethod(person, "getName");
     *         setName.invoke(person, "Peter Adams");
     *         System.out.println("name = " + getName.invoke(person));
     *         System.out.println(person);
     *     }
     * }</code></pre>
     * <hr>
     * <pre><code>
     * name = Peter Adams
     * Person { name = Peter Adams }
     * </code></pre>
     *
     * @param instance   The instantiated Class object.
     * @param name       The name of the class attribute to get.
     * @param sampleArgs Sample arguments/parameters. Used only to obtain their Class<&lt;?&gt; types.
     *
     * @return The Method object representing the required method.
     */
    public static Method getPrivateMethod(Object instance, String name, Object... sampleArgs) {
        Class<?>[] types = null;
        Method method = null;
        int length = sampleArgs.length;

        // instance and name must NOT be null.
        Objects.requireNonNull(instance, "instance must not be null.");
        Objects.requireNonNull(name, "name must not be null.");

        if (instance == null || name == null || name.isBlank())
        {
            return null;
        }

        if (length > 0)
        {
            types = new Class<?>[length];

            for (int i = 0; i < length; i++)
            {
                types[i] = sampleArgs[i].getClass();
            }
        }

        try
        {
            method = instance.getClass().getDeclaredMethod(name, types);
            method.setAccessible(true);
        } catch (NoSuchMethodException | SecurityException ex)
        {
            System.err.println(ex.getMessage());
        }

        return method;
    }

    /**
     * Instantiate a class with a private parameterized constructor.
     * To access the default constructor, only provide the {@code clazz} parameter.
     * <p>
     * Example:<br>
     * The follow classes are in separate files: {@code Person.java} and {@code Main.java}.<br>
     * <br>
     * <pre><code>
     * public class Person {
     *     private String name;
     *
     *     private Person() {
     *         name = "not set";
     *     }
     *
     *     private Person(String name) {
     *         this.name = name;
     *     }
     *
     *     public String toString() {
     *         return "Person { name = " + name + " }";
     *     }
     * }
     *
     * public class Main {
     *     public static void main() {
     *         Person person = instantiatePrivateClass(Person.class);
     *         System.out.println(person);
     *
     *         person = instantiatePrivateClass(Person.class, "Jane Doe");
     *         System.out.println(person);
     *     }
     * }</code></pre>
     * <hr>
     * <pre><code>
     * Person { name = not set }
     * Person { name = Jane Doe }
     * </code></pre>
     *
     * @param <T>   The class type.
     * @param clazz The class.
     * @param args  The parameters to be passed to the constructor.
     *
     *
     * @return The newly instantiated object, or <i>null</i> if error.
     */
    public static <T> T instantiatePrivateClass(Class<T> clazz, Object... args) {
        Class<?>[] types = null;
        T rtn = null;
        int length = args.length;

        // clazz must NOT be null.
        if (clazz == null)
        {
            return null;
        }

        // Setup the types array.
        if (length > 0)
        {
            types = new Class<?>[length];

            for (int i = 0; i < length; i++)
            {
                types[i] = args[i].getClass();
            }
        }

        try
        {
            // Get the array of Constructors.
            Constructor<?>[] cons = clazz.getDeclaredConstructors();

            // Check through all constructors to find what we need.
            for (Constructor<?> con : cons)
            {
                // Only looking for 'private' constructors.
                if (Modifier.isPrivate(con.getModifiers()))
                {
                    // Must have same number of parameters as 'args'.
                    if (con.getParameterCount() == length)
                    {
                        // If no parameters, then found it.
                        if (length == 0)
                        {
                            con.setAccessible(true);
                            rtn = (T) con.newInstance(args);
                            break;
                        } else // Make sure it has the right signature.
                        {
                            Class<?>[] params = con.getParameterTypes();
                            int found = 0;

                            // Cycle through the parameters.
                            for (int i = 0; i < length; i++)
                            {
                                // If match on class type...
                                if (params[i] == types[i])
                                {
                                    found++;
                                } else if (params[i].isPrimitive())
                                {
                                    if (Primitives.isWrapperFor(params[i].getName(), types[i].getName()))
                                    {
                                        found++;
                                    }
                                }
                            }

                            if (found == length)
                            {
                                con.setAccessible(true);
                                rtn = (T) con.newInstance(args);
                                break;
                            }
                        }
                    }
                }
            }

        } catch (IllegalAccessException | IllegalArgumentException
                 | InstantiationException | SecurityException | InvocationTargetException ex)
        {
            // TODO Add handler code.
            System.err.println(ex.getMessage());
        }

        return rtn;
    }

    /**
     * This constructor is private to prevent external instantiation
     * of this class.
     */
    private Reflect() {
    }

    /**
     * Used for matching primitives and their wrapper classes.
     */
    private static class Primitives {

        /**
         * Not to be instantiated.
         */
        private Primitives() {
        }

        /**
         * The list used in the comparison.
         */
        private static final String[][] THE_LIST =
        {
            {
                "boolean", "java.lang.Boolean"
            },
            {
                "byte", "java.lang.Byte"
            },
            {
                "char", "java.lang.Character"
            },
            {
                "double", "java.lang.Double"
            },
            {
                "float", "java.lang.Float"
            },
            {
                "long", "java.lang.Long"
            },
            {
                "int", "java.lang.Integer"
            },
            {
                "short", "java.lang.Short"
            }
        };

        /**
         * Determines whether or not the named class is the wrapper class
         * for the named primitive type.
         *
         * @param primitive Name of primitive.
         * @param className Name of class.
         *
         * @return <i>true</i> if it is, <i>false</i> otherwise.
         */
        public static boolean isWrapperFor(String primitive, String className) {

            // Linear search for primitive in the list.
            for (String[] entry : THE_LIST)
            {
                // Have we a match?
                if (entry[0].equals(primitive))
                {
                    // Is it the wrapper class?
                    if (entry[1].equals(className))
                    {
                        return true;
                    }
                }
            }

            return false;
        }
    }
}
