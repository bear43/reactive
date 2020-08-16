package com.axear.reactive.config.exception;

import com.axear.reactive.util.Exceptions;
import org.springframework.boot.web.reactive.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;

import java.util.Map;

@Component
public class GlobalErrorAttributes extends DefaultErrorAttributes {

    @Override
    public Map<String, Object> getErrorAttributes(ServerRequest request, boolean includeStackTrace) {
        Map<String, Object> map = super.getErrorAttributes(request, includeStackTrace);

        Throwable ex = getError(request);
        map.put(Exceptions.KEY_TO_PUT_VALUE, ex);
        return map;
    }
}
