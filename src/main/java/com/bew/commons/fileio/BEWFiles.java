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

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.spi.FileSystemProvider;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.nio.file.FileVisitResult.CONTINUE;
import static java.nio.file.Files.copy;
import static java.nio.file.Files.createDirectories;
import static java.nio.file.Files.getLastModifiedTime;
import static java.nio.file.Files.notExists;
import static java.nio.file.Files.walkFileTree;
import static java.nio.file.Path.of;

/**
 *
 * @author Bradley Willcott
 */
public class BEWFiles {

    /**
     * Recursively copies the directories and files of the {@code sourceDir} to the {@code destDir}.
     * <p>
     * Example:
     * <pre><code>
     * public static void main(String[] args) throws IOException {
     *     BEWFiles.copyDirTree("src", "target/src", "*.{html,css}", 2, COPY_ATTRIBUTES, REPLACE_EXISTING);
     * }</code></pre>
     *
     * @param sourceDir  Source Directory.
     * @param destDir    Destination Directory.
     * @param pattern    Regex pattern to find files.
     * @param verboseLvl Verbose level.
     * @param options    List of objects that configure how to copy or move a file.
     *
     * @throws IOException If any File IO errors.
     */
    public static void copyDirTree(String sourceDir, String destDir, String pattern, int verboseLvl, CopyOption... options) throws IOException {
        Path srcPath = (sourceDir != null ? of(sourceDir) : of(""));
        Path destPath = (destDir != null ? of(destDir) : of(""));

        copyDirTree(srcPath, destPath, pattern, verboseLvl, options);
    }

    /**
     * Recursively copies the directories and files of the {@code srcPath} to the {@code destPath}.
     * <p>
     * This method would most likely only be called by the
     * {@linkplain #copyDirTree(String, String, String, int, CopyOption...)}
     * method.
     *
     * @param srcPath    Source Path.
     * @param destPath   Destination Path.
     * @param pattern    File search pattern.
     * @param verboseLvl Verbose level.
     * @param options    List of objects that configure how to copy or move a file.
     *
     * @throws IOException
     */
    public static void copyDirTree(Path srcPath, Path destPath, String pattern, int verboseLvl, CopyOption... options) throws IOException {
        // Can't be copying source onto itself.
        // No point.
        if (srcPath.equals(destPath))
        {
            return;
        }

        // Initialise and prepare finder...
        Finder finder = new Finder(pattern != null ? pattern : "*", verboseLvl);

        walkFileTree(srcPath, finder);

        // Process list of files/directories found...
        SortedSet<Path> inpList = finder.done();
        List<Path[]> outList = new ArrayList<>(inpList.size());
        Set<Path> dirList = new TreeSet<>();
        Pattern p1 = Pattern.compile("^(?<filename>.*)$");
        Pattern p2 = Pattern.compile("^(?:" + srcPath + "/)(?<filename>.*)$");

        for (Path inPath : inpList)
        {
            Matcher m;

            if (srcPath.toString().isEmpty() || destPath.toString().isEmpty())
            {
                m = p1.matcher(inPath.toString());
            } else
            {
                m = p2.matcher(inPath.toString());
            }

            if (m.find())
            {
                Path outPath = of(destPath.toString(), m.group("filename"));

                if (notExists(outPath) || getLastModifiedTime(inPath).compareTo(getLastModifiedTime(outPath)) > 0)
                {
                    Path[] files = new Path[2];

                    files[0] = inPath;
                    files[1] = outPath;
                    outList.add(files);

                    Path parent = outPath.getParent();

                    if (notExists(parent))
                    {
                        dirList.add(parent);
                    }

                    if (verboseLvl >= 2)
                    {
                        System.err.println(outPath);
                    }
                }
            }
        }

        if (verboseLvl >= 2)
        {
            // Printout verbose info.
            System.err.println("Creating directories ...");

            for (Path dir : dirList)
            {
                System.err.println("    " + dir);
                createDirectories(dir);
            }
        } else
        {
            for (Path dir : dirList)
            {
                createDirectories(dir);
            }
        }

        if (verboseLvl >= 2)
        {
            // Printout verbose info.
            System.err.println("Copying files ...");

            for (Path[] filePairs : outList)
            {
                System.err.println(filePairs[0]);
                System.err.println("    " + filePairs[1]);
                copy(filePairs[0], filePairs[1], options);
            }
        } else
        {
            for (Path[] filePairs : outList)
            {
                copy(filePairs[0], filePairs[1], options);
            }
        }

    }

    /**
     * Get the resource Path.
     *
     * @param clazz Calling class.
     * @param name  Resource name.
     *
     * @return Path to the resource.
     *
     * @throws URISyntaxException Name produces an invalid path data.
     * @throws IOException        General IO failure.
     */
    public static Path getResource(Class<?> clazz, String name) throws URISyntaxException, IOException {
        // clazz and name must NOT be null.
        if (clazz == null || name == null || name.isBlank())
        {
            return null;
        }

        URI uri = clazz.getResource(name).toURI();

        if ("jar".equals(uri.getScheme()))
        {
            for (FileSystemProvider provider : FileSystemProvider.installedProviders())
            {
                if (provider.getScheme().equalsIgnoreCase("jar"))
                {
                    try
                    {
                        provider.getFileSystem(uri);
                    } catch (FileSystemNotFoundException e)
                    {
                        // in this case we need to initialize it first:
                        provider.newFileSystem(uri, Collections.emptyMap());
                    }
                }
            }
        }

        return Paths.get(uri);
    }

    /**
     * This class in not meant to be instantiated.
     */
    private BEWFiles() {
    }

    /**
     * This class is used by the
     * {@linkplain #copyDirTree(Path, Path, String, int, CopyOption...) copyDirTree}
     * method.
     */
    public static class Finder extends SimpleFileVisitor<Path> {

        private final int vlevel;
        private final PathMatcher matcher;
        private int numMatches = 0;
        private final SortedSet<Path> filenames = new TreeSet<>();

        Finder(String pattern, int vlevel) {
            matcher = FileSystems.getDefault()
                    .getPathMatcher("glob:" + pattern);
            this.vlevel = vlevel;
        }

        /**
         * Compares the glob pattern against the file name.
         *
         * @param file File to check.
         */
        void find(Path file) {
            Path name = file.getFileName();

            if (name != null && matcher.matches(name))
            {
                numMatches++;

                if (vlevel >= 2)
                {
                    // Printout verbose info.
                    System.err.println(file);
                }

                filenames.add(file);
            }
        }

        /**
         * Prints the total number of matches to standard out.
         */
        SortedSet<Path> done() {
            if (vlevel >= 1)
            {
                // Printout verbose info.
                System.err.println("Matched: " + numMatches);
            }

            return filenames;
        }

        /**
         * Invoked for a file in a directory.<br>
         * Invokes the pattern matching method on each file.
         *
         * @param file  a reference to the file
         * @param attrs the file's basic attributes
         *
         * @return the visit result
         */
        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
            find(file);
            return CONTINUE;
        }

        /**
         * @inheritDoc
         */
        @Override
        public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
            return CONTINUE;
        }

//        /**
//         * Invoked for a file that could not be visited.
//         * @param file a reference to the file.
//         * @param exc the I/O exception that prevented the file from being visited.
//         *
//         * @return the visit result.
//         */
        /**
         * @inheritDoc
         */
        @Override
        public FileVisitResult visitFileFailed(Path file, IOException exc) {
            System.err.println("visitFileFailed: " + exc);
            return CONTINUE;
        }
    }

}
