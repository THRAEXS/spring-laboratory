package org.thraex.security.filter;

import com.alibaba.fastjson.JSON;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.thraex.security.properties.CryptoProperties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @author 鬼王
 * @date 2020/09/27 11:28
 */
@Log4j2
public class CryptoAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    @Autowired
    private CryptoProperties crypto;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        Map<String, String> param = obtainParam(request);
        String username = Optional.ofNullable(param).map(p -> p.get(this.getUsernameParameter())).map(String::trim).orElse("");
        String password = Optional.ofNullable(param).map(p -> p.get(this.getPasswordParameter())).orElse("");

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);

        setDetails(request, token);

        return this.getAuthenticationManager().authenticate(token);
    }

    protected Map<String, String> obtainParam(HttpServletRequest request) {
        return Optional.ofNullable(request.getParameter(crypto.getParameterName()))
                .map(p -> crypto.decrypt(p))
                .map(p -> JSON.parseObject(p, Map.class))
                .map(p -> (Map<String, String>) p)
                .map(p -> {
                    Map<String, String> map = new HashMap<>(p.size());

                    p.entrySet().parallelStream()
                            .peek(it -> log.debug("Encrypted: [{}: {}]", it.getKey(), it.getValue()))
                            .forEach(it -> {
                                String key = crypto.decrypt(it.getKey());
                                String value = crypto.decrypt(it.getValue());
                                log.debug("Decrypted: [{}: {}]", key, value);
                                map.put(key, value);
                            });

                    return map;
                }).orElse(null);
    }

}
