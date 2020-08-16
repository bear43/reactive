package com.axear.reactive.router;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.*;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@SuppressWarnings("unchecked")
@Configuration
public class IndexRouter {

    private final List<IRouter> routers;

    public IndexRouter(List<IRouter> routers) {
        this.routers = routers;
    }


    @Bean
    public RouterFunction<ServerResponse> route() {
        RouterFunction<ServerResponse>[] result = new RouterFunction[1];
        routers.forEach(router -> {
            if(result[0] == null) {
                result[0] = (RouterFunctions.route(router.getRequestPredicate(), router.getHandlerFunction()));
            } else {
                result[0].andRoute(router.getRequestPredicate(), router.getHandlerFunction());
            }
        });
        return result[0];
    }
}
