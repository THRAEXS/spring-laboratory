package org.thraex.security.handler;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.thraex.base.properties.SiteProperties;
import org.thraex.base.result.Result;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 鬼王
 * @date 2020/09/28 11:20
 */
@Log4j2
@Component
public class AuthenticationSuccess extends SavedRequestAwareAuthenticationSuccessHandler {

    @Autowired
    private SiteProperties sitProperties;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws ServletException, IOException {
        log.debug("Authentication success");

        // TODO: Optimize
        Map<String, Object> result = new HashMap<>(1);
        result.put("redirect", sitProperties.redirect());
        JSONWriter.write(response, HttpStatus.OK, Result.ok(result));
    }

}
