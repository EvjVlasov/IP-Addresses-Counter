package com.example.ip_counter;

public class Main {

    public static void main(String[] args) {
        String filePath = "";

        if (args.length != 0) {
            filePath = args[0];
        }

        long result = FileReaderUtil.count(filePath);
        System.out.println("Unique IP addresses in file " + filePath + ": " + result);
    }
}
