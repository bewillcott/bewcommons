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

/**
 *
 * @author Brad Willcott
 */
public class DatabaseDefaultDataSQL {

    public static final String GROUP = "INSERT INTO Groups (Name) VALUES ('Food'),('Bills')";

    public static final String CATEGORY1 = "INSERT INTO Category (Name, GroupsID) "
                                           + "VALUES ('Meat', 1),('Vegies', 1),"
                                           + "('Dairy', 1),('Dry Goods', 1)";
    public static final String CATEGORY2 = "INSERT INTO Category (Name, GroupsID) "
                                           + "VALUES ('Utilities', 2),('Transport', 2),"
                                           + "('Insurance', 2)";
    public static final String CATEGORY3 = "INSERT INTO Category (Name, GroupsID) "
                                           + "VALUES ('Misc', NULL),('Other', NULL)";

    public static final String SELECT1 = "SELECT * FROM Groups";// WHERE id = 1";
    public static final String SELECT2 = "SELECT * FROM Groups";// WHERE id = 1";

    private DatabaseDefaultDataSQL() {
        // NoOp
    }

    /**
     * For use with
     * {@link Database#execute(java.sql.Connection, java.lang.String[]) Database.execute(conn, arraySQL)}
     *
     * @return An array of the SQL strings.
     */
    public static final String[] getSQLArray() {
        return new String[]{
            GROUP, CATEGORY1, CATEGORY2, CATEGORY3
        };
    }
}
