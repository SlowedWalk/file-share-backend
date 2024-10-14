package dev.hidetora.secureShare.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Slf4j
public class FileUtil {
    public static void writeToFile(File file, byte[] fileContent) {
        log.info("Writing bytes to file...");
        Path path = Paths.get(file.getAbsolutePath());
        try {
            Files.write(path, fileContent);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        log.info("Done writing bytes to file...");
    }

    public static byte[] readBytesFromFile(File file) {
        log.info("Reading bytes from file...");
        Path path = Paths.get(file.getAbsolutePath());
        byte[] data = new byte[0];
        try {
            data = Files.readAllBytes(path);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        log.info("Done reading from file...");
        return data;
    }

    public static byte[] combineBytes(byte[] a, byte[] b){
        byte[] combined = new byte[a.length + b.length];
        System.arraycopy(a, 0, combined, 0, a.length);
        System.arraycopy(b, 0, combined, a.length, b.length);

        return combined;
    }

    public static String readPublicKeyFromFile(String filePath) throws IOException {
        log.info("Reading public key from file...");
        StringBuilder publicKeyStringBuilder = new StringBuilder();

        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                publicKeyStringBuilder.append(line).append("\n");
            }
        }
        return publicKeyStringBuilder.toString();
    }
}

