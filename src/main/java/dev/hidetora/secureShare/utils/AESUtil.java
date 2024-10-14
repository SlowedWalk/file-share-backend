package dev.hidetora.secureShare.utils;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AESUtil {
    public static SecretKey generateAESKey() throws NoSuchAlgorithmException {
        KeyGenerator generator = KeyGenerator.getInstance("AES");
        generator.init(256);

        return generator.generateKey();
    }

    public static SecretKey getAESKey(byte[] keyBytes){
        return new SecretKeySpec(keyBytes, "AES");
    }

    public static IvParameterSpec getIVParams(byte[] ivBytes){
        return new IvParameterSpec(ivBytes);
    }

    public static byte[] encryptFile(SecretKey key, byte[] content) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

        SecureRandom rnd = new SecureRandom();
        byte[] iv = new byte[cipher.getBlockSize()];
        rnd.nextBytes(iv);
        IvParameterSpec ivParams = new IvParameterSpec(iv);
        cipher.init(Cipher.ENCRYPT_MODE, key, ivParams);

        byte[] encryptedContent = cipher.doFinal(content);

        return FileUtil.combineBytes(ivParams.getIV(), encryptedContent);
    }

    public static byte[] decryptFile(SecretKey key, IvParameterSpec iv, byte[] content) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, key, iv);

        return cipher.doFinal(content);
    }
}
