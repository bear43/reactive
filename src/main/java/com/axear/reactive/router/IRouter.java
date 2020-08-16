package com.axear.reactive.router;

import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.RequestPredicate;

public interface IRouter {
    RequestPredicate getRequestPredicate();
    HandlerFunction getHandlerFunction();
}
