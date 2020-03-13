/*
 * Copyright (C) 2020 Bradley Willcott
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.bew.util;

import java.io.IOException;
import java.util.Properties;

/**
 * Provides access to some of the projects pom.properties.
 * <p>
 * To setup in a new project:
 * </p><p>
 * To access the properties:
 * </p>
 * <pre><code>
 * POMProperties pom = POMProperties.INSTANCE;
 * System.out.println(pom.title):
 * </code></pre>
 *
 * @author Bradley Willcott
 */
public final class POMProperties {

    /**
     * Provides single instance of this class.
     */
    public final static POMProperties INSTANCE = new POMProperties();
    /**
     * The identifier for this artifact that is unique within
     * the group given by the group ID.
     */
    public final String artifactId;

    /**
     * Project Description
     */
    public final String description;

    /**
     * Project GroupId
     */
    public final String groupId;

    /**
     * Project Name
     */
    public final String title;

    /**
     * The version of the artifact.
     */
    public final String version;

    private POMProperties() {
        Properties properties = new Properties();
        try {
            properties.load(POMProperties.class.getResourceAsStream("/BEWCommons.properties"));
        } catch (IOException ex) {
            throw new RuntimeException("FileIOError", ex);
        }

        title = properties.getProperty("title");
        description = properties.getProperty("description");
        version = properties.getProperty("version");
        artifactId = properties.getProperty("artifactId");
        groupId = properties.getProperty("groupId");
    }

    @Override
    public String toString() {
        return new StringBuilder(POMProperties.class.getName()).append(":\n")
                .append("  title: ").append(title).append("\n")
                .append("  description: ").append(description).append("\n")
                .append("  version: ").append(version).append("\n")
                .append("  artifactId: ").append(artifactId).append("\n")
                .append("  groupId: ").append(groupId).append("\n").toString();
    }

    public static void main(String[] args) {
        System.out.println(POMProperties.INSTANCE);
    }
}
