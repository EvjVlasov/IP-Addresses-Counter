package com.example.ip_counter;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import static com.example.ip_counter.Constants.OCTET_SIZE;
import static com.example.ip_counter.Constants.ipAddrOpt;


public class FileReaderUtil {

    public static long count(String filePath) {
        long lineCounter = readFile(filePath);

        if (lineCounter == 1) {
            return 1L;
        } else if (lineCounter < 1) {
            return 0L;
        }

        return getNumUnqIp();
    }

    public static long readFile(String filePath) {
        long lineCounter = 0L;

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(filePath)))) {

            String line;

            while ((line = reader.readLine()) != null) {
                String[] octets = line.split("\\.");
                addIpValue(octets);
                lineCounter++;
            }

        } catch (IOException e) {
            throw new RuntimeException("File " + filePath + " is not found");
        } catch (NumberFormatException e) {
            throw new NumberFormatException("File " + filePath + " contains invalid data");
        }

        return lineCounter;
    }

    private static void addIpValue(String[] octets) {
        ipAddrOpt[Short.parseShort(octets[0])][Short.parseShort(octets[1])]
                [Short.parseShort(octets[2])][Short.parseShort(octets[3])] = true;
    }

    public static long getNumUnqIp() {
        long ipCounter = 0L;

        for (short oc1 = 0; oc1 < OCTET_SIZE; oc1++) {

            for (short oc2 = 0; oc2 < OCTET_SIZE; oc2++) {

                for (short oc3 = 0; oc3 < OCTET_SIZE; oc3++) {

                    for (short oc4 = 0; oc4 < OCTET_SIZE; oc4++) {

                        if (ipAddrOpt[oc1][oc2][oc3][oc4]) {
                            ipCounter++;
                        }
                    }
                }
            }
        }

        return ipCounter;
    }
}
