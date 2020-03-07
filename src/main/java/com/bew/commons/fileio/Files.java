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

import com.bew.commons.fileio.Files;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.nio.file.FileVisitResult.CONTINUE;
import static java.nio.file.Files.getLastModifiedTime;
import static java.nio.file.Files.notExists;
import static java.nio.file.Path.of;
import static java.nio.file.StandardCopyOption.COPY_ATTRIBUTES;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

/**
 *
 * @author Bradley Willcott
 */
public class Files {

    public static void copyDirTree(String sourceDir, String destDir, String pattern, int vlevel, CopyOption... options) throws IOException {
        Path srcPath = (sourceDir != null ? of(sourceDir) : of(""));
        Path destPath = (destDir != null ? of(destDir) : of(""));

        copyDirTree(srcPath, destPath, pattern, vlevel, options);
    }

    public static void copyDirTree(Path srcPath, Path destPath, String pattern, int vlevel, CopyOption... options) throws IOException {
        if (srcPath.equals(destPath)) {
            return;
        }

        Finder finder = new Finder(pattern != null ? pattern : "*", vlevel);

        java.nio.file.Files.walkFileTree(srcPath, finder);

        SortedSet<Path> inpList = finder.done();
        List<Path[]> outList = new ArrayList<>(inpList.size());
        Set<Path> dirList = new TreeSet<>();
        Pattern p1 = Pattern.compile("^(?<filename>.*)$");
        Pattern p2 = Pattern.compile("^(?:" + srcPath + "/)(?<filename>.*)$");

        for (Path inPath : inpList) {
            Matcher m;

            if (srcPath.toString().isEmpty() || destPath.toString().isEmpty()) {
                m = p1.matcher(inPath.toString());
            } else {
                m = p2.matcher(inPath.toString());
            }

            if (m.find()) {
                Path outPath = of(destPath.toString(), m.group("filename"));

                if (notExists(outPath) || getLastModifiedTime(inPath).compareTo(getLastModifiedTime(outPath)) > 0) {
                    Path[] files = new Path[2];

                    files[0] = inPath;
                    files[1] = outPath;
                    outList.add(files);

                    Path parent = outPath.getParent();

                    if (java.nio.file.Files.notExists(parent)) {
                        dirList.add(parent);
                    }

                    if (vlevel >= 2) {
                        System.err.println(outPath);
                    }
                }
            }
        }

        if (vlevel >= 2) {
            System.err.println("Creating directories ...");

            for (Path dir : dirList) {
                System.err.println("    " + dir);
                java.nio.file.Files.createDirectories(dir);
            }
        } else {
            for (Path dir : dirList) {
                java.nio.file.Files.createDirectories(dir);
            }
        }

        if (vlevel >= 2) {
            System.err.println("Copying files ...");

            for (Path[] filePairs : outList) {
                System.err.println(filePairs[0]);
                System.err.println("    " + filePairs[1]);
                java.nio.file.Files.copy(filePairs[0], filePairs[1], options);
            }
        } else {
            for (Path[] filePairs : outList) {
                java.nio.file.Files.copy(filePairs[0], filePairs[1], options);
            }
        }

    }

    public static void main(String[] args) throws IOException {

        Files.copyDirTree("src", "target/src", "*.{html,css}", 2, COPY_ATTRIBUTES, REPLACE_EXISTING);

    }

    private Files() {
    }

    public static class Finder
            extends SimpleFileVisitor<Path> {

        private final int vlevel;
        private final PathMatcher matcher;
        private int numMatches = 0;
        private final SortedSet<Path> filenames = new TreeSet<>();

        Finder(String pattern, int vlevel) {
            matcher = FileSystems.getDefault()
                    .getPathMatcher("glob:" + pattern);
            this.vlevel = vlevel;
        }

        // Compares the glob pattern against
        // the file name.
        void find(Path file) {
            Path name = file.getFileName();
            if (name != null && matcher.matches(name)) {
                numMatches++;

                if (vlevel >= 2) {
                    System.err.println(file);
                }

                filenames.add(file);
            }
        }

        // Prints the total number of
        // matches to standard out.
        SortedSet<Path> done() {
            if (vlevel >= 1) {
                System.err.println("Matched: "
                                   + numMatches);
            }
            return filenames;
        }

        // Invoke the pattern matching
        // method on each file.
        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
            find(file);
            return CONTINUE;
        }

        // Invoke the pattern matching
        // method on each directory.
        @Override
        public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
//            find(dir);
            return CONTINUE;
        }

        @Override
        public FileVisitResult visitFileFailed(Path file, IOException exc) {
            System.err.println("visitFileFailed: " + exc);
            return CONTINUE;
        }
    }

}
