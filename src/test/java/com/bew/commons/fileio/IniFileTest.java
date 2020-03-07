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

import com.bew.commons.InvalidProgramStateException;
import com.bew.util.Property;
import java.nio.file.Files;
import java.nio.file.*;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 *
 * @author Bradley Willcott
 * @since 1.0
 */
public class IniFileTest {

    private static final String TEST_RESOURCES = "src/test/resources/";
    private static final String FILEPATH
                                = TEST_RESOURCES + "Test.ini";
    private static final String FILEPATH_ORIG
                                = TEST_RESOURCES + "Test-orig.ini";

    private static final String FILEPATH_NEW
                                = TEST_RESOURCES + "Test-new.ini";

    private static final String FILEPATH2
                                = TEST_RESOURCES + "Test2.ini";
    private static final String FILEPATH_ORIG2
                                = TEST_RESOURCES + "Test-orig2.ini";

    private static final String[][] INICONTENTS = {
        {"C", "# this is an INI file"},
        {"C", "; this is an alternative comment"},
        {"B", ""},
        {"S", "others"},
        {"P", "Fred Smith = true"},
        {"P", "s1 = Hello World"},
        {"P", "boolean = true"},
        {"B", ""},
        {"C", "; The numbers racket is noisy."},
        {"S", "numbers"},
        {"P", "double = 11.1"},
        {"P", "float = 12.3F"},
        {"P", "int = 34"},
        {"C", "# This is the long but not the short of it."},
        {"P", "long = 12343567890L"},
        {"B", ""},
        {"S", "section"},
        {"P", "key = value"},
        {"P", "Bye now ="},
        {"B", ""}
    };

    private static final int SECTION = 0;
    private static final int KEY = 1;
    private static final int VALUE = 2;

    private static final String[][] INPUT = {
        {"others", "Home", "Newtown"},
        {"Employees", "001", "Fred Smith"},
        {"Fred Smith", "Address", "10 Anders Ave"},
        {"Fred Smith", "Suburb", "Jinville"},
        {"Fred Smith", "State", "SA"},
        {"Fred Smith", "Phone", "0412-345-395"},
        {"Fred Smith", "Comments", "Good worker"},
        {"Employees", "002", "Peter Davis"},
        {"Peter Davis", "Address", "12A Anders Way"},
        {"Peter Davis", "Suburb", "Shineytown"},
        {"Peter Davis", "State", "Vic"},
        {"Peter Davis", "Phone", "0428-859-271"},
        {"Peter Davis", "Comments", "Avg. worker"}
    };

    private static final String OUTPUT
                                = "Id = 001 | Name = Fred Smith | Comment=null\n"
                                  + "\tAddress: 10 Anders Ave\n"
                                  + "\tSuburb: Jinville\n"
                                  + "\tState: SA\n"
                                  + "\tPhone: 0412-345-395\n"
                                  + "\tComments: Good worker\n"
                                  + "\n"
                                  + "Id = 002 | Name = Peter Davis | Comment=null\n"
                                  + "\tAddress: 12A Anders Way\n"
                                  + "\tSuburb: Shineytown\n"
                                  + "\tState: Vic\n"
                                  + "\tPhone: 0428-859-271\n"
                                  + "\tComments: Avg. worker\n"
                                  + "\n";

    private static final Path PATH;
    private static final Path PATH_ORIG;
    private static final Path PATH_NEW;
    private static final Path PATH2;
    private static final Path PATH_ORIG2;

    static {
        System.out.println("Initializing: IniFileTest");
        PATH = FileSystems.getDefault().getPath(FILEPATH);
        PATH_ORIG = FileSystems.getDefault().getPath(FILEPATH_ORIG);
        PATH_NEW = FileSystems.getDefault().getPath(FILEPATH_NEW);
        PATH2 = FileSystems.getDefault().getPath(FILEPATH2);
        PATH_ORIG2 = FileSystems.getDefault().getPath(FILEPATH_ORIG2);
    }

    /**
     *
     */
    @BeforeAll
    public static void setup() {
        System.out.println("BeforeAll setup() method called");
        assertDoesNotThrow(() -> {
            Files.copy(PATH_ORIG2, PATH2, StandardCopyOption.REPLACE_EXISTING);
        });
    }

    /**
     *
     */
    @BeforeEach
    public void setupTest() {
        System.out.println("BeforeEach setupTest() method called");
        assertDoesNotThrow(() -> {
            Files.copy(PATH_ORIG, PATH, StandardCopyOption.REPLACE_EXISTING);
        });
    }

    /**
     * Testing filePath methods, of class IniFile.
     */
    @Test
    public void testFilePath() {
        System.out.println("testFilePath");
        System.out.println("  filePath not set");
//        assertNull(new IniFile().filePath());

        System.out.println("  filePath(PATH)");
        IniFile instance
                = assertDoesNotThrow(() -> {
                    return new IniFile(PATH).loadFile();
                });
        assertEquals(FILEPATH, instance.path.toString());

        System.out.println("  filePath(null)");
        assertThrows(NullPointerException.class, () -> {
                 new IniFile(null).loadFile();
             });

        System.out.println("  filePath(PATH3) - Doesn't exist");
        assertThrows(NoSuchFileException.class, () -> {
                 new IniFile(PATH_NEW).loadFile();
             });
    }

    /**
     * Tests the full life cycle:<br>
     * <ol>
     * <li>Load an ini file.</li>
     * <li>Add various properties grouped in sections.</li>
     * <li>Save the ini file.</li>
     * <li>Load the saved ini file.</li>
     * <li>Collect back the information previously stored.</li>
     * <li>Test compare this information with the original data.</li>
     * </ol>
     */
    @Test
    public void testLifeCycle() {
        final MembersState ms = new MembersState();

        System.out.println("testLifeCycle");
        assertDoesNotThrow(() -> {
            Files.copy(PATH_ORIG, PATH, StandardCopyOption.REPLACE_EXISTING);
        });

        System.out.println("  Load an ini file");
        ms.ini = assertDoesNotThrow(() -> {
            return new IniFile(PATH).loadFile();
        });

        System.out.println("  Add various properties grouped in sections");
        for (String[] inparr : INPUT) {
            ms.ini.iniDoc.setString(inparr[SECTION], inparr[KEY], inparr[VALUE]);
        }

        System.out.println("  Save the ini file");
        assertDoesNotThrow(() -> {
            ms.ini.saveFile();
        });

        System.out.println("  Load the saved ini file");
        ms.ini = assertDoesNotThrow(() -> {
            return new IniFile(PATH).loadFile();
        });

        // Collect all employees details
        System.out.println("  Collect back the information previously stored");
        StringBuilder sb = new StringBuilder();

        for (Property<String, Object> employee : ms.ini.iniDoc.getSection("Employees")) {
            sb.append(
                    "Id = " + employee.key
                    + " | Name = " + employee.value
                    + " | Comment=" + employee.comment
                    + "\n"
            );

            for (Property<String, Object> empDetails : ms.ini.iniDoc.getSection((String) employee.value)) {
                sb.append((empDetails.comment != null ? "\t" + empDetails.comment + "\n" : "")
                          + "\t" + empDetails.key + ": "
                          + empDetails.value
                          + "\n"
                );
            }
            sb.append("\n");
        }
        // Test correct
        System.out.println("  Test compare this information with the original data");
        assertTrue(OUTPUT.equals(sb.toString()));
    }

    /**
     * Test of saveFile method, of class IniFile.
     */
    @Test
    public void testSaveFile() {
        System.out.println("saveFile()");
        assertDoesNotThrow(() -> {
            Files.copy(PATH_ORIG2, PATH2, StandardCopyOption.REPLACE_EXISTING);
        });

        IniFile instance
                = assertDoesNotThrow(() -> {
                    return new IniFile(PATH).loadFile();
                });
        instance.paddedEquals = true;
        assertDoesNotThrow(() -> {
            instance.saveFile();
        });

        List<String> iniFile
                     = assertDoesNotThrow(() -> {
                    return Files.readAllLines(PATH);
                });
        List<String> iniFile2
                     = assertDoesNotThrow(() -> {
                    return Files.readAllLines(PATH_ORIG);
                });

        assertTrue((iniFile.size() == iniFile2.size()),
                   "Incorrect number of lines:\n  expected: " + iniFile2.size()
                   + "\n  but was: " + iniFile.size());

        for (int i = 0; i < iniFile2.size(); i++) {
            String get2 = iniFile2.get(i);
            String get = iniFile.get(i);

            assertTrue((get.equals(get2)),
                       "Line compare failed: (#" + i + 1 + ")\n  expected: "
                       + get2 + "\n  but was: " + get);
        }

        IniFile instance2 = assertDoesNotThrow(() -> {
            return new IniFile(PATH);
        });
        assertThrows(InvalidProgramStateException.class, () -> {
                 instance2.mergeFile(PATH2);
             });

        IniFile instance3 = assertDoesNotThrow(() -> {
            return new IniFile(PATH).loadFile();
        });
        assertDoesNotThrow(() -> {
            instance3.saveFile();
        });

        IniFileFormatException result
                               = assertThrows(IniFileFormatException.class, () -> {
                                          new IniFile(PATH2).loadFile();
                                      });
        String test = result.filepath;
        test = result.toString();

        assertThrows(NullPointerException.class, () -> {
                 new IniFile(null).loadFile();
             });

    }

    /**
     * Test of saveFile method, of class IniFile.
     */
    @Test

    public void testSaveFile_Path() {
        System.out.println("saveFile(PATH)");
        IniFile instance = assertDoesNotThrow(() -> {
            return new IniFile(PATH).loadFile();
        });
        IniDocument iniDoc = instance.iniDoc;

        final MembersState ms = new MembersState();

        for (String[] property : INICONTENTS) {
            switch (property[0]) {
                case "C":
                    if (ms.lastComment != null) {
                        assertDoesNotThrow(() -> {
                            iniDoc.setComment(ms.currentSection, "#" + ms.counter++, ms.lastComment);
                        });
                    }

                    ms.lastComment = property[1].strip();
                    break;

                case "B":
                    if (ms.lastComment != null) {
                        assertDoesNotThrow(() -> {
                            iniDoc.setComment(ms.currentSection, "#" + ms.counter++, ms.lastComment);
                        });
                    }

                    ms.lastComment = null;
                    break;

                case "S":
                    ms.currentSection = property[1].strip();
                    assertDoesNotThrow(() -> {
                        iniDoc.setSection(ms.currentSection, ms.lastComment);
                    });
                    ms.lastComment = null;
                    break;

                case "P":
                    String[] props = property[1].split("=");
                    assertDoesNotThrow(() -> {
                        iniDoc.setString(ms.currentSection, props[0].strip(),
                                         props.length == 2 ? props[1].strip() : "", ms.lastComment);
                    });
                    ms.lastComment = null;
            }
        }

        instance.paddedEquals = true;
        assertDoesNotThrow(() -> {
            instance.saveFileAs(PATH2);
        });

        List<String> iniFile
                     = assertDoesNotThrow(() -> {
                    return Files.readAllLines(PATH);
                });
        List<String> iniFile2
                     = assertDoesNotThrow(() -> {
                    return Files.readAllLines(PATH_ORIG);
                });

        assertTrue((iniFile2.size() == iniFile.size()),
                   "Incorrect number of lines:\n  expected: " + iniFile2.size()
                   + "\n  but was: " + iniFile.size());

        for (int i = 0; i < iniFile2.size(); i++) {
            String get2 = iniFile2.get(i);
            String get = iniFile.get(i);

            assertTrue((get2.equals(get)),
                       "Line compare failed: (#" + i + 1 + ")\n  expected: "
                       + get2 + "\n  but was: " + get);
        }

        IniFile instance2 = assertDoesNotThrow(() -> {
            return new IniFile(PATH).loadFile();
        });
        assertThrows(NullPointerException.class, () -> {
                 instance2.saveFileAs(null);
             });
    }

    /**
     * Test of toString method, of class IniFile.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        IniFile instance = assertDoesNotThrow(() -> {
            return new IniFile(PATH).loadFile();
        });
        assertTrue(instance.toString().startsWith("IniFile{\n_path="));
    }

    private class MembersState {

        String lastComment = null;
        String currentSection = null;
        int counter = 0;
        IniFile ini = null;
    }
}
