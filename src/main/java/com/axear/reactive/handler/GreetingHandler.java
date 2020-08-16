package com.axear.reactive.handler;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.io.Serializable;

@Component
public class GreetingHandler {

    public Mono<ServerResponse> hello(ServerRequest request) {
        throw new RuntimeException("test");
/*        String greetingsTo = request.queryParam("name").orElse("world");
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(BodyInserters.fromValue(new Wrapper(greetingsTo)));*/
    }

    private static class Wrapper implements Serializable {
        private final String string;

        public Wrapper(String string) {
            this.string = string;
        }

        public String getString() {
            return string;
        }
    }
}
