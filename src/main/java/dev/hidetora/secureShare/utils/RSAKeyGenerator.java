package dev.hidetora.secureShare.utils;

import org.bouncycastle.util.io.pem.PemObject;
import org.bouncycastle.util.io.pem.PemWriter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class RSAKeyGenerator {
    public static void main(String[] args) throws NoSuchAlgorithmException, IOException {
        /*KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        generator.initialize(2048);
        KeyPair kpg = generator.generateKeyPair();

        File privateKey = new File(Constants.PRIVATE_KEY);
        File publicKey = new File(Constants.PUBLIC_KEY);

        FileUtil.writeToFile(privateKey, Base64.getEncoder().encode(kpg.getPrivate().getEncoded()));
        FileUtil.writeToFile(publicKey, Base64.getEncoder().encode(kpg.getPublic().getEncoded()));*/
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
        keyPairGen.initialize(2048, new SecureRandom());
        KeyPair keyPair = keyPairGen.generateKeyPair();

        // Store key pair in files
        PemWriter pemWriter = new PemWriter(new OutputStreamWriter(new FileOutputStream(Constants.PRIVATE_KEY)));
        pemWriter.writeObject(new PemObject("PRIVATE KEY", keyPair.getPrivate().getEncoded()));
        pemWriter.close();

        PemWriter pemWriter2 = new PemWriter(new OutputStreamWriter(new FileOutputStream(Constants.PUBLIC_KEY)));
        pemWriter2.writeObject(new PemObject("PUBLIC KEY", keyPair.getPublic().getEncoded()));
        pemWriter2.close();
    }

    public static KeyPair generateKeyPair() throws NoSuchAlgorithmException {
        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        generator.initialize(2048);
        return generator.generateKeyPair();
    }
}