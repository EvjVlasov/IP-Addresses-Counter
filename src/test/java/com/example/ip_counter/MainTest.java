package com.example.ip_counter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MainTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    String[] args = new String[1];

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outContent));
    }

    @Test
    void successTest() {
        String file = "src/main/resources/test.txt";
        String successMessage = "Unique IP addresses in file src/main/resources/test.txt: 73872";

        args[0] = file;
        Main.main(args);
        assertEquals(successMessage, outContent.toString().replace("\r\n", ""));
    }

    @Test
    void errorFileTest() throws RuntimeException {
        String notExistFile = "src/main/resources/not_exist.txt";
        String errorFileMessage = "File src/main/resources/not_exist.txt is not found";

        args[0] = notExistFile;
        RuntimeException thrown = assertThrows(RuntimeException.class, () -> Main.main(args));
        assertEquals(errorFileMessage, thrown.getMessage());
    }

    @Test
    void errorDataTest() throws RuntimeException {
        String invalidFile = "src/main/resources/wrong.txt";
        String errorDataMessage = "File src/main/resources/wrong.txt contains invalid data";

        args[0] = invalidFile;
        NumberFormatException thrown = assertThrows(NumberFormatException.class, () -> Main.main(args));
        assertEquals(errorDataMessage, thrown.getMessage());
    }
}