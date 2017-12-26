package com;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class App {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("Expected arguments: <input file> <output file>");
            System.exit(1);
        }

        try {
            Stream<String> stream = Files.lines(Paths.get(args[0]));
            FileSystemWalker walker = new FileSystemWalker();
            stream.forEach(walker::visit);

            PrintStream out = new PrintStream(new FileOutputStream(args[1]));
            walker.getVisitedFiles().forEach(getFileProcessor(out));
            walker.getInvalidPaths().forEach(getInvalidPathProcessor(out));
        } catch (Exception e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }

    private static Consumer<String> getInvalidPathProcessor(PrintStream out) {
        return (String path) -> {
            out.printf("invalid path - %s\n", path);
        };
    }

    private static Consumer<Path> getFileProcessor(PrintStream out) {
        return (Path path) -> {
            FNVHasher hasher = new FNVHasher();
            FileWalker walker = new FileWalker(hasher::add);
            if (walker.walk(path)) {
                out.printf("%h - %s\n", hasher.getHash(), path.toString());
            }
        };
    }
}