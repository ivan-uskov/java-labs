package com;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileWalker {
    @FunctionalInterface
    public interface Visitor {
        void visit(byte[] fragment, int fragmentSize);
    }

    private static final int BUFFER_SIZE = 63 * 1024;

    private Visitor visitor;

    FileWalker(Visitor v) {
        visitor = v;
    }

    public boolean walk(Path filePath) {
        if (!Files.isRegularFile(filePath)) {
            return false;
        }

        try {
            final FileInputStream stream = new FileInputStream(filePath.toString());
            final byte[] buffer = new byte[BUFFER_SIZE];
            int readSize;
            while ((readSize = stream.read(buffer)) != -1) {
                this.visitor.visit(buffer, readSize);
            }
            stream.close();
        } catch (IOException e) {
            return false;
        }

        return true;
    }
}
