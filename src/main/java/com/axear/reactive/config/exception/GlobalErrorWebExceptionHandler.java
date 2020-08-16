package com.axear.reactive.config.exception;

import com.axear.reactive.model.ExceptionWrapper;
import com.axear.reactive.util.Exceptions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.autoconfigure.web.ResourceProperties;
import org.springframework.boot.autoconfigure.web.reactive.error.AbstractErrorWebExceptionHandler;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.*;
import reactor.core.publisher.Mono;

import java.util.Map;

@Configuration
@Order(Ordered.HIGHEST_PRECEDENCE)
public class GlobalErrorWebExceptionHandler extends AbstractErrorWebExceptionHandler {

    Logger logger = LogManager.getLogger(GlobalErrorWebExceptionHandler.class);

    public GlobalErrorWebExceptionHandler(GlobalErrorAttributes errorAttributes, ApplicationContext applicationContext,
                                          ServerCodecConfigurer serverCodecConfigurer) {
        super(errorAttributes, new ResourceProperties(), applicationContext);
        super.setMessageWriters(serverCodecConfigurer.getWriters());
        super.setMessageReaders(serverCodecConfigurer.getReaders());
    }

    @Override
    protected RouterFunction<ServerResponse> getRoutingFunction(ErrorAttributes errorAttributes) {
        return RouterFunctions.route(RequestPredicates.all(), this::renderErrorResponse);
    }

    private Mono<ServerResponse> renderErrorResponse(ServerRequest serverRequest) {
        final Map<String, Object> errorPropertiesMap = getErrorAttributes(serverRequest, false);
        final Throwable throwable = (Throwable) errorPropertiesMap.get(Exceptions.KEY_TO_PUT_VALUE);
        ExceptionWrapper wrappedException = Exceptions.createExceptionWrapper(throwable, logger);
        return ServerResponse.status(wrappedException.getStatus())
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(wrappedException));
    }
}
