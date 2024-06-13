package com.tornado4651.lmix.common.utils;

import lombok.extern.slf4j.Slf4j;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

/**
 * @author tornado4651
 * @date 2024/5/16 21:34
 */
@Slf4j
public class SecurityUtils {

    private static final String ALGORITHM = "AES";
    private static final String TRANSFORMATION = "AES";

    // Replace with your own secret key. Make sure the length is 16 bytes for AES-128
    private static final String SECRET_KEY = "l-mix-cloud-security-key";

    private static SecretKeySpec secretKey = null;
    private static Cipher encrypt_cipher = null;
    private static Cipher decrypt_cipher = null;
    static {
        try {
            secretKey = new SecretKeySpec(SECRET_KEY.getBytes(), ALGORITHM);
            encrypt_cipher = Cipher.getInstance(TRANSFORMATION);
            encrypt_cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            decrypt_cipher = Cipher.getInstance(TRANSFORMATION);
            decrypt_cipher.init(Cipher.DECRYPT_MODE, secretKey);
        } catch (Exception e) {
            log.error("密钥初始化失败！原因：{}", e.getMessage());
        }
    }
    public static String encrypt(String originalStr) {
        try {
            byte[] encryptedBytes = encrypt_cipher.doFinal(originalStr.getBytes());
            return Base64.getEncoder().encodeToString(encryptedBytes);
        } catch (Exception e) {
            log.error("AES加密算法加密异常！原字符串：{}", originalStr);
            e.printStackTrace();
            return null;
        }
    }

    public static String decrypt(String encryptedStr) {
        try {
            byte[] decodedBytes = Base64.getDecoder().decode(encryptedStr);
            byte[] decryptedBytes = decrypt_cipher.doFinal(decodedBytes);
            return new String(decryptedBytes);
        } catch (Exception e) {
            log.error("AES加密算法解密异常！解密字符串：{}", encryptedStr);
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        String originalString = "123456";
        String encrypt = encrypt(originalString);
        String decrypt = decrypt(encrypt);

        System.out.println("Original String: " + originalString);
        System.out.println("Encrypted String: " + encrypt);
        System.out.println("Decrypted String: " + decrypt);
    }

//    /**
//     *  DES加密算法密钥
//     */
//    private static final String DES_KEY_VAL = "l-mix-cloud-des-key";
//
//    /**
//     * 加密编码
//     */
//    private static final String KEY_UNICODE = "utf-8";
//
//    /**
//     * DES加解密算法 钥匙
//     */
//    private static Cipher DECRYPT_CHIPHER;
//    private static Cipher ENCRYPT_CHIPHER;
//
//    static{
//        try {
//            KeyGenerator generator = KeyGenerator.getInstance("DES");
//            generator.init(new SecureRandom(DES_KEY_VAL.getBytes())); // 根据参数生成key
//            Key DES_KEY = generator.generateKey();
//
//            // Cipher对象实际完成加解密操作
//            DECRYPT_CHIPHER = Cipher.getInstance("DES");
//            DECRYPT_CHIPHER.init(Cipher.DECRYPT_MODE, DES_KEY);
//
//            ENCRYPT_CHIPHER = Cipher.getInstance("DES");
//            ENCRYPT_CHIPHER.init(Cipher.ENCRYPT_MODE, DES_KEY);
//
//        } catch (Exception e) {
//            log.error("初始化DES密钥失败......原因: {}", e.getMessage());
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * 加密
//     * @param source 编码内容
//     * @return 加密后的内容，null表示加密失败
//     */
//    public static String encrypt(String source) {
//        String encrypt;
//        try {
//            byte[] ret = ENCRYPT_CHIPHER.doFinal(source.getBytes(KEY_UNICODE));
//            encrypt = new String(Base64.getEncoder().encode(ret));
//        } catch (Exception e) {
//            log.error("DES加密算法加密异常！原因:{}", e.getMessage());
//            e.printStackTrace();
//            encrypt = null;
//        }
//        return encrypt;
//    }
//
//
//    /**
//     * 解密
//     * @param encryptedData 解码内容
//     * @return
//     */
//    public static String decrypt(String encryptedData) {
//        String descryptedData;
//        try {
//            byte[] ret = DECRYPT_CHIPHER.doFinal(Base64.getDecoder().decode(encryptedData.getBytes()));
//            descryptedData = new String(ret, KEY_UNICODE);
//        } catch (Exception e) {
//            log.error("DES加密算法解密异常！原因:{}", e.getMessage());
//            e.printStackTrace();
//            descryptedData = null;
//        }
//        return descryptedData;
//    }
}
