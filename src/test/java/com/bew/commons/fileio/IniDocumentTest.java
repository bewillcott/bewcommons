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
package com.bew.commons.fileio;

import com.bew.commons.InvalidParameterValueException;
import com.bew.commons.property.IniProperty;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 *
 * @author Bradley Willcott
 */
public class IniDocumentTest {

    private static final String[][] INPUT =
    {
        {
            "others", "Home", "Newtown"
        },
        {
            "Employees", "001", "Fred Smith"
        },
        {
            "Fred Smith", "Address", "10 Anders Ave"
        },
        {
            "Fred Smith", "Suburb", "Jinville"
        },
        {
            "Fred Smith", "State", "SA"
        },
        {
            "Fred Smith", "Phone", "0412-345-395"
        },
        {
            "Fred Smith", "Comments", "Good worker"
        },
        {
            "Employees", "002", "Peter Davis"
        },
        {
            "Peter Davis", "Address", "12A Anders Way"
        },
        {
            "Peter Davis", "Suburb", "Shineytown"
        },
        {
            "Peter Davis", "State", "Vic"
        },
        {
            "Peter Davis", "Phone", "0428-859-271"
        },
        {
            "Peter Davis", "Comments", "Avg. worker"
        }
    };
    private static IniDocument INSTANCE;
    private static final int KEY = 1;
    private static final String NOVALUE_KEY = "NoValue";
    private static final int PROPERTY = 7;
    private static final int SECTION = 0;
    private static final String TEST_COMMENT = "# This is a valid comment.";
    private static final int TEST_INDEX = 1;
    private static final String TEST_KEY = INPUT[PROPERTY][KEY];
    private static final String TEST_SECTION = INPUT[PROPERTY][SECTION];
    private static final int TEST_SIZE = 2;
    private static final int VALUE = 2;
    private static final String TEST_VALUE = INPUT[PROPERTY][VALUE];

//    @AfterAll
//    public static void cleanUp() {
//        System.out.println("After All cleanUp() method called");
//        StringBuilder sb = new StringBuilder();
//        sb.append(INSTANCE.toString().replace("{", "\n{\n    ")
//                .replace("}", "\n}").replace("=\n", "\n    ==========\n")
//                .replace("}, ", "}\n    ").replace(", ", "\n    "));
//        sb.append("\n}");
//        System.out.println(sb.toString());
//    }
    @BeforeAll
    public static void init() {
        System.out.println("BeforeAll init() method called");
        INSTANCE = new IniDocument();
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    /**
     * Testing 'boolean' methods, of class IniDocument.
     */
    @Test
    public void testBoolean() {
        final MembersState sm = new MembersState();
        System.out.println("testBoolean");
        String section = "testBoolean";
        sm.key = "test";
        boolean value = true;
        boolean defaultvalue = false;
        assertDoesNotThrow(() ->
        {
            INSTANCE.setBoolean(section, sm.key, value);
            INSTANCE.setBoolean(section, sm.key, value);
        });
        assertThrows(NullPointerException.class, () ->
             {
                 INSTANCE.setBoolean(section, null, value);
             });

        boolean result = INSTANCE.getBoolean(section, sm.key, defaultvalue);
        assertEquals(value, result);

        sm.key = "test2";
        assertDoesNotThrow(() ->
        {
            INSTANCE.setBoolean(section, sm.key, value, TEST_COMMENT);
            INSTANCE.setBoolean(section, sm.key, value, TEST_COMMENT);
        });
        String result2 = INSTANCE.getComment(section, sm.key);
        assertEquals(TEST_COMMENT, result2);
        assertEquals(defaultvalue, INSTANCE.getBoolean(NOVALUE_KEY, sm.key, defaultvalue));

        sm.key = NOVALUE_KEY;
        assertEquals(defaultvalue, INSTANCE.getBoolean(section, sm.key, defaultvalue));
        assertDoesNotThrow(() ->
        {
            INSTANCE.setComment(section, sm.key, TEST_COMMENT);
        });
        assertEquals(defaultvalue, INSTANCE.getBoolean(section, sm.key, defaultvalue));

        sm.key = "testBooleanG";
        assertDoesNotThrow(() ->
        {
            INSTANCE.setBooleanG(sm.key, value);
            INSTANCE.setBooleanG(sm.key, value);
        });
        result = INSTANCE.getBooleanG(sm.key, defaultvalue);
        assertEquals(value, result);

        sm.key = "testBooleanG2";
        assertDoesNotThrow(() ->
        {
            INSTANCE.setBooleanG(sm.key, value, TEST_COMMENT);
            INSTANCE.setBooleanG(sm.key, value, TEST_COMMENT);
        });
        result2 = INSTANCE.getCommentG(sm.key);
        assertEquals(TEST_COMMENT, result2);

        assertThrows(InvalidParameterValueException.class, () ->
             {
                 INSTANCE.setBooleanG(sm.key, value, "This is a bad comment.");
             });

    }

    /**
     * Testing 'Comment' methods, of class IniDocument.
     */
    @Test
    public void testComment() {
        final MembersState sm = new MembersState();
        System.out.println("testComment");
        String section = "testComment";
        sm.key = "test";
        assertDoesNotThrow(() ->
        {
            INSTANCE.setComment(section, sm.key, TEST_COMMENT);
            INSTANCE.setComment(section, sm.key, TEST_COMMENT);
        });
        String result = INSTANCE.getComment(section, sm.key);
        assertEquals(TEST_COMMENT, result);

        assertThrows(NullPointerException.class, () ->
             {
                 INSTANCE.setComment(section, null, TEST_COMMENT);
             });

        sm.key = "testCommentG";
        assertDoesNotThrow(() ->
        {
            INSTANCE.setCommentG(sm.key, TEST_COMMENT);
        });
        result = INSTANCE.getCommentG(sm.key);
        assertEquals(TEST_COMMENT, result);

        sm.key = "testCommentG2";
        assertThrows(InvalidParameterValueException.class, () ->
             {
                 INSTANCE.setCommentG(sm.key, "");
             });

        assertDoesNotThrow(() ->
        {
            INSTANCE.setCommentG(sm.key, null);
        });

        assertNull(INSTANCE.getComment("NoOp", "NoHope"));
        assertNull(INSTANCE.getCommentG("NoHope"));
    }

    /**
     * Testing containsKey[G] methods, of class IniDocument.
     */
    @Test
    public void testContainsKey() {
        System.out.println("testContainsKey");
        String section = "testContainsKey";
        String key = "test";
        String value = "maybe";
        assertDoesNotThrow(() ->
        {
            INSTANCE.setString(section, key, value);
        });
        assertTrue(INSTANCE.containsKey(section, key));
        assertFalse(INSTANCE.containsKey(section, "badKey"));
        assertFalse(INSTANCE.containsKey("badSection", key));

        assertDoesNotThrow(() ->
        {
            INSTANCE.setStringG(key, value);
        });
        assertTrue(INSTANCE.containsKeyG(key));
        assertFalse(INSTANCE.containsKeyG("badKey"));
    }

    /**
     * Test of containsSection method, of class IniDocument.
     */
    @Test
    public void testContainsSection() {
        System.out.println("testContainsSection");
        String section = "testContainsSection";
        assertDoesNotThrow(() ->
        {
            INSTANCE.setSection(section);
        });
        assertTrue(INSTANCE.containsSection(section));
        assertFalse(INSTANCE.containsSection("badSection"));
    }

    /**
     * Testing 'double' methods, of class IniDocument.
     */
    @Test
    public void testDouble() {
        final MembersState sm = new MembersState();
        System.out.println("testDouble");
        String section = "testDouble";
        sm.key = "test";
        double value = 100.0;
        double defaultvalue = 0.0;
        assertDoesNotThrow(() ->
        {
            INSTANCE.setDouble(section, sm.key, value);
            INSTANCE.setDouble(section, sm.key, value);
        });
        double result = INSTANCE.getDouble(section, sm.key, defaultvalue);
        assertEquals(value, result);

        sm.key = "test2";
        assertDoesNotThrow(() ->
        {
            INSTANCE.setDouble(section, sm.key, value, TEST_COMMENT);
            INSTANCE.setDouble(section, sm.key, value, TEST_COMMENT);
        });
        String result2 = INSTANCE.getComment(section, sm.key);
        assertEquals(TEST_COMMENT, result2);

        sm.key = NOVALUE_KEY;
        assertDoesNotThrow(() ->
        {
            INSTANCE.setComment(section, sm.key, TEST_COMMENT);
        });
        result = INSTANCE.getDouble(section, sm.key, defaultvalue);
        assertEquals(defaultvalue, result);

        sm.key = "testDoubleG";
        assertDoesNotThrow(() ->
        {
            INSTANCE.setDoubleG(sm.key, value);
            INSTANCE.setDoubleG(sm.key, value);
        });
        result = INSTANCE.getDoubleG(sm.key, defaultvalue);
        assertEquals(value, result);

        sm.key = "testDoubleG2";
        assertDoesNotThrow(() ->
        {
            INSTANCE.setDoubleG(sm.key, value, TEST_COMMENT);
            INSTANCE.setDoubleG(sm.key, value, TEST_COMMENT);
        });
        result2 = INSTANCE.getCommentG(sm.key);
        assertEquals(TEST_COMMENT, result2);
    }

    /**
     * Testing 'float' methods, of class IniDocument.
     */
    @Test
    public void testFloat() {
        final MembersState sm = new MembersState();
        System.out.println("testFloat");
        String section = "testFloat";
        sm.key = "test";
        float value = 100.0F;
        float defaultvalue = 0.0F;
        assertDoesNotThrow(() ->
        {
            INSTANCE.setFloat(section, sm.key, value);
            INSTANCE.setFloat(section, sm.key, value);
        });
        float result = INSTANCE.getFloat(section, sm.key, defaultvalue);
        assertEquals(value, result);

        sm.key = "test2";
        assertDoesNotThrow(() ->
        {
            INSTANCE.setFloat(section, sm.key, value, TEST_COMMENT);
            INSTANCE.setFloat(section, sm.key, value, TEST_COMMENT);
        });
        String result2 = INSTANCE.getComment(section, sm.key);
        assertEquals(TEST_COMMENT, result2);

        sm.key = NOVALUE_KEY;
        assertDoesNotThrow(() ->
        {
            INSTANCE.setComment(section, sm.key, TEST_COMMENT);
        });
        result = INSTANCE.getFloat(section, sm.key, defaultvalue);
        assertEquals(defaultvalue, result);

        sm.key = "testFloatG";
        assertDoesNotThrow(() ->
        {
            INSTANCE.setFloatG(sm.key, value);
            INSTANCE.setFloatG(sm.key, value);
        });
        result = INSTANCE.getFloatG(sm.key, defaultvalue);
        assertEquals(value, result);

        sm.key = "testFloatG2";
        assertDoesNotThrow(() ->
        {
            INSTANCE.setFloatG(sm.key, value, TEST_COMMENT);
            INSTANCE.setFloatG(sm.key, value, TEST_COMMENT);
        });
        result2 = INSTANCE.getCommentG(sm.key);
        assertEquals(TEST_COMMENT, result2);
    }

    /**
     * Testing 'int' methods, of class IniDocument.
     */
    @Test
    public void testInt() {
        final MembersState sm = new MembersState();
        System.out.println("testInt");
        String section = "testInt";
        sm.key = "test";
        int value = 100;
        int defaultvalue = 0;
        assertDoesNotThrow(() ->
        {
            INSTANCE.setInt(section, sm.key, value);
            INSTANCE.setInt(section, sm.key, value);
        });
        int result = INSTANCE.getInt(section, sm.key, defaultvalue);
        assertEquals(value, result);

        sm.key = "test2";
        assertDoesNotThrow(() ->
        {
            INSTANCE.setInt(section, sm.key, value, TEST_COMMENT);
            INSTANCE.setInt(section, sm.key, value, TEST_COMMENT);
        });
        String result2 = INSTANCE.getComment(section, sm.key);
        assertEquals(TEST_COMMENT, result2);

        sm.key = NOVALUE_KEY;
        assertDoesNotThrow(() ->
        {
            INSTANCE.setComment(section, sm.key, TEST_COMMENT);
        });
        result = INSTANCE.getInt(section, sm.key, defaultvalue);
        assertEquals(defaultvalue, result);

        sm.key = "testIntG";
        assertDoesNotThrow(() ->
        {
            INSTANCE.setIntG(sm.key, value);
            INSTANCE.setIntG(sm.key, value);
        });
        result = INSTANCE.getIntG(sm.key, defaultvalue);
        assertEquals(value, result);

        sm.key = "testIntG2";
        assertDoesNotThrow(() ->
        {
            INSTANCE.setIntG(sm.key, value, TEST_COMMENT);
            INSTANCE.setIntG(sm.key, value, TEST_COMMENT);
        });
        result2 = INSTANCE.getCommentG(sm.key);
        assertEquals(TEST_COMMENT, result2);
    }

    /**
     * Testing 'long' methods, of class IniDocument.
     */
    @Test
    public void testLong() {
        final MembersState sm = new MembersState();
        System.out.println("testLong");
        String section = "testLong";
        sm.key = "test";
        long value = 100;
        long defaultvalue = 0;
        assertDoesNotThrow(() ->
        {
            INSTANCE.setLong(section, sm.key, value);
            INSTANCE.setLong(section, sm.key, value);
        });
        long result = INSTANCE.getLong(section, sm.key, defaultvalue);
        assertEquals(value, result);

        sm.key = "test2";
        assertDoesNotThrow(() ->
        {
            INSTANCE.setLong(section, sm.key, value, TEST_COMMENT);
            INSTANCE.setLong(section, sm.key, value, TEST_COMMENT);
        });
        String result2 = INSTANCE.getComment(section, sm.key);
        assertEquals(TEST_COMMENT, result2);

        sm.key = NOVALUE_KEY;
        assertDoesNotThrow(() ->
        {
            INSTANCE.setComment(section, sm.key, TEST_COMMENT);
        });
        result = INSTANCE.getLong(section, sm.key, defaultvalue);
        assertEquals(defaultvalue, result);

        sm.key = "testLongG";
        assertDoesNotThrow(() ->
        {
            INSTANCE.setLongG(sm.key, value);
            INSTANCE.setLongG(sm.key, value);
        });
        result = INSTANCE.getLongG(sm.key, defaultvalue);
        assertEquals(value, result);

        sm.key = "testLongG2";
        assertDoesNotThrow(() ->
        {
            INSTANCE.setLongG(sm.key, value, TEST_COMMENT);
            INSTANCE.setLongG(sm.key, value, TEST_COMMENT);
        });
        result2 = INSTANCE.getCommentG(sm.key);
        assertEquals(TEST_COMMENT, result2);
    }

    /**
     * Testing 'Section' methods, of class IniDocument.
     */
    @Test
    public void testSection() {
        System.out.println("testSection");
        String section = "testSection";
        assertDoesNotThrow(() ->
        {
            INSTANCE.setSection(section);
            INSTANCE.setSection(section);
        });
        assertTrue(INSTANCE.containsSection(section));

        for (String[] inparr : INPUT)
        {
            assertDoesNotThrow(() ->
            {
                INSTANCE.setString(inparr[SECTION], inparr[KEY], inparr[VALUE]);
            });
        }
        List<IniProperty<Object>> result = INSTANCE.getSection(TEST_SECTION);
        assertNotNull(result);
        assertTrue(result.size() == TEST_SIZE);
        assertTrue(result.get(TEST_INDEX).key().equals(TEST_KEY)
                   && result.get(TEST_INDEX).value().equals(TEST_VALUE));

        assertDoesNotThrow(() ->
        {
            INSTANCE.setSection(section, TEST_COMMENT);
        });
        assertTrue(TEST_COMMENT.equals(INSTANCE.getSectionComment(section)));

        List<String> result2 = INSTANCE.getSections();
        assertNotNull(result2);
        assertTrue(result2.size() >= 5);
        assertTrue(result2.contains(section));

        assertNull(INSTANCE.getSection(NOVALUE_KEY));
        assertNull(INSTANCE.getSectionComment(NOVALUE_KEY));
    }

    /**
     * Testing 'String' methods, of class IniDocument.
     */
    @Test
    public void testString() {
        final MembersState ms = new MembersState();

        System.out.println("testString");
        String section = "testString";
        ms.key = "test";
        String value = "A string";
        String defaultvalue = "Not there!";
        assertDoesNotThrow(() ->
        {
            INSTANCE.setString(section, ms.key, value);
            INSTANCE.setString(section, ms.key, value);
        });
        String result = INSTANCE.getString(section, ms.key, defaultvalue);
        assertEquals(value, result);

        ms.key = "test2";
        assertDoesNotThrow(() ->
        {
            INSTANCE.setString(section, ms.key, value, TEST_COMMENT);
            INSTANCE.setString(section, ms.key, value, TEST_COMMENT);
        });
        String result2 = INSTANCE.getComment(section, ms.key);
        assertEquals(TEST_COMMENT, result2);

        ms.key = NOVALUE_KEY;
        assertDoesNotThrow(() ->
        {
            INSTANCE.setComment(section, ms.key, TEST_COMMENT);
        });
        result = INSTANCE.getString(section, ms.key, defaultvalue);
        assertEquals(defaultvalue, result);

        ms.key = "testStringG";
        assertDoesNotThrow(() ->
        {
            INSTANCE.setStringG(ms.key, value);
            INSTANCE.setStringG(ms.key, value);
        });
        result = INSTANCE.getStringG(ms.key, defaultvalue);
        assertEquals(value, result);

        ms.key = "testStringG2";
        assertDoesNotThrow(() ->
        {
            INSTANCE.setStringG(ms.key, value, TEST_COMMENT);
            INSTANCE.setStringG(ms.key, value, TEST_COMMENT);
        });
        result2 = INSTANCE.getCommentG(ms.key);
        assertEquals(TEST_COMMENT, result2);
    }

    /**
     * Test of toString method, of class IniDocument.
     */
    @Test
    public void testToString() {
        System.out.println("testToString");
        String result = INSTANCE.toString();
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertTrue(result.startsWith(IniDocument.class.getName()));
    }

    /**
     * Testing validateComment method, of class IniDocument.
     */
    @Test
    public void testValidateComment() {
        System.out.println("testValidateComment");
        assertTrue(IniDocument.validateComment(TEST_COMMENT));

        String comment = "This is not a valid comment.";
        assertFalse(IniDocument.validateComment(comment));
    }

    private class MembersState {

        String key = null;
    }

    /**
     * Test of containsKeyG method, of class IniDocument.
     */
    @org.junit.Test
    public void testContainsKeyG() {
    }

    /**
     * Test of getBoolean method, of class IniDocument.
     */
    @org.junit.Test
    public void testGetBoolean() {
    }

    /**
     * Test of getBooleanG method, of class IniDocument.
     */
    @org.junit.Test
    public void testGetBooleanG() {
    }

    /**
     * Test of getComment method, of class IniDocument.
     */
    @org.junit.Test
    public void testGetComment() {
    }

    /**
     * Test of getCommentG method, of class IniDocument.
     */
    @org.junit.Test
    public void testGetCommentG() {
    }

    /**
     * Test of getDouble method, of class IniDocument.
     */
    @org.junit.Test
    public void testGetDouble() {
    }

    /**
     * Test of getDoubleG method, of class IniDocument.
     */
    @org.junit.Test
    public void testGetDoubleG() {
    }

    /**
     * Test of getFloat method, of class IniDocument.
     */
    @org.junit.Test
    public void testGetFloat() {
    }

    /**
     * Test of getFloatG method, of class IniDocument.
     */
    @org.junit.Test
    public void testGetFloatG() {
    }

    /**
     * Test of getInt method, of class IniDocument.
     */
    @org.junit.Test
    public void testGetInt() {
    }

    /**
     * Test of getIntG method, of class IniDocument.
     */
    @org.junit.Test
    public void testGetIntG() {
    }

    /**
     * Test of getLong method, of class IniDocument.
     */
    @org.junit.Test
    public void testGetLong() {
    }

    /**
     * Test of getLongG method, of class IniDocument.
     */
    @org.junit.Test
    public void testGetLongG() {
    }

    /**
     * Test of getSection method, of class IniDocument.
     */
    @org.junit.Test
    public void testGetSection() {
    }

    /**
     * Test of setSection method, of class IniDocument.
     */
    @org.junit.Test
    public void testSetSection_String() {
    }

    /**
     * Test of getSectionComment method, of class IniDocument.
     */
    @org.junit.Test
    public void testGetSectionComment() {
    }

    /**
     * Test of getSections method, of class IniDocument.
     */
    @org.junit.Test
    public void testGetSections() {
    }

    /**
     * Test of getString method, of class IniDocument.
     */
    @org.junit.Test
    public void testGetString() {
    }

    /**
     * Test of getStringG method, of class IniDocument.
     */
    @org.junit.Test
    public void testGetStringG() {
    }

    /**
     * Test of removeKey method, of class IniDocument.
     */
    @org.junit.Test
    public void testRemoveKey() {
    }

    /**
     * Test of removeSection method, of class IniDocument.
     */
    @org.junit.Test
    public void testRemoveSection() {
    }

    /**
     * Test of setBoolean method, of class IniDocument.
     */
    @org.junit.Test
    public void testSetBoolean_3args() {
    }

    /**
     * Test of setBoolean method, of class IniDocument.
     */
    @org.junit.Test
    public void testSetBoolean_4args() {
    }

    /**
     * Test of setBooleanG method, of class IniDocument.
     */
    @org.junit.Test
    public void testSetBooleanG_String_boolean() {
    }

    /**
     * Test of setBooleanG method, of class IniDocument.
     */
    @org.junit.Test
    public void testSetBooleanG_3args() {
    }

    /**
     * Test of setComment method, of class IniDocument.
     */
    @org.junit.Test
    public void testSetComment() {
    }

    /**
     * Test of setCommentG method, of class IniDocument.
     */
    @org.junit.Test
    public void testSetCommentG() {
    }

    /**
     * Test of setDouble method, of class IniDocument.
     */
    @org.junit.Test
    public void testSetDouble_3args() {
    }

    /**
     * Test of setDouble method, of class IniDocument.
     */
    @org.junit.Test
    public void testSetDouble_4args() {
    }

    /**
     * Test of setDoubleG method, of class IniDocument.
     */
    @org.junit.Test
    public void testSetDoubleG_String_double() {
    }

    /**
     * Test of setDoubleG method, of class IniDocument.
     */
    @org.junit.Test
    public void testSetDoubleG_3args() {
    }

    /**
     * Test of setFloat method, of class IniDocument.
     */
    @org.junit.Test
    public void testSetFloat_3args() {
    }

    /**
     * Test of setFloat method, of class IniDocument.
     */
    @org.junit.Test
    public void testSetFloat_4args() {
    }

    /**
     * Test of setFloatG method, of class IniDocument.
     */
    @org.junit.Test
    public void testSetFloatG_String_float() {
    }

    /**
     * Test of setFloatG method, of class IniDocument.
     */
    @org.junit.Test
    public void testSetFloatG_3args() {
    }

    /**
     * Test of setInt method, of class IniDocument.
     */
    @org.junit.Test
    public void testSetInt_3args() {
    }

    /**
     * Test of setInt method, of class IniDocument.
     */
    @org.junit.Test
    public void testSetInt_4args() {
    }

    /**
     * Test of setIntG method, of class IniDocument.
     */
    @org.junit.Test
    public void testSetIntG_String_int() {
    }

    /**
     * Test of setIntG method, of class IniDocument.
     */
    @org.junit.Test
    public void testSetIntG_3args() {
    }

    /**
     * Test of setLong method, of class IniDocument.
     */
    @org.junit.Test
    public void testSetLong_3args() {
    }

    /**
     * Test of setLong method, of class IniDocument.
     */
    @org.junit.Test
    public void testSetLong_4args() {
    }

    /**
     * Test of setLongG method, of class IniDocument.
     */
    @org.junit.Test
    public void testSetLongG_String_long() {
    }

    /**
     * Test of setLongG method, of class IniDocument.
     */
    @org.junit.Test
    public void testSetLongG_3args() {
    }

    /**
     * Test of setSection method, of class IniDocument.
     */
    @org.junit.Test
    public void testSetSection_String_String() {
    }

    /**
     * Test of setString method, of class IniDocument.
     */
    @org.junit.Test
    public void testSetString_3args() {
    }

    /**
     * Test of setString method, of class IniDocument.
     */
    @org.junit.Test
    public void testSetString_4args() {
    }

    /**
     * Test of setStringG method, of class IniDocument.
     */
    @org.junit.Test
    public void testSetStringG_String_String() {
    }

    /**
     * Test of setStringG method, of class IniDocument.
     */
    @org.junit.Test
    public void testSetStringG_3args() {
    }
}
