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
package com.bew.commons.sqlite;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.ResultSet;
import org.junit.jupiter.api.Test;

import static com.bew.commons.sqlite.Database.DbOpen.CREATE;
import static com.bew.commons.sqlite.Database.DbOpen.OPEN;
import static com.bew.commons.sqlite.Database.DbOpen.REPLACE;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 *
 * @author Bradley Willcott
 */
public class DatabaseTest {

//    private static Path PATH;
//
//    @BeforeAll
//    public static void setUpClass() throws Exception {
//        PATH = FileSystems.getDefault().getPath("", "test2.dbs");
//    }
//
//    @BeforeEach
//    public void setUp() throws Exception {
//    }
//
//    @AfterAll
//    public static void cleanupClass() {
//
//    }
    /**
     * This tests the Database class from initial database creation, through
     * table creation and data insertion.
     */
    @Test
    public void testlifeCycle() {
        final MembersState ms = new MembersState();
        System.out.println("testlifeCycle");

        Path path = FileSystems.getDefault().getPath("", "test.dbs");
        Path path2 = FileSystems.getDefault().getPath("/tmp", "test.dbs");

        try {
            assertDoesNotThrow(() -> {
                Files.deleteIfExists(path2);
            });

            ms.db = assertDoesNotThrow(() -> {
                return new Database(path, REPLACE);
            });

            assertEquals(Database.Status.ON,
                         assertDoesNotThrow(() -> {
                             return ms.db.getForeignKeysConstraint();

                         }));

            // All is well, now let's get some work done ...
            assertTrue(
                    assertDoesNotThrow(() -> {
                        return ms.db.execute(DatabaseStructureSQL.getSQLArray());
                    })
            );
            assertTrue(
                    assertDoesNotThrow(() -> {
                        return ms.db.execute(DatabaseDefaultDataSQL.getSQLArray());
                    })
            );

            try {
                ms.rs = assertDoesNotThrow(() -> {

                    for (int i = 0; i < 6; i++) {
                        ResultSet rs1 = ms.db.executeQuery(DatabaseDefaultDataSQL.SELECT1);
                        rs1.close();
                    }
                    return ms.db.executeQuery(DatabaseDefaultDataSQL.SELECT1);
                });
                assertEquals("Food", assertDoesNotThrow(() -> {
                         return ms.rs.getString("Name");
                     })
                );
            } finally {
                assertDoesNotThrow(() -> {
                    ms.rs.close();
                });
            }

            assertThrows(IOException.class, () -> {
                     new Database(path, CREATE);
                 });

            assertThrows(IOException.class, () -> {
                     new Database(path2, OPEN);
                 });

            assertThrows(IOException.class, () -> {
                     new Database(path2, REPLACE);
                 });

            assertDoesNotThrow(() -> {
                Database db = new Database(path2, CREATE);
                db.setForeignKeysConstraint(Database.Status.OFF);
                db.getForeignKeysConstraint();
            });

            assertDoesNotThrow(() -> {
                new Database(path, OPEN);
            });

        } finally {
            assertDoesNotThrow(() -> {
                ms.db.close();
            });
        }
    }

    private class MembersState {

        ResultSet rs;
        Database db;
    }
}
