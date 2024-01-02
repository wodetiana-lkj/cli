package top.tsview.demo.utils;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.jose4j.jws.AlgorithmIdentifiers;
import org.jose4j.jws.JsonWebSignature;
import org.jose4j.keys.HmacKey;
import org.jose4j.lang.JoseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

@Slf4j
@Configuration
public class JwtUtil {

    // 256bit => 32byte
    @Bean
    public Key hmac256Key(@Value("${jwt.salt}") String salt) {
        return new HmacKey(salt.getBytes(StandardCharsets.UTF_8));
    }

    public static String getToken(Object obj) {
        JsonWebSignature jws = new JsonWebSignature();
        setHMAC256(jws);
        // 设置 payload
        jws.setPayload(obj.toString());
        try {
            return jws.getCompactSerialization();
        } catch (JoseException e) {
            log.error("serializable jwt error", e);
            // TODO 自定义实现error
            throw new RuntimeException();
        }
    }

    public static <T> T verifyToken(String token, Class<T> aClass) {
        if (!StringUtils.hasText(token)) {
            return null;
        }

        JsonWebSignature jws = new JsonWebSignature();
        setHMAC256(jws);
        try {
            jws.setCompactSerialization(token);
            if (jws.verifySignature()) {
                Gson gson = SpringUtil.getBean(Gson.class);
                return gson.fromJson(jws.getPayload(), aClass);
            } else {
                // TODO 自定义认证异常
                throw new RuntimeException();
            }
        } catch (JoseException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * HMAC256 设置头部
     */
    private static void setHMAC256(JsonWebSignature jws) {
        jws.setAlgorithmHeaderValue(AlgorithmIdentifiers.HMAC_SHA256);
        jws.setKey(SpringUtil.getBean(Key.class));
    }

    /**
     * @param jws jws
     * @deprecated
     */
    @Deprecated
    private static void setES256(JsonWebSignature jws) {
        KeyPair keyPair = generateKeyPair();
        jws.setAlgorithmHeaderValue(AlgorithmIdentifiers.ECDSA_USING_P256_CURVE_AND_SHA256);
        jws.setKey(keyPair.getPrivate());
    }

    private static KeyPair generateKeyPair() {
        KeyPairGenerator keyPairGenerator;
        try {
            keyPairGenerator = KeyPairGenerator.getInstance("EC");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        keyPairGenerator.initialize(256); // 使用256位曲线
        return keyPairGenerator.generateKeyPair();
    }

}
