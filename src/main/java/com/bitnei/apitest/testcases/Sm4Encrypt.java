package com.bitnei.apitest.testcases;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.pqc.math.linearalgebra.ByteUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Security;

/**
 * @Author: zhanghailong@bitnei.cn
 * @Date: 2020/5/8 14:41
 */
public class Sm4Encrypt {

    private static final Logger log = LoggerFactory.getLogger(Sm4Encrypt.class);

    public static final String SM4_HEXKEY = "B13CC1A8D9CF88C92C8EFA805658DFF9";
    private static final String ALGORITHM_NAME_CBC_PADDING = "SM4/CBC/PKCS7Padding";
    private static int LENGTH = 16;

    /**
     * 生成国密Key：SM4，密钥为 128bit， 16byte
     */
    private static SecretKeySpec getSm4Key(byte[] key) {
        if (key.length != LENGTH) {
            log.warn("SM4's key should be 16bytes, 128bits");
        }
        return new SecretKeySpec(key, "SM4");
    }

    /**
     * 初始化向量
     *
     * @param len 长度
     * @return IvParameterSpec
     */
    private static IvParameterSpec getIv(int len) {
        byte[] zero = new byte[len];
        return new IvParameterSpec(zero);
    }

    /**
     * sm4加密
     *
     * @param hexKey   16进制密钥（忽略大小写）
     * @param paramStr 待加密字符串
     * @return 返回16进制的加密字符串
     * @explain 加密模式：CBC
     * 密文长度不固定，会随着被加密字符串长度的变化而变化
     */
    public static String encryptCbc(String hexKey, String paramStr) {
        String cipherText = "";
        // 16进制字符串-->byte[]
        byte[] keyData = ByteUtils.fromHexString(hexKey);
        // String-->byte[]
        byte[] srcData = paramStr.getBytes(StandardCharsets.UTF_8);
        // 加密后的数组
        byte[] cipherArray = encryptCbcPadding(keyData, srcData, "SM4/CBC/PKCS7Padding");
        // byte[]-->hexString
        cipherText = ByteUtils.toHexString(cipherArray);
        return cipherText;
    }

    /**
     * SM4 Cbc模式 加密
     *
     * @param key       密钥
     * @param data      明文
     * @param algorithm 加密模式+填充模式
     * @return 密文
     */
    private static byte[] encryptCbcPadding(byte[] key, byte[] data, String algorithm) {
        byte[] res = null;
        try {
            Security.addProvider(new BouncyCastleProvider());
            Cipher cipher = Cipher.getInstance(algorithm, "BC");
            SecretKeySpec secretKeySpec = getSm4Key(key);
            IvParameterSpec ivParameterSpec = getIv(cipher.getBlockSize());
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
            res = cipher.doFinal(data);
            return res;
        } catch (Exception e) {
            log.error("Fail: Sm4 Cbc Encrypt {1}", e);
        }
        return res;
    }

    /**
     * sm4解密
     *
     * @param hexKey     16进制密钥
     * @param cipherText 16进制的加密字符串（忽略大小写）
     * @return 解密后的字符串
     * @explain 解密模式：采用CBC
     */
    public static String decryptCbc(String hexKey, String cipherText) {
        // 用于接收解密后的字符串
        String decryptStr = "";
        // hexString-->byte[]
        byte[] keyData = ByteUtils.fromHexString(hexKey);
        // hexString-->byte[]
        byte[] cipherData = ByteUtils.fromHexString(cipherText);
        // 解密
        byte[] srcData = decryptCbcPadding(keyData, cipherData, ALGORITHM_NAME_CBC_PADDING);
        // byte[]-->String
        decryptStr = new String(srcData, StandardCharsets.UTF_8);
        return decryptStr;
    }

    /**
     * SM4 Cbc模式 解密
     *
     * @param key       密钥
     * @param data      密文
     * @param algorithm 加密模式+填充模式
     * @return 明文
     */
    public static byte[] decryptCbcPadding(byte[] key, byte[] data, String algorithm) {
        byte[] res = null;
        try {
            Security.addProvider(new BouncyCastleProvider());
            Cipher cipher = Cipher.getInstance(algorithm, "BC");
            SecretKeySpec secretKeySpec = getSm4Key(key);
            IvParameterSpec ivParameterSpec = getIv(cipher.getBlockSize());
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);
            res = cipher.doFinal(data);
            return res;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }
    
    public static void main(String[] args) {
    	System.out.println(Sm4Encrypt.decryptCbc(SM4_HEXKEY, "df905de61852a533ad21654680c37b90b1f0182858311071436d809d4d0156867dea18f3f467772f9c83888a83af3c3709fe10cae04186a968b9c73afdfd7a3f60459a9dc20242c8e48ceae15cbce3216017e409906fe48a58c7bdb30dcd7cdbee13f84f1b07e6ffa62db9088f5e2f0f1f81bc44b8e1ab1df3fb5845a1f129b3edd506f50cefabb42461305ce1978b41"));
    }


}
