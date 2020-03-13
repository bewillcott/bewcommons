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

module BEWCommons {
//    requires java.logging;
    requires java.desktop;
    requires transitive java.sql;

    exports com.bew.commons;
    exports com.bew.commons.fileio;
    exports com.bew.commons.regexp;
    exports com.bew.commons.sqlite;
    exports com.bew.util;
    exports com.centerkey.utils;
}
