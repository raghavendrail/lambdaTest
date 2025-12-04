package com.raghavendra.lambdatest.utils;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * Utility class for local encryption/decryption of secrets.
 * 
 * NOTE:
 * - Store the AES key as BASE64 in an environment variable (e.g. LT_SECRET_KEY)
 * - Do NOT commit the key to Git.
 * - Use this class to encrypt username/access key and store only encrypted values in lt-config.json.
 */
public class CryptoUtil {

    private static final String AES_ALGO = "AES";
    private static final String AES_TRANSFORMATION = "AES/GCM/NoPadding";
    private static final int GCM_TAG_LENGTH = 128;
    private static final int IV_LENGTH = 12;

    private CryptoUtil() {
        // utility class
    }

    private static SecretKey getKeyFromBase64(String base64Key) {
        byte[] keyBytes = Base64.getDecoder().decode(base64Key);
        if (keyBytes.length != 16 && keyBytes.length != 24 && keyBytes.length != 32) {
            throw new IllegalArgumentException("Invalid AES key length. Use 128/192/256-bit key.");
        }
        return new SecretKeySpec(keyBytes, AES_ALGO);
    }

    public static String encrypt(String plainText, String base64Key) {
        try {
            SecretKey key = getKeyFromBase64(base64Key);

            byte[] iv = new byte[IV_LENGTH];
            SecureRandom random = new SecureRandom();
            random.nextBytes(iv);

            Cipher cipher = Cipher.getInstance(AES_TRANSFORMATION);
            GCMParameterSpec spec = new GCMParameterSpec(GCM_TAG_LENGTH, iv);
            cipher.init(Cipher.ENCRYPT_MODE, key, spec);

            byte[] cipherBytes = cipher.doFinal(plainText.getBytes("UTF-8"));

            // store iv + cipher together
            byte[] ivPlusCipher = new byte[iv.length + cipherBytes.length];
            System.arraycopy(iv, 0, ivPlusCipher, 0, iv.length);
            System.arraycopy(cipherBytes, 0, ivPlusCipher, iv.length, cipherBytes.length);

            return Base64.getEncoder().encodeToString(ivPlusCipher);
        } catch (Exception e) {
            throw new RuntimeException("Error encrypting value", e);
        }
    }

    public static String decrypt(String encryptedText, String base64Key) {
        try {
            SecretKey key = getKeyFromBase64(base64Key);
            byte[] ivPlusCipher = Base64.getDecoder().decode(encryptedText);

            byte[] iv = new byte[IV_LENGTH];
            byte[] cipherBytes = new byte[ivPlusCipher.length - IV_LENGTH];

            System.arraycopy(ivPlusCipher, 0, iv, 0, IV_LENGTH);
            System.arraycopy(ivPlusCipher, IV_LENGTH, cipherBytes, 0, cipherBytes.length);

            Cipher cipher = Cipher.getInstance(AES_TRANSFORMATION);
            GCMParameterSpec spec = new GCMParameterSpec(GCM_TAG_LENGTH, iv);
            cipher.init(Cipher.DECRYPT_MODE, key, spec);

            byte[] plainBytes = cipher.doFinal(cipherBytes);
            return new String(plainBytes, "UTF-8");
        } catch (Exception e) {
            throw new RuntimeException("Error decrypting value", e);
        }
    }
}
