package org.thraex.security.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.security.authentication.InternalAuthenticationServiceException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.IntStream;

/**
 * @author 鬼王
 * @date 2020/09/27 11:31
 */
@ConfigurationProperties("thraex.security.crypto")
public class CryptoProperties {

    public static final String CHARSET = "UTF-8";

    private String key = "1BA9311D7AD0434A";

    private String iv = "A539C0C928217AB2";

    public String decrypt(String raw) {
        try {
            SecretKey secretKey = new SecretKeySpec(key.getBytes(CHARSET), "AES");
            IvParameterSpec ivParameter = new IvParameterSpec(iv.getBytes(CHARSET));
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, secretKey, ivParameter);
            byte[] hexBytes = hexStringToByte(raw);
            byte[] plainBytes = cipher.doFinal(hexBytes);

            return new String(plainBytes, CHARSET);
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException
                | InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException e) {
            throw new InternalAuthenticationServiceException("Username and password resolution failed", e);
        }
    }

    public static byte[] hexStringToByte(String hex) {
        return Optional.ofNullable(hex).map(String::toUpperCase).map(h -> {
            byte[] b = new byte[h.length() / 2];
            IntStream.range(0, b.length).forEach(i -> {
                int pos = i * 2;
                b[i] = (byte) Integer.parseInt(h.substring(pos, pos + 2), 16);
            });

            return b;
        }).orElse(null);
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getIv() {
        return iv;
    }

    public void setIv(String iv) {
        this.iv = iv;
    }

    public static void main(String[] args) {
        String uuid = UUID.randomUUID().toString().replace("-", "").toUpperCase();
        int middle = uuid.length() / 2;
        System.out.println(uuid);
        System.out.println(uuid.substring(0, middle));
        System.out.println(uuid.substring(middle));
    }

}
