package com;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;

public class FileSystemWalker {
    private HashSet<Path> visitedFiles = new HashSet<>();
    private HashSet<Path> visitedDirectories = new HashSet<>();
    private ArrayList<String> invalidPaths = new ArrayList<>();

    void visit(String pathStr) {
        Path path = createPathObject(pathStr);
        if (path == null) {
            return;
        }

        doVisit(path);
    }

    ArrayList<String> getInvalidPaths() {
        return invalidPaths;
    }

    HashSet<Path> getVisitedFiles() {
        return visitedFiles;
    }

    private void doVisit(Path path) {
        if (Files.isRegularFile(path)) {
            visitedFiles.add(path);
            return;
        }

        if (visitedDirectories.contains(path)) {
            return;
        } else {
            visitedDirectories.add(path);
        }

        try {
            Files.walk(path).forEach(this::doVisit);
        } catch (IOException e) {
            invalidPaths.add(path.toString());
        }
    }

    private Path createPathObject(String path) {
        try {
            return Paths.get(path);
        } catch (InvalidPathException e) {
            invalidPaths.add(path);
            return null;
        }
    }
}
