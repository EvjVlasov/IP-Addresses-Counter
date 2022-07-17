package com.example.ip_counter;

import org.junit.jupiter.api.Test;

import static com.example.ip_counter.Constants.ipAddrOpt;
import static org.junit.jupiter.api.Assertions.assertEquals;

class FileReaderUtilTest {

    @Test
    void testNumLine() {
        String filePath = "src/main/resources/test.txt";
        assertEquals(73873L, FileReaderUtil.readFile(filePath));
    }

    @Test
    void getNumUnqIp() {
        ipAddrOpt[217][153][163][188] = true;
        ipAddrOpt[217][153][163][189] = true;
        ipAddrOpt[217][153][163][190] = true;
        assertEquals(3L, FileReaderUtil.getNumUnqIp());
    }
}