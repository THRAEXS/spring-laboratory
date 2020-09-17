package org.thraex.aes;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * @author 鬼王
 * @date 2020/09/15 15:44
 */
public class Test {

    private static String ALGORITHM = "AES";
    private static String PROVIDER = "AES/CBC/PKCS5Padding"; //算法/模式/补码方式

    public final static String KEY = "143127d371244133";
    private static String IV = "a21bc8ffeb3857c3";

    public static void main(String[] args) {
        // byte key[] = generatorKey();
        //System.out.println(UUID.randomUUID());
        //System.out.println(UUID.randomUUID().toString().replace("-", ""));
        //String ki = UUID.randomUUID().toString().replace("-", "");
        //System.out.println(ki);
        //System.out.println(ki.substring(0, ki.length() / 2));
        //System.out.println(ki.substring(ki.length() / 2));

        //System.out.println("FUjs@17654HGJKKn".length());
        try {
            // 密钥必须是16的倍数
            //byte[] key = "FUjs@17654HGJKKn".getBytes("utf-8");
            //String src = "usersrs=111111?sdfjsalkj=1mlkjklasjdfkls?sss=sdfsjlk1123123123?sdd=453456465432165765432221351567897654132";
            String src = "Xukun1981!@#";
            byte[] encrypt = encrypt(src, KEY);
            System.out.println(new String(encrypt));
            //System.out.println("密钥:"+byteToHexString(KEY));
            //System.out.println("原字符串:"+src);
            //String enc = byteToHexString(encrypt(src, KEY));
            //System.out.println("加密："+enc);
            System.out.println("解密："+decrypt(byteToHexString(encrypt), KEY));

            System.out.println("********************");
            //String raw = "98090eccdd99702f61acdf13f80f304d";
            ////String raw = "de0023ff5bbe11b887c23fdc4ba64845";
            String raw = "b5ad7f9b42c0432fa233adb523a9a289";
            System.out.println("解密：" + decrypt(raw, "143127d371244133"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static byte[] generatorKey() throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(ALGORITHM);
        keyGenerator.init(256);//默认128，获得无政策权限后可为192或256
        SecretKey secretKey = keyGenerator.generateKey();
        return secretKey.getEncoded();
    }

    public static IvParameterSpec getIv() throws UnsupportedEncodingException {
        IvParameterSpec ivParameterSpec = new IvParameterSpec(IV.getBytes("utf-8"));
        System.out.println("偏移量：" + byteToHexString(ivParameterSpec.getIV()));
        return ivParameterSpec;
    }

    public static String decrypt(String src, String keyStr) throws Exception {
        byte key[] = keyStr.getBytes("utf-8");
        SecretKey secretKey = new SecretKeySpec(key, ALGORITHM);

        IvParameterSpec ivParameterSpec = getIv();
        Cipher cipher = Cipher.getInstance(PROVIDER);
        cipher.init(Cipher.DECRYPT_MODE, secretKey, ivParameterSpec);
        byte[] hexBytes = hexStringToBytes(src);
        byte[] plainBytes = cipher.doFinal(hexBytes);
        return new String(plainBytes,"UTF-8");
    }


    public static byte[] encrypt(String src, String keyStr) throws NoSuchAlgorithmException, NoSuchPaddingException,
            InvalidKeyException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException, InvalidAlgorithmParameterException {

        byte key[] = keyStr.getBytes("utf-8");
        SecretKey secretKey = new SecretKeySpec(key, ALGORITHM);
        IvParameterSpec ivParameterSpec = getIv();
        Cipher cipher = Cipher.getInstance(PROVIDER);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivParameterSpec);
        byte[] cipherBytes = cipher.doFinal(src.getBytes(Charset.forName("utf-8")));
        //return new String(cipherBytes, "UTF-8");
        return cipherBytes;
    }

    public static String byteToHexString(byte[] src) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xff;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                sb.append("0");
            }
            sb.append(hv);
        }
        return sb.toString();
    }

    /**
     * 将16进制字符串装换为byte数组
     * @param hexString
     * @return
     */
    public static byte[] hexStringToBytes(String hexString) {
        hexString = hexString.toUpperCase();
        //char[] hexChars = hexString.toCharArray();
        byte[] b = new byte[hexString.length() / 2];
        for (int i = 0; i < b.length; i++) {
            int pos = i * 2;
            //char c = hexChars[pos];
            //b[i] = (byte) (charToByte(hexChars[pos]) << 4 | (charToByte(hexChars[pos + 1]))& 0xff);
            int j = Integer.parseInt(hexString.substring(pos, pos + 2), 16);
            b[i] = (byte) j;
        }
        return b;
    }

    private static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }

    public static void tt() {
        String str = "p";
        byte[] val = new byte[str.length() / 2];
        for (int i = 0; i < val.length; i++) {
            int index = i * 2;
            int j = Integer.parseInt(str.substring(index, index + 2), 16);
            val[i] = (byte) j;
        }
        System.out.println(val);
    }

}
