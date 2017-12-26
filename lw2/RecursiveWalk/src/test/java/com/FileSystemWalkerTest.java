package com;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class FileSystemWalkerTest {
    private FileSystemWalker walker;

    @BeforeEach
    void createWalker() {
        walker = new FileSystemWalker();
    }

    @AfterEach
    void clearWalker() {
        walker = null;
    }

    @Test
    void visitSaveSpecifiedFileForFileArgument() {
        File f = Utils.nullSafe(Utils.exceptionSafe(() -> File.createTempFile("test", ".temp")));

        walker.visit(f.toString());
        HashSet<Path> visitedFiles = walker.getVisitedFiles();
        assertEquals(1, visitedFiles.size());
        assertEquals(f.getPath(), visitedFiles.iterator().next().toString());
    }

    @Test
    void visitWithNotFilePathParameterSaveInvalidPath() {
        final String INVALID_PATH = "123123412451241d_/*";
        walker.visit(INVALID_PATH);

        ArrayList<String> invalidPaths = walker.getInvalidPaths();
        assertEquals(1, invalidPaths.size());
        assertEquals(INVALID_PATH, invalidPaths.get(0));
    }

    @Test
    void visitWorksRecursive() {
        Path directory = Utils.exceptionSafe(() -> Files.createTempDirectory("testDir"));
        File f1 = Utils.nullSafe(Utils.exceptionSafe(() -> File.createTempFile("test1", ".temp1", new File(directory.toString()))));
        File f2 = Utils.nullSafe(Utils.exceptionSafe(() -> File.createTempFile("test2", ".temp2", new File(directory.toString()))));

        walker.visit(directory.toString());
        HashSet<Path> files = walker.getVisitedFiles();
        ArrayList<Path> filesList = new ArrayList<Path>(files);
        Collections.sort(filesList);
        Iterator<Path> it = filesList.iterator();

        assertEquals(2, files.size());
        assertEquals(f1.toString(), it.next().toString());
        assertEquals(f2.toString(), it.next().toString());
    }
}