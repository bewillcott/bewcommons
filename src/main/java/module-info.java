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
/**
 * BEW Commons is a collection classes covering various topics.
 * The work is ongoing.  As I need additional functionality, it will be added.
 * <p>
 * The code is mostly of my own development.  However, there are classes
 * and methods that are either copies of, or are derived from, the works
 * of others.  Where ever possible I have given credit to those developers.
 * In many instances by including a link to the source web page.
 * <p>
 * If you recognize your work, I would be interested in hearing from you.
 * If you would like to suggest additional functionality, please contact me.
 *
 * @author <a href="mailto:bw.opensource@yahoo.com">Bradley Willcott</a>
 *
 * @since 1.0
 * @version 1.0.21
 */
module BEWCommons {
    requires transitive java.desktop;
    requires transitive java.sql;
    requires transitive org.apache.logging.log4j;

    exports com.bew.commons;
    exports com.bew.commons.fileio;
    exports com.bew.commons.graphics;
    exports com.bew.commons.property;
    exports com.bew.commons.reflect;
    exports com.bew.commons.regexp;
    exports com.bew.commons.sqlite;
    exports com.bew.commons.string;
}
