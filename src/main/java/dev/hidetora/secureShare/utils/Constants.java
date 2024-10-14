package dev.hidetora.secureShare.utils;

public class Constants {
    private static final String FOLDER = System.getProperty("user.dir") + "/src/main/resources/";

    public static String PRIVATE_KEY = FOLDER + "certs/private.pem";
    public static String PUBLIC_KEY = FOLDER + "certs/public.pub";
    public static String FILE_TO_ENCRYPT = FOLDER + "test.pdf";
    public static String ENCRYPTED_FILE = FOLDER + "java_encrypt_test.pdf";
    public static String DECRYPTED_FILE = FOLDER + "java_decrypt_test.pdf";

    public static String PRIVATE_KEYS_DIR = FOLDER + "private_keys";
    public static final String ENCRYPTED_FILES_FOLDER = FOLDER + "encrypted_files";
}
