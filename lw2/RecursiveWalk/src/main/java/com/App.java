package com;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class App {
    @FunctionalInterface
    public interface Liner {
        void accept(String t);
    }

    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("Expected arguments: <input file> <output file>");
            System.exit(1);
        }

        try {
            FileSystemWalker walker = new FileSystemWalker();
            forEachLine(args[0], walker::visit);
            walker.getVisitedFiles().forEach(App::processFile);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }

    private static void processFile(Path path) {
        FNVHasher hasher = new FNVHasher();
        FileWalker walker = new FileWalker(hasher::add);
        if (walker.walk(path)) {
            System.out.println(hasher.getHash() + " " + path.toString());
        }
    }

    private static void forEachLine(String filePath, Liner liner) {
        try {
            Stream<String> stream = Files.lines(Paths.get(filePath));
            stream.forEach(liner::accept);
        } catch (IOException e) {
            throw new RuntimeException("Read input file failed ");
        }
    }
}