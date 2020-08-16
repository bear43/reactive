package com.axear.reactive.router.hello;

import com.axear.reactive.handler.GreetingHandler;
import com.axear.reactive.router.IRouter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.RequestPredicate;
import org.springframework.web.reactive.function.server.RequestPredicates;

@Component
public class HelloRouter implements IRouter {

    private final RequestPredicate requestPredicate;

    private final HandlerFunction handlerFunction;

    @Autowired
    public HelloRouter(GreetingHandler greetingHandler) {
        this.requestPredicate = RequestPredicates
                .GET("/hello")
                .and(RequestPredicates.accept(MediaType.APPLICATION_JSON));
        this.handlerFunction = greetingHandler::hello;
    }

    @Override
    public RequestPredicate getRequestPredicate() {
        return requestPredicate;
    }

    @Override
    public HandlerFunction getHandlerFunction() {
        return handlerFunction;
    }
}
