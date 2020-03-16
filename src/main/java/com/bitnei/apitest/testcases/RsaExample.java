package com.bitnei.apitest.testcases;

import org.apache.commons.codec.binary.Base64;
import javax.crypto.Cipher;
import java.io.ByteArrayOutputStream;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.time.LocalDateTime;
import java.sql.Timestamp;

/** 
* @author 作者 hangang
* @version 创建时间：2020年3月11日 上午11:35:23 
* 类说明 
*/
public class RsaExample {


    /**
     * RSA最大加密明文大小
     */
    private static final int MAX_ENCRYPT_BLOCK = 117;

    /**
     * RSA最大解密密文大小
     */
    private static final int MAX_DECRYPT_BLOCK = 128;

    /**
     * 获取私钥
     *
     * @param privateKey 私钥字符串
     * @return
     */
    public static PrivateKey getPrivateKey(String privateKey) throws Exception {
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        byte[] decodedKey = Base64.decodeBase64(privateKey.getBytes());
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(decodedKey);
        return keyFactory.generatePrivate(keySpec);
    }

    /**
     * 获取公钥
     *
     * @param publicKey 公钥字符串
     * @return
     */
    public static PublicKey getPublicKey(String publicKey) throws Exception {
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        byte[] decodedKey = Base64.decodeBase64(publicKey.getBytes());
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(decodedKey);
        return keyFactory.generatePublic(keySpec);
    }

    /**
     * RSA加密
     *
     * @param data      待加密数据
     * @param publicKey 公钥
     * @return
     */
    public static String encrypt(String data, PublicKey publicKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        int inputLen = data.getBytes().length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offset = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段加密
        while (inputLen - offset > 0) {
            if (inputLen - offset > MAX_ENCRYPT_BLOCK) {
                cache = cipher.doFinal(data.getBytes(), offset, MAX_ENCRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(data.getBytes(), offset, inputLen - offset);
            }
            out.write(cache, 0, cache.length);
            i++;
            offset = i * MAX_ENCRYPT_BLOCK;
        }
        byte[] encryptedData = out.toByteArray();
        out.close();
        // 获取加密内容使用base64进行编码,并以UTF-8为标准转化成字符串
        // 加密后的字符串
        return new String(Base64.encodeBase64String(encryptedData));
    }


    /**
     * 签名
     *
     * @param data       待签名数据
     * @param privateKey 私钥
     * @return 签名
     */
    public static String sign(String data, PrivateKey privateKey) throws Exception {
        byte[] keyBytes = privateKey.getEncoded();
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey key = keyFactory.generatePrivate(keySpec);
        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initSign(key);
        signature.update(data.getBytes());
        return new String(Base64.encodeBase64(signature.sign()));
    }


    public static void main(String[] args) {
        try {


            String rsaPublicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCCmmHGgfjVDcWg6aP7Dm5YbshkLqMOCPBN+w7BFwVF2TSDYXZODhOMEqeytmMN+mZX4i5g+4ofyuJqjk4TLR84bFpMQ1s46oGMC+V1JtQOm1svxhee9uYlWgsPzyF01RMlEToPhpCTgyaUEgOjJNXqt4vssub5eDcyJ1nvaCWvmQIDAQAB";
            String signPrivateKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAMldj1iFaxTlvw0v8AVJSN/RlW2EuJbF2JIl7b33tX5J8X6nTA8cUwdMy1fgTjOKHzj0gYdU3w1S1W/iRe5I+4gPhMx/T4Ela+lIUx1tErQnz2tdJAmh4gq4bMb6rYWMcGoqDspSSrZ+a5AvWyFqjkMIIRhjkwuYKp1cWb5FYWAFAgMBAAECgYB2Timx8HEBThn5PXjfIjdWiGQqfBeXPZYIB5CuU6KmF/tyVggxuIpvd4bgkkBft36wj7aqNAr4YPpVSbOcU/Sy6NTfUEshOwQLOxvobLEyOg62//mB4USxqj3ENeNehYwZ5CXLnr9wK7ni/RSnFJAW5nz5flXOCZNkPVdxdwND4QJBAOdkGYoOiP4m1NosqZCjvcxKmkBc0Ntj7vXQ2364Omn4hxYjjcdvQg6qbzuOlxbSsGGtzGsqdSVlVeXrQS0jmscCQQDex/p2mAeybl3IryAjHHkTOnOCDBbts90BAP1ie7oHN8w0a2yYZnITPpxbu7Rx5YQjlDiWYqeUUtSRo9huUsLTAkAxPTHaGQG545WD3+EtcEqhQHbWn2mqZfehw5IRwy5bApHseiBfgiNyb35AFDW+m5MBFjTb0SsgjBHdXVR/QIZdAkBObPnYC/cRslajkjrvAVQCF96X1mev8FSJyO5qYAICDnt9rJ+DGvVnnu/uPyrruY+F8uTk1AAIcAxS5OoJFGtvAkEAhD6tTAMoaBWxQf2UP1TsQoE7WpPd7Rl/T0dUCgCJum2+8AU/8h/ygWv78iRJAHoyys9wz0zweQYgPD0q1q8qvw==";



            // RSA加密
            String data = "LGXC76C3XJ0108468";
            String encryptData = encrypt(data, getPublicKey(rsaPublicKey));
            //String encryptData = "PXoKTu0u4bmKCD82cH82lRG0aSqOTCbvVbuIMWWXLqDdxVxNHvP0mvv0tPyZcttcINgzn1YLw5iBgNerVWEbH8nFe6WRtUcX7BvhzfJ14oXGEUHapQ2xmDYG3n2Y27RHh1Na3/HxHU89mIXpEC+790sXLwWQrWlaupj+KGnWlP4=";
            System.out.println("VIN:  " + encryptData);
            System.out.println("licensePlate: " + encrypt("津AF05690",getPublicKey(rsaPublicKey)));

            // SHA256withRSA签名
            long s = Timestamp.valueOf(LocalDateTime.now()).getTime();
            String sign = sign(data + "_" + s, getPrivateKey(signPrivateKey));
            System.out.println("signature:  " + sign);
            System.out.println("timestamp:  " + s);
            System.out.println("licensePlate sign : "+sign("津AF05690"+"_"+s,getPrivateKey(signPrivateKey)));

        } catch (Exception e) {
            e.printStackTrace();
            System.out.print("加解密异常");
        }
    }

}
