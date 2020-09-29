package org.thraex.security.handler;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Charsets;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.thraex.base.result.Result;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.function.BiConsumer;

/**
 * @author 鬼王
 * @date 2020/09/29 11:43
 */
public class JSONWriter {

    public static <R extends Result> BiConsumer<HttpStatus, R> write(HttpServletResponse response) {
        return (s, r) -> {
            try {
                write(response, s, r);
            } catch (IOException e) {
                e.printStackTrace();
            }
        };
    }

    public static <R extends Result> void write(HttpServletResponse response, HttpStatus status, R r) throws IOException {
        write(response, status.value(), r);
    }

    public static <R extends Result> void write(HttpServletResponse response, int status, R r) throws IOException {
        response.setStatus(status);
        response.setCharacterEncoding(Charsets.UTF_8.displayName());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().write(JSON.toJSONString(r));
    }

}
