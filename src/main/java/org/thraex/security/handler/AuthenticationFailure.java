package org.thraex.security.handler;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Charsets;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.thraex.base.result.Result;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 鬼王
 * @date 2020/09/28 11:21
 */
@Log4j2
@Component
public class AuthenticationFailure implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {
        String message = exception.getMessage();
        log.debug("Authentication failed: [{}]", message);

        int status = HttpStatus.UNAUTHORIZED.value();
        response.setStatus(status);
        response.setCharacterEncoding(Charsets.UTF_8.displayName());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().write(JSON.toJSONString(Result.fail(message, status)));
    }

}
