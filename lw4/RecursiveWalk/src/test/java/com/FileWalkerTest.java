package com;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

import static org.junit.jupiter.api.Assertions.*;

class FileWalkerTest {
    @Test
    void fileWalkerShowsFileContents() {
        final String VALUE = "12345!";
        File f = Utils.nullSafe(Utils.exceptionSafe(() -> File.createTempFile("test", ".temp")));
        FileWriter fileWriter = Utils.exceptionSafe(() -> new FileWriter(f.toString()));
        PrintWriter printWriter = new PrintWriter(fileWriter);
        printWriter.print(VALUE);
        printWriter.close();

        FileWalker walker = new FileWalker((byte[] buffer, int bufferSize) -> {
            assertEquals(VALUE, bytesToString(buffer, bufferSize));
        });
        assertTrue(walker.walk(f.toPath()));
    }

    private String bytesToString(byte[] bytes, int length) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < length; ++i) {
            sb.append((char) bytes[i]);
        }

        return sb.toString();
    }
}