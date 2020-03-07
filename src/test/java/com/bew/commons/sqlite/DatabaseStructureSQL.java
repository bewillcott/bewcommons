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
 * This static class contains static methods that provide the SQL strings used
 * to CREATE the jShopping Database structure.
 *
 * @author Brad Willcott
 */
public final class DatabaseStructureSQL {

    public static final String GROUPS = "CREATE TABLE Groups (ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL UNIQUE,Name TEXT NOT NULL UNIQUE)";

    public static final String CATEGORY = "CREATE TABLE Category (ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL UNIQUE,Name TEXT NOT NULL UNIQUE,GroupsID INTEGER,FOREIGN KEY(GroupsID) REFERENCES Groups(ID))";

    public static final String ITEM = "CREATE TABLE Item (ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL UNIQUE,Name TEXT NOT NULL UNIQUE,Size REAL DEFAULT 0.0,CategoryID INTEGER,FOREIGN KEY(CategoryID) REFERENCES Category(ID))";

    public static final String SHOP = "CREATE TABLE Shop (ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL UNIQUE,Name TEXT NOT NULL,Address_Line1 TEXT,Address_Line2 TEXT,Suburb TEXT,State TEXT,Postcode TEXT,Phone TEXT)";
    public static final String SHOP_IDX = "CREATE INDEX Shop_Name_Idx ON Shop(Name)";

    public static final String RECEIPT = "CREATE TABLE Receipt (ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL UNIQUE,PurchaseDate TEXT NOT NULL,TotalCost REAL DEFAULT 0.0,ShopID INTEGER,FOREIGN KEY(ShopID) REFERENCES Shop(ID))";
    public static final String RECEIPT_IDX = "CREATE INDEX Receipt_PurchaseDate_Idx ON Receipt(PurchaseDate)";

    public static final String LINEITEM = "CREATE TABLE LineItem (ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL UNIQUE,Quantity REAL DEFAULT 0.0,CostPerItem REAL DEFAULT 0.0,ReceiptID INTEGER,ItemID INTEGER,FOREIGN KEY(ReceiptID) REFERENCES Receipt(ID),FOREIGN KEY(ItemID) REFERENCES Item(ID))";

    private DatabaseStructureSQL() {
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
            GROUPS, CATEGORY, ITEM, SHOP, SHOP_IDX, RECEIPT, RECEIPT_IDX, LINEITEM
        };
    }
}
