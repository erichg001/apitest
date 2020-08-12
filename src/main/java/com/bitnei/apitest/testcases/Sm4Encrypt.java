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

    public static final String SM4_HEXKEY = "C18A0B56421F88726A18AC056D6F16FD";
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
    	System.out.println(Sm4Encrypt.decryptCbc(SM4_HEXKEY, "370585d6779d46a72f7216e3da96b575afa1ce1021ce372dafceb0aba98045f86dc44b79e0d053aa3010d5ada9c6a25c0f8504f0903ffdb76b76ac921fdbc0388f2c2e88f9d6396c0c0461f514c29b7543b640e3161947c38bef53342edf33224ba5df85cb4c32542fd60b83487cd81e3cbdaf4bdd35abb86eae9a9a810e9e2e4648571394ec9b978e7d82106fc8944bedb98899dff5bb65b947de895f722f5ce987424859a63156133ea8c046ddbbfe20bdfae39ba7c4fc7f0349a0f229bd0dcdb66cda8e51f1fad6a621ea81940842e9280e48e30efc3a181bfac904718d55437b734e15488d2f03a22b2f2b0fd89fbdb7b7f6cf81bf9d69458eed4468e16fd9a3ab866324244fa06293ba5fecdd5ef311f2e6c88121b29410b12c4ba8dcea3491470d8cb06194a6f1d31b79221723725606ee8651c8b5ff52467c5b13298cc4014a7e3663511a86bcf889f877f38ea5576cb541cdb21e338a94463874a5e78480b112e7d193bbd5bc44e2b8828bbb67e5b49244d0339e7f72abc60c5c2f1a545fe73f15ceb0e98158afbb7a174799233d6c8f9f13a06d4f47c5cc9e75e226f62ea1d632206afe7a3e962444c8a77c03c9e5fea1c7c47089a40c7dce4a2b27524ce027e2f1cdc7a535a11d7938bb6c37313ba7a359f79d5ab46f05188eb0e313756ce62b817c4d5d8e38c2589806cc79ce6827cd72aee16cf8bc4059ee4b1543fa469f85e1cf0499c44cc1673951ea42e9fa18047f1a57bb4e81091bf870d2ad66c321cab4c744e2940e59f166b38acdd8a1bfc8636a3ce978fe7110ba090c68aa8e6fdd68c3b036905850d4895c3db0b9f4d5f42f7f0723b7fb6352d2877562e93f5b98387160b5413a5115c174589cbac1ec59cfe5194b56b7aa73cf18a9b6621d59da1ec52e8fecf169f1200a78847169827516fcd801b2549945e704bc6a51874aabc59a1d5892656c5e7b098f3cbc84dc111b78f86f58542e5dc459f0496039c5278432f92df7c6b26d462d0177ffd4c94cbe37f4f8101bdd8713b1227effb9343c5d379089d0ee14868fc022e9d73b134ebdad3d5fef4d5f9c8d09b0959f7f9c2a24c145be2e5dad96385dcc0736f6e01a97e3b08d798b4ff72db988c8e42448104ad1b9d94a7334b84fc6981a1775cd7b248628cb558c0f23a866535ba79cd29bf44583b4167915a7abd775eb27ffc8032aa2132376dbefb4efcb62d816cc41070c3726ee00dca6a40a5bc1bede6b914b6e7c7753c7e8b43ab929251dce337aade974ae17dc0bbb7dfb2be82b7457fcaad75a3854ee52bb75d9f15d66a9b9786c77b85200194a4f8f5d50337967456d10cb2422a6e2acb35001d2c826633493fcf18e1b954717632440bda4f21eb4c04d07a60ae6b6f4059e7e56a891761669596cbcab15a8d962fef9e4d1ac402f265bb8bed3f4ea3311976e35ce2edbb1bcf88c9c971f61e9b3c947a2e7305d233cb45e2de9db3af08d5275c1e1052494676164315daf3a17f3f64fdbc78c59e14fad6b2290e241622f8d877fb89f258873d076b34cdd9fbcf3cb993b6ef1cce39caae59df568f431c077063f9f409a10ab6dbd23c7d768c6383a777d7a815107ae0e80a3331cde22aae2f4ceb1013cb6efa920de566194612dd94806f95494b66f9fa1fb365e46d3d64da2f09a3d6dabbe2f6b29942e7e9a3e8e21dde8731138247d8f3d919dec3958a3c511282150c12d25e6dc61913e0f25289e8ebe74b89532454e454b3365846a84d4b929ca0d9cf1cdc3462ecb2354c5c2560cb4e1c3dfc024e902b7bd9515d4141c97514d9a5b37589eefe844349f6e65e9714e9a1af313db779f183941b4303afcdcb7c46689f7d4f64251b2db20df7c22623950d7c66e08b1f478334162bf03f276bb766a919c8e3665bdadf030bdc9b8e240d77c35f2f05812c8376763e8bb4a50efbc323ee73dd8a38d2ee54e495abc257fccce7c6586423032f7770d2115351acba07ad21a56b685e3292a1fd25ff1d50fce86abcbb2ae36ca032cf950e6c2c76d75c3ba4c4ef14b0a69504a3ed699690c79c77f88ebd8e1d610dc761ffef486b5af7c4d26b1c5085b97f2f9a3c4fde2a416fc23420aa148e03da251c48f2cdb34414b96b9185874cf88527d0a299b646f42f14e837080a27a7d3bc3c12bda93edc2c41808363bdb6e3e5a42b94d788ea50424aa12426c30288c12890affbd036ce83c2aa092f74daf9aa7b515463c32ad92fe45a3fb164610b13ae06a8715215c188cb6c0cbb09c6d3f528e3205f8b420a067987650795ea6d4608299f00e9f51e15d7f874cd37a5226223cdd6da9d71e397c873ce0cd26cf7d62391b208532578aeca20d1b79645d363a4818c4560862049fe3b7757aa3f0f895a9d1927e32dbe612a1bfb1e6996b4cf98a9bebc45de76b11d9b33ffc0a736099a64d568d68565abc15e6afe7862217743fb244363d5363caa740a46243e4534935bf38e61d0d149b287d5328352b38ecdac42395f9caa7ff290a2d7baa38941ccd4ba22d411095a065b85374dc6d2e734f5d1212698a58f8f3e25e582ade7c68be4b0aa55fcad683cd57135b6716161b424dbf2de0b9469588f723059702c60d9101b13b7b12d13aa1761e1cf65164f08ff1ab8830b71ceaf1e8fb9e9e25af89ebf1d9bea221d1217c8841baf7fc25ab880a217e604954a381c87292908f2d448658c7e7f8c1576afa07cb745181edeb9ea158faf4cca36541bd4cf84af4af6d16553855cb0e2308e65d4cf27d6b520fc9d72ac84113d29a3317804ca9320566ba6a94079b6a5e6bea267f0d1d7714320dcabccf5068c5c214579d6512e3d86da6dcb3000a8f746cfd4a9cc4c226db81ea03cada05ab424f450554139c029eb4b6a5994d2d7b1c3215a03cd9daa3acd602e5d0563adacac2d600f2063c064e2e40e7dadfefacf4b6eec777459d57b47a6f17721e4f545ad34f2a17f03d6f86ada59ac229a6997a31819da0a50d66253964a97726ab66c3cf50c58cb702976eb9e8c5f498e46c99f0b4cf53b05f3899cfb22da608fb0fbeb1e482e89cb26fd04b2fb1274e752e19f503fc40b6632a7485fd7593d256d86625bc51aec1fbba0787c3e21ff37df4c1ce47d836e565e0530714374866c64f1670f78763848f8d7c3841d38ede82aa5d5979222edc05d21fbf54a57bc375673e52c46cff69f3496262988a88453b7be048b1e22d3eb5e4a46a5dd2d8f2de849c31b2452a89c95ef5824977ea68063af9204b7ae48d783184150f2eebf7f599c5f65ed88a03ffa96efda5ffb88b88784ea1d76301029cb41c921a951274e500c79ecfdc0ab19f366fb3983a584eb1bc10cd23bf339871df76bef748b2f539ede3d04c3e578d0b8871b48a787ef99d0f9550a7adf73a51e3be54459181ba2438e601dc18f9b44fcf867b8b9d70adbf302728dc9de39dafb2f151ae950eeaded04c5019fed5a984838a78b890d96c021761fb6c6f90c7022cedaae28a988e7f1086ff1ef48d7efa2e5e68ae34f53a9d501224c0658506c7d3884de4dd6d514b817bf0e694a1ecd4158bb847e77553b32c2b29cb4570af909f22c8380a20226f7017dfbca1c41da266db1167cd06474030175e33798ea52c96d107c46a52ec2265b1afe731b96428d9f98dace418e76fa71e50b6f927c101d7408f307931e4b1396c6379208d26755f1b68fb006fd6a6a133bcdaa79db1f88617ea56871466c2b7662638210537a63de64fcac6f5a78d54da3a9e0aec65952dba3264697ff9df87fcad3b8856b4393d1b97bcf32c0e5ccb0361e98cd5100fa27b7e246afe7bc73f3aecafe1280cf74eb5a6e90bc9f16e4cbd6fbaa07767caa6325371b3a3b23f706234ff0582dae91295d9883dd287a3f4daef181cb53c4614df1a7b73946ea3c92727c8a53a3262636c706f13d483bc7f99c92764470b75b9641274dccd2f8c685aa18d0566b7bdbfa996f2b649485dbeb581568812c91fef36ddd6f1f730bc6f668d496f1bcf418ac6be7fd351b93529a61807fcd5ccff2720ecafb54d4e7e64ccd752fab0cd89b2fa43f91161c574391d1266812b2bb2054709545961c9592facf4ff387e81157f4c14ed25d2a97b232026af6007b66dfff98744284db30040fe21679f9d29d07e25968c76ac622ab1837e06ee7a0a942c36e9622aeed85735ac84ebc54560b248277c7007171b30301f22a1ece888e4de2f48ce2a837e2d4ab40ee8c1ed3251d6906453da4688fc1be17ff0c83f016a5d70901ca78546f77d3f4ea58ee3fe77420a2d21a33fb01e05b6719715e5ea389fd1968a38f5f821e4b537242af1f51875b4c1bc42f9d1e071f4dae637219d84fb87b5c6c21698b69f1b9b0c382423f4beed1ee882c78c7f2f723718cf57b371ef7da870a0f799e6adcca6e1282142bdac0de240fedc5951fe224234ce2dd46788dbb21cea639790771737f4b03f5a87ce5a924b42e04eb9fd814bbae444b9812bad26ab129d9803ad7dccb5c02f2607c32d2b557bb548685aa638da599270427199768b3d7d3237d84356e0b6b189530386ad3b1d4678b0ea99e87fd71d5211147bd1fa045634e29ceaf36a0d2755071b19c04a28989880eff7f6ea0fa73c166c1a2e6265643edd184e00fb1ac9f798fbf7e77a7b0917b101188aa05652a685f48ba265dc9ebc7d06dff2bdc41c2efaaec1459cc71059050b763c8a77e6e78aac976b876f5e642dfb3989fb042ef4dca9c12ee5fe921fc97954d15f2a23c7189ff264c33c8b10907aebcb429f28376208386dbeeb80ca43996b31bb0675dd2777dec5035153112f1b34c468ad3de6b270fd109f4bf497d35ca921ff39b9318baa3fa9bf11f578687d4b2048f0ada96fe76d76ee5ead29e243dc712c862416d0ab3e4631ef92cc0dfc6b13cb4c572c49283371f38b71472087277d5f75994d329047b775f4391483ad3185da8b21278c069ed5b5553caf7694cf685beb5c324ef2686781ffa4fed0fda90ddae0d2d1e206cdf4610b1f115331e4d458dfbbc7b8e8c0373558ae2d3e1f5b2ad602ed019f38941aeeaba5d4ffec06b91ac55f482c0dc4904ed4af3e7bb17f95f5f2f1d9dfd3e7640092af12ce9759cf08ecf2487aff63c179f0fc9a0cff2653d74ad63026ec9b553873b42dc291cd7244d4ac9807b26e446583f2f7157ec964787aeb8d4da8b2560d121af12630322d2d1aa6eecba91f8a4fe9f129773f962cfdbb70ad069c8745424036fec1ff"));
    }


}
