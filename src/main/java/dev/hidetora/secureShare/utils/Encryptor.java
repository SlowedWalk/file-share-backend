package dev.hidetora.secureShare.utils;

import javax.crypto.SecretKey;
import java.io.File;
import java.io.IOException;
import java.security.PublicKey;

import static dev.hidetora.secureShare.utils.FileUtil.readPublicKeyFromFile;

public class Encryptor {
    public static void main(String[] args){
        File publicKeyFile = new File(Constants.PUBLIC_KEY);
        String publicKeyFromFile;
        try {
            publicKeyFromFile = readPublicKeyFromFile(publicKeyFile.getPath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        File input = new File(Constants.FILE_TO_ENCRYPT);
        File output = new File(Constants.ENCRYPTED_FILE);

        byte[] content = FileUtil.readBytesFromFile(input);
        try {
            // 1. Generate AES key
            SecretKey key = AESUtil.generateAESKey();

            // 2. Encrypt file content
            byte[] encryptedContent = AESUtil.encryptFile(key, content);

            // 3. Encrypt AES key
//            PublicKey publicKey = RSAUtil.getPublicKey(Base64.getDecoder().decode(FileUtil.readBytesFromFile(publicKeyFile)));
            PublicKey publicKey = RSAUtil.parsePublicKey(publicKeyFromFile);
            byte[] encryptedKey = RSAUtil.encryptKey(publicKey, key.getEncoded());

            // 4. Attach encrypted key to file
            byte[] fileOutputContent = FileUtil.combineBytes(encryptedKey, encryptedContent);

            // 5. Write to output
            FileUtil.writeToFile(output, fileOutputContent);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
